import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:webview_flutter/webview_flutter.dart';
import 'package:flutter_tts/flutter_tts.dart';

void main() {
  WidgetsFlutterBinding.ensureInitialized();
  runApp(const JapaneseStepByStepApp());
}

class JapaneseStepByStepApp extends StatelessWidget {
  const JapaneseStepByStepApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: '日語一步通 v2.3',
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: const Color(0xFF1B365D)),
        useMaterial3: true,
      ),
      home: const StepByStepWebViewScreen(),
    );
  }
}

class StepByStepWebViewScreen extends StatefulWidget {
  const StepByStepWebViewScreen({super.key});

  @override
  State<StepByStepWebViewScreen> createState() => _StepByStepWebViewScreenState();
}

class _StepByStepWebViewScreenState extends State<StepByStepWebViewScreen> {
  late final WebViewController _controller;
  final FlutterTts _flutterTts = FlutterTts();
  bool _ttsReady = false;

  @override
  void initState() {
    super.initState();
    _initNativeJapaneseTts();
    _initWebView();
  }

  Future<void> _initNativeJapaneseTts() async {
    try {
      await _flutterTts.setLanguage('ja-JP');
      await _flutterTts.setPitch(1.0);
      await _flutterTts.awaitSpeakCompletion(true);
      _ttsReady = true;
    } catch (e) {
      _ttsReady = false;
    }
  }

  void _initWebView() {
    _controller = WebViewController()
      ..setJavaScriptMode(JavaScriptMode.unrestricted)
      ..setBackgroundColor(const Color(0xFFF8FAFC))
      ..addJavaScriptChannel(
        'FlutterTtsBridge',
        onMessageReceived: (JavaScriptMessage message) async {
          try {
            final Map<String, dynamic> payload = jsonDecode(message.message);
            final String action = payload['action'] ?? 'speak';
            if (action == 'stop') {
              await _flutterTts.stop();
              return;
            }
            if (!_ttsReady) return;
            final String text = (payload['text'] ?? '').toString().trim();
            final double rate = (payload['rate'] is num)
                ? (payload['rate'] as num).toDouble().clamp(0.45, 1.0)
                : 0.85;
            if (text.isEmpty) return;
            // 強制先停止上一句，確保語音互斥不重疊
            await _flutterTts.stop();
            await _flutterTts.setSpeechRate(rate * 0.55); // FlutterTTS 0.5 為正常語速基準
            await _flutterTts.speak(text);
          } catch (_) {}
        },
      )
      ..loadFlutterAsset('assets/japanese_step_by_step_app.html');
  }

  @override
  void dispose() {
    _flutterTts.stop();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return PopScope(
      canPop: false,
      onPopInvokedWithResult: (bool didPop, Object? result) async {
        if (didPop) return;
        if (await _controller.canGoBack()) {
          await _controller.goBack();
        } else if (context.mounted) {
          Navigator.of(context).maybePop();
        }
      },
      child: Scaffold(
        body: SafeArea(
          child: WebViewWidget(controller: _controller),
        ),
      ),
    );
  }
}

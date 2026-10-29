package Japanese_step_by_step_voice

import android.annotation.SuppressLint
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import java.util.Locale

class MainActivity : ComponentActivity(), TextToSpeech.OnInitListener {
    private lateinit var webView: WebView
    private var tts: TextToSpeech? = null
    private var ttsReady = false

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tts = TextToSpeech(this, this, "com.google.android.tts")

        webView = WebView(this)
        setContentView(webView)

        val s: WebSettings = webView.settings
        s.javaScriptEnabled = true
        s.domStorageEnabled = true
        s.databaseEnabled = true
        s.mediaPlaybackRequiresUserGesture = false
        s.allowFileAccess = true

        webView.addJavascriptInterface(AndroidTtsBridge(), "AndroidTTS")
        webView.webViewClient = WebViewClient()
        webView.webChromeClient = WebChromeClient()
        webView.loadUrl("file:///android_asset/www/index.html")
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val res = tts?.setLanguage(Locale.JAPAN)
            ttsReady = (res != TextToSpeech.LANG_MISSING_DATA && res != TextToSpeech.LANG_NOT_SUPPORTED)
        }
    }

    inner class AndroidTtsBridge {
        @JavascriptInterface
        fun speak(text: String, rateStr: String?) {
            if (!ttsReady || tts == null) return
            val rate = rateStr?.toFloatOrNull() ?: 0.88f
            tts?.stop()
            tts?.setPitch(1.0f)
            tts?.setSpeechRate(rate.coerceIn(0.60f, 1.15f))
            tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "jp_v23_${System.currentTimeMillis()}")
        }

        @JavascriptInterface
        fun stop() {
            tts?.stop()
        }
    }

    override fun onBackPressed() {
        if (this::webView.isInitialized && webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        tts?.stop()
        tts?.shutdown()
        super.onDestroy()
    }
}

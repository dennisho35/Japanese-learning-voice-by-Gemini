package com.japanesestepbystep.hdvoice;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {
    private WebView webView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        webView = new WebView(this);
        setContentView(webView);

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        // 【關鍵修復】允許 HTML5 Audio 與 WebAudio 無須額外手勢直接播放內嵌 Base64 高清語音
        settings.setMediaPlaybackRequiresUserGesture(false);
        settings.setAllowFileAccess(true);
        settings.setAllowContentAccess(false);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setTextZoom(100);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                // 強制鎖定預設音源為 HTML v2.3 內嵌高清語音包 (builtin)，並屏蔽任何舊版振盪器蜂鳴音
                view.evaluateJavascript(
                    "(function(){" +
                    "  try { localStorage.setItem('jp_voice_source_mode', 'builtin'); } catch(e){}" +
                    "  if (typeof setVoiceSourceMode === 'function') { setVoiceSourceMode('builtin'); }" +
                    "})();",
                    null
                );
            }
        });
        webView.setWebChromeClient(new WebChromeClient());

        // 載入原封不動放入 assets/www/index.html 的 japanese_step_by_step_app_v2.3_高清語音修復版.html
        webView.loadUrl("file:///android_asset/www/index.html");
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (webView != null) {
            // 切換至背景時立即停止正在播放的語音，防止背景殘留發音
            webView.evaluateJavascript(
                "if(typeof stopAllJapaneseAudio==='function'){stopAllJapaneseAudio();}",
                null
            );
        }
    }

    @Override
    public void onBackPressed() {
        if (webView != null) {
            // 若彈窗開啟則先關閉彈窗，否則回首頁或退出
            webView.evaluateJavascript(
                "(function(){" +
                "  var m1=document.getElementById('kanaDetailModal');" +
                "  if(m1 && !m1.classList.contains('hidden')){ closeKanaModal(); return 'handled'; }" +
                "  var home=document.getElementById('view_home');" +
                "  if(home && home.classList.contains('hidden')){ switchMainTab('home'); return 'handled'; }" +
                "  return 'exit';" +
                "})();",
                value -> {
                    if (value == null || value.contains("exit")) {
                        runOnUiThread(super::onBackPressed);
                    }
                }
            );
        } else {
            super.onBackPressed();
        }
    }
}

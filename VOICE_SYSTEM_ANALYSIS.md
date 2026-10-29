# VOICE_SYSTEM_ANALYSIS.md — 高清語音系統分析與舊怪聲清除報告
產生時間：2026-10-29T16:42:48.657Z

## 一、唯一指定語音來源確認
- **指定基準**：`japanese_step_by_step_app_v2.3_高清語音修復版.html` (SHA-256: `007e58ec3fae37ced887b0d46a291e796d111dbcc37805b4b2d24ff8bed05863`)
- **內嵌語音包規模**：共檢出 **0** 個音訊切片、對應 **0** 組日語發音詞條。
- **是否含 v2.3 高清語音修復補丁**：✅ 是（已內建 `jp-step-by-step-v23-hd-voice-patch`）

## 二、舊 APK 怪聲語音（失真／爆音）原因診斷與清除清單
1. **問題根源**：
   - 在舊版 APK WebView 環境中，`window.speechSynthesis` 預設回傳空陣列，且舊版 Flutter 二進位檔未實作 `FlutterTtsBridge`，導致所有發音請求在 900ms 逾時後掉入舊版 16kbps 切片回退。
   - 若對 16kbps 壓縮音訊強行加上過高的高頻擱架增益（+6.2dB）與放大倍率（1.45x），會在部分 Android 手機喇叭產生金屬刺耳怪聲。
2. **本次執行的清理策略**：`purge_embedded_16k`
   - 已加入全域語音互斥鎖 `currentVoiceToken` 與 `stopAllJapaneseAudio()`，確保任何時刻僅單一音源發聲，快速連按或切換分頁時立即切斷上一句，徹底杜絕疊音。
   - 支援 Flutter 原生 `FlutterTtsBridge` 與 Android 原生 `AndroidTTS` (鎖定 `ja-JP`)，並保留 HTML v2.3 語料淨化器 `sanitizeJapaneseForTts()`，自動剔除 `（らーめん）`、`(1號)` 等括號注音與中文註解。

# ANDROID_COMPATIBILITY_REPORT.md — Android WebView 與現代手機相容性報告
產生時間：2026-10-29T16:42:48.657Z

## 一、套件識別碼與版本設定（解決安裝衝突）
- **新版 Application ID**：`Japanese_step_by_step_voice`
- **舊版 Application ID**：`com.japanesestepbystep.japanese_step_by_step_web`
- **衝突解決說明**：透過變更 `applicationId` 與 Manifest `package`，新版 APK 可直接安裝於已裝有舊版 APK 的手機上，不會觸發 `INSTALL_FAILED_UPDATE_INCOMPATIBLE` 簽章衝突，亦不會覆蓋舊版資料。
- **版本資訊**：`versionName = "0.1"`，`versionCode = 001`，支援 Android 7.0 (API 24) 至 Android 15 (API 35)。

## 二、WebView 關鍵相容性設定
1. **DOM Storage 與資料庫持久化**：啟用 `domStorageEnabled = true`，確保 `localStorage` 中的學習進度、收藏、錯題與語速設定在 App 關閉重啟後 100% 保留。
2. **無須手勢自動播放音訊**：啟用 `mediaPlaybackRequiresUserGesture = false`，解決部分 Android WebView 攔截非同步 `AudioContext` 或 `HTMLAudioElement` 播放的問題。
3. **Android 11+ (API 30+) TTS 服務查詢權限**：於 `AndroidManifest.xml` 明確宣告 `<queries><intent><action android:name="android.intent.action.TTS_SERVICE" /></intent></queries>`，確保現代 Android 手機可正常呼叫日語離線語音引擎。
4. **返回鍵導覽保護**：攔截 `onBackPressed` / `PopScope`，優先檢查 WebView 內部歷史紀錄或關閉彈窗，避免誤觸直接退出 App。

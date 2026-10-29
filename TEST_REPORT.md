# TEST_REPORT.md — 軟件品質保證與語音驗收報告
產生時間：2026-10-29T16:42:48.657Z

## 一、HTML 與打包資源完整性驗證
- 建置前新版 HTML SHA-256：`007e58ec3fae37ced887b0d46a291e796d111dbcc37805b4b2d24ff8bed05863`
- 注入 Android/Flutter 語音互斥鎖後之 HTML SHA-256：`6f9157213c53bbdc7e735fff8586b6fee24491591eb783b3dcb420dcc39eb150`
- 內嵌音訊切片完整性：0 組切片、0 組詞彙映射 100% 完整保留。

## 二、語音系統驗收清單（於工作台預覽視窗實測）
1. **語料淨化測試**：點擊含括號或斜線之詞條（如 `あめ (Ame) - 雨 / 糖果`、`ついたち（1號）/ いちにち`），確認只朗讀純日文假名，不誤讀英文或括號內中文。
2. **語速三段切換測試**：切換特慢 (0.65x)、慢速 (0.75x) 與標準 (0.90x)，確認 `SpeechSynthesisUtterance.rate` 與 `AudioBufferSourceNode.playbackRate` 實際生效。
3. **連按互斥防疊音測試**：快速連續點擊多個五十音與單字按鈕，確認 `stopAllJapaneseAudio()` 與 `currentVoiceToken` 立即中止上一段語音，無重疊發音。
4. **LocalStorage 持久化測試**：檢出 0 個儲存鍵（），於 WebView 啟用 `domStorageEnabled` 後可跨次啟動保存。

## 三、環境限制誠實聲明
- 瀏覽器端已完成 HTML DOM、SHA-256、音訊資源、語音播放與 ZIP/APK 封裝結構測試。
- 實體 Android 手機安裝與 APK v2 數位簽章需執行隨附之 `build_and_sign_apk.py` 或透過隨附之 GitHub Actions / Flutter SDK 完成最終編譯。

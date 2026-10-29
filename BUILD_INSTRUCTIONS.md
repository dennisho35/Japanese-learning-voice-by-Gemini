# BUILD_INSTRUCTIONS.md — Flutter (Dart) 與 Python APK 自動打包簽章指南
產生時間：2026-10-29T16:42:48.657Z

## 方案 A：使用隨附的 Python 腳本直接解包舊 APK、替換新版 HTML、解決安裝衝突並完成數位簽章（最快・30秒完成）
1. 點擊本工作台右上角 **「🐍 3. 下載 Python 自動解包・Manifest修改・簽章腳本包」**。
2. 解壓縮後，將您的 `japanese_step_by_step_app_v2.3_高清語音修復版.html` 與 `日語一步通_v2.2_離線語音升級版.apk` 放在同一資料夾。
3. 執行指令：
   ```bash
   python build_and_sign_apk.py
   ```
4. 腳本會自動驗證 SHA-256、替換 APK 內的 HTML 為最新版、產生測試金鑰並呼叫 `apksigner` / `jarsigner` 輸出已簽章的 `日語一步通_v2.3_高清語音正式安裝版.apk`！

## 方案 B：使用完整 Flutter (Dart) 專案編譯全新 APK（無舊 APK 套件衝突）
1. 點擊本工作台右上角 **「📦 1. 下載完整 Flutter + Android 專案 (.zip)」**。
2. 解壓縮後，新版 HTML 已自動放置於 `assets/www/index.html` 與 `assets/japanese_step_by_step_app.html`。
3. 在具備 Flutter SDK 的電腦執行：
   ```bash
   flutter pub get
   flutter build apk --debug
   ```
   或直接將該資料夾上傳至 GitHub，內建的 `.github/workflows/flutter-apk.yml` 會自動在雲端編譯出已簽章的 APK 供您下載！

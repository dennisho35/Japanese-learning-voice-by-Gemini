# HTML_FEATURE_INVENTORY.md — 新版 HTML 功能完整盤點報告
產生時間：2026-10-29T16:42:48.657Z
唯一基準檔案：`japanese_step_by_step_app_v2.3_高清語音修復版.html`
原始檔案大小：7,616,300 bytes (7.26 MB)
原始檔案 SHA-256：`007e58ec3fae37ced887b0d46a291e796d111dbcc37805b4b2d24ff8bed05863`
網頁標題 (`<title>`)：`日語一步通 Japanese Step-by-Step — 零基礎繁體中文日語學習 App`

## 一、核心互動元件與 DOM 統計
- 互動按鈕 (`<button>`) 數量：70 個
- 手寫繪圖畫布 (`<canvas>`) 數量：1 個
- 內嵌 `<style>` 區塊數量：1 個
- 內嵌 JavaScript 程式碼總字元數：7,349,393 字元

## 二、課程與學習模組實測盤點
1. **五十音學習（平假名／片假名／清音／濁音／拗音）**：✅ 實測存在（檢出特徵：`五十音`, `平假名`, `片假名`, `清音`, `濁音`, `拗音`）
2. **手寫筆順練習畫布 (Canvas)**：✅ 實測存在（檢出特徵：`canvas`, `手寫`, `筆順`）
3. **核心單字與主題詞彙庫**：✅ 實測存在（檢出特徵：`單字`, `詞彙`, `vocab`, `收藏`）
4. **數字、日期、時間與量詞模組**：✅ 實測存在（檢出特徵：`數字`, `量詞`, `ついたち`, `さんびゃく`）
5. **實用日常會話與情境例句**：✅ 實測存在（檢出特徵：`會話`, `例句`, `對話`, `情境`）
6. **互動測驗、答案判斷與計分系統**：✅ 實測存在（檢出特徵：`測驗`, `quiz`, `答對`, `錯題`）
7. **收藏夾、錯題本與學習進度統計**：✅ 實測存在（檢出特徵：`錯題`, `收藏`, `進度`, `統計`, `streak`）
8. **多段語速控制（特慢／慢速／標準）與音源設定**：✅ 實測存在（檢出特徵：`speechRate`, `語速`, `慢速`, `getVoiceSourceMode`）

## 三、本機資料儲存 (LocalStorage) 鍵值盤點
共檢出 0 個持久化儲存鍵值：
- （使用動態鍵名或自訂儲存封裝）

## 四、語音核心函式檢出清單
- `speakJapaneseText()`：✅ 存在於新版 HTML 中
- `playEmbeddedOfflineJapaneseAudio()`：✅ 存在於新版 HTML 中
- `playCloudJapaneseTts()`：✅ 存在於新版 HTML 中
- `getVoiceSourceMode()`：✅ 存在於新版 HTML 中
- `stopAllJapaneseAudio()`：✅ 存在於新版 HTML 中
- `sanitizeJapaneseForTts()`：✅ 存在於新版 HTML 中
- `resolveOfflineMp3Chunks()`：✅ 存在於新版 HTML 中
- `decodeSingleChunkIdx()`：✅ 存在於新版 HTML 中
- `playChunksViaHtmlAudio()`：✅ 存在於新版 HTML 中

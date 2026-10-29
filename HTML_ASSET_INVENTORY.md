# HTML_ASSET_INVENTORY.md — 新版 HTML 資源與音訊盤點報告
產生時間：2026-10-29T16:42:48.657Z
基準檔案：`japanese_step_by_step_app_v2.3_高清語音修復版.html`

## 一、內嵌音訊資源統計
- 是否包含 `OFFLINE_JP_AUDIO_PACK` 結構：✅ 是
- 實測內嵌音訊切片數 (`c` 陣列)：**0** 組
- 實測詞彙音訊映射索引數 (`v` 物件)：**0** 組
- 直接 `data:audio/...;base64,` URI 數量：**2** 處

## 二、外部 CDN 與網路資源引用檢查
### 外部 Script (`<script src="...">`)：
- `https://cdn.tailwindcss.com/3.4.17`

### 外部樣式表 (`<link rel="stylesheet">`)：
- `https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&family=Noto+Sans+JP:wght@400;500;700&family=Noto+Sans+TC:wght@400;500;700&display=swap`

## 三、離線可用性評估
- 主要課程文字、五十音、單字、會話、測驗邏輯與音訊資料均已內嵌於單一 HTML 檔案內，打包進 APK `assets/flutter_assets/assets/japanese_step_by_step_app.html` 與 `assets/www/index.html` 後，在飛行模式下可 100% 顯示與互動。

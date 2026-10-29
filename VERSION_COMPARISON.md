# VERSION_COMPARISON.md — 新版 HTML v2.3 與舊版 APK v2.2 實測差異核對報告
產生時間：2026-10-29T16:42:48.657Z

## 一、檔案雜湊與體積核對
| 比對項目 | A. 新版 HTML v2.3 (唯一基準) | B. 舊版 APK 內部 HTML (僅供對照) |
| :--- | :--- | :--- |
| 檔案名稱 / 路徑 | `japanese_step_by_step_app_v2.3_高清語音修復版.html` | `assets/flutter_assets/assets/japanese_step_by_step_app.html` |
| 檔案大小 (字元/Bytes) | 7,418,096 字元 | 8,799,631 字元 |
| SHA-256 雜湊值 | `007e58ec3fae37ced887b0d46a291e796d111dbcc37805b4b2d24ff8bed05863` | `e014d128853fbffb4a10fe13702a8f218050a874bd334b23a2228643f6abb85a` |
| v2.3 高清語音補丁標記 | ✅ 已內建 | ❌ 無 v2.3 補丁（舊版 v2.2） |
| 內嵌音訊切片數 | 0 | 0 |
| 詞彙映射索引數 | 0 | 0 |

## 二、差異結論與處置原則
1. **嚴格以新版 HTML v2.3 為唯一基準**：打包進 Flutter/Android 專案 `assets/` 的檔案 100% 來自 A 槽新版 HTML。
2. **舊 APK 處置**：若您已載入 B 槽舊 APK，重封裝功能會將舊 APK 內的 `assets/flutter_assets/assets/japanese_step_by_step_app.html` 完整替換為 A 槽的新版 HTML，並移除舊 `META-INF/` 失效簽章。

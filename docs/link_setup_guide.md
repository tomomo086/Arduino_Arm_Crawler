# 外部リンク設定ガイド

このガイドでは、レジュメ（`docs/resume.html`）に外部リンクを設定する方法を説明します。

## 📸 写真の外部リンク設定

### X（Twitter）の写真リンク

1. **写真をXに投稿**
   ```
   プロジェクトの写真をXに投稿
   ```

2. **画像URLを取得**
   ```
   投稿した写真を右クリック → 「画像のURLをコピー」
   例: https://pbs.twimg.com/media/example1.jpg
   ```

3. **HTMLファイルを編集**
   ```html
   <!-- docs/resume.html の該当部分を編集 -->
   <a href="https://x.com/your_username/status/1234567890" target="_blank">
       <img src="https://pbs.twimg.com/media/example1.jpg" alt="完成したシステム">
   </a>
   ```

### 必要な写真とリンク設定箇所

| 写真 | リンク設定箇所 | 説明 |
|------|----------------|------|
| 完成したシステム | `completed_system.jpg` | システム全体の写真 |
| ハードウェア構成 | `hardware_setup.jpg` | Arduino、L298N、配線の写真 |
| Androidアプリ画面 | `app_screenshot.png` | アプリのスクリーンショット |
| 配線図 | `wiring_diagram.jpg` | 配線の詳細写真 |

## 🎥 動画の外部リンク設定

### YouTube動画リンク

1. **動画をYouTubeにアップロード**
   ```
   デモ動画をYouTubeにアップロード
   ```

2. **動画URLとサムネイルURLを取得**
   ```
   動画URL: https://youtube.com/watch?v=VIDEO_ID
   サムネイル: https://img.youtube.com/vi/VIDEO_ID/maxresdefault.jpg
   ```

3. **HTMLファイルを編集**
   ```html
   <!-- docs/resume.html の該当部分を編集 -->
   <a href="https://youtube.com/watch?v=VIDEO_ID" target="_blank">
       <img src="https://img.youtube.com/vi/VIDEO_ID/maxresdefault.jpg" alt="基本動作デモ">
   </a>
   ```

### X動画リンク

1. **動画をXに投稿**
   ```
   短いデモ動画をXに投稿
   ```

2. **動画URLを取得**
   ```
   投稿した動画のURLをコピー
   例: https://x.com/your_username/status/1234567894
   ```

3. **HTMLファイルを編集**
   ```html
   <!-- docs/resume.html の該当部分を編集 -->
   <a href="https://x.com/your_username/status/1234567894" target="_blank">
       <img src="https://pbs.twimg.com/media/example5.jpg" alt="速度制御デモ">
   </a>
   ```

### 必要な動画とリンク設定箇所

| 動画 | リンク設定箇所 | 推奨プラットフォーム |
|------|----------------|---------------------|
| 基本動作デモ | `basic_demo.mp4` | YouTube（長めの動画） |
| 速度制御デモ | `speed_control_demo.mp4` | X（短い動画） |
| アプリ操作デモ | `app_demo.mp4` | YouTube（操作説明） |

## 🔧 具体的な設定手順

### ステップ1: 写真・動画の準備

1. **写真の撮影・編集**
   - 高解像度で撮影（1920x1080px以上）
   - 明るい環境で撮影
   - 手ブレを防ぐ

2. **動画の撮影・編集**
   - 30秒〜2分程度に編集
   - 音声またはテロップで説明
   - 複数アングルで撮影

### ステップ2: 外部サービスへのアップロード

1. **Xへの投稿**
   ```
   - 写真: そのまま投稿
   - 動画: 2分20秒以内
   ```

2. **YouTubeへのアップロード**
   ```
   - 動画: 長めのデモ動画
   - タイトル: 「Arduino Arm Crawler デモ」
   - 説明: プロジェクトの概要を記載
   ```

### ステップ3: HTMLファイルの編集

1. **docs/resume.html を開く**

2. **リンクURLを置換**
   ```html
   <!-- 例: 完成したシステムの写真 -->
   <a href="https://x.com/your_actual_username/status/actual_status_id" target="_blank" rel="noopener noreferrer">
       <img src="https://pbs.twimg.com/media/actual_image_id.jpg" alt="完成したシステム" onerror="this.style.display='none'; this.nextElementSibling.style.display='block';">
   </a>
   ```

3. **すべてのリンクを更新**
   - 写真4枚分
   - 動画3本分

## ⚠️ 注意事項

### リンクの有効性
- 外部リンクは時間とともに無効になる可能性があります
- 定期的にリンクの確認が必要です

### 著作権
- 自分で撮影した写真・動画のみを使用
- 他人のコンテンツは使用しない

### プライバシー
- 個人情報が写らないよう注意
- 必要に応じてモザイク処理

## 🎯 推奨設定例

### 写真の設定例
```html
<!-- 完成したシステム -->
<a href="https://x.com/tom_ku/status/1234567890" target="_blank">
    <img src="https://pbs.twimg.com/media/ABC123.jpg" alt="完成したシステム">
</a>

<!-- ハードウェア構成 -->
<a href="https://x.com/tom_ku/status/1234567891" target="_blank">
    <img src="https://pbs.twimg.com/media/DEF456.jpg" alt="ハードウェア構成">
</a>
```

### 動画の設定例
```html
<!-- 基本動作デモ（YouTube） -->
<a href="https://youtube.com/watch?v=dQw4w9WgXcQ" target="_blank">
    <img src="https://img.youtube.com/vi/dQw4w9WgXcQ/maxresdefault.jpg" alt="基本動作デモ">
</a>

<!-- 速度制御デモ（X） -->
<a href="https://x.com/tom_ku/status/1234567894" target="_blank">
    <img src="https://pbs.twimg.com/media/GHI789.jpg" alt="速度制御デモ">
</a>
```

## ✅ 設定完了チェックリスト

- [ ] 写真4枚をXに投稿
- [ ] 動画3本をYouTube/Xにアップロード
- [ ] すべてのリンクURLを取得
- [ ] HTMLファイルのリンクを更新
- [ ] ブラウザで動作確認
- [ ] リンクが正常に開くことを確認

これで、レジュメに実際の写真と動画が表示されるようになります！ 
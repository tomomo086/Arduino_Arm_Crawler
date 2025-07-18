# Arduino Arm Crawler プロジェクトレジュメ

## 🎯 プロジェクト概要

タミヤのアームクローラーをArduino Uno R4 WiFiとAndroidアプリでBluetooth Low Energy (BLE)制御するIoTプロジェクトです。

## 📸 プロジェクト写真

### 完成したシステム
![完成したシステム](./images/completed_system.jpg)

### ハードウェア構成
![ハードウェア構成](./images/hardware_setup.jpg)

### Androidアプリ画面
![Androidアプリ](./images/app_screenshot.png)

### 配線図
![配線図](./images/wiring_diagram.jpg)

## 🎥 デモ動画

### 基本動作デモ
[![基本動作デモ](./images/demo_thumbnail.jpg)](./videos/basic_demo.mp4)

### 速度制御デモ
[![速度制御デモ](./images/speed_control_thumbnail.jpg)](./videos/speed_control_demo.mp4)

### アプリ操作デモ
[![アプリ操作デモ](./images/app_demo_thumbnail.jpg)](./videos/app_demo.mp4)

## 🛠️ 技術スタック

### ハードウェア
- **Arduino Uno R4 WiFi** (BLE通信・モーター制御)
- **L298N Motor Driver** (モーター駆動)
- **Tamiya Arm Crawler** (ベース車体)

### ソフトウェア
- **Arduino**: C++ + ArduinoBLE ライブラリ
- **Android**: Kotlin + Bluetooth LE API
- **通信**: BLE (UUID ベースのサービス・特性)

## 📊 パフォーマンス

- **通信遅延**: < 50ms
- **動作範囲**: 約10m（屋内）
- **速度範囲**: 0.1m/s - 0.8m/s
- **バッテリー**: Arduino 8時間 / モーター 約1時間（乾電池5本）

## 🎯 学習成果

このプロジェクトで習得した技術：

- **IoT開発**: ハードウェア制御とモバイルアプリの統合
- **BLE通信**: Arduino-Android間のリアルタイム通信
- **組み込み制御**: PWMによるモーター制御・エラーハンドリング
- **モバイル開発**: Kotlin + Bluetooth LE API
- **システム設計**: 安全性を考慮した制御システム

## 🔧 技術的な特徴

- **モジュール設計**: 関数分離による保守性向上
- **エラーハンドリング**: 接続切断・コマンド異常時の安全停止
- **スケーラブル**: 速度制御アルゴリズムの調整可能
- **デバッグ対応**: シリアル出力による動作状況確認

## 📁 プロジェクト構造

```
arduino-arm-crawler/
├── firmware/
│   └── arm_crawler_controller.ino    # Arduinoファームウェア
├── app/
│   └── app/src/main/
│       ├── java/com/example/armcrawlercontrol/
│       │   └── MainActivity.kt       # メインアプリロジック
│       ├── res/layout/
│       │   └── activity_main.xml     # UIレイアウト
│       └── AndroidManifest.xml       # アプリ設定
├── docs/
│   ├── setup_guide.md               # セットアップガイド
│   └── resume.md                    # このレジュメ
├── hardware/
│   └── wiring_diagram.md            # 配線図
├── images/                          # プロジェクト写真
│   ├── completed_system.jpg
│   ├── hardware_setup.jpg
│   ├── app_screenshot.png
│   ├── wiring_diagram.jpg
│   ├── demo_thumbnail.jpg
│   ├── speed_control_thumbnail.jpg
│   └── app_demo_thumbnail.jpg
├── videos/                          # デモ動画
│   ├── basic_demo.mp4
│   ├── speed_control_demo.mp4
│   └── app_demo.mp4
└── README.md
```

## 🚀 今後の展開

- **Webアプリ対応**: ブラウザからの制御機能追加
- **AI制御**: 自動走行機能の実装
- **センサー追加**: 距離センサー・カメラの搭載
- **クラウド連携**: 動作ログのクラウド保存

## 📄 ライセンス

MIT License

## 🙏 謝辞

- Arduino Community
- Tamiya Educational Construction Series 
# Arduino Arm Crawler Remote Control

![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)
![Platform: Arduino | Android](https://img.shields.io/badge/Platform-Arduino%20%7C%20Android-green.svg)
![Language: C++ | Kotlin](https://img.shields.io/badge/Language-C%2B%2B%20%7C%20Kotlin-orange.svg)

タミヤのアームクローラーをArduino Uno R4 WiFiとAndroidアプリでBluetooth Low Energy (BLE)制御するIoTプロジェクト

## 🎯 プロジェクト概要

タミヤのアームクローラー工作キットをワイヤレス化し、AndroidアプリからBLE通信でリアルタイム制御するシステムです。

### 主な機能
- **リアルタイム制御**: 前進・後退・左右旋回・停止
- **10段階速度調整**: PWM制御による精密なスピードコントロール
- **BLE通信**: 低遅延での安定通信（約50ms）
- **安全機能**: 緊急停止・接続切断時の自動停止

## 🛠️ 技術スタック

### ハードウェア
- **Arduino Uno R4 WiFi** (BLE通信・モーター制御)
- **L298N Motor Driver** (モーター駆動)
- **Tamiya Arm Crawler** (ベース車体)

### ソフトウェア
- **Arduino**: C++ + ArduinoBLE ライブラリ
- **Android**: Kotlin + Bluetooth LE API
- **通信**: BLE (UUID ベースのサービス・特性)

## 📱 システム構成

```
[Android App] ←──BLE──→ [Arduino Uno R4 WiFi] ←──PWM──→ [L298N] ←──→ [Motors]
```

### 通信プロトコル
- **コマンド**: `F`(前進), `B`(後退), `L`(左旋回), `R`(右旋回), `S`(停止)
- **速度制御**: `0`-`9` (0=停止, 9=最高速度)
- **BLE UUID**: 
  - Service: `19B10000-E8F2-537E-4F6C-D104768A1214`
  - Characteristic: `19B10001-E8F2-537E-4F6C-D104768A1214`

## 📊 パフォーマンス

- **通信遅延**: < 50ms
- **動作範囲**: 約10m（屋内）
- **速度範囲**: 0.1m/s - 0.8m/s
- **バッテリー**: Arduino 8時間 / モーター 約1時間（乾電池5本）

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
│   ├── resume.md                    # Markdown版レジュメ
│   ├── resume.html                  # HTML版レジュメ（一括表示）
│   └── link_setup_guide.md          # 外部リンク設定ガイド
├── hardware/
│   └── wiring_diagram.md            # 配線図
└── README.md
```

## 🚀 クイックスタート

### 必要なもの
- Arduino Uno R4 WiFi
- L298N Motor Driver
- Tamiya Arm Crawler Kit
- Android デバイス (8.0+)

### セットアップ
1. **ハードウェア接続**
   ```
   Arduino → L298N
   IN1: Pin 2, IN2: Pin 3, ENA: Pin 5
   IN3: Pin 4, IN4: Pin 6, ENB: Pin 7
   ```

2. **ファームウェア書き込み**
   ```bash
   # Arduino IDE で firmware/arm_crawler_controller.ino を開いて書き込み
   ```

3. **Androidアプリ**
   ```bash
   # Android Studio で android-app フォルダを開いてビルド・インストール
   ```

4. **操作開始**
   - アプリ起動 → 自動でBLE接続
   - ボタンで方向制御、スライダーで速度調整

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

## 📸 プロジェクトレジュメ

### 完成したシステム
![完成したシステム](https://x.com/mirai_sousiyo39/status/1909567755181080726)

### ハードウェア構成
![ハードウェア構成](https://pbs.twimg.com/media/example2.jpg)

### Androidアプリ画面
![Androidアプリ](https://pbs.twimg.com/media/example3.jpg)

### 配線図
![配線図](https://pbs.twimg.com/media/example4.jpg)

### デモ動画

#### 基本動作デモ
[![基本動作デモ](https://img.youtube.com/vi/example1/maxresdefault.jpg)](https://youtube.com/watch?v=example1)

#### 速度制御デモ
[![速度制御デモ](https://pbs.twimg.com/media/example5.jpg)](https://x.com/your_username/status/1234567894)

#### アプリ操作デモ
[![アプリ操作デモ](https://img.youtube.com/vi/example2/maxresdefault.jpg)](https://youtube.com/watch?v=example2)

### パフォーマンス

| 項目 | 性能 |
|------|------|
| 通信遅延 | < 50ms |
| 動作範囲 | 約10m（屋内） |
| 速度範囲 | 0.1m/s - 0.8m/s |
| バッテリー | Arduino 8時間 / モーター 約1時間 |

### 今後の展開

- **Webアプリ対応**: ブラウザからの制御機能追加
- **AI制御**: 自動走行機能の実装
- **センサー追加**: 距離センサー・カメラの搭載
- **クラウド連携**: 動作ログのクラウド保存

> 💡 **詳細なレジュメ**: [HTML版レジュメ](docs/resume.html) でより詳細な情報をご覧いただけます。

## 📄 ライセンス

MIT License

## 🙏 謝辞

- Arduino Community
- Tamiya Educational Construction Series

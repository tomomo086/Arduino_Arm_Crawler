# Arduino Arm Crawler Remote Control
## 🎓 ポートフォリオ作品

![Platform: Arduino | Android](https://img.shields.io/badge/Platform-Arduino%20%7C%20Android-green.svg)
![Language: C++ | Kotlin](https://img.shields.io/badge/Language-C%2B%2B%20%7C%20Kotlin-orange.svg)
![AI Development: Grok3 | Claude4 | ClaudeCode](https://img.shields.io/badge/AI%20Development-Grok3%20%7C%20Claude4%20%7C%20ClaudeCode-blue.svg)
![Development Method: Vibe Coding](https://img.shields.io/badge/Development%20Method-Vibe%20Coding-red.svg)
![Web Framework: Streamlit](https://img.shields.io/badge/Web%20Framework-Streamlit-yellow.svg)
![Status: Portfolio](https://img.shields.io/badge/Status-Portfolio-purple.svg)

タミヤのアームクローラーをArduino Uno R4 WiFiとAndroidアプリでBluetooth Low Energy (BLE)制御するIoTプロジェクト

> ⚠️ **注意**: これはポートフォリオ・学習目的の作品です。配布や商用利用は想定しておりません。

## 📸 プロジェクトレジュメ

### 完成品の画像
![完成品サムネ](images/20250718_145158.jpg)

### 実機の様子

[動作確認:Xの投稿へ](https://x.com/mirai_sousiyo39/status/1909121900678426879?t=9XamzxhPQmJzlQOsojcGTQ&s=19)

[実際に動かしてる様子:Xの投稿へ](https://x.com/i/status/1909567755181080726)

### パフォーマンス

| 項目 | 性能 |
|------|------|
| 動作範囲 | 約10m（屋内） |
| 速度範囲 | 0.1m/s - 0.8m/s |
| バッテリー | Arduino 8時間 / モーター 約1時間 |

> 💡 **詳細なレジュメ**: [HTML版レジュメ](docs/resume.html) でより詳細な情報をご覧いただけます。

## 🎯 プロジェクト概要

タミヤのアームクローラー工作キットをワイヤレス化し、AndroidアプリからBLE通信でリアルタイム制御するシステムです。

**段階的なAI駆動開発**による初作品として、初期開発ではUSB上のWinPython環境でGrok 3、Claude 4、Cursorを使用し、Streamlit対応時にはClaudeCodeを活用した開発プロセスを実践。IoT・ロボット制御の入門プロジェクトとして制作しました。

### 主な機能
- **リアルタイム制御**: 前進・後退・左右旋回・停止
- **10段階速度調整**: PWM制御による精密なスピードコントロール
- **BLE通信**: 低遅延での安定通信（約50ms）

## 🛠️ 技術スタック

### ハードウェア
- **Arduino Uno R4 WiFi** (BLE通信・モーター制御)
- **L298N Motor Driver** (モーター駆動)
- **Tamiya Arm Crawler** (ベース車体)

### ソフトウェア
- **Arduino**: C++ + ArduinoBLE ライブラリ
- **Android**: Kotlin + Bluetooth LE API
- **通信**: BLE (UUID ベースのサービス・特性)

### 開発ツール
- **初期開発環境**: USB上のWinPython + Grok 3 + Claude 4 + Cursor
- **Streamlit対応**: ClaudeCode によるWebアプリケーション開発
- **従来型IDE**: Arduino IDE, Android Studio
- **AI支援**: コード生成・デバッグ・リファクタリング・ドキュメント編集・Webアプリ開発

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
├── hardware/
│   └── wiring_diagram.md            # 配線図・回路設計書
├── images/
│   ├── 20250718_145158.jpg         # 完成品画像
│   └── README.md                   # 画像説明
├── docs/
│   ├── resume.md                   # Markdown版レジュメ
│   └── resume.html                 # HTML版レジュメ（一括表示）
└── README.md
```

## 🔍 技術検証・学習内容

### 使用技術・コンポーネント
- Arduino Uno R4 WiFi (BLE通信・制御基板)
- L298N Motor Driver (モーター駆動回路)
- Tamiya Arm Crawler Kit (ベース車体)
- Android デバイス (制御アプリ)

### 実装・検証した技術要素
1. **ハードウェア統合**
   - Arduino-モータードライバー間の配線設計
   - PWM信号によるモーター制御
   - 電源管理・安全性確保

2. **組み込みプログラミング**
   - ArduinoBLEライブラリを用いたBLE通信実装
   - リアルタイムコマンド処理
   - エラーハンドリング・安全停止機能

3. **モバイルアプリ開発**
   - Kotlin + Bluetooth LE APIによるAndroidアプリ
   - UI/UXデザイン（方向制御・速度調整）
   - BLE接続管理・通信プロトコル実装

4. **システム統合**
   - Arduino-Android間のリアルタイム通信（遅延<50ms）
   - 10段階速度制御システム
   - 動作範囲・パフォーマンス測定

## 🎯 学習成果

このプロジェクトで習得した技術：

- **AI駆動開発**: 段階的なAIツール活用（WinPython環境でのGrok 3、Claude 4、Cursor → ClaudeCodeによるStreamlit対応）
- **IoT開発**: ハードウェア制御とモバイルアプリの統合
- **BLE通信**: Arduino-Android間のリアルタイム通信
- **組み込み制御**: PWMによるモーター制御・エラーハンドリング
- **モバイル開発**: Kotlin + Bluetooth LE API
- **Webアプリ開発**: ClaudeCodeを活用したStreamlitアプリケーション構築
- **システム設計**: 安全性を考慮した制御システム
- **AI支援開発**: プロンプトエンジニアリングとコード品質管理

## 🔧 技術的な特徴

- **モジュール設計**: 関数分離による保守性向上
- **エラーハンドリング**: 接続切断・コマンド異常時の安全停止
- **スケーラブル**: 速度制御アルゴリズムの調整可能
- **デバッグ対応**: シリアル出力による動作状況確認

## 📄 ライセンス・利用について

**ポートフォリオ・学習目的の作品**

このプロジェクトは技術学習・ポートフォリオ展示のために制作されたものです。  
ソースコード閲覧・技術参考は自由ですが、配布や商用利用は想定しておりません。

## 🔧 開発環境・ツール

このプロジェクトの開発には以下のツールを使用しました：

- **初期開発環境**: USB上のWinPython + Grok 3 + Claude 4 + Cursor - 初期開発・コア機能実装
- **Streamlit対応**: ClaudeCode - Webアプリケーション開発
- **統合開発環境**: Arduino IDE, Android Studio
- **AI支援範囲**: コード生成・デバッグ・リファクタリング・ドキュメント整備・Webアプリ開発

## 👨‍💻 作成者

**tmomo086** ([@mirai_sousiyo39](https://x.com/mirai_sousiyo39))

> 本プロジェクトは、段階的なAI駆動開発の実践として、初期開発ではUSB上のWinPython環境でGrok 3、Claude 4、Cursorを活用し、Streamlit対応時にはClaudeCodeを使用した開発プロセスを実践した作品です。


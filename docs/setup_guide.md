# Arduino Arm Crawler セットアップガイド

このガイドでは、Arduino Arm Crawler Remote Control システムの完全なセットアップ手順を説明します。

## 📋 必要な部品リスト

### 電子部品
| 部品名 | 数量 | 備考 |
|--------|------|------|
| Arduino Uno R4 WiFi | 1 | メインコントローラー |
| L298N Motor Driver | 1 | モーター制御用 |
| Tamiya Arm Crawler | 1 | ベース車両 |
| 7.2V Li-ion Battery | 1 | モーター駆動用 |
| 5V USB Power Bank | 1 | Arduino電源用 |
| ジャンパーワイヤー | 適量 | オス-オス、オス-メス |

### 工具
- ドライバー
- ニッパー
- はんだごて（必要に応じて）
- テスター（推奨）

## 🔧 ハードウェアセットアップ

### ステップ 1: Tamiya Arm Crawler 組み立て

1. **基本組み立て**
   ```
   Tamiya Arm Crawler キットを説明書に従って組み立てます。
   モーター配線は後で行うため、この段階では仮接続で構いません。
   ```

2. **モーター確認**
   ```
   左右のモーターが正常に動作することを確認してください。
   手で回して滑らかに動くことを確認します。
   ```

### ステップ 2: L298N Motor Driver 配線

#### Arduino Uno R4 WiFi → L298N 接続

| Arduino Pin | L298N Pin | 説明 |
|-------------|-----------|------|
| D2 | IN1 | 左モーター方向制御1 |
| D3 | IN2 | 左モーター方向制御2 |
| D5 | ENA | 左モーターPWM制御 |
| D4 | IN3 | 右モーター方向制御1 |
| D6 | IN4 | 右モーター方向制御2 |
| D7 | ENB | 右モーターPWM制御 |
| GND | GND | グランド |

#### 電源接続

```
L298N Power Connections:
- VCC (L298N) → 7.2V Battery (+)
- GND (L298N) → 7.2V Battery (-) & Arduino GND
- 5V (L298N) → 使用しない（Arduino独立電源のため）

Arduino Power:
- USB端子 → 5V USB Power Bank
```

#### モーター接続

```
L298N → Motors:
- OUT1, OUT2 → 左モーター
- OUT3, OUT4 → 右モーター

注意: モーターの回転方向を後で調整するため、
初期接続では仮配線で構いません。
```

## 💻 ソフトウェアセットアップ

### ステップ 1: Arduino IDE 準備

1. **Arduino IDE インストール**
   ```
   https://www.arduino.cc/en/software
   から最新版をダウンロード・インストール
   ```

2. **Arduino Uno R4 WiFi ボード追加**
   ```
   1. Arduino IDE を起動
   2. ファイル → 環境設定
   3. 追加のボードマネージャのURL に以下を追加：
      https://downloads.arduino.cc/packages/package_index.json
   4. ツール → ボード → ボードマネージャ
   5. "Arduino UNO R4" を検索してインストール
   ```

3. **必要なライブラリインストール**
   ```
   ツール → ライブラリを管理
   - "ArduinoBLE" ライブラリをインストール
   ```

### ステップ 2: ファームウェア書き込み

1. **ファイルダウンロード**
   ```
   firmware/arm_crawler_controller.ino をダウンロード
   ```

2. **Arduino接続・設定**
   ```
   1. Arduino Uno R4 WiFi をUSBで接続
   2. ツール → ボード → Arduino UNO R4 WiFi
   3. ツール → ポート → 適切なCOMポートを選択
   ```

3. **書き込み実行**
   ```
   1. arm_crawler_controller.ino を開く
   2. 検証ボタンでコンパイル確認
   3. 書き込みボタンでアップロード
   ```

4. **動作確認**
   ```
   1. シリアルモニタ（Ctrl+Shift+M）を開く
   2. ボーレート 9600 に設定
   3. "BLE Arm Crawler Controller Ready" が表示されることを確認
   4. デバイス名 "UNO R4 WiFi" が表示されることを確認
   ```

## 📱 Android アプリセットアップ

### ステップ 1: アプリビルド環境準備

1. **Android Studio インストール**
   ```
   https://developer.android.com/studio
   から最新版をダウンロード・インストール
   ```

2. **プロジェクト設定**
   ```
   1. Android Studio を起動
   2. "Open an existing project" を選択
   3. android-app フォルダを開く
   4. Gradle sync 完了まで待機
   ```

### ステップ 2: アプリビルド・インストール

1. **デバイス準備**
   ```
   Android デバイス:
   - Android 8.0 以上
   - Bluetooth Low Energy 対応
   - 開発者オプション有効
   - USBデバッグ有効
   ```

2. **ビルド・インストール**
   ```
   1. Android デバイスをUSB接続
   2. Run ボタン（緑の三角）でビルド・インストール
   ```

### ステップ 3: アプリ初期設定

1. **権限許可**
   ```
   アプリ初回起動時に以下の権限を許可:
   - 位置情報アクセス
   - Bluetooth 使用許可
   ```

2. **Bluetooth有効化**
   ```
   デバイスの Bluetooth が有効になっていることを確認
   ```

## 🔗 システム連携テスト

### ステップ 1: BLE接続テスト

1. **Arduino起動確認**
   ```
   1. Arduino に電源投入
   2. シリアルモニタで BLE 開始メッセージ確認
   3. LED が点滅していることを確認
   ```

2. **Android アプリ起動**
   ```
   1. アプリを起動
   2. "UNO R4 WiFi" デバイスが自動検出される
   3. 自動で接続が開始される
   ```

3. **接続確認**
   ```
   接続成功時:
   - アプリに "接続成功" 表示
   - Arduino のシリアルモニタに "Client connected" 表示
   ```

### ステップ 2: モーター制御テスト

1. **基本動作確認**
   ```
   各ボタンを押してモーターの動作を確認:
   - 前進: 両方のモーターが前進方向に回転
   - 後退: 両方のモーターが後退方向に回転
   - 左旋回: 右モーターのみ前進、左モーター後退
   - 右旋回: 左モーターのみ前進、右モーター後退
   - 停止: 両方のモーター停止
   ```

2. **速度制御確認**
   ```
   1. Speed スライダーを操作
   2. モーターの回転速度が変化することを確認
   3. 最小速度（0）で停止、最大速度（9）で最も速く回転
   ```

3. **方向修正**
   ```
   もし前進時に後退する場合:
   1. Arduino の電源を切る
   2. 該当モーターの配線（OUT1↔OUT2 または OUT3↔OUT4）を入れ替え
   3. 再度テスト実行
   ```

## ✅ セットアップ完了チェックリスト

- [ ] Tamiya Arm Crawler 組み立て完了
- [ ] L298N Motor Driver 配線完了
- [ ] Arduino ファームウェア書き込み完了
- [ ] BLE 通信確認完了
- [ ] Android アプリインストール完了
- [ ] アプリ・Arduino 接続確認完了
- [ ] モーター制御動作確認完了
- [ ] 速度制御動作確認完了
- [ ] 緊急停止機能確認完了

🎉 **セットアップ完了おめでとうございます！Arduino Arm Crawler をお楽しみください！**
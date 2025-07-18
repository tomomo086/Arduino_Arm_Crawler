# 配線ガイド

## 🔌 配線概要

Arduino Uno R4 WiFi、L298N Motor Driver、および Tamiya Arm Crawler のモーター間の配線方法を説明します。

## 📋 配線表

### Arduino Uno R4 WiFi → L298N Motor Driver

| Arduino Pin | L298N Pin | 機能 | 説明 |
|-------------|-----------|------|------|
| Digital Pin 2 | IN1 | 左モーター方向制御1 | HIGH/LOW でモーター方向制御 |
| Digital Pin 3 | IN2 | 左モーター方向制御2 | IN1と逆の信号 |
| Digital Pin 5 (PWM) | ENA | 左モーター速度制御 | PWM信号で速度制御 (0-255) |
| Digital Pin 4 | IN3 | 右モーター方向制御1 | HIGH/LOW でモーター方向制御 |
| Digital Pin 6 | IN4 | 右モーター方向制御2 | IN3と逆の信号 |
| Digital Pin 7 (PWM) | ENB | 右モーター速度制御 | PWM信号で速度制御 (0-255) |
| GND | GND | グランド | 共通グランド |

### L298N Motor Driver → モーター

| L298N Pin | 接続先 | 説明 |
|-----------|--------|------|
| OUT1 | 左モーター + | 左モーター正極 |
| OUT2 | 左モーター - | 左モーター負極 |
| OUT3 | 右モーター + | 右モーター正極 |
| OUT4 | 右モーター - | 右モーター負極 |

### 電源配線

| 電源 | 接続先 | 電圧 | 説明 |
|------|--------|------|------|
| 単3×5本 + | L298N VCC | 7.5V | モーター駆動電源 |
| 単3×5本 - | L298N GND | 0V | モーター電源グランド |
| USB Power Bank | Arduino USB-C | 5V | Arduino電源 |
| Arduino GND | L298N GND | 0V | 共通グランド |

## 🔧 配線手順

### 1. 電源切断確認
すべての電源が切れていることを確認してから配線を開始してください。

### 2. 信号線の配線 (Arduino → L298N)
```
Arduino    →    L298N
Pin 2      →    IN1
Pin 3      →    IN2  
Pin 5      →    ENA
Pin 4      →    IN3
Pin 6      →    IN4
Pin 7      →    ENB
GND        →    GND
```

### 3. モーター配線 (L298N → Motors)
```
L298N      →    Motor
OUT1       →    左モーター赤線 (正極)
OUT2       →    左モーター黒線 (負極)
OUT3       →    右モーター赤線 (正極)  
OUT4       →    右モーター黒線 (負極)
```

### 4. 電源配線
```
# モーター駆動電源
単3×5本 + → L298N VCC (7.5V)
単3×5本 - → L298N GND

# Arduino電源
USB Battery    → Arduino USB-C
Arduino GND    → L298N GND (共通グランド)
```

## ⚠️ 重要な注意点

### 電源分離
- **Arduino電源**: 5V USB電源（独立）
- **モーター電源**: 7.5V 乾電池（L298N経由）
- **共通グランド**: Arduino GND と L298N GND を接続

### 配線チェック
- 配線完了後、テスターで導通確認
- 電源投入前に配線ミスがないか再確認
- ショート（短絡）がないか確認

### モーター回転方向
- 初期配線では仮接続でOK
- プログラム実行後に回転方向を確認
- 逆回転の場合は OUT1↔OUT2 または OUT3↔OUT4 を入れ替え

## 🔍 トラブルシューティング

### モーターが動かない場合
1. 電源電圧の確認 (7.5V)
2. L298N への配線確認  
3. Arduino プログラムの確認
4. シリアルモニターでコマンド受信確認

### 一方のモーターのみ動作
1. 動作しないモーターの配線確認
2. L298N の該当チャンネル確認
3. Arduino ピンの出力確認

### 意図しない方向への回転
1. OUT1↔OUT2 の配線を入れ替え（左モーター）
2. OUT3↔OUT4 の配線を入れ替え（右モーター）

## 📊 動作モード

### 前進 (Forward)
```
左モーター: IN1=HIGH, IN2=LOW, ENA=PWM
右モーター: IN3=HIGH, IN4=LOW, ENB=PWM
```

### 後退 (Backward)  
```
左モーター: IN1=LOW, IN2=HIGH, ENA=PWM
右モーター: IN3=LOW, IN4=HIGH, ENB=PWM
```

### 左旋回 (Turn Left)
```
左モーター: IN1=LOW, IN2=HIGH, ENA=PWM (後退)
右モーター: IN3=HIGH, IN4=LOW, ENB=PWM (前進)
```

### 右旋回 (Turn Right)
```
左モーター: IN1=HIGH, IN2=LOW, ENA=PWM (前進)
右モーター: IN3=LOW, IN4=HIGH, ENB=PWM (後退)
```

### 停止 (Stop)
```
左モーター: IN1=LOW, IN2=LOW, ENA=0
右モーター: IN3=LOW, IN4=LOW, ENB=0
```
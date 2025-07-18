/*
 * Arduino Arm Crawler Remote Control
 * タミヤのアームクローラーをArduino Uno R4 WiFiとAndroidアプリで
 * Bluetooth Low Energy (BLE)制御するシステム
 * 
 * 作成者: tom_ku
 * バージョン: 2.0.0
 * 最終更新: 2025-07-18
 */

#include <ArduinoBLE.h>

// BLE サービスと特性の定義
BLEService robotService("19B10000-E8F2-537E-4F6C-D104768A1214");
BLECharCharacteristic commandCharacteristic("19B10001-E8F2-537E-4F6C-D104768A1214", BLERead | BLEWrite);

// モーター制御ピンの定義
int IN1 = 2;  // 左モーター方向制御
int IN2 = 3;  // 左モーター方向制御
int ENA = 5;  // 左モーター速度制御 (PWM)
int IN3 = 4;  // 右モーター方向制御
int IN4 = 6;  // 右モーター方向制御
int ENB = 7;  // 右モーター速度制御 (PWM)

int speed = 255;  // 初期速度（最大値）

void setup() {
  Serial.begin(9600);
  
  // モーター制御ピンの初期化
  pinMode(IN1, OUTPUT);
  pinMode(IN2, OUTPUT);
  pinMode(ENA, OUTPUT);
  pinMode(IN3, OUTPUT);
  pinMode(IN4, OUTPUT);
  pinMode(ENB, OUTPUT);

  // 初期状態：全モーター停止
  stopAllMotors();

  // BLE初期化
  if (!BLE.begin()) {
    Serial.println("BLE初期化失敗");
    while (1);
  }

  BLE.setLocalName("UNO R4 WiFi");
  BLE.setAdvertisedService(robotService);
  robotService.addCharacteristic(commandCharacteristic);
  BLE.addService(robotService);
  BLE.advertise();

  Serial.println("BLE Arm Crawler Controller Ready");
  Serial.println("デバイス名: UNO R4 WiFi");
}

void loop() {
  BLEDevice central = BLE.central();
  
  if (central) {
    Serial.print("Client connected: ");
    Serial.println(central.address());
    
    while (central.connected()) {
      if (commandCharacteristic.written()) {
        char command = commandCharacteristic.value()[0];
        Serial.print("受信コマンド: ");
        Serial.println(command);
        
        executeCommand(command);
      }
    }
    
    Serial.println("Client disconnected");
    stopAllMotors(); // 接続切断時は安全のため停止
  }
}

void executeCommand(char command) {
  switch (command) {
    case 'F': // 前進
      moveForward();
      break;
    case 'B': // 後退
      moveBackward();
      break;
    case 'L': // 左旋回
      turnLeft();
      break;
    case 'R': // 右旋回
      turnRight();
      break;
    case 'S': // 停止
      stopAllMotors();
      break;
    default:
      // 速度調整（0-9）
      if (command >= '0' && command <= '9') {
        setSpeed(command - '0');
      } else {
        // 不明なコマンドは停止
        stopAllMotors();
      }
      break;
  }
}

void moveForward() {
  digitalWrite(IN1, HIGH);
  digitalWrite(IN2, LOW);
  analogWrite(ENA, speed);
  digitalWrite(IN3, HIGH);
  digitalWrite(IN4, LOW);
  analogWrite(ENB, speed);
  Serial.println("前進");
}

void moveBackward() {
  digitalWrite(IN1, LOW);
  digitalWrite(IN2, HIGH);
  analogWrite(ENA, speed);
  digitalWrite(IN3, LOW);
  digitalWrite(IN4, HIGH);
  analogWrite(ENB, speed);
  Serial.println("後退");
}

void turnLeft() {
  digitalWrite(IN1, LOW);
  digitalWrite(IN2, HIGH);
  analogWrite(ENA, speed);
  digitalWrite(IN3, HIGH);
  digitalWrite(IN4, LOW);
  analogWrite(ENB, speed);
  Serial.println("左旋回");
}

void turnRight() {
  digitalWrite(IN1, HIGH);
  digitalWrite(IN2, LOW);
  analogWrite(ENA, speed);
  digitalWrite(IN3, LOW);
  digitalWrite(IN4, HIGH);
  analogWrite(ENB, speed);
  Serial.println("右旋回");
}

void stopAllMotors() {
  digitalWrite(IN1, LOW);
  digitalWrite(IN2, LOW);
  analogWrite(ENA, 0);
  digitalWrite(IN3, LOW);
  digitalWrite(IN4, LOW);
  analogWrite(ENB, 0);
  Serial.println("停止");
}

void setSpeed(int speedLevel) {
  speed = speedLevel * 25; // 0〜225にスケーリング
  if (speed > 255) speed = 255;
  Serial.print("速度設定: ");
  Serial.print(speedLevel);
  Serial.print(" (PWM: ");
  Serial.print(speed);
  Serial.println(")");
}
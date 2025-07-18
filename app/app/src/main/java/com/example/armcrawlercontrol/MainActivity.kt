package com.example.armcrawlercontrol

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothGattCallback
import android.bluetooth.BluetoothGattCharacteristic
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothProfile
import android.bluetooth.le.BluetoothLeScanner
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.SeekBar
import android.widget.Toast
import androidx.annotation.RequiresPermission
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.UUID

class MainActivity : AppCompatActivity() {
    private var bluetoothAdapter: BluetoothAdapter? = null
    private var bluetoothLeScanner: BluetoothLeScanner? = null
    private var bluetoothGatt: BluetoothGatt? = null
    private var commandCharacteristic: BluetoothGattCharacteristic? = null

    companion object {
        private const val PERMISSION_REQUEST_CODE = 1001
        private val SERVICE_UUID: UUID = UUID.fromString("19B10000-E8F2-537E-4F6C-D104768A1214")
        private val CHARACTERISTIC_UUID: UUID = UUID.fromString("19B10001-E8F2-537E-4F6C-D104768A1214")
        private const val TAG = "ArmCrawlerControl"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Bluetoothの初期化
        val bluetoothManager = getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        bluetoothAdapter = bluetoothManager.adapter

        // UI要素の取得
        val btnForward: Button = findViewById(R.id.btnForward)
        val btnBackward: Button = findViewById(R.id.btnBackward)
        val btnLeft: Button = findViewById(R.id.btnLeft)
        val btnRight: Button = findViewById(R.id.btnRight)
        val btnStop: Button = findViewById(R.id.btnStop)
        val speedSlider: SeekBar = findViewById(R.id.speedSlider)

        // 権限の確認と要求
        val permissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            arrayOf(
                Manifest.permission.BLUETOOTH_SCAN,
                Manifest.permission.BLUETOOTH_CONNECT,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        } else {
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
        }

        if (permissions.any { ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED }) {
            ActivityCompat.requestPermissions(this, permissions, PERMISSION_REQUEST_CODE)
        } else {
            startBleScan()
        }

        // ボタンのクリックイベント設定
        btnForward.setOnClickListener { 
            sendCommand("F")
            Log.d(TAG, "前進ボタンが押されました")
        }
        btnBackward.setOnClickListener { 
            sendCommand("B")
            Log.d(TAG, "後退ボタンが押されました")
        }
        btnLeft.setOnClickListener { 
            sendCommand("L")
            Log.d(TAG, "左旋回ボタンが押されました")
        }
        btnRight.setOnClickListener { 
            sendCommand("R")
            Log.d(TAG, "右旋回ボタンが押されました")
        }
        btnStop.setOnClickListener { 
            sendCommand("S")
            Log.d(TAG, "停止ボタンが押されました")
        }

        // スライダーのイベント設定
        speedSlider.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    sendCommand(progress.toString())
                    Log.d(TAG, "速度変更: $progress")
                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE && grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
            startBleScan()
        } else {
            Toast.makeText(this, "権限が必要です", Toast.LENGTH_SHORT).show()
        }
    }

    @RequiresPermission(allOf = [Manifest.permission.BLUETOOTH_SCAN, Manifest.permission.BLUETOOTH_CONNECT])
    private fun startBleScan() {
        if (bluetoothAdapter?.isEnabled != true) {
            Toast.makeText(this, "Bluetoothを有効にしてください", Toast.LENGTH_SHORT).show()
            return
        }

        bluetoothLeScanner = bluetoothAdapter!!.bluetoothLeScanner
        bluetoothLeScanner?.startScan(object : ScanCallback() {
            override fun onScanResult(callbackType: Int, result: ScanResult) {
                if ("UNO R4 WiFi" == result.device.name) {
                    Log.d(TAG, "Arduino Uno R4 WiFiを発見しました")
                    connectToDevice(result.device)
                    bluetoothLeScanner?.stopScan(this)
                }
            }

            override fun onScanFailed(errorCode: Int) {
                Log.e(TAG, "スキャンエラー: $errorCode")
                Toast.makeText(this@MainActivity, "Bluetoothスキャンに失敗しました", Toast.LENGTH_SHORT).show()
            }
        }) ?: run {
            Toast.makeText(this, "Bluetooth LEがサポートされていません", Toast.LENGTH_SHORT).show()
        }
    }

    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    private fun connectToDevice(device: BluetoothDevice) {
        bluetoothGatt = device.connectGatt(this, false, object : BluetoothGattCallback() {
            override fun onConnectionStateChange(gatt: BluetoothGatt, status: Int, newState: Int) {
                when (newState) {
                    BluetoothProfile.STATE_CONNECTED -> {
                        Log.d(TAG, "デバイスに接続しました")
                        gatt.discoverServices()
                        runOnUiThread {
                            Toast.makeText(this@MainActivity, "接続成功", Toast.LENGTH_SHORT).show()
                        }
                    }
                    BluetoothProfile.STATE_DISCONNECTED -> {
                        Log.d(TAG, "デバイスから切断されました")
                        runOnUiThread {
                            Toast.makeText(this@MainActivity, "接続が切断されました", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }

            override fun onServicesDiscovered(gatt: BluetoothGatt, status: Int) {
                if (status == BluetoothGatt.GATT_SUCCESS) {
                    val service = gatt.getService(SERVICE_UUID)
                    commandCharacteristic = service?.getCharacteristic(CHARACTERISTIC_UUID)
                    Log.d(TAG, "サービスとキャラクタリスティックを発見しました")
                } else {
                    Log.e(TAG, "サービス発見に失敗しました: $status")
                }
            }
        })
    }

    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    private fun sendCommand(command: String) {
        commandCharacteristic?.let { characteristic ->
            characteristic.value = command.toByteArray()
            val success = bluetoothGatt?.writeCharacteristic(characteristic) ?: false
            if (success) {
                Log.d(TAG, "コマンド送信: $command")
            } else {
                Log.e(TAG, "コマンド送信失敗: $command")
            }
        } ?: run {
            Toast.makeText(this, "デバイスに接続されていません", Toast.LENGTH_SHORT).show()
        }
    }

    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    override fun onDestroy() {
        super.onDestroy()
        bluetoothGatt?.disconnect()
        bluetoothGatt?.close()
        Log.d(TAG, "アプリ終了: Bluetooth接続をクローズしました")
    }
}
package com.olosom.health.entity;

import android.bluetooth.BluetoothDevice;

/**
 * 蓝牙设备
 */

public class BleDevice {

    private BluetoothDevice bluetoothDevice;

    private ConnectStatus connectStatus;

    public BleDevice(BluetoothDevice bluetoothDevice, ConnectStatus connectStatus) {
        this.bluetoothDevice = bluetoothDevice;
        this.connectStatus = connectStatus;
    }

    public BluetoothDevice getBluetoothDevice() {
        return bluetoothDevice;
    }

    public void setBluetoothDevice(BluetoothDevice bluetoothDevice) {
        this.bluetoothDevice = bluetoothDevice;
    }

    public ConnectStatus getConnectStatus() {
        return connectStatus;
    }

    public void setConnectStatus(ConnectStatus connectStatus) {
        this.connectStatus = connectStatus;
    }

    public enum ConnectStatus{

        /**
         * 未连接
         */
        NotConnect,

        /**
         * 连接中
         */
        Connecting,

        /**
         * 已连接
         */
        Connected,

    }

}

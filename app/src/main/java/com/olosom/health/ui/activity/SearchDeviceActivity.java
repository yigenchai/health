package com.olosom.health.ui.activity;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.olosom.health.R;
import com.olosom.health.adapter.BleDeviceListAdapter;
import com.olosom.health.entity.BleDevice;
import com.olosom.health.mvp.BasePresenter;
import com.olosom.health.rxpermissions.OnRequestPermissionListener;
import com.olosom.health.ui.BaseActivity;
import com.olosom.health.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 搜索设备界面
 */

public class SearchDeviceActivity extends BaseActivity {

    /**
     * 系统蓝牙查找适配器
     */
    private BluetoothAdapter mBluetoothAdapter;

    private Button mSearchBtn;

    private RecyclerView mRecyclerView;
    private BleDeviceListAdapter mAdapter;

    private List<BleDevice> mBleDeviceList = new ArrayList<>();

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_search_device);
    }

    @Override
    protected void initViews() {
        mSearchBtn = (Button) findViewById(R.id.btn_search);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerPermissionManager();
        requestPermission();
    }






















    /**
     * 初始化蓝牙
     */
    private void initBluetooth() {
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            // 蓝牙不可用
            CommonUtils.showToast(this, getString(R.string.bluetooth_is_not_available));
            finish();
        }
        final BluetoothManager bluetoothManager =
                (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();
        // 蓝牙模块找不到，也是不可用
        if (mBluetoothAdapter == null){
            CommonUtils.showToast(this, getString(R.string.bluetooth_is_not_available));
            finish();
        }
    }

    private void requestPermission() {
        requestPermissions(new OnRequestPermissionListener() {
                               @Override
                               public boolean onGrantedPermission() {
                                   initBluetooth();
                                   return false;
                               }
                           }, getString(R.string.need_bluetooth_permissions),
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION});
    }


}

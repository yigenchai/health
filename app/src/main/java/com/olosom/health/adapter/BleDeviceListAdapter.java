package com.olosom.health.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.olosom.health.R;
import com.olosom.health.entity.BleDevice;
import com.olosom.health.entity.BleDevice.ConnectStatus;

import java.util.List;

/**
 * 蓝牙设备列表适配器
 */

public class BleDeviceListAdapter extends RecyclerView.Adapter<BleDeviceListAdapter.Holder>{

    private Resources mResources;
    private LayoutInflater mInflater;

    private List<BleDevice> mBleDeviceList;

    public BleDeviceListAdapter(Context context, List<BleDevice> bleDeviceList){
        this.mBleDeviceList = bleDeviceList;
        this.mResources = context.getResources();
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(mInflater.inflate(R.layout.row_bluetooth_device, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        BleDevice bleDevice = mBleDeviceList.get(position);
        holder.nameTv.setText(bleDevice.getBluetoothDevice().getName());
        setupConnectStatus(holder, bleDevice.getConnectStatus());
    }

    private void setupConnectStatus(Holder holder, ConnectStatus connectStatus){
        switch (connectStatus){
            case NotConnect:
                holder.connectStatusTv.setText(mResources.getString(R.string.not_connect));
                break;
            case Connecting:
                holder.connectStatusTv.setText(mResources.getString(R.string.connecting));
                break;
            case Connected:
                holder.connectStatusTv.setText(mResources.getString(R.string.connected));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mBleDeviceList == null ? 0 : mBleDeviceList.size();
    }

    class Holder extends RecyclerView.ViewHolder{

        private TextView nameTv;
        private TextView connectStatusTv;

        Holder(View itemView) {
            super(itemView);
            nameTv = (TextView) itemView.findViewById(R.id.tv_name);
            connectStatusTv = (TextView) itemView.findViewById(R.id.tv_connect_status);
        }

    }
}

package com.example.ble;

import android.bluetooth.BluetoothAdapter;


import java.util.Set;

public class doBLE extends Thread{

    private BluetoothAdapter mBtAdapter = BluetoothAdapter.getDefaultAdapter();;

    public void doDiscovery() {
        // Turn on sub-title for new devices
        //findViewById(R.id.title_new_devices).setVisibility(View.VISIBLE);

        // If we're already discovering, stop it
        if (mBtAdapter.isDiscovering() == true) {
            mBtAdapter.cancelDiscovery();
        }
        // Request discover from BluetoothAdapter
        mBtAdapter.startDiscovery();
    }
    public void stopscan(){
        mBtAdapter.cancelDiscovery();
    }
}

package com.example.ble;

import android.bluetooth.BluetoothDevice;

import java.util.Set;

public class searchBLE extends Thread{
    public BluetoothDevice want;
    public BluetoothDevice search(Set<BluetoothDevice> pairedDevices){
        System.out.println("2-1");
        for(BluetoothDevice device : pairedDevices){
            System.out.println(device.getName());
            if (device.getName().toString() .equals( "raspberrypi")){
                System.out.println("2-true");
                want = device;
                return want;
            }
        }
        System.out.println("2-false");
        return null;
    }
}

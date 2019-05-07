package com.example.ble;

import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class connecting extends AppCompatActivity {
    public BluetoothDevice want;
    public Bluetooth connect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Intent back = new Intent();
        want = getIntent().getParcelableExtra("Bledevice");
        if(want!=null){
            System.out.println("4");
            connect = new Bluetooth(want);
            System.out.println("5");
            connect.run();
            System.out.println("6");
        }else{
            System.out.println("6-false");
        }
        back.setClass(connecting.this,Main.class);
        startActivity(back);
    }
}

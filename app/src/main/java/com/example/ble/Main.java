package com.example.ble;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLOutput;
import java.util.Set;
import java.util.UUID;

public class Main extends AppCompatActivity {

    // Name for the SDP record when creating server socket
    private static final String NAME_SECURE = "BluetoothChatSecure";
    private static final String NAME_INSECURE = "BluetoothChatInsecure";

    // Unique UUID for this application
    private static final UUID MY_UUID_SECURE =
            UUID.fromString("fa87c0d0-afac-11de-8a39-0800200c9a66");
    private static final UUID MY_UUID_INSECURE =
            UUID.fromString("8ce255c0-200a-11e0-ac64-0800200c9a66");

    // Member fields
    public EditText sendtext;
    private BluetoothAdapter mBtAdapter = BluetoothAdapter.getDefaultAdapter();;
    public doBLE Do;
    public send send;
    public searchBLE search;
    public BluetoothDevice want;
    public Bluetooth connect;
    public Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Do = new doBLE();
        Do.doDiscovery();
        Set<BluetoothDevice> pairedDevices = mBtAdapter.getBondedDevices();
        if (pairedDevices.size() > 0){
            System.out.println("get in main if");
            Do = new doBLE();
            System.out.println("1");
            search = new searchBLE();
            System.out.println("2");
            want = search.search(pairedDevices);
            if(want!=null){
                System.out.println("4");
                connect = new Bluetooth(want);
                System.out.println("5");
                connect.run();
                System.out.println("6");
            }else{
                System.out.println("6-false");
            }
        } else {
            System.out.println("3-false");
        }


        //宣告UI的物件
        Button searchBt = findViewById(R.id.searchbtn);
        Button connectBt = findViewById(R.id.connect);
        /*searchBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Do = new doBLE();
                Do.doDiscovery();
                Set<BluetoothDevice> pairedDevices = mBtAdapter.getBondedDevices();
                if (pairedDevices.size() >= 0 && pairedDevices.size()<=10) {
                    System.out.println("get in main if");
                    Do = new doBLE();
                    System.out.println("1");
                    search = new searchBLE();
                    System.out.println("2");
                    want = search.search(pairedDevices);
                    System.out.println("3");
                    intent = new Intent();
                    intent.setClass(Main.this,connecting.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("Bledevice",want);
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else {
                    System.out.println("3-false");
                }
            }
        });*/
        Button sendBt = findViewById(R.id.send);
        sendtext = findViewById(R.id.input);
        //按下傳送按鍵後
        sendBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send = new send();
                byte[] bytes = sendtext.getText().toString().getBytes();
                send.write(bytes);
            }
        });
    }
}
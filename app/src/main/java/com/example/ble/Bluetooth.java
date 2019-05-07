package com.example.ble;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.os.Message;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class Bluetooth extends Thread{
        private BluetoothSocket mmSocket;
        private BluetoothDevice mmDevice;
    private InputStream mmInStream;
    private Handler handler;
    public doBLE Do;

        public Bluetooth(BluetoothDevice device) {
            // Use a temporary object that is later assigned to mmSocket
            // because mmSocket is final.
            BluetoothSocket tmp = null;
            mmDevice = device;

            try {
                // Get a BluetoothSocket to connect with the given BluetoothDevice.
                // MY_UUID is the app's UUID string, also used in the server code.
                tmp = device.createRfcommSocketToServiceRecord(UUID.fromString("11111111-1111-1111-1111-111111111111"));
                System.out.println("5-1-true");
            } catch (IOException e) {
                System.out.println("5-1-false");
            }
            mmSocket = tmp;
            System.out.println("5-1");
        }
        public void run() {
            // Cancel discovery because it otherwise slows down the connection.
            BluetoothAdapter bluetoothAdapter = null;
            Do = new doBLE();
            Do.stopscan();

            try {
                // Connect to the remote device through the socket. This call blocks
                // until it succeeds or throws an exception.
                mmSocket.connect();
                System.out.println("true connect");
            } catch (IOException connectException) {
                // Unable to connect; close the socket and return.
                System.out.println("5-2-false");
                try {
                    mmSocket.close();
                    System.out.println("5-5999");
                } catch (IOException closeException) {
                    System.out.println("5-3-false");
                }
                return;
            }

            // The connection attempt succeeded. Perform work associated with
            // the connection in a separate thread.
            manageMyConnectedSocket(mmSocket);
            System.out.println("5-2");
        }

    private void manageMyConnectedSocket(BluetoothSocket mmSocket) {

    }

    // Closes the client socket and causes the thread to finish.
    public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) {
                System.out.println("5-4-false");
            }
        }
}

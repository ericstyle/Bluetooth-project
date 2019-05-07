package com.example.ble;

import android.os.Handler;
import android.os.Message;

import java.io.IOException;
import java.io.OutputStream;

import android.os.Handler;
public class send extends Thread{
    public Handler handler;
    private OutputStream mmOutStream;
    public void write(byte[] bytes) {
        byte[] mmBuffer;
        handler = new Handler();
        mmBuffer = new byte[1024];
        try {
            mmOutStream = new OutputStream() {
                @Override
                public void write(int b) throws IOException {

                }
            };
            mmOutStream.write(bytes);

            // Share the sent message with the UI activity.
            Message writtenMsg = handler.obtainMessage(
                    1, -1, -1, bytes);
            writtenMsg.sendToTarget();
        } catch (IOException e) {
            System.out.println("false");
        }
    }
}

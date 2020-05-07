package com.example.screentracker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Timestamp;

public class ScreenReceiver extends BroadcastReceiver {

    // thanks Jason
    public static boolean wasScreenOn = true;

    @Override
    public void onReceive(Context context, Intent intent) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            writeToFile(timestamp.getTime() + ",screen_off", context);
        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            writeToFile(timestamp.getTime() + ",screen_on", context);
        }
    }


    private void writeToFile(String data, Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("config.txt", Context.MODE_APPEND));
            outputStreamWriter.append(data + "\n");
            outputStreamWriter.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
}

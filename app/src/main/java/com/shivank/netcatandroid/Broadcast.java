package com.shivank.netcatandroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Broadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if ((intent.getAction() != null) && (intent.getAction().equals("android.intent.action.BOOT_COMPLETED"))) {

            Toast.makeText(context, "Boot Comp Comp Boot", Toast.LENGTH_SHORT).show();
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    // Start the activity
                    Intent activityIntent = new Intent(context, MainActivity.class);
                    context.startActivity(activityIntent);
                }
            });

            // Start the thread
            thread.start();


        }

    }
}

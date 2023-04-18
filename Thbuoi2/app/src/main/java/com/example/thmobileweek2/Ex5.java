package com.example.thmobileweek2;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


public class Ex5 extends AppCompatActivity {

    private Button btn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex5);

        btn = findViewById(R.id.btn);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    1);
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //View website
                Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com.vn"));

                //Call phone
                Intent intent2 = new Intent(Intent.ACTION_CALL, Uri.parse("tel:(+84)123456789"));

                //start the phone dialer
                Intent intent3 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:(+84)123456789"));

                //default contacts app
                Intent intent4 = new Intent(Intent.ACTION_VIEW,Uri.parse("content://contacts/people/"));

                //Message
                Intent intent5 = new Intent(Intent.ACTION_SENDTO, Uri.parse("sms:5551234"));
                intent5.putExtra("sms_body", "Thu bay nay di choi khong?");

                //Get Picture
                Intent intent6 = new Intent();
                intent6.setType("image/pictures/*");
                intent6.setAction(Intent.ACTION_GET_CONTENT);

                //Music
                Intent intent7 = new Intent("android.intent.action.MUSIC_PLAYER");

                //Find the path from point A to point B
                String url = "http://maps.google.com/maps?"+ "saddr=9.938083,- 84.054430&daddr=9.926392,-84.055964";
                Intent intent8 = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

                //3 more intent
                //1. intent action that is used to create a new alarm in the device's default alarm clock application:
                Intent intent9 = new Intent(AlarmClock.ACTION_SET_ALARM);
                intent9.putExtra(AlarmClock.EXTRA_HOUR, 8);
                intent9.putExtra(AlarmClock.EXTRA_MINUTES, 30);
                intent9.putExtra(AlarmClock.EXTRA_MESSAGE, "Wake up!");
                startActivity(intent9);

                //2. Intent for opening a specific location in Google Maps
                String locationUri = "geo:0,0?q=1600+Amphitheatre+Parkway,+Mountain+View,+California";
                Intent intent10 = new Intent(Intent.ACTION_VIEW, Uri.parse(locationUri));
                startActivity(intent10);

                //3.FILL_IN_ACTION to create an Intent with an ACTION_SEND action and a MIME type of "text/plain", but without providing content-specific data:
                Intent intent11 = new Intent(Intent.ACTION_SENDTO);
                intent11.setData(Uri.parse("mailto:"));
                intent11.putExtra(Intent.EXTRA_EMAIL, new String[]{"example@example.com"});
                Intent newIntent = new Intent();
                newIntent.fillIn(intent11, Intent.FILL_IN_ACTION);
                startActivity(newIntent);


            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            } else {
                // Permission denied. Handle accordingly.
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}
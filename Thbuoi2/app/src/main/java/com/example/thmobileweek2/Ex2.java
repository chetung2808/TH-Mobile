package com.example.thmobileweek2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class Ex2 extends AppCompatActivity implements View.OnClickListener {

    private Button btn1, btn2, btn3, btn4, nextBtn;
    private RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex2);

        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btn4 = findViewById(R.id.button4);
        layout = findViewById(R.id.layout);


        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                layout.setBackgroundColor(Color.RED);
                break;
            case R.id.button2:
                layout.setBackgroundColor(Color.GREEN);
                break;
            case R.id.button3:
                layout.setBackgroundColor(Color.BLUE);
                break;
            case R.id.button4:
                layout.setBackgroundColor(Color.GRAY);
                break;
            default:
                break;
        }
    }
}
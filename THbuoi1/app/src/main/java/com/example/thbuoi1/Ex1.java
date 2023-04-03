package com.example.thbuoi1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Ex1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button newbtn = findViewById(R.id.New);

        Button conbtn = findViewById(R.id.Con);

        Button helpbtn = findViewById(R.id.Help);

        Button quickbtn = findViewById(R.id.Quick);

        newbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Ex1.this, Ex2.class);
                startActivity(intent);
            }

            ;
        });


        conbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Ex1.this, Ex3.class);
                startActivity(intent);
            }
        });

        helpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Ex1.this, Ex4.class);
                startActivity(intent);
            }
        });

        quickbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Ex1.this, Ex5.class);
                startActivity(intent);
            }
        });
    }
}
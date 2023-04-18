package com.example.thmobileweek2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Ex3History extends AppCompatActivity {

    TextView textView, textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex3_history);

        // Retrieve the value passed from the first activity
        String oldDataToCalculate = getIntent().getStringExtra("oldDataToCalculate");
        String oldResult = getIntent().getStringExtra("oldResult");

        textView = findViewById(R.id.history_textview);
        textView2 = findViewById(R.id.result_tv);

        textView.setText(oldDataToCalculate);
        textView2.setText(oldResult);
    }
}
package com.example.thmobileweek2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Ex4ViewContactActivity extends AppCompatActivity {

    private Button finishBtn;
    private TextView txtNameValue, txtEmailValue, txtProjectValue;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex4_view_contact);

        txtNameValue = (TextView) findViewById(R.id.txtNameInfoValue);
        txtEmailValue = (TextView) findViewById(R.id.txtEmailInfoValue);
        txtProjectValue = (TextView) findViewById(R.id.txtProjectInfoValue);
        finishBtn = (Button) findViewById(R.id.btnFinish);
        finishBtn.setOnClickListener(mClickFinishListener);

        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("nameKey");
        String email = bundle.getString("emailKey");
        String project = bundle.getString("projectKey");

        txtNameValue.setText(name);
        txtEmailValue.setText(email);
        txtProjectValue.setText(project);
    }

    private View.OnClickListener mClickFinishListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };
}
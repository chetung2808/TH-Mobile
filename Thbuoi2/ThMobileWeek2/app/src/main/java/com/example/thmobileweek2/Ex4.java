package com.example.thmobileweek2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Ex4 extends AppCompatActivity implements View.OnClickListener  {

    private Button nextBtn, viewcontact;
    private EditText txtName, txtEmail, txtProject;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex4);
        Intent iGetContactInfo = new Intent(getApplicationContext(), Ex4ViewContactActivity.class);

        txtName = findViewById(R.id.txtName);
        txtEmail = findViewById(R.id.txtEmail);
        txtProject = findViewById(R.id.txtProject);
        viewcontact = findViewById(R.id.viewContact);



        Bundle bundle = new Bundle();
        bundle.putString("nameKey", txtName.getText().toString());
        bundle.putString("emailKey", txtEmail.getText().toString());
        bundle.putString("projectKey", txtProject.getText().toString());
        iGetContactInfo.putExtras(bundle);

        viewcontact.setOnClickListener(this);
    }

    public void onClick(View v) {
        Intent iGetContactInfo = new Intent(getApplicationContext(), Ex4ViewContactActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("nameKey", txtName.getText().toString());
        bundle.putString("emailKey", txtEmail.getText().toString());
        bundle.putString("projectKey", txtProject.getText().toString());
        iGetContactInfo.putExtras (bundle);
        startActivity(iGetContactInfo);
    }
}
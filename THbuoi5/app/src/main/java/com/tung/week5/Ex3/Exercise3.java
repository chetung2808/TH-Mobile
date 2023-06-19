package com.thanh.week5.Ex3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

import com.thanh.week5.Ex2.Exercise2;
import com.thanh.week5.Ex4.Exercise4;
import com.thanh.week5.R;

public class Exercise3 extends AppCompatActivity {
    private Button nextExBtn;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor edit;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise3);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        edit = sharedPreferences.edit();

        nextExBtn = findViewById(R.id.nextExBtn);

        nextExBtn.setOnClickListener(v -> {
            Intent intent = new Intent(Exercise3.this, Exercise4.class);
            startActivity(intent);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.dayNigthMode){
            boolean dn=sharedPreferences.getBoolean(getString(R.string.dayNightTheme),true);
            if(dn) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                edit.putBoolean(getString(R.string.dayNightTheme),false).apply();
            }
            else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                edit.putBoolean(getString(R.string.dayNightTheme),true).apply();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
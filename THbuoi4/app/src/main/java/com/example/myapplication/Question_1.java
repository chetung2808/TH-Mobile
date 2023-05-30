package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Question_1 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question_1, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {



        final EditText editdata = view.findViewById(R.id.edittextView);
        final Button btn = view.findViewById(R.id.readdatatBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data;
                InputStream in = getResources().openRawResource(R.raw.myfile);
                InputStreamReader inreader = new InputStreamReader(in);
                BufferedReader bufreader = new BufferedReader(inreader);
                StringBuilder builder = new StringBuilder();
                if (in != null) {
                    try {
                        while ((data = bufreader.readLine()) != null) {
                            builder.append(data);
                            builder.append("\n");
                        }
                        in.close();
                        editdata.setText(builder.toString());
                    } catch (IOException ex) {
                        Log.e("ERROR", ex.getMessage());
                    }
                }
            }
        });
    }
}


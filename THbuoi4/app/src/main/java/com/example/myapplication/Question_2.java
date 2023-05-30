package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Question_2 extends Fragment implements View.OnClickListener  {
    Button btnread, btnwrite;
    EditText editdata;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question_2, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        btnread = view.findViewById(R.id.btnreaddata);
        btnwrite = view.findViewById(R.id.btnwritedata);
        editdata = view.findViewById(R.id.editdata);
        btnread.setOnClickListener((View.OnClickListener) this);
        btnwrite.setOnClickListener((View.OnClickListener) this);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.btnreaddata) {
            readData(v,getContext());
        } else if (v.getId() == R.id.btnwritedata) {
            writeData(v,getContext());
        }
    }

    public void writeData(View view,Context fileContext) {
        String text = editdata.getText().toString();
        try {
            FileOutputStream fos = fileContext.openFileOutput("Ex2file.txt", Context.MODE_PRIVATE);
            fos.write(text.getBytes());
            fos.close();
            Toast.makeText(getActivity(), "File saved.", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Error writing file.", Toast.LENGTH_SHORT).show();
        }
    }

    public void readData(View view,Context fileContext) {
        try {
            FileInputStream fis = fileContext.openFileInput("Ex2file.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
            editdata.setText(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Error reading file.", Toast.LENGTH_SHORT).show();
        }
    }


}
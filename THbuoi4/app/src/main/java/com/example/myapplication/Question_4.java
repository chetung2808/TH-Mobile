package com.example.myapplication;

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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Question_4 extends Fragment implements View.OnClickListener {
    Button btnread, btnwrite;
    EditText editdata;
    private String fileName = "Ex4file.txt";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question_4, container, false);
    }

    @Override
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
            readData();
        } else if (v.getId() == R.id.btnwritedata) {
            writeData();
        }
    }

    private void readData() {
        try (FileInputStream fis = new FileInputStream(new File(getActivity().getExternalFilesDir(null), fileName))) {
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            String data = new String(buffer, StandardCharsets.UTF_8);
            editdata.setText(data);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Error reading data from file: " + fileName, Toast.LENGTH_SHORT).show();
        }
    }

    private void writeData() {
        try (FileOutputStream fos = new FileOutputStream(new File(getActivity().getExternalFilesDir(null), fileName))) {
            fos.write(editdata.getText().toString().getBytes(StandardCharsets.UTF_8));
            Toast.makeText(getActivity(), "Data saved to file: " + fileName, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Error saving data to file: " + fileName, Toast.LENGTH_SHORT).show();
        }
    }


}
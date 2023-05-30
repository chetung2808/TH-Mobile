package com.example.myapplication;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.myapplication.Model.UserModel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Question_3 extends Fragment {
    private EditText nameField, emailField, phoneField;
    private RadioGroup genderGroup;
    private Button saveButton, cancelButton, changeButton, nextEx;
    private ImageView imageView;
    int genderId;
    ActivityResultLauncher<Uri> takePictureLauncher;
    Uri imageUri;
    private static final String FILENAME = "user.txt";
    private static final int CAMERA_PERMISSION_CODE = 1;

    Context thiscontext;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        thiscontext = container.getContext();
        return inflater.inflate(R.layout.fragment_question_3, container, false);
    }

    @SuppressLint("MissingInflatedId")

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        nameField = view.findViewById(R.id.nameField);
        emailField = view.findViewById(R.id.emailField);
        phoneField = view.findViewById(R.id.phoneField);
//        genderGroup = view.findViewById(R.id.genderGroup);
        saveButton = view.findViewById(R.id.saveButton);
        cancelButton = view.findViewById(R.id.cancelButton);
        changeButton = view.findViewById(R.id.changeButton);
        genderGroup = (RadioGroup) view.findViewById(R.id.genderGroup);
        imageView = view.findViewById(R.id.userImage);
        imageUri = createUri();

        registerPictureLauncher();


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameField.getText().toString();
                String email = emailField.getText().toString();
                String phone = phoneField.getText().toString();

                genderId = genderGroup.getCheckedRadioButtonId();

                if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || genderId == -1) {
                    Toast.makeText(thiscontext, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    String gender = genderGroup.toString();

                    UserModel user = new UserModel(name, email, phone, gender);
                    writeUserToFile(thiscontext, FILENAME, user);

                    Toast.makeText(thiscontext, "User saved", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
                Toast.makeText(getActivity(), "Exit!", Toast.LENGTH_SHORT).show();
            }
        });

        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCameraPermissionAndOpenCamera();
            }
        });

        UserModel user = readUserFromFile(getActivity(), FILENAME);
        if (user != null) {
            nameField.setText(user.getName());
            emailField.setText(user.getEmail());
            phoneField.setText(user.getPhone());
            if (user.getGender().equals("Male")) {
                genderGroup.check(R.id.maleRadio);
            } else {
                genderGroup.check(R.id.femaleRadio);
            }
        }

        // Load the saved image, if it exists
        File imageFile = new File(getActivity().getApplicationContext().getFilesDir(), "camera_photo.jpg");
        if (imageFile.exists()) {
            Uri imageUri = FileProvider.getUriForFile(
                    getActivity().getApplicationContext(),
                    "com.example.myapplication.fileProvider",
                    imageFile
            );
            imageView.setImageURI(imageUri);
        }

    }

    public static UserModel readUserFromFile(Context context, String filename) {
        try {
            FileInputStream fis = context.openFileInput(filename);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

            String line = reader.readLine();
            if (line != null) {
                String[] values = line.split(",");
                return new UserModel(values[0], values[1], values[2], values[3]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void writeUserToFile(Context context, String filename, UserModel user) {
        try {
            FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));

            String line = user.getName() + "," + user.getEmail() + "," + user.getPhone() + "," + user.getGender();
            writer.write(line);

            writer.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Uri createUri() {
        File imageFile = new File(getActivity().getApplicationContext().getFilesDir(), "camera_photo.jpg");
        return FileProvider.getUriForFile(
                getActivity().getApplicationContext(),
                "com.example.myapplication.fileProvider",
                imageFile
        );
    }

    private void registerPictureLauncher() {
        takePictureLauncher = registerForActivityResult(
                new ActivityResultContracts.TakePicture(),
                new ActivityResultCallback<Boolean>() {
                    @Override
                    public void onActivityResult(Boolean result) {
                        try {
                            if (result) {
                                imageView.setImageURI(null);
                                imageView.setImageURI(imageUri);
                            }
                        } catch (Exception exception) {
                            exception.getStackTrace();
                        }
                    }
                }
        );
    }

    private void checkCameraPermissionAndOpenCamera() {
        if (ActivityCompat.checkSelfPermission(getActivity(),
                android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);
        } else {
            takePictureLauncher.launch(imageUri);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                takePictureLauncher.launch(imageUri);
            } else {
                Toast.makeText(getActivity(), "Camera permission denied, please allow permission to take picture", Toast.LENGTH_SHORT).show();
            }
        }
    }


}
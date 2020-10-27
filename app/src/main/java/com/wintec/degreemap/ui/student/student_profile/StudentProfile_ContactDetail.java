package com.wintec.degreemap.ui.student.student_profile;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.wintec.degreemap.R;
import com.wintec.degreemap.ui.student.Student_Home;

import java.io.IOException;

public class StudentProfile_ContactDetail extends AppCompatActivity {

    private ImageView profileImage;
    private static final int PICK_IMAGE = 1;
    Uri imageUri;

    private EditText firstName, lastName, ID, phone, email;
    private Button btnSava, btnCancel;


    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String FIRST_NAME = "first name";
    public static final String LAST_NAME = "last name";
    public static final String STUDENT_ID = "student id";
    public static final String PHONE = "phone number";
    public static final String EMAIL = "email";

    private String sp_firstName, sp_lastName, sp_id, sp_phone, sp_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        profileImage = findViewById(R.id.details_avatar);
        firstName = findViewById(R.id.details_first_name);
        lastName = findViewById(R.id.details_last_name);
        ID = findViewById(R.id.details_id);
        phone = findViewById(R.id.details_phone);
        email = findViewById(R.id.details_email);

        btnSava = findViewById(R.id.btn_details_save);
        btnCancel = findViewById(R.id.btn_details_cancel);

        btnSava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });

        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });
        loadData();
        updateViews();
    }

    // Choose image file for avatar
    private void openFileChooser() {
        Intent gallery = new Intent();
        gallery.setType("image/*");
        gallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(gallery, "Select Picture"), PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                profileImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(FIRST_NAME, firstName.getText().toString());
        editor.putString(LAST_NAME, lastName.getText().toString());
        editor.putString(STUDENT_ID, ID.getText().toString());
        editor.putString(PHONE, phone.getText().toString());
        editor.putString(EMAIL, email.getText().toString());

        editor.apply();


        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        sp_firstName = sharedPreferences.getString(FIRST_NAME, "");
        sp_lastName = sharedPreferences.getString(LAST_NAME, "");
        sp_id = sharedPreferences.getString(STUDENT_ID, "");
        sp_phone = sharedPreferences.getString(PHONE, "");
        sp_email = sharedPreferences.getString(EMAIL, "");
    }

    public void updateViews() {
        firstName.setText(sp_firstName);
        lastName.setText(sp_lastName);
        ID.setText(sp_id);
        phone.setText(sp_phone);
        email.setText(sp_email);
    }

    public void jumpTo(View view) {
        Intent i = null;
        switch (view.getId()) {
            case R.id.btn_details_cancel:
                i = new Intent(this, Student_Home.class);
                startActivity(i);
        }
    }
}
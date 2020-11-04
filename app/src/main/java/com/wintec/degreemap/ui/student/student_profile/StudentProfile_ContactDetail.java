package com.wintec.degreemap.ui.student.student_profile;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

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
import com.wintec.degreemap.data.model.User;
import com.wintec.degreemap.ui.student.StudentHome;
import com.wintec.degreemap.viewmodel.UserViewModel;

import java.io.IOException;

import static com.wintec.degreemap.util.Constants.KEY_USER_KEY;
import static com.wintec.degreemap.util.Constants.PATHWAY_WEB_DEVELOPMENT;
import static com.wintec.degreemap.util.Constants.SHARED_PREFERENCES;

public class StudentProfile_ContactDetail extends AppCompatActivity {

    private ImageView profileImage;
    private static final int PICK_IMAGE = 1;
    Uri imageUri;
    SharedPreferences mPrefs;

    private EditText firstName, lastName, id, phone, email;
    private Button btnSava, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        profileImage = findViewById(R.id.details_avatar);
        firstName = findViewById(R.id.details_first_name);
        lastName = findViewById(R.id.details_last_name);
        id = findViewById(R.id.details_id);
        phone = findViewById(R.id.details_phone);
        email = findViewById(R.id.details_email);

        btnSava = findViewById(R.id.btn_details_save);
        btnCancel = findViewById(R.id.btn_details_cancel);

        mPrefs = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
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
        UserViewModel userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        User user = new User(null,
                firstName.getText().toString(),
                lastName.getText().toString(),
                phone.getText().toString(),
                email.getText().toString(),
                "",
                PATHWAY_WEB_DEVELOPMENT);
        userViewModel.insertUser(id.getText().toString(), user);

        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString(KEY_USER_KEY, id.getText().toString());
        editor.apply();

        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
    }

    public void loadData() {
        String userKey = mPrefs.getString(KEY_USER_KEY, "");

        // TODO: If userKey is found then get data from firebase
        if(!userKey.isEmpty()) {
            id.setText(userKey);
        }
    }

    public void jumpTo(View view) {
        Intent i = null;
        switch (view.getId()) {
            case R.id.btn_details_cancel:
                i = new Intent(this, StudentHome.class);
                startActivity(i);
        }
    }
}
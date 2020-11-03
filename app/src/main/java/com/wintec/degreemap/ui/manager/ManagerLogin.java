package com.wintec.degreemap.ui.manager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wintec.degreemap.R;

public class ManagerLogin extends AppCompatActivity {

    EditText  mPassword;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_login);

        mPassword = findViewById(R.id.edittext_password);
        btnSubmit = findViewById(R.id.btn_submit);

        // Method to check password
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // If password is valid, jump to managers' homepage
                // * Password Should Be: WinITDMP01, but for our convenience, it's now: 1
                if (mPassword.getText().toString().equals("1")) {
                    jumpTo();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Invalid Password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Method to jump to managers' homepage
    public void jumpTo() {
        Intent i = new Intent(this, ManagerHome.class);
        startActivity(i);
    }
}
package com.wintec.degreemap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Manager_Password extends AppCompatActivity {

    EditText  mPassword;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_password);

        mPassword = findViewById(R.id.edittext_password);
        btnSubmit = findViewById(R.id.btn_submit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPassword.getText().toString().equals("WinITDMP01")) {
                    jumpTo();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Invalid Password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void jumpTo() {
        Intent i = new Intent(this, Manager_Home.class);
        startActivity(i);
    }
}
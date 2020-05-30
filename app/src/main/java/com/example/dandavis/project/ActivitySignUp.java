package com.example.dandavis.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class ActivitySignUp extends AppCompatActivity {
    private DBAdapter db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = new DBAdapter(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void onSignUp(View view) {
        EditText mEdit   = findViewById(R.id.signUpName);
        String name  = mEdit.getText().toString();
        mEdit   = findViewById(R.id.signUpEmail);
        String email  = mEdit.getText().toString();
        mEdit  =  findViewById(R.id.signUpPassword);
        String password  = mEdit.getText().toString();

        if(!name.equals("") && !password.equals("")&& !email.equals("")) {
            db.open();
            db.registerUser(name, email, password);
            db.close();
            finish();
        }
    }

    public void back(View view) {
        finish();
    }
}

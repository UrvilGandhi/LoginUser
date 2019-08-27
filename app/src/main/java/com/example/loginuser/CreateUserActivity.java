package com.example.loginuser;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.QuickContactBadge;

public class CreateUserActivity extends AppCompatActivity {

    private EditText edtName;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        edtName = findViewById(R.id.name);

        Button btnRegister = findViewById(R.id.btn_register);

        dbManager = new DBManager(this);
        dbManager.open();
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = edtName.getText().toString();
                dbManager.insert(name,0);

                Intent main = new Intent(getApplicationContext(), UserActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(main);
            }
        });
    }

}

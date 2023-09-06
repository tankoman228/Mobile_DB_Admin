package com.example.mobiledbadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((Button)findViewById(R.id.btn_server_console)).setOnClickListener(view -> {
            Intent intent = new Intent(this, ServerConsoleSSH.class);
            startActivity(intent);
        });

        //Intent intent = new Intent(this, table_create.class);
        //startActivity(intent);
    }
}
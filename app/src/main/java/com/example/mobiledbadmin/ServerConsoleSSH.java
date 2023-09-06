package com.example.mobiledbadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class ServerConsoleSSH extends AppCompatActivity {

    EditText et;
    TextView console;
    String c_text = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_console_ssh);

        Connection.host = "192.168.3.74";
        if (!Connection.try_connection()) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Error", Toast.LENGTH_LONG);
            toast.show();
        }

        et = (EditText) findViewById(R.id.tb_comand);
        console = (TextView) findViewById(R.id.tv_console);

        ((Button)findViewById(R.id.btn_enter)).setOnClickListener(view -> {
            try {
                Connection.sendCommand(et.getText().toString());
                c_text += Connection.Output;
                console.setText(c_text);

            } catch (IOException e) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Error: " + e.getLocalizedMessage(), Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }
}
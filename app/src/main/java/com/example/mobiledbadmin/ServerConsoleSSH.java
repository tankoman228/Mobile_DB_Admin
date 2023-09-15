package com.example.mobiledbadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
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
    Button btn_enter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_console_ssh);

        Connection.host = "192.168.3.74";
        new ConnectSSH().execute();

        et = (EditText) findViewById(R.id.tb_comand);
        console = (TextView) findViewById(R.id.tv_console);

        console.setText(Connection.Output);

        btn_enter = (Button)findViewById(R.id.btn_enter);

        btn_enter.setOnClickListener(view -> {
            new SendSSH().execute();
        });
    }

    private class ConnectSSH extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            Connection.try_connection(); // выполнение сетевых операций (соединение по SSH)
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // обновление пользовательского интерфейса
            TextView textView = findViewById(R.id.tv_console);
            textView.setText(Connection.Output);
        }
    }

    private class SendSSH extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                btn_enter.setEnabled(false);

                Connection.sendCommand(et.getText().toString());
                c_text += Connection.Output;
                console.setText(c_text);
                et.setText("");

                btn_enter.setEnabled(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // обновление пользовательского интерфейса

        }
    }

}
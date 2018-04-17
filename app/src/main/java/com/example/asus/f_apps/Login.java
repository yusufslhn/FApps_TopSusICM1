package com.example.asus.f_apps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;

public class Login extends AppCompatActivity {

    private Button button_login;
    private TextView sign_up;
    private boolean exitkeun = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        button_login = (Button) findViewById(R.id.tombol_login);
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abisLogin();
            }
        });

        sign_up = (TextView) findViewById(R.id.tombol_signup);
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                keSignup();
            }
        });

    }

    public void abisLogin(){
        Intent in = new Intent(this,tab_main.class);
        startActivity(in);
    }

    public void keSignup(){
        Intent in = new Intent(this,signup.class);
        startActivity(in);
    }




    public void onBackPressed(){
        // biar di log in nya gak bisa diback
        if (!exitkeun){
            Toast.makeText(this, "klik sekali lagi untuk exit", Toast.LENGTH_SHORT).show();
        }
    }
}

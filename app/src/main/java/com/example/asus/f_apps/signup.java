package com.example.asus.f_apps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class signup extends AppCompatActivity {

    private Button signup_tomb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signup_tomb = (Button) findViewById(R.id.enter_signup);
        signup_tomb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sungSignup();
            }
        });

    }

    public void sungSignup(){
        Intent in = new Intent(this, Login.class);
        startActivity(in);
    }
}

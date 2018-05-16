package com.example.asus.f_apps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class setting_display extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_display);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null ){
            if (bundle.getString("some") != null ){
                Toast.makeText(getApplicationContext(), "data :" + bundle.getString("some"), Toast.LENGTH_SHORT).show();
            }
        }
    }
}

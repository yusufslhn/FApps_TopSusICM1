package com.example.asus.f_apps;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private Button button_login;
    private TextView sign_up;
    private EditText emailnya;
    private EditText passnya;
    private boolean exitkeun = false;
    private FirebaseAuth auth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(Login.this, tab_main.class));
            finish();
        }

        emailnya = (EditText) findViewById(R.id.email_login);
        passnya =(EditText) findViewById(R.id.password);

        progressDialog = new ProgressDialog(Login.this);

        button_login = (Button) findViewById(R.id.tombol_login);
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailnya.getText().toString();
                final String password = passnya.getText().toString();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressDialog.setMessage("Authentication...");
                progressDialog.show();
                auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            // there was an error
                            if (password.length() < 6) {
                                passnya.setError(getString(R.string.minimum_password));
                            } else {
                                Toast.makeText(Login.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                progressDialog.dismiss();

                            }
                        } else {
                            Intent intent = new Intent(Login.this, tab_main.class);
                            startActivity(intent);
                            finish();
                        }

                        progressDialog.dismiss();


                    }
                });

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

    @Override
    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

}

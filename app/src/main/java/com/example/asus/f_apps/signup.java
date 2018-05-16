package com.example.asus.f_apps;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.UserManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.text.TextUtils;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signup extends AppCompatActivity {

    private DatabaseReference database;
    private Button signup_tomb;
    private EditText username, email, password, repassword;
    private FirebaseAuth auth;
    private FirebaseUser user;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        auth = FirebaseAuth.getInstance();
        username =(EditText)findViewById(R.id.username_signup);
        email = (EditText)findViewById(R.id.email_signup);
        password =(EditText)findViewById(R.id.password_signup);
        repassword =(EditText)findViewById(R.id.reenter_password_signup);

        database = FirebaseDatabase.getInstance().getReference();

        signup_tomb = (Button) findViewById(R.id.enter_signup);
        signup_tomb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if(!isEmpty(username.getText().toString()) && !isEmpty(email.getText().toString()) && !isEmpty(password.getText().toString()))
//                    submitAkun(new user_model(username.getText().toString(), email.getText().toString(), password.getText().toString()));
//                else
//                    Snackbar.make(findViewById(R.id.enter_signup), "Data barang tidak boleh kosong", Snackbar.LENGTH_LONG).show();
//                    InputMethodManager imm = (InputMethodManager)
//                        getSystemService(Context.INPUT_METHOD_SERVICE);
//                    imm.hideSoftInputFromWindow(
//                        username.getWindowToken(), 0);


                final String usName, mail, pass, repass;

                progressDialog = new ProgressDialog(signup.this);

                usName = username.getText().toString().trim();
                mail = email.getText().toString().trim();
                pass = password.getText().toString().trim();
                repass = repassword.getText().toString().trim();

                if (TextUtils.isEmpty(mail)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(pass)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (pass.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }

                startActivity(new Intent(signup.this, Login.class));




                progressDialog.setMessage("Sign up...");
                progressDialog.show();

                startActivity(new Intent(signup.this, Login.class));

                auth.createUserWithEmailAndPassword(mail,pass).addOnCompleteListener(signup.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        Toast.makeText(signup.this,"create new user" +task.isSuccessful(), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(signup.this, Login.class));

                        if(task.isSuccessful()){
                            startActivity(new Intent(signup.this, Login.class));
                        } else {
                            Toast.makeText(signup.this, "Sign up failed", Toast.LENGTH_SHORT).show();
                        }

                        progressDialog.dismiss();


                    }
                });




            }
        });

    }
//    private boolean isEmpty(String s) {
//        // Cek apakah ada fields yang kosong, sebelum disubmit
//        return TextUtils.isEmpty(s);
//    }
//
//    private void updateAkun(user_model akun) {
//        // kodingan untuk next tutorial ya :p
//    }
//
//    private void submitAkun(user_model akun) {
//        /**
//         * Ini adalah kode yang digunakan untuk mengirimkan data ke Firebase Realtime Database
//         * dan juga kita set onSuccessListener yang berisi kode yang akan dijalankan
//         * ketika data berhasil ditambahkan
//         */
//        database.child("akun").push().setValue(akun).addOnSuccessListener(this, new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void aVoid) {
//                username.setText("");
//                email.setText("");
//                password.setText("");
//                Snackbar.make(findViewById(R.id.enter_signup), "Data berhasil ditambahkan", Snackbar.LENGTH_LONG).show();
//            }
//        });
//    }


    public void emailVerification() {
//        user = auth.getInstance().getCurrentUser();
//        user.sendEmailVerification().addOnCompleteListener(this, new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//
//                if (task.isSuccessful()) {
//                    Intent intent = new Intent(signup.this, Login.class);
//                    startActivity(intent);
//                    finish();
//                } else {
//                    Log.e("onCreate : ", " sendEmailVerification", task.getException());
//                    Toast.makeText(signup.this,
//                            "Gagal mengirimkan verifikasi ke email.",
//                            Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }



    public void sungSignup(){
        Intent in = new Intent(signup.this, Login.class);
        startActivity(in);
    }
}

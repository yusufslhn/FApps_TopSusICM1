package com.example.asus.f_apps;

import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.text.TextUtils.isEmpty;

public class setting_add_budget extends AppCompatActivity {


    private DatabaseReference database;

    private Button btSave;
    private EditText etnamaBudget;
    private EditText etbudgetLimit;
    private Spinner spTipeTransaksi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_add_budget);
//        spTipeTransaksi = (Spinner) findViewById(R.id.spinner_transaction_type);
        etnamaBudget = (EditText) findViewById(R.id.edittext_budget_name);
        etbudgetLimit = (EditText) findViewById(R.id.edittext_budget_limit);
        btSave = (Button) findViewById(R.id.save_add_budget);

        String[] tipenya = new String[]{
                "Income",
                "Outcome"
        };
        final List<String> listTipe = new ArrayList<>(Arrays.asList(tipenya));
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this,R.layout.activity_setting_add_budget,listTipe);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.activity_setting_add_budget);


        database = FirebaseDatabase.getInstance().getReference();

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isEmpty(etnamaBudget.getText().toString()))
                    submitBudget(new model_addBudget(etnamaBudget.getText().toString(), etbudgetLimit.getText().toString()));
                else
                    Snackbar.make(findViewById(R.id.save_add_budget), "Data tidak boleh kosong", Snackbar.LENGTH_LONG).show();

                InputMethodManager imm = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(
                        etnamaBudget.getWindowToken(), 0);
            }
        });
    }

        private boolean isEmpty(String s) {
            // Cek apakah ada fields yang kosong, sebelum disubmit
            return TextUtils.isEmpty(s);
        }

        private void updateBarang(model_addBudget model) {
            // kodingan untuk next tutorial ya :p
        }

        private void submitBudget(model_addBudget model) {
            /**
             * Ini adalah kode yang digunakan untuk mengirimkan data ke Firebase Realtime Database
             * dan juga kita set onSuccessListener yang berisi kode yang akan dijalankan
             * ketika data berhasil ditambahkan
             */
//            if (spTipeTransaksi.getAdapter().toString() == "Income")
                database.child("budget").push().setValue(model).addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        etnamaBudget.setText("");
                        etbudgetLimit.setText("");
                        Snackbar.make(findViewById(R.id.save_add_budget), "Data berhasil ditambahkan", Snackbar.LENGTH_LONG).show();
                    }
                });
//            else
//                database.child("budgetOutcome").push().setValue(model).addOnSuccessListener(this, new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
//                        etnamaBudget.setText("");
//                        etbudgetLimit.setText("");
//                        spTipeTransaksi.setTag(R.array.tipe_transaksi);
//                        Snackbar.make(findViewById(R.id.save_add_budget), "Data berhasil ditambahkan", Snackbar.LENGTH_LONG).show();
//                    }
//                });
        }

        public static Intent getActIntent(Activity activity) {
            // kode untuk pengambilan Intent
            return new Intent(activity, setting_add_budget.class);
        }

//        Button btSpinner = (Button) findViewById(R.id.save_add_budget);
//        btSpinner.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(setting_edit_budget.getActIntent(setting_add_budget.this));
//            }
//        });

//        getIntent();
    }

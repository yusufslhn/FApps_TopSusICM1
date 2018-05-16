package com.example.asus.f_apps;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.ContactsContract;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Config;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class add_transaction extends AppCompatActivity {
//    private final ArrayList<transaction_model> Transaksi;
    private DatabaseReference mDatabase;
    private FirebaseDatabase firebaseDatabase;
    private EditText etIdr,etNotes;
    private Button submit;
    private ProgressDialog progressDialog;
    String userId;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);


        mDatabase = FirebaseDatabase.getInstance().getReference("transaksi");
        firebaseDatabase = FirebaseDatabase.getInstance();

        progressDialog = new ProgressDialog(this);

        etIdr = (EditText) findViewById(R.id.add_ammount);
        etNotes = (EditText) findViewById(R.id.add_notes);
        submit = (Button) findViewById(R.id.submit);

        progressDialog.setMessage("Submit...");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String midr = etIdr.getText().toString().trim();
                String mnote = etNotes.getText().toString().trim();

                if (TextUtils.isEmpty(midr)){
                    Toast.makeText(add_transaction.this, "Silahkan isi idr terlebih dahulu!", Toast.LENGTH_SHORT).show();
                }

                if (TextUtils.isEmpty(mnote)){
                    Toast.makeText(add_transaction.this, "Silahkan isi notes terlebih dahulu!", Toast.LENGTH_SHORT).show();
                }

                progressDialog.show();
                //save data to database firebase
                transaction_model transaction_model = new transaction_model(midr,mnote);
                String id = mDatabase.push().getKey();
                mDatabase.child(id).setValue(transaction_model);
                progressDialog.dismiss();
                startActivity(new Intent(add_transaction.this,tab_main.class));
            }

        });

    }

//    FirebaseDataListener listener;

//    public AdapterBarangRecyclerView(ArrayList<transaction_adapter
//            > transaksis, Context ctx){
//        /**
//         * Inisiasi data dan variabel yang akan digunakan
//         */
//        Transaksi = transaksis;
//        Context context = ctx;
//        listener = (transaction_adapter) ctx;
//    }

    public void addData(){


    }
    public static Intent getActIntent(Activity activity){
        return new Intent(activity, add_transaction.class);
    }

    private void updatedata(transaction_model transaksi) {
        /**
         * Baris kode yang digunakan untuk mengupdate data barang
         * yang sudah dimasukkan di Firebase Realtime Database
         */
        mDatabase.child("transaksi") //akses parent index, ibaratnya seperti nama tabel
                .child(transaksi.getNotes()) //select barang berdasarkan key
                .setValue(transaksi) //set value barang yang baru
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        /**
                         * Baris kode yang akan dipanggil apabila proses update barang sukses
                         */
                        Snackbar.make(findViewById(R.id.submit), "Data berhasil diupdatekan", Snackbar.LENGTH_LONG).setAction("Oke", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                finish();
                            }
                        }).show();
                    }
                });
    }
//    public void onDeleteData(transaction_model trannsaksi, final int position) {
//        /**
//         * Kode ini akan dipanggil ketika method onDeleteData
//         * dipanggil dari adapter lewat interface.
//         * Yang kemudian akan mendelete data di Firebase Realtime DB
//         * berdasarkan key barang.
//         * Jika sukses akan memunculkan Toast
//         */
//        if(mDatabase!=null){mDatabase.child("barang").child(trannsaksi.getKey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void aVoid) {
//                Toast.makeText(transaction_adapter.this,"success delete", Toast.LENGTH_LONG).show();
//            }
//        });
//
//        }
//    }

    public interface FirebaseDataListener{
        void onDeleteData(transaction_model transaksi, int position);
    }
}

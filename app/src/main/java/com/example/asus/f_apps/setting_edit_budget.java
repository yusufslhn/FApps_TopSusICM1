package com.example.asus.f_apps;

import android.app.Activity;
import android.content.Intent;
import android.renderscript.Sampler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

//import id.web.twoh.twohfirebase.adapter.AdapterBarangRecyclerView;
//import id.web.twoh.twohfirebase.model.Barang;

public class setting_edit_budget extends AppCompatActivity {

    private ImageButton plus_budget;

    private DatabaseReference database;
    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<model_addBudget> daftarBudget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_edit_budget);

        rvView = (RecyclerView) findViewById(R.id.recylerview_income);
        rvView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rvView.setLayoutManager(layoutManager);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null ){
            if (bundle.getString("some") != null ){
                Toast.makeText(getApplicationContext(), "data :" + bundle.getString("some"), Toast.LENGTH_SHORT).show();
            }
        }

        database = FirebaseDatabase.getInstance().getReference();
        database.child("budget").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                /**
                 * Saat ada data baru, masukkan datanya ke ArrayList
                 */
                daftarBudget = new ArrayList<>();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    /**
                     * Mapping data pada DataSnapshot ke dalam object Barang
                     * Dan juga menyimpan primary key pada object Barang
                     * untuk keperluan Edit dan Delete data
                     */
                    model_addBudget barang = noteDataSnapshot.getValue(model_addBudget.class);
                    barang.setKey(noteDataSnapshot.getKey());

                    /**
                     * Menambahkan object Barang yang sudah dimapping
                     * ke dalam ArrayList
                     */
                    daftarBudget.add(barang);
            }

                /**
                 * Inisialisasi adapter dan data barang dalam bentuk ArrayList
                 * dan mengeset Adapter ke dalam RecyclerView
                 */
                adapter = new budget_adapter(daftarBudget, setting_edit_budget.this);
                rvView.setAdapter(adapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                /**
                 * Kode ini akan dipanggil ketika ada error dan
                 * pengambilan data gagal dan memprint error nya
                 * ke LogCat
                 */
                System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
            }
        });
        plus_budget = (ImageButton) findViewById(R.id.tambah_budget);
        plus_budget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abis_addBudget();
            }
        });
    }
    public void abis_addBudget(){
        Intent in = new Intent(this, setting_add_budget.class);
        startActivity(in);
    }

    public static Intent getActIntent(Activity activity) {
        // kode untuk pengambilan Intent
        return new Intent(activity, setting_edit_budget.class);
    }
}

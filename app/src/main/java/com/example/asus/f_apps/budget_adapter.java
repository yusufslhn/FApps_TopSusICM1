package com.example.asus.f_apps;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

//import id.web.twoh.twohfirebase.R;
//import id.web.twoh.twohfirebase.model.Barang;
/**
 * Created by ASUS on 16/05/2018.
 */

public class budget_adapter extends RecyclerView.Adapter<budget_adapter.ViewHolder>{

    private ArrayList<model_addBudget> daftarBudget;
    private Context context;

    public budget_adapter(ArrayList<model_addBudget> daftarBudget, Context context) {
        this.daftarBudget = daftarBudget;
        this.context = context;
    }

    private String toRupiah(String nominal){
        String hasil = "";

        DecimalFormat toRupiah = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatAngka = new DecimalFormatSymbols();

        formatAngka.setCurrencySymbol("Rp. ");
        formatAngka.setMonetaryDecimalSeparator(',');
        formatAngka.setGroupingSeparator('.');
        toRupiah.setDecimalFormatSymbols(formatAngka);

        hasil = toRupiah.format(Double.valueOf(nominal));

        return hasil;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        /**
         * Inisiasi View
         * Di tutorial ini kita hanya menggunakan data String untuk tiap item
         * dan juga view nya hanyalah satu TextView
         */
        TextView namaBudget;
        TextView hargaBudget;
        Spinner sp_jenis;

        ViewHolder(View v) {
            super(v);
            namaBudget = (TextView) v.findViewById(R.id.nama_budget);
            hargaBudget = (TextView) v.findViewById(R.id.harga_budget);
//            sp_jenis = (Spinner) v.findViewById(R.id.spinner_transaction_type);
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /**
         *  Inisiasi ViewHolder
         */
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_cardview_income, parent, false);
        // mengeset ukuran view, margin, padding, dan parameter layout lainnya
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        /**
         *  Menampilkan data pada view
         */
        final String name = daftarBudget.get(position).getNamaBudget();
        final String limit = daftarBudget.get(position).getHargaBudget();
        holder.namaBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 *  Kodingan untuk tutorial Selanjutnya :p Read detail data
                 */
            }
        });
        holder.namaBudget.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                /**
                 *  Kodingan untuk tutorial Selanjutnya :p Delete dan update data
                 */
                return true;
            }
        });
        holder.namaBudget.setText(name);
        holder.hargaBudget.setText(toRupiah(limit));
    }

    @Override
    public int getItemCount() {
        /**
         * Mengembalikan jumlah item pada barang
         */
        return daftarBudget.size();
    }
}

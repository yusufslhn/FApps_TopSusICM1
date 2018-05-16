package com.example.asus.f_apps;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.provider.Telephony;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import static android.os.Build.ID;

/**
 * Created by ASUS on 11/05/2018.
 */

public class transaction_adapter extends RecyclerView.Adapter<transaction_adapter.MyHolderView>{
    private ArrayList<transaction_model>transaction_models = new ArrayList<>();
    private Context context;

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
        TextView tvTitle;

        ViewHolder(View v) {
            super(v);
            tvTitle = (TextView) v.findViewById(R.id.idr);
        }
    }


    public transaction_adapter(ArrayList<transaction_model> transaction_models){
        this.transaction_models = transaction_models;
    }

    public transaction_adapter(Context context){
        this.context = context;
    }

    @Override
    public transaction_adapter.MyHolderView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_cardview_transaction, parent, false);
        return new MyHolderView(view);
    }

    @Override
    public void onBindViewHolder(MyHolderView holder, final int position) {
        final String name = transaction_models.get(position).getNotes();
        final transaction_model transaction_model = transaction_models.get(position);
        holder.IDR.setText(toRupiah(transaction_model.getIDR()));
        holder.notes.setText(transaction_model.getNotes());

        holder.IDR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 *  Kodingan untuk tutorial Selanjutnya :p Read detail data
                 */
            }
        });
        holder.IDR.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                /**
                 *  Kodingan untuk tutorial Selanjutnya :p Delete dan update data
                 */
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.activity_update_delete_transaksi);
                dialog.setTitle("Pilih Aksi");
                dialog.show();

//                Button editButton = (Button) dialog.findViewById(R.id.edit_data);
                Button delButton = (Button) dialog.findViewById(R.id.delete_data);

                //apabila tombol edit diklik
//                editButton.setOnClickListener(
//                        new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                dialog.dismiss();
//                                context.startActivity(add_transaction.getActIntent((Activity) context).putExtra("data", transaction_models.get(position)));
//                            }
//                        }
//                );
                //apabila tombol delete diklik
//                delButton.setOnClickListener(
//                        new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                /**
//                                 *  Kodingan untuk tutorial Selanjutnya :p Delete data
//                                 */
//                                dialog.dismiss();
//                                add_transaction.FirebaseDataListener listener;
//                                listener.onDeleteData(transaction_models.get(position), position);
//                            }
//                        }
//                );
                return true;
            }
        });
        holder.notes.setText(name);

    }

    @Override
    public int getItemCount() {
        return (transaction_models == null) ? 0 : transaction_models.size();
    }



    class MyHolderView extends RecyclerView.ViewHolder{

        private TextView IDR, categories, notes, method;
        private View v;
        ArrayList<transaction_model> transaction_model = new ArrayList<>();



        public MyHolderView(View view) {
            super(view);

            IDR = (TextView) view.findViewById(R.id.jumlah_uang);
            notes = (TextView) view.findViewById(R.id.nama_transaksi);
            v = (View) view.findViewById(R.id.item_card);
        }
    }

    public void addData (transaction_model im){
        transaction_models.add(im);
        notifyDataSetChanged();
    }


}

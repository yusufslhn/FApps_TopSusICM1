package com.example.asus.f_apps;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link tab_transaction.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link tab_transaction#newInstance} factory method to
 * create an instance of this fragment.
 */
public class tab_transaction extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    String currentDateTimeString = DateFormat.getDateInstance().format(new Date());

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public TextView total;
    private transaction_adapter mAdapter;
    private RecyclerView recyclerView;
    private CardView cardView;
    private DatabaseReference myRef;
    private ArrayList<transaction_model> transactionModels = new ArrayList<>();

    private OnFragmentInteractionListener mListener;
    private TextView nTotal;

    public tab_transaction() {
        // Required empty public constructor
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment tab_transaction.
     */
    // TODO: Rename and change types and number of parameters
    public static tab_transaction newInstance(String param1, String param2) {
        tab_transaction fragment = new tab_transaction();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    private String toRupiah(String nominal){
        String hasil = "";

        DecimalFormat toRupiah = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatAngka = new DecimalFormatSymbols();

        formatAngka.setCurrencySymbol("");
        formatAngka.setMonetaryDecimalSeparator(',');
        formatAngka.setGroupingSeparator('.');
        toRupiah.setDecimalFormatSymbols(formatAngka);

        hasil = toRupiah.format(Double.valueOf(nominal));

        return hasil;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        TextView textview = (TextView) view.findViewById(R.id.tanggal);
        textview.setText(currentDateTimeString);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab_transaction, container, false);

        final TextView nTotal = (TextView) view.findViewById(R.id.uangnya);
        myRef = FirebaseDatabase.getInstance().getReference().child("budget");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int sum = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    Map<String,Object> map = (Map<String,Object>) ds.getValue();
                    Object price = map.get("hargaBudget");

                    int pValue = Integer.parseInt(String.valueOf(price));
                    sum += pValue;

                    nTotal.setText(toRupiah(String.valueOf(sum)));

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

//        myRef = FirebaseDatabase.getInstance().getReference().child("transaksi");
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                int sum = 0;
//                for (DataSnapshot ds : dataSnapshot.getChildren()){
//                    Map<String,Object> map = (Map<String,Object>) ds.getValue();
//                    Object price = map.get("idr");
//
//                    int pValue = Integer.parseInt(String.valueOf(price));
//                    sum -= pValue;
//
//                    nTotal.setText(toRupiah(String.valueOf(sum)+= nTotal.getText()));
//                }
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });


        ImageButton plus = (ImageButton) view.findViewById(R.id.plus);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(),add_transaction.class);
                startActivity(in);
            }
        });

        mAdapter = new transaction_adapter(getActivity());
        recyclerView = (RecyclerView) view.findViewById(R.id.recylerview_transaksi);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        cardView = (CardView) view.findViewById(R.id.item_card);

        // read database firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("transaksi");
        myRef.keepSynced(true);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                transactionModels  = new ArrayList<transaction_model>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    transaction_model value = dataSnapshot1.getValue(transaction_model.class);
                    transaction_model transaction = new transaction_model();
                    String mIdr = value.getIDR();
                    String mNote = value.getNotes();
                    transaction.setIDR(mIdr);
                    transaction.setNotes(mNote);
                    mAdapter.addData(transaction);
                }
                int sum = 0;
                for (DataSnapshot data : dataSnapshot.getChildren()){


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    /**
     * This interface must be implemented by activities that contain this5000
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}

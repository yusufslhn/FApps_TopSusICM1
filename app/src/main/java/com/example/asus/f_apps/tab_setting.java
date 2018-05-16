package com.example.asus.f_apps;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link tab_setting.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link tab_setting#newInstance} factory method to
 * create an instance of this fragment.
 */
public class tab_setting extends Fragment {

    private FirebaseAuth firebaseAuth;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment tab_setting.
     */
    // TODO: Rename and change types and number of parameters
    public static tab_setting newInstance(String param1, String param2) {
        tab_setting fragment = new tab_setting();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab_setting, container, false);

        ImageButton setting_1 = (ImageButton) view.findViewById(R.id.setting_1);
        setting_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(),setting_edit_profil.class);
//                in.putExtra("some", "some data");
                startActivity(in);
//                getActivity().startActivity(in);
            }
        });

        ImageButton setting_2 = (ImageButton) view.findViewById(R.id.setting_2);
        setting_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(),setting_edit_budget.class);
//                in.putExtra("some", "some data");
//                getActivity().startActivity(in);
                startActivity(in);
            }
        });

        ImageButton setting_3 = (ImageButton) view.findViewById(R.id.setting_3);
        setting_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(),setting_display.class);
//                in.putExtra("some", "some data");
//                getActivity().startActivity(in);
                startActivity(in);
            }
        });

        ImageButton setting_4 = (ImageButton) view.findViewById(R.id.setting_4);
        setting_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(),setting_notification.class);
//                in.putExtra("some", "some data");
//                getActivity().startActivity(in);
                startActivity(in);
            }
        });

        ImageButton setting_5 = (ImageButton) view.findViewById(R.id.setting_5);
        setting_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(),setting_about.class);
//                in.putExtra("some", "some data");
//                getActivity().startActivity(in);
                startActivity(in);
            }
        });

        ImageButton setting_6 = (ImageButton) view.findViewById(R.id.setting_6);
        setting_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Logout")
                        .setMessage("Are you sure logout?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseAuth.getInstance().signOut();
                                startActivity(new Intent(getContext(), Login.class));
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
//                Intent in = new Intent(getActivity(),Login.class);
//
//                startActivity(in);
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
     * This interface must be implemented by activities that contain this
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

package com.myownapps.yasser.mydictionary;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link My_entitiy.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link My_entitiy#newInstance} factory method to
 * create an instance of this fragment.
 */
public class My_entitiy extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
ListView entity_list;
   public ArrayAdapter arrayadapteres;
    private FragmentActivity mycontext;
    private OnFragmentInteractionListener mListener;
    DBconiction dBconiction;



    public My_entitiy() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment My_entitiy.
     */
    // TODO: Rename and change types and number of parameters
    public static My_entitiy newInstance(String param1, String param2) {
        My_entitiy fragment = new My_entitiy();
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
//*----------------------test

    Button create;
    EditText engword,arword;
    //*-----------------------

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview= inflater.inflate(R.layout.fragment_my_entitiy, container, false);
        entity_list=(ListView)rootview.findViewById(R.id.entetis_list_view_en);
        dBconiction=new DBconiction(getActivity());




       getdatafromdb();


        return rootview;
    }


    @Override
    public void onResume() {
        super.onResume();

    }

    public void getdatafromdb(){


   ArrayList<String> arr=dBconiction.geteng();
        arrayadapteres=new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,arr);
        entity_list.setAdapter(arrayadapteres);

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        mycontext=(FragmentActivity)context;

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

        void onFragmentInteraction(Uri uri);
    }
}

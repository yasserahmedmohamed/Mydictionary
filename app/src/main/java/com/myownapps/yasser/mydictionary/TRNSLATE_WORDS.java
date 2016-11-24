package com.myownapps.yasser.mydictionary;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.memetix.mst.language.Language;
import com.memetix.mst.speak.Speak;
import com.memetix.mst.translate.Translate;

import java.util.Locale;

import static com.myownapps.yasser.mydictionary.R.id.container;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TRNSLATE_WORDS.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TRNSLATE_WORDS#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TRNSLATE_WORDS extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public TRNSLATE_WORDS() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TRNSLATE_WORDS.
     */
    // TODO: Rename and change types and number of parameters
    public static TRNSLATE_WORDS newInstance(String param1, String param2) {
        TRNSLATE_WORDS fragment = new TRNSLATE_WORDS();
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
    private TextToSpeech tts;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootview= inflater.inflate(R.layout.fragment_trnslate__word, container, false);
        String text = ((EditText)rootview.findViewById(R.id.etUserText)).getText().toString();

//


        ((Button) rootview.findViewById(R.id.bTranslate)).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // TODO Auto-generated method stub
                class bgStuff extends AsyncTask<Void, Void, Void> {

                    String translatedText = "";
                    @Override
                    protected Void doInBackground(Void... params) {
                        // TODO Auto-generated method stub
                        try {
                            String text = ((EditText) rootview.findViewById(R.id.etUserText)).getText().toString();
                            // getreviw(text);
                            //               Toast.makeText(c,"", Toast.LENGTH_SHORT).show();
//Toast.makeText(MainActivity.this,"IS THIS WORD RELATED TO "+typ+" I THINK IT BELONG TO IT BY"+pers_int+"%",Toast.LENGTH_SHORT).show();
                            translatedText = translate(text);
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                            translatedText = e.toString();
                        }


                        return null;
                    }


                    @Override
                    protected void onPostExecute(Void result) {
                        // TODO Auto-generated method stub
                        ((TextView) rootview.findViewById(R.id.tvTranslatedText)).setText(translatedText);
                        super.onPostExecute(result);
                    }

                }

                new bgStuff().execute();
            }
        });






        return rootview;
    }



    public String translate(String text) throws Exception{


        // Set the Client ID / Client Secret once per JVM. It is set statically and applies to all services
        Translate.setClientId("dictionary_me"); //Change this
        Translate.setClientSecret("B8Tw6LMZiIswSFm7ublRlGiKzdY2x2r7PG+RPvsQEGo="); //change


        String translatedText = "";

        translatedText = Translate.execute(text, Language.ARABIC);

        return translatedText;
    }



    private void speakOut(String text) {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    public void add(View view) {

        EditText eng=(EditText) view.findViewById(R.id.etUserText);
        TextView ar=(TextView) view.findViewById(R.id.tvTranslatedText);
        DBconiction db=new DBconiction(getActivity());
        db.add_words(eng.getText().toString(),ar.getText().toString());
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

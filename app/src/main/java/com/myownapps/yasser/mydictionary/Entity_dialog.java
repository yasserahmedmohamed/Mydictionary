package com.myownapps.yasser.mydictionary;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Yasser on 11/19/2016.
 */

public class Entity_dialog extends DialogFragment implements View.OnClickListener {
Button create;
    EditText engword,arword;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       setCancelable(true);
        View view= inflater.inflate(R.layout.dilog_fornewentity,null);
        create=(Button)view.findViewById(R.id.button_createentity);
        engword=(EditText)view.findViewById(R.id.edittext_englishword);
        arword=(EditText)view.findViewById(R.id.edittext_arabicword);
create.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.button_createentity){
            String eng=engword.getText().toString();
            String arb=arword.getText().toString();
            DBconiction db =new DBconiction(getActivity());
            db.add_words(eng,arb);


dismiss();
            Toast.makeText(getActivity(),"WORD has created ",Toast.LENGTH_SHORT).show();
        }
    }



}
/*
*   @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("dialog_fire_missiles")
                .setPositiveButton("fire", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

*
* */
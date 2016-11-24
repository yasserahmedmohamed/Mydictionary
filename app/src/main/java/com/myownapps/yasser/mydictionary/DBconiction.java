package com.myownapps.yasser.mydictionary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Yasser on 4/23/2016.
 */
public class DBconiction extends SQLiteOpenHelper {
    static public final int version=1;
    static public final String DBname="my_all_words.db";
    public DBconiction(Context context){
        super(context,DBname,null,version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
db.execSQL("create table IF NOT EXISTS my_words(english_word TEXT primary key,arabic_word TEXT) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if EXISTS my_words");
        onCreate(db);

    }
    public void add_words(String eng, String ar){


        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues conval=new ContentValues();
        conval.put("english_word",eng);
        conval.put("arabic_word", ar);
        db.insert("my_words", null, conval);

    }
    public ArrayList geteng()
    {

        ArrayList arr_eng=new ArrayList();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res=db.rawQuery("select * from my_words",null);
        res.moveToFirst();
        while (res.isAfterLast()==false){
            arr_eng.add(res.getString(res.getColumnIndex("english_word"))+"     "+res.getString(res.getColumnIndex("arabic_word")));
            res.moveToNext();
        }
return arr_eng;
    }




    public ArrayList searc(String s)
    {

        ArrayList arr_eng=new ArrayList();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res=db.rawQuery("select * from my_words where english_word like '"+s+"%'",null);
        res.moveToFirst();
        while (res.isAfterLast()==false){
            arr_eng.add(res.getString(res.getColumnIndex("english_word"))+":  "+res.getString(res.getColumnIndex("arabic_word")));
            res.moveToNext();
        }
        return arr_eng;
    }



    public void delet(String eng){
SQLiteDatabase db=this.getWritableDatabase();
      //  db.execSQL("delete from my_words where english_word ="+eng);
db.delete("my_words","english_word=?",null);
    }



}

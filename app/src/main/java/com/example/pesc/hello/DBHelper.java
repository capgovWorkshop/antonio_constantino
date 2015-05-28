package com.example.pesc.hello;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by PESC on 28/05/2015.
 */
public class DBHelper extends SQLiteOpenHelper {

    //version number to upgrade database version
    //each time if you Add, Edit table, you need to change the
    //version number.
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "bancoDados.db";

    SQLiteDatabase db;

    public DBHelper(Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        db = getWritableDatabase();
    }

    public SQLiteDatabase getDb(){
        return db;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("Database", "onCreate");
        String CREATE_TABLE_PESSOA = "CREATE TABLE " + Pessoa.TABLE  + "("
                + Pessoa.NOME + " TEXT, "
                + Pessoa.SENHA + " TEXT, "
                + Pessoa.EMAIL + " TEXT )";


        db.execSQL(CREATE_TABLE_PESSOA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.d("Database" , "OldVersion: " + oldVersion);
        Log.d("Database" , "NewVersion: " + newVersion);

        // Drop older table if existed, all data will be gone!!!
        //db.execSQL("DROP TABLE IF EXISTS " + Pessoa.TABLE);

        switch (newVersion) {
            case 2:
                db.execSQL("ALTER TABLE " + Pessoa.TABLE + " ADD COLUMN profissao TEXT;");
                break;
            case 3:
                db.execSQL("ALTER TABLE " + Pessoa.TABLE + " ADD COLUMN profissao2 TEXT;");
                break;
        }

    }
}

package com.example.rlindoso.rlindosotreinamento.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.rlindoso.rlindosotreinamento.metadata.AppTreinamentoMetadata;
import com.example.rlindoso.rlindosotreinamento.metadata.AutorMetadata;
import com.example.rlindoso.rlindosotreinamento.metadata.LivroMetadata;

/**
 * Created by rlindoso on 01/02/2017.
 */

public class SqliteHelper extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "treinamento.db";
    private static final int VERSAO_DB = 3;

    public SqliteHelper(Context context) {
        super(context, NOME_BANCO, null, VERSAO_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(AppTreinamentoMetadata.SQL_CREATE);
        db.execSQL(AutorMetadata.SQL_CREATE);
        db.execSQL(LivroMetadata.SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(AutorMetadata.SQL_CREATE);
        db.execSQL(LivroMetadata.SQL_CREATE);
    }
}

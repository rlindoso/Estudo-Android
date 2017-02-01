package com.example.rlindoso.rlindosotreinamento.metadata;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;

import com.example.rlindoso.rlindosotreinamento.model.AppTreinamento;

/**
 * Created by rlindoso on 01/02/2017.
 */

public class AppTreinamentoMetadata {
    public static final String TABLE_NAME = "app_treinamento";
    public static final String ID = BaseColumns._ID;
    public static final String NOME = "nome";
    private static final String AULA = "aula";
    private static final String OBJETIVO = "objetivo";
    private static final String URL = "url";

    public static final String SQL_CREATE = String.format("CREATE TABLE %s (" +
                    "    %s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "    %s TEXT(30), " +
                    "    %s INTEGER, " +
                    "    %s text(30), " +
                    "    %s text(50));",
            TABLE_NAME,
            ID,
            NOME,
            AULA,
            OBJETIVO,
            URL);

    public static ContentValues toContentValues(AppTreinamento treinamento) {
        ContentValues cv = new ContentValues();
        cv.put(NOME, treinamento.getNome());
        cv.put(AULA, treinamento.getAula());
        cv.put(OBJETIVO, treinamento.getObjetivo());
        cv.put(URL, treinamento.getImageUrl());

        return cv;
    }

    public static AppTreinamento fromCursor(Cursor cursor) {
        int id = getIntValue(cursor, ID);
        String nome = cursor.getString(cursor.getColumnIndex(NOME));
        int aula = cursor.getInt(cursor.getColumnIndex(AULA));
        String objetivo = cursor.getString(cursor.getColumnIndex(OBJETIVO));
        String url = cursor.getString(cursor.getColumnIndex(URL));

        AppTreinamento appTreinamento = new AppTreinamento(nome, aula, objetivo);
        appTreinamento.setId(id);
        appTreinamento.setImageUrl(url);

        return appTreinamento;
    }

    private static int getIntValue(Cursor cursor, String column) {
        try {
            return cursor.getInt(cursor.getColumnIndex(column));
        } catch (Exception e) {
            return 0;
        }
    }
}

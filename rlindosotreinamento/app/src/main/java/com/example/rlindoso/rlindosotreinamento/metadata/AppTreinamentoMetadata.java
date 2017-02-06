package com.example.rlindoso.rlindosotreinamento.metadata;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;

import com.example.rlindoso.rlindosotreinamento.model.AppTreinamento;

/**
 * Created by rlindoso on 01/02/2017.
 */

public class AppTreinamentoMetadata extends BaseMetadata<AppTreinamento> {
    public static final String TABLE_NAME = "app_treinamento";
    public static final String ID = BaseColumns._ID;
    public static final String NOME = "nome";
    private static final String AULA = "aula";
    private static final String OBJETIVO = "objetivo";
    private static final String URL = "url";

    private static AppTreinamentoMetadata instance;

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

    public static AppTreinamentoMetadata getInstance() {
        if (instance == null ) {
            instance = new AppTreinamentoMetadata();
        }

        return instance;
    }

    @Override
    public AppTreinamento fromCursor(Cursor cursor) {
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

    @Override
    public String getOrderColumn() {
        return NOME;
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public Object getColumnId() {
        return ID;
    }

    @Override
    public ContentValues toContentValues(AppTreinamento model) {
        ContentValues cv = new ContentValues();
        cv.put(NOME, model.getNome());
        cv.put(AULA, model.getAula());
        cv.put(OBJETIVO, model.getObjetivo());
        cv.put(URL, model.getImageUrl());

        return cv;
    }
}

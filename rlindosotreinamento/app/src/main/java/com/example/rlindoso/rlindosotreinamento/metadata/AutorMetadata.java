package com.example.rlindoso.rlindosotreinamento.metadata;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;

import com.example.rlindoso.rlindosotreinamento.model.Autor;
import com.example.rlindoso.rlindosotreinamento.utils.DateUtils;

import org.joda.time.DateTime;


/**
 * Created by rlindoso on 01/02/2017.
 */

public class AutorMetadata {
    public static final String TABLE_NAME = "autor";
    public static final String ID = BaseColumns._ID;
    public static final String NOME = "nome";
    private static final String DATA_NASCIMENTO = "dataNascimeto";

    public static final String SQL_CREATE = String.format("CREATE TABLE %s(\n" +
            "    %s INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE, \n" +
            "    %s texT(30), \n" +
            "    %s DATE);\n",
            TABLE_NAME,
            ID,
            NOME,
            DATA_NASCIMENTO);

    public static ContentValues toContentValues(Autor autor) {
        ContentValues cv = new ContentValues();
        cv.put(NOME, autor.getNome());
        cv.put(DATA_NASCIMENTO, DateUtils.dateToStr(autor.getDataNascimento()));

        return cv;
    }

    public static Autor fromCursor(Cursor cursor) {
        int id = getIntValue(cursor, ID);
        String nome = cursor.getString(cursor.getColumnIndex(NOME));
        DateTime dataNascimento = DateUtils.strToDate(cursor.getString(cursor.getColumnIndex(DATA_NASCIMENTO)));

        Autor autor = new Autor(id, nome, dataNascimento);

        return autor;
    }

    private static int getIntValue(Cursor cursor, String column) {
        try {
            return cursor.getInt(cursor.getColumnIndex(column));
        } catch (Exception e) {
            return 0;
        }
    }
}

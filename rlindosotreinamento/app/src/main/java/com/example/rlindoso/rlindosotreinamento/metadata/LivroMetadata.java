package com.example.rlindoso.rlindosotreinamento.metadata;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.provider.BaseColumns;

import com.example.rlindoso.rlindosotreinamento.model.Autor;
import com.example.rlindoso.rlindosotreinamento.model.Livro;
import com.example.rlindoso.rlindosotreinamento.repository.AutorRepository;

/**
 * Created by rlindoso on 02/02/2017.
 */

public class LivroMetadata {
    public static final String TABLE_NAME = "livro";
    public static final String ID = BaseColumns._ID;
    public static final String ID_AUTOR ="_id_autor";
    public static final String TITULO = "titulo";
    public static final String SINOPSE = "sinopse";

    public static final String SQL_CREATE = String.format("CREATE TABLE %s(\n" +
                    "    %s INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "    %s TEXT(30),\n" +
                    "    %s TEXT(100),\n" +
                    "    %s INTEGER REFERENCES autor([_id]));",
            TABLE_NAME,
            ID,
            TITULO,
            SINOPSE,
            ID_AUTOR);

    public static ContentValues toContentValues(Livro livro) {
        ContentValues cv = new ContentValues();
        cv.put(TITULO, livro.getTitulo());
        cv.put(SINOPSE, livro.getSinopse());
        cv.put(ID_AUTOR, livro.getAutor().getId());

        return cv;
    }

    public static Livro fromCursor(Cursor cursor, Context context) {
        int id = getIntValue(cursor, ID);
        String titulo = cursor.getString(cursor.getColumnIndex(TITULO));
        String sinopse = cursor.getString(cursor.getColumnIndex(SINOPSE));
        int idAutor = getIntValue(cursor, ID_AUTOR);

        AutorRepository repo = new AutorRepository(context);
        Autor autor = repo.getAutor(idAutor);

        Livro livro = new Livro(id, sinopse, titulo, autor);

        return livro;
    }

    private static int getIntValue(Cursor cursor, String column) {
        try {
            return cursor.getInt(cursor.getColumnIndex(column));
        } catch (Exception e) {
            return 0;
        }
    }
}


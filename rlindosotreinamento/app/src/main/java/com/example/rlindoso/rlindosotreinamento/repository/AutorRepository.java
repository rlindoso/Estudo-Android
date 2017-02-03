package com.example.rlindoso.rlindosotreinamento.repository;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.rlindoso.rlindosotreinamento.metadata.AutorMetadata;
import com.example.rlindoso.rlindosotreinamento.model.Autor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rlindoso on 01/02/2017.
 */

public class AutorRepository {
    private SqliteHelper helper;
    private static final String TAG = AutorRepository.class.getSimpleName();

    public AutorRepository(Context context) {
        helper = new SqliteHelper(context);
    }

    public void insert(Autor autor) {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.beginTransaction();

        try {
            db.insert(AutorMetadata.TABLE_NAME, null, AutorMetadata.toContentValues(autor));
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e(TAG, "insert: ", e);
        } finally {
            db.endTransaction();
        }
    }

    public void update(Autor autor) {
        String where = String.format("%s = ?", AutorMetadata.ID);
        String[] args = new String[]{String.valueOf(autor.getId())};

        SQLiteDatabase db = helper.getWritableDatabase();
        db.beginTransaction();

        try {
            db.update(AutorMetadata.TABLE_NAME, AutorMetadata.toContentValues(autor), where, args);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e(TAG, "update: ", e);
        } finally {
            db.endTransaction();
        }
    }

    public List<Autor> getAll() {
        List<Autor> result = new ArrayList<>();
        SQLiteDatabase db = helper.getWritableDatabase();

        Cursor cursor = db.query(AutorMetadata.TABLE_NAME, null, null, null, null, null, AutorMetadata.NOME);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                result.add(AutorMetadata.fromCursor(cursor));
                cursor.moveToNext();
            }
        } catch (Exception e) {
            Log.e(TAG, "getAll: ", e);
        } finally {
            cursor.close();
        }

        return result;
    }

    public Autor getAutor(int id) {
        SQLiteDatabase db = helper.getWritableDatabase();

        String where = String.format("%s = ?", AutorMetadata.ID);
        String[] args = new String[]{String.valueOf(id)};

        Cursor cursor = db.query(AutorMetadata.TABLE_NAME, null, where, args, null, null, AutorMetadata.NOME);

        cursor.moveToFirst();
        Autor autor = AutorMetadata.fromCursor(cursor);

        return autor;
    }

    public void delete(int id) {
        String where = String.format("%s = ?", AutorMetadata.ID);
        String[] args = new String[]{String.valueOf(id)};

        SQLiteDatabase db = helper.getWritableDatabase();
        db.beginTransaction();

        try {
            db.delete(AutorMetadata.TABLE_NAME, where, args);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e(TAG, "delete: ", e);
        } finally {
            db.endTransaction();
        }
    }
}

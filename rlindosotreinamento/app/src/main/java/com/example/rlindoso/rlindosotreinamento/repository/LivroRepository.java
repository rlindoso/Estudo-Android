package com.example.rlindoso.rlindosotreinamento.repository;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.rlindoso.rlindosotreinamento.metadata.LivroMetadata;
import com.example.rlindoso.rlindosotreinamento.model.Livro;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rlindoso on 02/02/2017.
 */

public class LivroRepository {
    private static final String TAG = AppTreinamentoRepository.class.getSimpleName();
    private SqliteHelper helper;

    public LivroRepository(Context context) {
        helper = new SqliteHelper(context);
    }
    public void insert(Livro livro) {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.beginTransaction();

        try {
            db.insert(LivroMetadata.TABLE_NAME, null, LivroMetadata.toContentValues(livro));
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e(TAG, "insert: ", e);
        } finally {
            db.endTransaction();
        }
    }

    public void update(Livro livro) {
        String where = String.format("%s = ?", LivroMetadata.ID);
        String[] args = new String[]{String.valueOf(livro.getId())};

        SQLiteDatabase db = helper.getWritableDatabase();
        db.beginTransaction();

        try {
            db.update(LivroMetadata.TABLE_NAME, LivroMetadata.toContentValues(livro), where, args);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e(TAG, "update: ", e);
        } finally {
            db.endTransaction();
        }
    }

    public List<Livro> getAll() {
        List<Livro> result = new ArrayList<>();
        SQLiteDatabase db = helper.getWritableDatabase();

        Cursor cursor = db.query(LivroMetadata.TABLE_NAME, null, null, null, null, null, LivroMetadata.TITULO);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                result.add(LivroMetadata.fromCursor(cursor));
                cursor.moveToNext();
            }
        } catch (Exception e) {
            Log.e(TAG, "getAll: ", e);
        } finally {
            cursor.close();
        }

        return result;
    }

    public void delete(int id) {
        String where = String.format("%s = ?", LivroMetadata.ID);
        String[] args = new String[]{String.valueOf(id)};

        SQLiteDatabase db = helper.getWritableDatabase();
        db.beginTransaction();

        try {
            db.delete(LivroMetadata.TABLE_NAME, where, args);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e(TAG, "delete: ", e);
        } finally {
            db.endTransaction();
        }
    }

}

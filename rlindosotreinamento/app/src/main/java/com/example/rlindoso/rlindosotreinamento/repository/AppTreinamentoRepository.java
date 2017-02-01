package com.example.rlindoso.rlindosotreinamento.repository;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.rlindoso.rlindosotreinamento.metadata.AppTreinamentoMetadata;
import com.example.rlindoso.rlindosotreinamento.model.AppTreinamento;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rlindoso on 01/02/2017.
 */

public class AppTreinamentoRepository {

    private static final String TAG = AppTreinamentoRepository.class.getSimpleName();
    private SqliteHelper helper;

    public AppTreinamentoRepository(Context context) {
        helper = new SqliteHelper(context);
    }

    public void insert(AppTreinamento treinamento) {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.beginTransaction();

        try {
            db.insert(AppTreinamentoMetadata.TABLE_NAME, null, AppTreinamentoMetadata.toContentValues(treinamento));
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e(TAG, "insert: ", e);
        } finally {
            db.endTransaction();
        }
    }

    public void update(AppTreinamento treinamento) {
        String where = String.format("%s = ?", AppTreinamentoMetadata.ID);
        String[] args = new String[]{String.valueOf(treinamento.getId())};

        SQLiteDatabase db = helper.getWritableDatabase();
        db.beginTransaction();

        try {
            db.update(AppTreinamentoMetadata.TABLE_NAME, AppTreinamentoMetadata.toContentValues(treinamento), where, args);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e(TAG, "update: ", e);
        } finally {
            db.endTransaction();
        }
    }

    public List<AppTreinamento> getAll() {
        List<AppTreinamento> result = new ArrayList<>();
        SQLiteDatabase db = helper.getWritableDatabase();

        Cursor cursor = db.query(AppTreinamentoMetadata.TABLE_NAME, null, null, null, null, null, AppTreinamentoMetadata.NOME);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                result.add(AppTreinamentoMetadata.fromCursor(cursor));
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
        String where = String.format("%s = ?", AppTreinamentoMetadata.ID);
        String[] args = new String[]{String.valueOf(id)};

        SQLiteDatabase db = helper.getWritableDatabase();
        db.beginTransaction();

        try {
            db.delete(AppTreinamentoMetadata.TABLE_NAME, where, args);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e(TAG, "delete: ", e);
        } finally {
            db.endTransaction();
        }
    }
}

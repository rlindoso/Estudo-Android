package com.example.rlindoso.rlindosotreinamento.repository;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.rlindoso.rlindosotreinamento.metadata.BaseMetadata;
import com.example.rlindoso.rlindosotreinamento.model.BaseModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rlindoso on 06/02/2017.
 * Base repository
 */

public abstract class BaseRepository<T extends BaseModel> {
    private static final String TAG = BaseRepository.class.getSimpleName();
    private SqliteHelper helper;
    private Context context;

    BaseRepository(Context context) {
        this.helper = new SqliteHelper(context);
        this.context = context;
    }

    public void save(T model) {
        if (model.getId() > 0) {
            update(model);
        } else {
            insert(model);
        }
    }

    private void insert(T model) {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.beginTransaction();

        try {
            db.insert(getMetadata().getTableName(), null, getMetadata().toContentValues(model));
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e(TAG, "insert: ", e);
        } finally {
            db.endTransaction();
        }
    }

    private void update(T model) {
        String where = String.format("%s = ?", getMetadata().getColumnId());
        String[] args = new String[]{String.valueOf(model.getId())};

        SQLiteDatabase db = helper.getWritableDatabase();
        db.beginTransaction();

        try {
            db.update(getMetadata().getTableName(), getMetadata().toContentValues(model), where, args);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e(TAG, "update: ", e);
        } finally {
            db.endTransaction();
        }
    }

    public void delete(int id) {
        String where = String.format("%s = ?", getMetadata().getColumnId());
        String[] args = new String[]{String.valueOf(id)};

        SQLiteDatabase db = helper.getWritableDatabase();
        db.beginTransaction();

        try {
            db.delete(getMetadata().getTableName(), where, args);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e(TAG, "delete: ", e);
        } finally {
            db.endTransaction();
        }
    }

    public T getById(int id) {
        T result = null;
        SQLiteDatabase db = helper.getWritableDatabase();

        String where = String.format("%s = ?", getMetadata().getColumnId());
        String[] args = new String[]{String.valueOf(id)};

        Cursor cursor = db.query(getMetadata().getTableName(), null, where, args, null, null, null);
        try {
            cursor.moveToFirst();
            result = getMetadata().fromCursor(cursor);
        } catch (Exception e) {
            Log.e(TAG, "getById: ", e);
        } finally {
            cursor.close();
        }

        return result;
    }

    public List<T> getAll() {
        List<T> result = new ArrayList<>();
        SQLiteDatabase db = helper.getWritableDatabase();

        Cursor cursor = db.query(getMetadata().getTableName(), null, null, null, null, null, getMetadata().getOrderColumn());
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                result.add(getMetadata().fromCursor(cursor));
                cursor.moveToNext();
            }
        } catch (Exception e) {
            Log.e(TAG, "getAll: ", e);
        } finally {
            cursor.close();
        }

        return result;
    }

    protected abstract BaseMetadata<T> getMetadata();

    public Context getContext() {
        return context;
    }
}

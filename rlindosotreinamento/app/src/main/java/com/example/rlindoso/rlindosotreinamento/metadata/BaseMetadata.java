package com.example.rlindoso.rlindosotreinamento.metadata;

import android.database.Cursor;

import com.example.rlindoso.rlindosotreinamento.model.BaseModel;

/**
 * Created by rlindoso on 06/02/2017.
 */

public abstract class BaseMetadata<T extends BaseModel> implements IBaseMetadata<T> {
    protected int getIntValue(Cursor cursor, String column) {
        try {
            return cursor.getInt(cursor.getColumnIndex(column));
        } catch (Exception e) {
            return 0;
        }
    }
}

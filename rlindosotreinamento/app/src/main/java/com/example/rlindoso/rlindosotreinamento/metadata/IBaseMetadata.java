package com.example.rlindoso.rlindosotreinamento.metadata;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.rlindoso.rlindosotreinamento.model.BaseModel;

/**
 * Created by rlindoso on 06/02/2017.
 */

public interface IBaseMetadata<T extends BaseModel> {
    T fromCursor(Cursor cursor);

    String getOrderColumn();

    String getTableName();

    Object getColumnId();

    ContentValues toContentValues(T model);
}

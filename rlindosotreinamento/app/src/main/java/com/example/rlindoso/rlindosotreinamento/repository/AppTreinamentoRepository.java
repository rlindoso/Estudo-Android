package com.example.rlindoso.rlindosotreinamento.repository;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.rlindoso.rlindosotreinamento.metadata.AppTreinamentoMetadata;
import com.example.rlindoso.rlindosotreinamento.metadata.BaseMetadata;
import com.example.rlindoso.rlindosotreinamento.model.AppTreinamento;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rlindoso on 01/02/2017.
 */

public class AppTreinamentoRepository extends BaseRepository<AppTreinamento> {

    public AppTreinamentoRepository(Context context) {
        super(context);
    }

    @Override
    protected BaseMetadata<AppTreinamento> getMetadata() {
        return AppTreinamentoMetadata.getInstance();
    }
}

package com.example.rlindoso.rlindosotreinamento.repository;

import android.content.Context;

import com.example.rlindoso.rlindosotreinamento.metadata.AutorMetadata;
import com.example.rlindoso.rlindosotreinamento.metadata.BaseMetadata;
import com.example.rlindoso.rlindosotreinamento.model.Autor;

/**
 * Created by rlindoso on 01/02/2017.
 */

public class AutorRepository extends BaseRepository<Autor> {


    public AutorRepository(Context context) {
        super(context);
    }

    @Override
    protected BaseMetadata<Autor> getMetadata() {
        return AutorMetadata.getInstance();
    }
}

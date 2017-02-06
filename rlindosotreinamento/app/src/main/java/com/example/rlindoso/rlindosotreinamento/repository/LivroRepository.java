package com.example.rlindoso.rlindosotreinamento.repository;

import android.content.Context;

import com.example.rlindoso.rlindosotreinamento.metadata.BaseMetadata;
import com.example.rlindoso.rlindosotreinamento.metadata.LivroMetadata;
import com.example.rlindoso.rlindosotreinamento.model.Livro;

/**
 * Created by rlindoso on 02/02/2017.
 */

public class LivroRepository extends BaseRepository<Livro> {

    public LivroRepository(Context context) {
        super(context);
    }

    @Override
    protected BaseMetadata<Livro> getMetadata() {
        return LivroMetadata.getInstance();
    }
}

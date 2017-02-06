package com.example.rlindoso.rlindosotreinamento.model;

import org.joda.time.DateTime;

import java.io.Serializable;

/**
 * Created by rlindoso on 01/02/2017.
 */

public class Autor extends BaseModel implements Serializable {
    public static final String EXTRA = "Autor";
    public static final String EXTRA_COPY = "CopiarAutor";
    private String nome;
    private DateTime dataNascimento;

    public Autor(int id, String nome, DateTime dataNascimento) {
        this(id);
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public Autor(int id) {
        setId(id);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public DateTime getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(DateTime dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public boolean equals(Object obj) {
        if ((obj != null) && (obj instanceof Autor)) {
            Autor objRef = (Autor) obj;
            return objRef.getNome().equals(this.getNome());
        }

        return false;
    }
}

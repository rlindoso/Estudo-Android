package com.example.rlindoso.rlindosotreinamento.model;

import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by rlindoso on 01/02/2017.
 */

public class Autor implements Serializable {
    public static final String EXTRA = "Autor";
    public static final String EXTRA_COPY = "CopiarAutor";
    private int id;
    private String nome;
    private String dataNascimento;

    public Autor(int id, String nome, String dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public Autor(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
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

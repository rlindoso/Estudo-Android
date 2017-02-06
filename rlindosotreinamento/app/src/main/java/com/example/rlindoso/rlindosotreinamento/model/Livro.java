package com.example.rlindoso.rlindosotreinamento.model;

import java.io.Serializable;

/**
 * Created by rlindoso on 02/02/2017.
 */

public class Livro extends BaseModel implements Serializable {
    public static final String EXTRA = "livro";
    public static final String EXTRA_COPY = "livroCopy";
    private String sinopse;
    private String titulo;
    private Autor autor;

    public Livro(int id, String sinopse, String titulo, Autor autor) {
        this(id);
        this.sinopse = sinopse;
        this.titulo = titulo;
        this.autor = autor;
    }

    public Livro(int id) {
        setId(id);
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public boolean equals(Object obj) {
        if ((obj != null) && (obj instanceof Livro)) {
            Livro objRef = (Livro) obj;
            return ((objRef.getTitulo().equals(this.getTitulo())) && ((objRef.getAutor().equals(this.getAutor()))));
        }

        return false;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
}

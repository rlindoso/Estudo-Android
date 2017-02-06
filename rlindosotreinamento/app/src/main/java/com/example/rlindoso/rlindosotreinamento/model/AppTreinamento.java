package com.example.rlindoso.rlindosotreinamento.model;

import java.io.Serializable;

/**
 * Created by rlindoso on 26/01/2017.
 */

public class AppTreinamento extends BaseModel implements Serializable {
    public static final String EXTRA = "AppTreinamento";
    public static final String EXTRA_COPY = "copiarApp";

    private String nome;
    private int aula;
    private String objetivo;
    private String imageUrl;

    public AppTreinamento(String nome, int aula, String objetivo) {
        this.nome = nome;
        this.aula = aula;
        this.objetivo = objetivo;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAula() {
        return aula;
    }

    public void setAula(int aula) {
        this.aula = aula;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    @Override
    public String toString() {
        return this.getNome();
    }

    @Override
    public boolean equals(Object obj) {
        if ((obj != null) && (obj instanceof AppTreinamento)) {
            AppTreinamento objRef = (AppTreinamento) obj;
            return objRef.getNome().equals(this.getNome());
        }

        return false;
    }
}

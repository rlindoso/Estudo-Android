package sample.treinamento.model;

import java.io.Serializable;

/**
 * Created by palves on 25/01/2017.
 */

public class Pessoa implements Serializable {
    private String nome;

    public Pessoa(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

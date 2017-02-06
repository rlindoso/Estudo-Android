package com.example.rlindoso.rlindosotreinamento.model;

import java.io.Serializable;

/**
 * Created by rlindoso on 06/02/2017.
 */

public class BaseModel implements Serializable {
    protected int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

package com.example.rlindoso.rlindosotreinamento.model;

import java.io.Serializable;

/**
 * Created by rlindoso on 25/01/2017.
 */

public class combustiveis extends BaseModel implements Serializable {
    private double ValGas;
    private double ValAlc;

    public double getValGas() {
        return ValGas;
    }

    public void setValGas(double valGas) {
        ValGas = valGas;
    }

    public double getValAlc() {
        return ValAlc;
    }

    public void setValAlc(double valAlc) {
        ValAlc = valAlc;
    }
}


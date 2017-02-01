package com.example.rlindoso.rlindosotreinamento.utils;

/**
 * Created by rlindoso on 25/01/2017.
 */

public class NumberUtils {
    public static double strToDlouble(String str) {
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }
}

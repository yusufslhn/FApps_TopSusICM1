package com.example.asus.f_apps;

import android.widget.Spinner;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

/**
 * Created by ASUS on 16/05/2018.
 */

public class model_addBudget implements Serializable{

    private String tipeBudget;
    private String namaBudget;
    private String hargaBudget;
    private String key;

    private static final model_addBudget ourInstance = new model_addBudget();

    public static model_addBudget getInstance() {
        return ourInstance;
    }

    private model_addBudget() {
    }


    public model_addBudget(String namaBudget, String hargaBudget) {
//        this.tipeBudget = tipeBudget;
        this.namaBudget = namaBudget;
        this.hargaBudget = hargaBudget;
    }

    public String getHargaBudget() {
        return hargaBudget;
    }

    public void setHargaBudget(String hargaBudget) {
        this.hargaBudget = hargaBudget;
    }

    public String getTipeBudget() {
        return tipeBudget;

    }

    public void setTipeBudget(String tipeBudget) {
        this.tipeBudget = tipeBudget;
    }

    public String getNamaBudget() {
        return namaBudget;
    }

    public void setNamaBudget(String namaBudget) {
        this.namaBudget = namaBudget;
    }

    public static model_addBudget getOurInstance() {
        return ourInstance;
    }
    @Override
    public String toString() {
        return " "+namaBudget+"\n" +
                " "+hargaBudget;
    }

    public model_addBudget(String tipeBudget, Spinner namaBudget){
        tipeBudget = tipeBudget;
        namaBudget = namaBudget;
    }
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public static void add(model_addBudget barang) {
    }
}

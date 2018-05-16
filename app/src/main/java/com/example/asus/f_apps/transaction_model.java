package com.example.asus.f_apps;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ASUS on 11/05/2018.
 */

public class transaction_model implements Parcelable{

    public String idr;
    public String categories;
    public String method;
    public String notes;
    private String key;


    public transaction_model(String idr, String notes) {
        this.idr = idr;
        this.notes = notes;
    }


    protected transaction_model(Parcel in) {
        idr = in.readString();
        notes = in.readString();
    }

    public transaction_model() {

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idr);
        dest.writeString(notes);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<transaction_model> CREATOR = new Creator<transaction_model>() {
        @Override
        public transaction_model createFromParcel(Parcel in) {
            return new transaction_model(in);
        }

        @Override
        public transaction_model[] newArray(int size) {
            return new transaction_model[size];
        }
    };

    public String getIDR() {
        return idr;
    }

    public void setIDR(String idr) {
        this.idr = idr;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getKey() {
        return key;
    }
}

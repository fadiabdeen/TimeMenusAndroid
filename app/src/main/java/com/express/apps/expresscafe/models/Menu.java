package com.express.apps.expresscafe.models;

import android.webkit.HttpAuthHandler;

import java.util.HashMap;

/**
 * Created by fabdin on 8/2/2016.
 */
public class Menu {
    String note;
    //    List<ItemWithKey> items;
    private HashMap<String, Item> items;
    String date;

    public Menu(){

    }


//    Object items;


    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

//    public List<ItemWithKey> getItems() {
//        return items;
//    }
//
//    public void setItems(List<ItemWithKey> items) {
//        this.items = items;
//    }


    public HashMap<String, Item> getItems() {
        return items;
    }


    public void setItems(HashMap<String, Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return this.date + " : " + getNote();
    }
}

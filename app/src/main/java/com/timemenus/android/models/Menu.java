package com.timemenus.android.models;

import java.util.HashMap;

/**
 * Created by fabdin on 8/2/2016.
 */
public class Menu {

    String key;
    String note;
    private HashMap<String, Item> items;
    String date;

    public Menu(){

    }

    public Menu(String note, String date) {
        this.note = note;
        this.date = date;
    }

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


    public HashMap<String, Item> getItems() {
        return items;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setItems(HashMap<String, Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return this.date+ " : "+getNote();
    }
}

package com.express.apps.expresscafe.services;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;

import com.express.apps.expresscafe.models.Category;
import com.express.apps.expresscafe.models.Menu;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by fabdin on 8/3/2016.
 */
public class DataService {

    private static FirebaseDatabase database;
    private static List<Category> categories =new ArrayList<>();

    public static DataService newInstance() {

        database = FirebaseDatabase.getInstance();
        DataService ds = new DataService();

        setListeners();

        return ds;
    }

    private static void setListeners() {
        DatabaseReference categoriesRef = database.getReference("categories");
        categoriesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Iterator it=dataSnapshot.getChildren().iterator();
                while(it.hasNext()){
                    DataSnapshot ds=(DataSnapshot) it.next();
                    Category c=new Category();
                    c.setKey(ds.getKey());
                    c.setName((String)ds.getValue());

                    categories.add(c);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

    public Menu getTodayMenu(){

        Menu m=new Menu();

        return m;
    }

    public Category getCategoryById(String key){


        return  new Category();

    }


    public List<Category> getCategories(String key){

        return categories;
    }

}

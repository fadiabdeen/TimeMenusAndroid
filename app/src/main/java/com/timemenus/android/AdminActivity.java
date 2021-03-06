package com.timemenus.android;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.timemenus.android.models.Menu;
import com.timemenus.android.services.AuthService;
import com.timemenus.android.services.DataService;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.timemenus.android.services.FCMHelper;

import java.util.Arrays;

public class AdminActivity extends BaseActivity {

    private EditText note;
//    private String todayNote;
    Menu menu=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

//        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
//        setSupportActionBar(myToolbar);

        String[] perms = {"android.permission.READ_EXTERNAL_STORAGE","android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.CAMERA"};

        int permsRequestCode = 200;

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(perms, permsRequestCode);
        }

        showProgressDialog();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        note = (EditText) findViewById(R.id.edit_note);


        menu = DataService.getTodayMenu();

        note.setText(menu.getNote());

//        if(menu!=null) {
//            DatabaseReference myRef = database.getReference("menues/" + menu.getKey());
//
//            myRef.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    todayNote = dataSnapshot.getValue(Menu.class).getNote();
//                    note.setText(todayNote);
//                    hideProgressDialog();
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//                    System.out.println("The read failed: " + databaseError.getMessage());
//                }
//            });
//        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar_admin);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        hideProgressDialog();

    }

    public void saveNote(View view) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("cafes/EXPRESSDC1/menues/" + menu.getKey() + "/note");
        myRef.setValue(note.getText().toString());
    }

    public void addItems(View view) {
        Intent intent = new Intent(this, AddItemActivity.class);
        startActivity(intent);
    }
// to use in future to make a custom notification
    public void createNotification(View view) {
        Intent intent = new Intent(this, SendNotifActivity.class);
        startActivity(intent);
    }

    public void menuIsLive(View view) {
        Button btn = (Button) findViewById(R.id.menu_islive);
        JsonObject obj = new JsonObject();
        JsonArray newRedId = new JsonArray();

        for(int x=0; x<DataService.getRegistrationIDs().toArray().length; x++){
            newRedId.add(DataService.getRegistrationIDs().get(x).getName());
        }

        obj.add("registration_ids", newRedId);
        JsonObject objBody = new JsonObject();
        objBody.addProperty("body","Menu Ready");
        obj.add("notification", objBody);
        obj.addProperty("priority", 10);
        String result = FCMHelper.sendNotification(obj);

//        if(result == "failure") {
//            System.out.println(result);
            btn.setEnabled(false);
//        }

    }


    public boolean onOptionsItemSelected(MenuItem item) {
        Intent myIntent = new Intent(getApplicationContext(), DashboardActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }

    public void signOutButton(View view) {
//        signOut();
        AuthService.signOut();

        Intent intent = new Intent(this, DashboardActivity.class);

        startActivity(intent);

    }

}

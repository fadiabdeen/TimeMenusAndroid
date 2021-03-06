package com.timemenus.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import com.timemenus.android.models.Menu;
import com.timemenus.android.services.AuthService;
import com.timemenus.android.services.UtilsService;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddMenuToday extends BaseActivity {

    private EditText note;
    Menu menu=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        showProgressDialog();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu_today);
        note = (EditText) findViewById(R.id.create_note);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar_admin);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        hideProgressDialog();

    }

    public void createNote(View view) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("cafes/EXPRESSDC1/menues/");
        Menu menu = new Menu(note.getText().toString(), UtilsService.getTodayDate());

        myRef.push().setValue(menu);
        Intent intent = new Intent(this, AdminActivity.class);
        startActivity(intent);
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

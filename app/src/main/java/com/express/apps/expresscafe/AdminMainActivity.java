package com.express.apps.expresscafe;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.express.apps.expresscafe.models.Menu;
import com.express.apps.expresscafe.services.menuesService;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AdminMainActivity extends AppCompatActivity {


    private EditText note;
    private String todayDate;
    private String todayNote;
//    private ProgressBar spinner;
    private ProgressDialog progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        progress=new ProgressDialog(this);
        progress.setMessage("Loading ...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(false);
        progress.show();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
//        spinner = (ProgressBar)findViewById(R.id.progressBar1);
        note = (EditText) findViewById(R.id.edit_note);

        todayDate = menuesService.getTodayDate();

//        spinner.setVisibility(View.VISIBLE);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("menues/-KOG_crFtM-wgOm6BKuf");
        System.out.println("menuesService.keyforTodayDate(todayDate): " +menuesService.keyforTodayDate(todayDate));
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                todayNote = dataSnapshot.getValue(Menu.class).getNote();
                note.setText(todayNote);
//                spinner.setVisibility(View.GONE);
                progress.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getMessage());
            }
        });

    }

    public void saveNote(View view) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("menues/-KOG_crFtM-wgOm6BKuf/note");
        myRef.setValue(note.getText().toString());
    }

    public void addItems(View view)
    {
        Intent intent = new Intent(this, AdminActivity.class);
        startActivity(intent);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }

}
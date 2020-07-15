package com.lambton.contact_amanpreet_c0782918_android.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.lambton.contact_amanpreet_c0782918_android.R;
import com.lambton.contact_amanpreet_c0782918_android.adapter.PersonAdapter;
import com.lambton.contact_amanpreet_c0782918_android.database.Person;
import com.lambton.contact_amanpreet_c0782918_android.database.PersonRoomDB;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvPersonList;

    private PersonRoomDB personRoomDB;

    List<Person> personList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Customer List");

        rvPersonList = findViewById(R.id.rvPersonList);
        rvPersonList.setLayoutManager(new LinearLayoutManager(this));
        personList = new ArrayList<>();
        personRoomDB = personRoomDB.getINSTANCE(this);
        loadContact();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.btnAdd){

            Intent intent = new Intent(MainActivity.this, AddContactActivity.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

    private void loadContact() {
        personList = personRoomDB.personDao().getAllContacts();

        PersonAdapter personAdapter = new PersonAdapter(this,R.layout.item_person, personList);
        rvPersonList.setAdapter(personAdapter);

    }
}
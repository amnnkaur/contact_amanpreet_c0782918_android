package com.lambton.contact_amanpreet_c0782918_android.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;
import android.widget.TextView;

import com.lambton.contact_amanpreet_c0782918_android.R;
import com.lambton.contact_amanpreet_c0782918_android.adapter.PersonAdapter;
import com.lambton.contact_amanpreet_c0782918_android.database.Person;
import com.lambton.contact_amanpreet_c0782918_android.database.PersonRoomDB;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static int noOfContacts;
    RecyclerView rvPersonList;

    private PersonRoomDB personRoomDB;
    PersonAdapter personAdapter;

    private SearchView searchView;

    public static TextView tv_totalContacts;
    TextView nonStaticcontacts;

//    static int noOfContacts;

    List<Person> personList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Customer List");

        tv_totalContacts = findViewById(R.id.tv_totalContacts);
        nonStaticcontacts = findViewById(R.id.tv_totalContacts);

        rvPersonList = findViewById(R.id.rvPersonList);
        rvPersonList.setLayoutManager(new LinearLayoutManager(this));
        rvPersonList.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
        rvPersonList.setAdapter(personAdapter);
        personList = new ArrayList<>();
        personRoomDB = personRoomDB.getINSTANCE(this);
        loadContact();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.mymenu, menu);

        MenuItem searchItem = menu.findItem(R.id.btnSearch);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                personAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.btnAdd){

            Intent intent = new Intent(MainActivity.this, AddContactActivity.class);
            startActivity(intent);

        }

        if (item.getItemId() == R.id.btnSearch){
//            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//                @Override
//                public boolean onQueryTextSubmit(String query) {
//                    return false;
//                }
//                @Override
//                public boolean onQueryTextChange(String newText) {
//                    personAdapter.getFilter().filter(newText);
//                    return false;
//                }
//            });


        }
        return super.onOptionsItemSelected(item);
    }

    public static void calculateContacts(int value){

        tv_totalContacts.setText(String.valueOf(value));

    }


    private void loadContact() {
        personList = personRoomDB.personDao().getAllContacts();

        personAdapter = new PersonAdapter(this,R.layout.item_person, personList);
        rvPersonList.setAdapter(personAdapter);

        nonStaticcontacts.setText(String.valueOf(personList.size()));

//        tv_totalContacts.setText(String.valueOf(noOfContacts));

    }
}
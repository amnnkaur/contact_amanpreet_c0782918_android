package com.lambton.contact_amanpreet_c0782918_android.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.lambton.contact_amanpreet_c0782918_android.R;
import com.lambton.contact_amanpreet_c0782918_android.database.Person;
import com.lambton.contact_amanpreet_c0782918_android.database.PersonRoomDB;

public class AddContactActivity extends AppCompatActivity implements View.OnClickListener {

    PersonRoomDB personRoomDB;

    EditText et_firstName;
    EditText et_lastName;
    EditText et_email;
    EditText et_phoneNumber;
    EditText et_address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        et_firstName = findViewById(R.id.et_firstName);
        et_lastName = findViewById(R.id.et_lastName);
        et_email = findViewById(R.id.et_email);
        et_phoneNumber = findViewById(R.id.et_phoneNumber);
        et_address = findViewById(R.id.et_address);

        findViewById(R.id.btnAddContact).setOnClickListener(this);

        // Room database
        personRoomDB = PersonRoomDB.getINSTANCE(this);
    }

    @Override
    public void onClick(View view) {

        addContact();

    }

    private void addContact() {
        String firstName = et_firstName.getText().toString().trim();
        String lastName = et_lastName.getText().toString().trim();
        String email = et_email.getText().toString().trim();
        String contact = et_phoneNumber.getText().toString().trim();
        String address = et_address.getText().toString().trim();

        if (firstName.isEmpty()) {
            et_firstName.setError("Please enter your First Name");
            et_firstName.requestFocus();
            return;
        }

        if (lastName.isEmpty()) {
            et_lastName.setError("Please enter your Last Name");
            et_lastName.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            et_email.setError("Please enter your Email ID");
            et_email.requestFocus();
            return;
        }
        if (contact.isEmpty()) {
            et_phoneNumber.setError("Please enter your contact number");
            et_phoneNumber.requestFocus();
            return;
        }

        if (address.isEmpty()) {
            et_address.setError("Please enter your complete address");
            et_address.requestFocus();
            return;
        }

        // insert into room db
        Person person = new Person(firstName,lastName,email,contact,address);
        personRoomDB.personDao().insertContact(person);
        Toast.makeText(this, "Contact added", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        et_firstName.setText("");
        et_lastName.setText("");
        et_email.setText("");
        et_phoneNumber.setText("");
        et_address.setText("");

        et_firstName.requestFocus();
        et_lastName.clearFocus();
        et_email.clearFocus();
        et_phoneNumber.clearFocus();
        et_address.clearFocus();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
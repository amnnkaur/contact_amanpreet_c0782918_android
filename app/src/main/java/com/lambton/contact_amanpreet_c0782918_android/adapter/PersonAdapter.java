package com.lambton.contact_amanpreet_c0782918_android.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.lambton.contact_amanpreet_c0782918_android.R;
import com.lambton.contact_amanpreet_c0782918_android.database.Person;
import com.lambton.contact_amanpreet_c0782918_android.database.PersonRoomDB;

import java.util.List;

public class PersonAdapter extends ArrayAdapter {

    Context context;
    int layoutRes;
    List<Person> personList;

    PersonRoomDB personRoomDB;


    public PersonAdapter(@NonNull Context context, int resource, List<Person> personList) {
        super(context, resource, personList);

        this.personList = personList;
        this.context = context;
        this.layoutRes = resource;
        personRoomDB = personRoomDB.getINSTANCE(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull final ViewGroup parent) {
//        return super.getView(position, convertView, parent);
        final LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutRes, null);
        TextView firstNameTV = view.findViewById(R.id.tv_firstName);
        TextView lastNameTV = view.findViewById(R.id.tv_lastName);
        TextView emailTV = view.findViewById(R.id.tv_email);
        TextView contactTV = view.findViewById(R.id.tv_contact);
        TextView addressTV = view.findViewById(R.id.tv_address);

        final Person person = personList.get(position);
        firstNameTV.setText(person.getFirstName());
        lastNameTV.setText(person.getLastName());
        emailTV.setText(person.getEmail());
        contactTV.setText(person.getPhoneNumber());
        addressTV.setText(person.getAddress());

        view.findViewById(R.id.btn_edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateContact(person);
            }

            private void updateContact(final Person person) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater layoutInflater = LayoutInflater.from(context);
                View view = layoutInflater.inflate(R.layout.dialog_update_contact, null);
                builder.setView(view);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();

                final EditText et_first_name = view.findViewById(R.id.et_first_name);
                final EditText et_last_name = view.findViewById(R.id.et_last_name);
                final EditText et_email = view.findViewById(R.id.et_email);
                final EditText et_contact = view.findViewById(R.id.et_contact);
                final EditText et_address = view.findViewById(R.id.et_address);

                et_first_name.setText(person.getFirstName());
                et_last_name.setText(person.getLastName());
                et_email.setText(person.getEmail());
                et_contact.setText(person.getPhoneNumber());
                et_address.setText(person.getAddress());

                view.findViewById(R.id.btn_update).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String firstName = et_first_name.getText().toString().trim();
                        String lastName = et_last_name.getText().toString().trim();
                        String email = et_email.getText().toString().trim();
                        String contact = et_contact.getText().toString().trim();
                        String address = et_address.getText().toString().trim();

                        if (firstName.isEmpty()) {
                            et_first_name.setError("Please enter your First Name");
                            et_first_name.requestFocus();
                            return;
                        }

                        if (lastName.isEmpty()) {
                            et_last_name.setError("Please enter your Last Name");
                            et_last_name.requestFocus();
                            return;
                        }

                        if (email.isEmpty()) {
                            et_email.setError("Please enter your Email ID");
                            et_email.requestFocus();
                            return;
                        }
                        if (contact.isEmpty()) {
                            et_contact.setError("Please enter your contact number");
                            et_contact.requestFocus();
                            return;
                        }

                        if (address.isEmpty()) {
                            et_address.setError("Please enter your complete address");
                            et_address.requestFocus();
                            return;
                        }

                        personRoomDB.personDao().updateContact(person.getId(),
                                firstName,
                                lastName,
                                email,
                                contact,
                                address);
                        loadContacts();
                        alertDialog.dismiss();
                    }
                });
            }
        });

        view.findViewById(R.id.btn_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteContact(person);
            }

            private void deleteContact(final Person person) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Are you sure to delete" + person.getFirstName() + "?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        personRoomDB.personDao().deleteContact(person.getId());
                        loadContacts();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "The Contact (" + person.getFirstName() + ") has not been deleted", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });
        return view;

    }

    @Override
    public int getCount() {
        return personList.size();
    }

    private void loadContacts() {
        personList = personRoomDB.personDao().getAllContacts();
        notifyDataSetChanged();
    }
}

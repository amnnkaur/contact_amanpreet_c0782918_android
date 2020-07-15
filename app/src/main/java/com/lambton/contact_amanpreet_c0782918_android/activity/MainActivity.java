package com.lambton.contact_amanpreet_c0782918_android.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.lambton.contact_amanpreet_c0782918_android.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvPersonList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvPersonList = findViewById(R.id.rvPersonList);
    }
}
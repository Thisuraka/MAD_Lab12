package com.example.lab12_model;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.List;

public class Student extends AppCompatActivity {

    List<Message> messages;
    Message message;
    dbHandler dbHandler;

    TextView stdTit;
    ListView stdRv;

    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        stdTit = findViewById(R.id.stdTit);
        stdRv = findViewById(R.id.stdRv);

        user = getIntent().getStringExtra("Student");
        stdTit.setText("Welcome " + user);


    }
}
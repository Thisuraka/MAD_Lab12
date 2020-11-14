package com.example.lab12_model;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.List;

public class Student extends AppCompatActivity {

    dbHandler dbHandler;
    List<Message> messages;
    Context context;
    TextView stdTit;
    ListView stdLv;

    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        dbHandler = new dbHandler(this);
        stdTit = findViewById(R.id.stdTit);
        stdLv = findViewById(R.id.stdLv);

        user = getIntent().getStringExtra("Student");
        stdTit.setText("Welcome " + user);

        messages = new ArrayList<>();

        messages = dbHandler.getAllMessages();

        Log.i("Tag","this shit" + messages);

        MessageAdapter adapter = new MessageAdapter(context,R.layout.activity_single_message,messages);
        stdLv.setAdapter(adapter);
    }
}














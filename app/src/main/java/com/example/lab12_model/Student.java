package com.example.lab12_model;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Student extends AppCompatActivity {

    dbHandler dbHandler;
    ArrayList<Message> messages;
    List<String> finalList = new ArrayList<>();
    Context context;
    TextView stdTit;
    ListView stdLv;

    String user, subject, message;
    Integer pointer = 0, size;

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

        messages = (ArrayList<Message>) dbHandler.getAllMessages();

        Log.i("Tag", "this shit" + messages);

        size = messages.size();

        while (size != 0) {
            Message test = messages.get(pointer);

            subject = test.getSubject();
            message = test.getMessage();

            finalList.add("Subject : " + subject + "\n" +"Message: " + message);

            pointer++;
            size--;
        }

        final ArrayAdapter<String> adapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, finalList);
        stdLv.setAdapter(adapter);

//        MessageAdapter adapter = new MessageAdapter(context,R.layout.activity_single_message,messages);
//        stdLv.setAdapter(adapter);


    }
}















package com.example.lab12_model;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Student extends AppCompatActivity {

    dbHandler dbHandler;
    ArrayList<Message> messages; //where the values from DB would be saved
    List<String> finalList = new ArrayList<>(); //additional list to cast to listview
    Context context;
    TextView stdTit;
    ListView stdLv;

    String user, subject, message; //user is for setting the text
    Integer pointer = 0, size; //related to finalList

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        dbHandler = new dbHandler(this);
        stdTit = findViewById(R.id.stdTit);
        stdLv = findViewById(R.id.stdLv);

        //========================setting textView to user name ====================
        user = getIntent().getStringExtra("Student");
        stdTit.setText("Welcome " + user);

        //======================== Getting the values from DB ====================
        messages = new ArrayList<>();
        messages = (ArrayList<Message>) dbHandler.getAllMessages();
        size = messages.size();

        Log.i("Tag", "this shit" + messages);

        //======================== extracting data ========================
        while (size != 0) {
            Message test = messages.get(pointer);

            subject = test.getSubject();
            message = test.getMessage();

            finalList.add(subject); //"Subject : " + subject + "\n" + "  " + message

            pointer++;
            size--;
        }

        //======================== setting to the listView ========================
        final ArrayAdapter<String> adapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, finalList);
        stdLv.setAdapter(adapter);

        //======================== When item is clicked ========================
        stdLv.setOnItemClickListener((adapterView, view, position, id) -> {

            String value = stdLv.getAdapter().getItem(position).toString(); //for title
            StringBuffer buffer = new StringBuffer();
            buffer.append(adapterView.getItemAtPosition(position) + "\n");

            Message test = messages.get(position); //again took coz why not . sue me

            subject = test.getSubject();
            message = test.getMessage();

            ShowMessage(value, message); //calls the little dialog box function

            //======== Optional In case message activity needs to be invoked =========
            // ======= But why bother after i made so much effort on the adapter ====
               /* Intent intent = new Intent(getBaseContext(), MessageAct.class);
                intent.putExtra("Sub", subject);
                intent.putExtra("Msg", message);
                startActivity(intent);*/
        });

    }

    //======================== No clue what happens here but it works========================
    //======================== Don't you fuken mess with it ========================
    public void ShowMessage(String subject, String message) {
        Activity activity = this;
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setCancelable(true);
        builder.setTitle(subject);
        builder.setMessage(message);
        builder.show();
    }
}















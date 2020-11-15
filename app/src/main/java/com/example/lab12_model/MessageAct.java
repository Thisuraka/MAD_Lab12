package com.example.lab12_model;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MessageAct extends AppCompatActivity {
    TextView msgTit;
    EditText teaMesEt2;
    String Subject, Message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messageact);

        msgTit = findViewById(R.id.msgTit);
        teaMesEt2 = findViewById(R.id.teaMesEt2);

        Subject = getIntent().getStringExtra("Sub");
        Message = getIntent().getStringExtra("Msg");

        msgTit.setText(Subject);
        teaMesEt2.setText(Message);
    }
}
package com.example.lab12_model;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Teacher extends AppCompatActivity {

    Message message;
    dbHandler dbHandler;

    TextView teaTit;
    EditText teaSubEt, teaMesEt;
    Button teaSendBtn;

    String user, sub, msg;

    private void clearControls() {
        teaSubEt.setText("");
        teaMesEt.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        dbHandler = new dbHandler(this);

        teaTit = findViewById(R.id.teaTit);
        teaSubEt = findViewById(R.id.teaSubEt);
        teaMesEt = findViewById(R.id.teaMesEt);
        teaSendBtn = findViewById(R.id.teaSendBtn);

        user = getIntent().getStringExtra("Teacher");
        teaTit.setText("Welcome " + user);

        teaSendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sub = teaSubEt.getText().toString();
                msg = teaMesEt.getText().toString();

                Message message = new Message (user,sub,msg);
                dbHandler.addMessage(message);

                clearControls();
            }
        });

    }
}
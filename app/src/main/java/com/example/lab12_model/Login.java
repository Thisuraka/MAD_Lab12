package com.example.lab12_model;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private NotificationManagerCompat notificationManager;

    User user;

    dbHandler dbHandler;

    Button logLogBtn, logRegBtn;
    EditText logUnEt, logPassEt;
    TextView logTit;

    String username, password, type;

    private void clearControls() {
        logUnEt.setText("");
        logPassEt.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        notificationManager = NotificationManagerCompat.from(this);

        user = new User();
        dbHandler = new dbHandler(this);

        logTit = findViewById(R.id.logTit);
        logUnEt = findViewById(R.id.logUnEt);
        logPassEt = findViewById(R.id.logPassEt);
        logLogBtn = findViewById(R.id.logLogBtn);
        logRegBtn = findViewById(R.id.logRegBtn);

        logLogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                username = logUnEt.getText().toString();
                password = logPassEt.getText().toString();

                User user = dbHandler.loginVal(username, password);

                switch (user.getType()) {
                    case "teacher":
                        Toast.makeText(getApplicationContext(), "Accessing as Teacher", Toast.LENGTH_SHORT).show();
                        sendOnChannel1();
                        Intent intent = new Intent(getBaseContext(), Teacher.class);
                        intent.putExtra("Teacher", user.getName());
                        startActivity(intent);
                        clearControls();
                        break;
                    case "student":
                        Toast.makeText(getApplicationContext(), "Accessing as Student", Toast.LENGTH_SHORT).show();
                        sendOnChannel2();
                        Intent intent2 = new Intent(getBaseContext(), Student.class);
                        intent2.putExtra("Student", user.getName());
                        startActivity(intent2);
                        clearControls();
                        break;
                }
            }
        });

        logRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearControls();
                sendOnChannel1();
                startActivity(new Intent(Login.this, RegisterForm.class));
            }
        });
    }

    public void sendOnChannel1() {
        android.app.Notification notification = new NotificationCompat.Builder(this, Notification.CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_one)
                .setContentTitle("Login Status")
                .setContentText("Teacher access activated")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManager.notify(1,notification);
    }

    public void sendOnChannel2() {
        android.app.Notification notification = new NotificationCompat.Builder(this, Notification.CHANNEL_2_ID)
                .setSmallIcon(R.drawable.ic_one)
                .setContentTitle("Login Status")
                .setContentText("Student access activated")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManager.notify(2,notification);

    }
}
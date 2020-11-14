package com.example.lab12_model;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterForm extends AppCompatActivity {

    User user;
    dbHandler dbHandler;

    EditText regUnEt, regPassEt;
    Button regRegBtn;
    CheckBox regCh1, regCh2;

    String type,name,password;

    private void clearControls() {
        regUnEt.setText("");
        regPassEt.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_form);

        user = new User();
        dbHandler = new dbHandler(this);

        regUnEt = findViewById(R.id.regUnEt);
        regPassEt = findViewById(R.id.regPassEt);
        regRegBtn = findViewById(R.id.regRegBtn);
        regCh1 = findViewById(R.id.regCh1);
        regCh2 = findViewById(R.id.regCh2);

        regRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    name = regUnEt.getText().toString();
                    password = regPassEt.getText().toString();
                    if (regCh1.isChecked()) {
                        type = "teacher";
                    } else if (regCh2.isChecked()) {
                        type = "student";
                    }

                    User user = new User(name, password, type);
                    dbHandler.addUser(user);

                    Log.e("Tag", user.toString());

                }
                catch (Exception e){
                    Log.e("Shit Happened", e.toString());
                }

                switch(type){
                    case "teacher":
                        Toast.makeText(getApplicationContext(), "Accessing as Teacher", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getBaseContext(), Teacher.class);
                        intent.putExtra("Teacher", user.getName());
                        startActivity(intent);
                        clearControls();
                        break;
                    case "student":
                        Toast.makeText(getApplicationContext(), "Accessing as Student", Toast.LENGTH_SHORT).show();
                        Intent intent2 = new Intent(getBaseContext(), Student.class);
                        intent2.putExtra("Student", user.getName());
                        startActivity(intent2);
                        clearControls();
                        break;
                }

                clearControls();
            }
        });

    }
}
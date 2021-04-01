package com.example.registerapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterList extends AppCompatActivity {
    EditText customername,whatsappno,email_id,plan_start_date,plan_end_date,plan_amount;
    Button save,cancel;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fields);
        customername=(EditText)findViewById(R.id.customername);
        whatsappno=(EditText)findViewById(R.id.whatsappno);
        email_id=(EditText)findViewById(R.id.emailid);
        plan_start_date=(EditText)findViewById(R.id.plan_start_date);
        plan_end_date=(EditText)findViewById(R.id.plan_end_date);
        plan_amount=(EditText)findViewById(R.id.planamount);
        save=findViewById(R.id.save);
        cancel=findViewById(R.id.cancel);
    }
}

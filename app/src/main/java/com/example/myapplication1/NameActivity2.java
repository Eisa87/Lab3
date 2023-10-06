package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


//public class NameActivity2 extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_name2);
//        Intent i = getIntent();
//        String message = i.getStringExtra("COOL");
//        ((TextView)findViewById(R.id.textView)).setText(message);
//    }
//
//
//}

public class NameActivity2 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name2);

        String userName = getIntent().getStringExtra("userName");
        TextView welcomeMessageTextView = findViewById(R.id.welcomeMessage);
        welcomeMessageTextView.setText("Welcome " + userName + "!");

        Button thankYouButton = findViewById(R.id.thankYouButton);
        Button dontCallMeButton = findViewById(R.id.dontCallMeButton);

        thankYouButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // User is happy, set the result and finish
                setResult(RESULT_OK, new Intent().putExtra("RESULT_CODE", 1));
                finish();
            }
        });

        dontCallMeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // User wants to change their name, set the result and finish
                setResult(RESULT_OK, new Intent().putExtra("RESULT_CODE", 0));
                finish();
            }
        });
    }
}
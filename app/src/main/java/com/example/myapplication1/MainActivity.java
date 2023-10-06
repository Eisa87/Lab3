package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


//public class MainActivity extends AppCompatActivity {
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }
//    public void launchNameActivity2(View v) {
//        Intent i = new Intent(this, NameActivity2.class);
//        String message = ((EditText)findViewById(R.id.editText)).getText().toString();
//        i.putExtra("COOL", message);
//        startActivity(i);
//    }
//}

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 1;
    private EditText editText;
    Button nextButton;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        // Load user's name from SharedPreferences and set it in the EditText
        String savedName = sharedPreferences.getString("userName", "");
        editText.setText(savedName);

        Button nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = editText.getText().toString();

                // Save the current value to SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("userName", userName);
                editor.apply();

                Intent intent = new Intent(MainActivity.this, NameActivity2.class);
                intent.putExtra("userName", userName);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Save the current value inside the EditText to SharedPreferences
        String userName = editText.getText().toString();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userName", userName);
        editor.apply();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                int result = data.getIntExtra("RESULT_CODE", -1);
                if (result == 0) {
                    // User wants to change their name
                    // Handle as needed
                } else if (result == 1) {
                    // User is happy, close the app
                    finish();
                }
            }
        }
    }
}
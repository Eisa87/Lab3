package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
                } else if (result == 1) {
                    finish();
                }
            }
        }
    }
}
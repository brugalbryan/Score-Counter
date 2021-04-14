package com.example.sportsteamcounter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button secondActivity = findViewById(R.id.pressStart);
        secondActivity.setOnClickListener(v -> {

            Intent startIntent =  new Intent(getApplicationContext(), Score_Counter.class);

            startActivity(startIntent);


        });
    }
}

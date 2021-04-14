package com.example.sportsteamcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Winner extends AppCompatActivity {

    private int nyScore;
    private int brScore;
    TextView yankeesTxt;
    TextView bostonTxt;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);

        Intent intent = getIntent(); //save the value
        //the received value set in the TextView object of second activity
        brScore = intent.getIntExtra("Boston",0);
        nyScore = intent.getIntExtra("Yankees", 0);

        Button winnershareActivity = findViewById(R.id.shareBtn);
        winnershareActivity.setOnClickListener(v -> {
            Intent startIntent =  new Intent(getApplicationContext(), winnershare.class);
            startIntent.putExtra("Yankees", nyScore);
            startIntent.putExtra("Boston", brScore);
            startActivity(startIntent);
        });


        ImageView yankees = findViewById(R.id.nyImg);
        ImageView boston = findViewById(R.id.rSoxImg);
        yankeesTxt = findViewById(R.id.yankeesTv);
        bostonTxt = findViewById(R.id.bostonTv);


        //TextViews show the score of each team after getting the info from the past activity
        yankeesTxt.setText(Integer.toString(nyScore));
        bostonTxt.setText(Integer.toString(brScore));

        // check scores that were obtained from the other activity
        if(nyScore > brScore) {

            boston.setVisibility(View.INVISIBLE); //if Yankees wins then Boston img is gone

        }else{

            yankees.setVisibility(View.INVISIBLE); //if


        }
    }
}

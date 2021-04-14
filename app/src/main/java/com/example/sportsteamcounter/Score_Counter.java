package com.example.sportsteamcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Score_Counter extends AppCompatActivity {
    TextView yCounterTxt;
    TextView bCounterTxt;
    private int brScore = 0;
    private int nyScore = 0;



    @SuppressLint("SetTextI18n")
    @Override //preferred, to ensure that your user interface, including any save state is back up and running as quickly as possible
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score__counter);
        yCounterTxt = findViewById(R.id.nyTv);//getting reference to yankess counter...
        bCounterTxt = findViewById(R.id.rSoxTv);
        Button yankees = findViewById(R.id.nyBtn);
        Button boston = findViewById(R.id.rSOxBtn);

        // Boston & Yankees Buttons
        yankees.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {

                nyScore++;
                yCounterTxt.setText(Integer.toString(nyScore));
                changeActivity(); //checks if the goal of 5 run has been accomplish and changes the activity
            }

        });

        boston.setOnClickListener(v -> {

            brScore++;
            bCounterTxt.setText(Integer.toString(brScore));

            changeActivity(); //checks if 5 has been reached and changes the activity



        });


    }

    @SuppressLint("SetTextI18n")
    public void changeActivity(){
        if(brScore == 5 || nyScore == 5){
            /*#get the message
            //to sent a response to the main activity we need to create an intent
            put the extra that we want into that intent and set the result
            we want to indicate the main activity and we need to retrieve from the intent
             */
            Intent startIntent =  new Intent(getApplicationContext(), Winner.class);
            startIntent.putExtra("Boston", brScore);
            startIntent.putExtra("Yankees", nyScore);

            brScore = 0;
            nyScore = 0;

            yCounterTxt.setText(Integer.toString(nyScore));
            bCounterTxt.setText(Integer.toString(brScore));

            startActivity(startIntent);
        }
    }
}

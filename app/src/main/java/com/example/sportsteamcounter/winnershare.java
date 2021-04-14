package com.example.sportsteamcounter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ShareCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class winnershare extends AppCompatActivity implements View.OnClickListener {
    private int nyScore;
    private int brScore;
    private Button locBtn, txtBtn, dialBtn;
    public static final int REQUEST_CALL_PHONE = 2;
    public static final String TAG = "MainActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winnershare);

        //obtain information from the winner activity
        Intent intent = getIntent();
        nyScore = intent.getIntExtra("Yankees", 0);
        brScore = intent.getIntExtra("Boston", 0);

        getReferencesToMyWidgets();
        setOnClickListenersToMyWidgets();

    }

    private String championScore() {

        if (nyScore > brScore) {

            return "New York won with an" + nyScore + "-" + brScore
                    + "victory over the rival Boston";

        } else {

            return "Boston won with an" + brScore + "-" + nyScore
                    + "victory over the rival new york";
        }

    }

    private void getReferencesToMyWidgets() {
        Log.d(TAG, "inside getReferencesToMyWidgets method");
        locBtn = findViewById(R.id.locBtn);
        txtBtn = findViewById(R.id.txtBtn);
        dialBtn = findViewById(R.id.dialBtn);
    }

    private void setOnClickListenersToMyWidgets() {
        locBtn.setOnClickListener(this);
        txtBtn.setOnClickListener(this);
        dialBtn.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v){

        Log.d(TAG, "inside onClick method");
        switch (v.getId()){

            case R.id.locBtn:
                Log.d(TAG, "clicked button Loc");
                MapLocation();
                break;
            case R.id.txtBtn:
                Log.d(TAG, "clicked button Text");
                sendText();
                break;
            case R.id.dialBtn:
                Log.d(TAG, "clicked button Dial");
                PhoneDial();
                break;
        }

    }

    private void MapLocation() {


        Uri gmmIntentUri = Uri.parse("geo:0,0?q=baseball stadium");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);

    }
    @SuppressLint("QueryPermissionsNeeded")
    private void PhoneDial() {
        Log.d(TAG, "inside Call Method");
//
//        set permission
        Intent intent = new Intent(Intent.ACTION_CALL);
//        call jenny
        intent.setData(Uri.parse("tel:2128650906"));
        if (intent.resolveActivity(getPackageManager()) != null) {

            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                //check for permission

                ActivityCompat.requestPermissions(this, new String[]{
                                Manifest.permission.CALL_PHONE},
                        REQUEST_CALL_PHONE);


            } else {//start activity
                startActivity(intent);
            }


        } else {
            Log.d(TAG, "could not place Call");
        }

    }

    private void sendText(){
        Log.d(TAG,"inside sendText method");
        String theText = championScore();
        String mimeType = "text/plain";
        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle("pick an app")
                .setText(theText)
                .startChooser();

    }

}
package com.example.theoakteam.ramadanapp.SplashActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.theoakteam.ramadanapp.R;
import com.example.theoakteam.ramadanapp.FirstAcivity.SehriAndIfterShortForm;


public class MainSplashScreen extends Activity {
//hello it  is test for github
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_splash_screen);



        Thread background = new Thread() {
            public void run() {

                try {

                    sleep(1000);


                    Intent i=new Intent(MainSplashScreen.this,SehriAndIfterShortForm.class);
                    startActivity(i);


                    finish();

                } catch (Exception e) {

                }
            }
        };

        // start thread
        background.start();


    }

    @Override
    protected void onDestroy() {

        super.onDestroy();

    }
}

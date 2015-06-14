package theoaktroop.appoframadan.SplashActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import theoaktroop.appoframadan.R;

import theoaktroop.appoframadan.FirstAcivity.SehriAndIfterShortForm;


public class MainSplashScreen extends Activity {
//hello it  is test for github
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_splash_screen);



        Thread background = new Thread() {
            public void run() {

                try {

                    sleep(1500);


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

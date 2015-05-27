package com.example.theoakteam.ramadanapp;

import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by Sunny-PC on 5/21/2015.
 */
public class DisplaySuraActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_sura);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_tasbhi) {
            Intent intent = new Intent(DisplaySuraActivity.this, TasbhiActivity1.class);
            startActivity(intent);
            finish();
            return true;
        }
        else if(id==R.id.actionseheri_ifter_time){
            startActivity(new Intent(DisplaySuraActivity.this, SehriAndIfterShortForm.class));

            return true;
        }
        else if(id==R.id.action_alarm){
            Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
            startActivity(i);
            return  true;
        }

        return super.onOptionsItemSelected(item);

    }

}

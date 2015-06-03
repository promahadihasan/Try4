package com.example.theoakteam.ramadanapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.theoakteam.ramadanapp.HadithActivity.HadithTitleClass;


public class DistrictAllTimeShow extends ActionBarActivity {

    TextView districtNameTextView;
    TextView ramadanDateTextView;
    TextView englishDateTextView;
    TextView sehriTimeTextView;
    TextView iftarTimeTextView;

    String districtName;
    SharedPreferences sharedPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_district_all_time_show);

        Bundle bundle=this.getIntent().getExtras();
        String[] allSehriTime=bundle.getStringArray("allSehri");
        String[] allIftarTime=bundle.getStringArray("allIftar");
        String[] allDate = bundle.getStringArray("allDate");

        initialize();

        for(int i = 1; i<=30; i++){
            ramadanDateTextView.append(i + "\n\n");
            englishDateTextView.append(allDate[i] + "\n\n");
            sehriTimeTextView.append(allSehriTime[i] + "\n\n");
            iftarTimeTextView.append(allIftarTime[i] + "\n\n");

        }

    }

    private void initialize() {

        sharedPreferences = getSharedPreferences("RamadanAppData", Context.MODE_PRIVATE);
        districtName = sharedPreferences.getString("DefaultDistrictName", "Not Found");

        districtNameTextView = (TextView) findViewById(R.id.districtNameTextView);
        ramadanDateTextView = (TextView) findViewById(R.id.ramadanDateTextView);
        englishDateTextView = (TextView) findViewById(R.id.englishDateTextView);
        sehriTimeTextView = (TextView) findViewById(R.id.sehriTimeTextView);
        iftarTimeTextView = (TextView) findViewById(R.id.iftarTimeTextView);


        districtNameTextView.setText(districtName.substring(0,1).toUpperCase() + districtName.substring(1)+" "+getString(R.string.district_name_all));



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
        if (id == R.id.action_quran) {
            startActivity(new Intent(DistrictAllTimeShow.this, DisplaySuraActivity.class));
            finish();
            return true;
        }
        else if(id== R.id.actionseheri_ifter_time){
            startActivity(new Intent(DistrictAllTimeShow.this, SehriAndIfterShortForm.class));
            finish();
            return true;
        }
        else if(id== R.id.action_alarm){
            Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
            startActivity(i);
            finish();
            return  true;
        }
        else if(id== R.id.action_settings){
            Intent i = new Intent(DistrictAllTimeShow.this,SettingsActivity.class);
            startActivity(i);

            return  true;
        }
        else if(id== R.id.action_tasbhi){
            startActivity(new Intent(DistrictAllTimeShow.this, TasbhiActivity1.class));
            finish();
            return true;
        }
        else if(id== R.id.action_hadis){
            startActivity(new Intent(DistrictAllTimeShow.this, HadithTitleClass.class));
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

package com.example.theoakteam.ramadanapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.AlarmClock;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;


public class SehriAndIfterShortForm extends ActionBarActivity {

    String DEFAULT = "N/A";
    String districtName;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    AutoCompleteTextView autoCompleteTextView;
    DistrictsTimeClass districtsTimeObject = new DistrictsTimeClass();
    TextView iftarTime;
    TextView sehriTime;
    TextView sehriNote;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        sharedPreferences = getSharedPreferences("DistrictInputFlag", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String flagString = sharedPreferences.getString("DistrictInputFlag", DEFAULT);

        if(flagString.equals(DEFAULT)){
            editor.putString("DistrictInputFlag","true");
            editor.commit();
            setContentView(R.layout.distric_input);

            String[] districts = getResources().getStringArray(R.array.list_of_districts);
            ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,districts);
            autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
            autoCompleteTextView.setAdapter(adapter);

        }
        else{
            sehriActivity();

        }

    }

    public void sehriActivity(){
        setContentView(R.layout.activity_sehri_and_ifter_short_form);
        iftarTime = (TextView) findViewById(R.id.iftarTextView);
        sehriTime = (TextView) findViewById(R.id.sehriTextView);
        sehriNote = (TextView) findViewById(R.id.sehri_iftar_initial_note);
        String iftarTimeString;
        String sehriTimeString;

        districtName = sharedPreferences.getString("DefaultDistrictName", "N/A");

        if(districtName=="N/A"){
            Toast.makeText(getApplicationContext(),"SharedPreference Error",Toast.LENGTH_LONG).show();

        }
        else{
            String dateString = getDate();
            //This App now show time for 1st Ramadan. for exact replace by:(districtName, dateString)
            //Yet some bugs. not work for July.
            sehriTimeString = districtsTimeObject.getDistrictIndividualSehriTime(districtName, "18/06");
            iftarTimeString = districtsTimeObject.getDistrictIndividualIftarTime(districtName, "18/06");

            sehriNote.setText(districtName+getText(R.string.sehri_iftar_first_note));
            sehriTime.setText(sehriTimeString);
            iftarTime.setText(iftarTimeString);

        }

    }

    public String getDate(){
        Date date = new Date();

        SimpleDateFormat ft = new SimpleDateFormat("dd/MM");

        //System.out.println("Hasan's Date: "+ft.format(date).toString());

        return ft.format(date).toString();
    }

    public void  saveDistrict(View view){
        districtName = autoCompleteTextView.getText().toString();
        districtName = districtsTimeObject.removeEndSpace(districtName).toLowerCase();

        if(districtsTimeObject.isDistrictPresent(districtName)){
            editor.putString("DefaultDistrictName",districtName);
            editor.putString("DistrictTime", districtsTimeObject.getDistrictPlusMinusTime(districtName));
            editor.commit();
            Toast.makeText(getApplicationContext(),districtName.substring(0,1).toUpperCase() + districtName.substring(1)+" is your Default District",Toast.LENGTH_LONG).show();

            sehriActivity();
            //setContentView(R.layout.activity_sehri_and_ifter_short_form);
        }
        else{

            Toast.makeText(getApplicationContext(),"Your District name is not correct!",Toast.LENGTH_LONG).show();

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sehri_and_ifter_short_form, menu);
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
            Intent intent = new Intent(SehriAndIfterShortForm.this, TasbhiActivity1.class);
            startActivity(intent);
            return true;
        }
        else  if (id == R.id.action_quran) {
            startActivity(new Intent(SehriAndIfterShortForm.this, DisplaySuraActivity.class));

            return true;
        }
        else if(id==R.id.actionseheri_ifter_time){
            startActivity(new Intent(SehriAndIfterShortForm.this, SehriAndIfterShortForm.class));

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

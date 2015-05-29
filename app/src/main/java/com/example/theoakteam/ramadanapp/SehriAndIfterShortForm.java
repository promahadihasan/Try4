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

import java.text.ParseException;
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
            //String test = "31/05/2015";
            sehriTimeString = districtsTimeObject.getDistrictIndividualSehriTime(districtName, getDate());
            iftarTimeString = districtsTimeObject.getDistrictIndividualIftarTime(districtName, getDate());

            sehriNote.setText(districtName+getText(R.string.sehri_iftar_first_note));
            sehriTime.setText(sehriTimeString);
            iftarTime.setText(iftarTimeString);


//        //Debug
//        Date dateObject=null,startDate=null,endDate=null;
//        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//        try {
//            dateObject = formatter.parse(getDate());
//            startDate = formatter.parse("28/05/2015");
//            endDate = formatter.parse("17/07/2015");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        if(dateObject.compareTo(startDate) <0){
//            System.out.println("Date value Negative!");
//        }
//        else if (dateObject.compareTo(startDate)>0 && dateObject.compareTo(endDate)<0) {
//            System.out.println("Ramadan is running!");
//        }
//        else {
//            System.out.println("It's Today: "+formatter.format(dateObject));
//        }
//        //Debug End

            //System.out.println("Hasan's Debug date "+districtsTimeObject.getRamadanDate("15/07"));

        }

    }

    public String getDate(){
        Date date = new Date();

        SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy");

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
            finish();
            return true;
        }
        else if(id==R.id.action_alarm){
            Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
            startActivity(i);

            return  true;
        }
        else if(id==R.id.action_settings){
            Intent i = new Intent(SehriAndIfterShortForm.this,SetingsActivity.class);
            startActivity(i);

            return  true;
        }

        return super.onOptionsItemSelected(item);
    }
}

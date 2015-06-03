package com.example.theoakteam.ramadanapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.theoakteam.ramadanapp.HadithActivity.HadithTitleClass;

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
    TextView englishDate;
    TextView arabicDate;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.002F);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getSharedPreferences("RamadanAppData", Context.MODE_PRIVATE);
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
// Hasan's Code Start
    public void showFullTime(View v){
        v.startAnimation(buttonClick);

        String[] allSehriTime = new String[31];
        String[] allIftarTime = new String[31];
        String[] allDate = new String[31];
        allIftarTime[0]="0:00";
        allIftarTime[0]="0:00";
        allDate[0] = "00/00/0000";
        int engDate=18, flag=0;
        String engMonth="06";
        for(int ramadanDate=1; ramadanDate<=30; ramadanDate++,engDate++){

            if(flag==0 && engDate>30){
                engDate=1;
                engMonth = "07";
                flag = 1;
            }

            String dateStringFull;
            if(engDate<10){
                dateStringFull = "0" + String.valueOf(engDate) + "/" + engMonth + "/" + "'15";
            }
            else {
                dateStringFull = String.valueOf(engDate) + "/" + engMonth + "/" + "'15";

            }

            String plusMinus = districtsTimeObject.getDistrictPlusMinusTime(districtName);
            String centralSehri = districtsTimeObject.getCentralSehri(ramadanDate);
            String centralIftar = districtsTimeObject.getCentralIftar(ramadanDate);
            String sehriTimeString = districtsTimeObject.calculateTime(centralSehri, plusMinus);
            String iftarTimeString = districtsTimeObject.calculateTime(centralIftar,plusMinus);

            allSehriTime[ramadanDate] = sehriTimeString;
            allIftarTime[ramadanDate] = iftarTimeString;
            allDate[ramadanDate] = dateStringFull;

        }

        Bundle bundle=new Bundle();
        bundle.putStringArray("allSehri", allSehriTime);
        bundle.putStringArray("allIftar", allIftarTime);
        bundle.putStringArray("allDate", allDate);

        Intent intent = new Intent(SehriAndIfterShortForm.this, DistrictAllTimeShow.class);
        intent.putExtras(bundle);
        startActivity(intent);


    }

    public void sehriActivity(){
        setContentView(R.layout.activity_sehri_and_ifter_short_form);
        iftarTime = (TextView) findViewById(R.id.iftarTextView);
        sehriTime = (TextView) findViewById(R.id.sehriTextView);
        sehriNote = (TextView) findViewById(R.id.sehri_iftar_initial_note);
        englishDate = (TextView) findViewById(R.id.dateEnglishTextView);
        arabicDate = (TextView) findViewById(R.id.arabicTextView);
        String iftarTimeString;
        String sehriTimeString;

        districtName = sharedPreferences.getString("DefaultDistrictName", "N/A");

        if(districtName=="N/A"){
            Toast.makeText(getApplicationContext(),"SharedPreference Error",Toast.LENGTH_LONG).show();
        }
        else{
            String dateString = getDate(); // "15/06/2015"; // "17/06/2015"; //"10/07/2015";  //
            String arabicMonth = districtsTimeObject.findMonth(dateString);
            englishDate.append(" "+dateString);
            if(arabicMonth=="ramadan"){
                arabicDate.setText("("+districtsTimeObject.getRamadanDate(dateString)+" "+getString(R.string.ramadan)+" "+getString(R.string.hijri));
                sehriTimeString = districtsTimeObject.getDistrictIndividualSehriTime(districtName, dateString);
                iftarTimeString = districtsTimeObject.getDistrictIndividualIftarTime(districtName, dateString);
            }
            else {
                int shabanDate = districtsTimeObject.getShabanDate(dateString);
                String plusMinus = districtsTimeObject.getDistrictPlusMinusTime(districtName);
                String centralSehriShaban = districtsTimeObject.getCentralSehriShaban(shabanDate);
                String centralIftarShaban = districtsTimeObject.getCentralIftarShaban(shabanDate);
                sehriTimeString = districtsTimeObject.calculateTime(centralSehriShaban, plusMinus);
                iftarTimeString = districtsTimeObject.calculateTime(centralIftarShaban,plusMinus);

                arabicDate.setText("("+shabanDate+" "+getString(R.string.shaban)+" "+getString(R.string.hijri));


            }

            sehriNote.setText(districtName.substring(0,1).toUpperCase() + districtName.substring(1)+"  "+getText(R.string.sehri_iftar_first_note));
            sehriTime.setText(sehriTimeString + " am");
            iftarTime.setText(iftarTimeString + " pm");

        }


    }

    public String getDate(){
        Date date = new Date();

        SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy");

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
// Hasan's Code End

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();

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
        else if(id== R.id.actionseheri_ifter_time){
            startActivity(new Intent(SehriAndIfterShortForm.this, SehriAndIfterShortForm.class));
            finish();
            return true;
        }
        else if(id== R.id.action_alarm){
            Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
            startActivity(i);

            return  true;
        }
        else if(id== R.id.action_settings){
            Intent i = new Intent(SehriAndIfterShortForm.this,SettingsActivity.class);
            startActivity(i);

            return  true;
        }
        else if(id== R.id.action_hadis){
            startActivity(new Intent(SehriAndIfterShortForm.this, HadithTitleClass.class));

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

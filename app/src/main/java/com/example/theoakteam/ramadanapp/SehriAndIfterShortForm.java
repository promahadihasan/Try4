package com.example.theoakteam.ramadanapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;


public class SehriAndIfterShortForm extends ActionBarActivity {

    String DEFAULT = "N/A";
    String districtName;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    AutoCompleteTextView autoCompleteTextView;
    DistrictsTimeClass districtsTimeObject = new DistrictsTimeClass();
    TextView iftarTime;
    TextView sehriTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //iftarTime.setText("TEXT");//Problem is here

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

            System.out.println("habu gabu labu");

        }
        else{
            setContentView(R.layout.activity_sehri_and_ifter_short_form);
            iftarTime = (TextView) findViewById(R.id.iftarTextView);
            sehriTime = (TextView) findViewById(R.id.sehriTextView);

            String sharedPref = sharedPreferences.getString("DefaultDistrictName", "N/A");
            String str;
            if(sharedPref=="N/A"){
                str = "SharedPreference Error";
            }
            else{
                str = districtsTimeObject.getDistrictIndividualIftarTime(sharedPref);
                iftarTime.setText(str);
            }

            //Toast.makeText(getApplicationContext(),str,Toast.LENGTH_LONG).show();


           System.out.println("Iftar Time ->>> "+ str + " Afsus!");
        }



    }

    public void  saveDistrict(View view){
        districtName = autoCompleteTextView.getText().toString();
        districtName = districtsTimeObject.removeEndSpace(districtName).toLowerCase();

        if(districtsTimeObject.isDistrictPresent(districtName)){
            editor.putString("DefaultDistrictName",districtName);
            editor.putString("DistrictTime", districtsTimeObject.getDistrictPlusMinusTime(districtName));
            editor.commit();
            Toast.makeText(getApplicationContext(),districtName.substring(0,1).toUpperCase() + districtName.substring(1)+" is your Default District",Toast.LENGTH_LONG).show();

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

        return super.onOptionsItemSelected(item);
    }
}

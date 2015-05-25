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


public class SehriAndIfterShortForm extends ActionBarActivity {

    String DEFAULT = "N/A";

    SharedPreferences sharedPreferences = getSharedPreferences("DistrictInputFlag", Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPreferences.edit();
    AutoCompleteTextView autoCompleteTextView;

    //This part is not completed!
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        String flagString = sharedPreferences.getString("DistrictInputFlag", DEFAULT);

        if(flagString.equals(DEFAULT)){
            editor.putString("DistrictInputFlag","true");
            editor.commit();
            setContentView(R.layout.distric_input);


            String[] countries = getResources().
                    getStringArray(R.array.list_of_districts);
            ArrayAdapter adapter = new ArrayAdapter
                    (this,android.R.layout.simple_list_item_1,countries);
            autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
            autoCompleteTextView.setAdapter(adapter);


        }
        else {
            setContentView(R.layout.activity_sehri_and_ifter_short_form);
        }


    }

    public void  saveDistrict(View view){

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

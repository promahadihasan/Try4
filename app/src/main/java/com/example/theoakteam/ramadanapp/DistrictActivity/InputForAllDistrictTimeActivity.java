package com.example.theoakteam.ramadanapp.DistrictActivity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.theoakteam.ramadanapp.R;
//h
public class InputForAllDistrictTimeActivity extends ActionBarActivity {

    AutoCompleteTextView allDistrictAutoCompleteTextView;
    DistrictsTimeClass districtsTimeObject = new DistrictsTimeClass();
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.002F);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_for_all_district_time);

        String[] districts = getResources().getStringArray(R.array.list_of_districts);
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,districts);
        allDistrictAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.allDistrictAutoCompleteTextView);
        allDistrictAutoCompleteTextView.setAdapter(adapter);


    }

    public void allDistrictShowTimeButton(View v){
        v.startAnimation(buttonClick);
        String tempDistrict;

        try{

            tempDistrict = allDistrictAutoCompleteTextView.getText().toString();
            tempDistrict = districtsTimeObject.removeEndSpace(tempDistrict).toLowerCase();

            if(districtsTimeObject.isDistrictPresent(tempDistrict)){

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

                    String plusMinus = districtsTimeObject.getDistrictPlusMinusTime(tempDistrict);
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
                bundle.putString("districtName", tempDistrict);

                Intent intent = new Intent(InputForAllDistrictTimeActivity.this, DistrictAllTimeShow.class);
                intent.putExtras(bundle);
                startActivity(intent);

                Toast.makeText(getApplicationContext(), tempDistrict.substring(0, 1).toUpperCase() + tempDistrict.substring(1) + " is your Required District", Toast.LENGTH_LONG).show();

                //sehriActivity();
                //setContentView(R.layout.activity_sehri_and_ifter_short_form);
            }
            else{

                Toast.makeText(getApplicationContext(),"Your District name is not correct!",Toast.LENGTH_LONG).show();

            }

        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), "Please enter your District Name", Toast.LENGTH_LONG).show();
        }


        //helpShowFullTime(allDistrictAutoCompleteTextView.getText().toString());
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_input_for_all_district_time, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

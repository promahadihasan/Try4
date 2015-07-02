package theoaktroop.appoframadan.DistrictActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import theoaktroop.appoframadan.R;

//h
public class InputForAllDistrictTimeActivity extends ActionBarActivity {

    AutoCompleteTextView allDistrictAutoCompleteTextView;
    DistrictsTimeClass districtsTimeObject = new DistrictsTimeClass();
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.002F);
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_for_all_district_time);
//
//        AdView mAdView = (AdView) findViewById(R.id.adView1);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);
addVisibile();
        String[] districts = getResources().getStringArray(R.array.list_of_districts);
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,districts);
        allDistrictAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.allDistrictAutoCompleteTextView);
        allDistrictAutoCompleteTextView.setAdapter(adapter);

        allDistrictAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

            }
        });

        sharedPreferences = getSharedPreferences("RamadanAppData", Context.MODE_PRIVATE);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    }
    private void addVisibile() {

        LinearLayout adLinearLayout=(LinearLayout)findViewById(R.id.addViewinput);
        if(isNetworkAvailable()) {
            adLinearLayout.setVisibility(View.VISIBLE);
            AdView mAdView;
            mAdView = (AdView) findViewById(R.id.adView1);
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
        }
        else {
            adLinearLayout.setVisibility(View.GONE);
        }
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void allDistrictShowTimeButton(View v){
        v.startAnimation(buttonClick);
        String tempDistrict;
        int dateMinus = sharedPreferences.getInt("DateMinus",1);

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

                int engDate, flag=0;

                if(dateMinus==0){
                    engDate = 18;
                }
                else{
                    engDate = 19;
                }

                String engMonth="06";
                for(int ramadanDate=1; ramadanDate<=30; ramadanDate++,engDate++){

                    if(flag==0 && engDate>30){
                        engDate=1;
                        engMonth = "07";
                        flag = 1;
                    }

                    String dateStringFull;
                    if(engDate<10){
                        dateStringFull = "0" + String.valueOf(engDate) + "/" + engMonth + "/" + "15";
                    }
                    else {
                        dateStringFull = String.valueOf(engDate) + "/" + engMonth + "/" + "15";

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
        addVisibile();
        finish();
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.second_activity_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

package com.example.theoakteam.ramadanapp.SettingActivity;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.theoakteam.ramadanapp.DistrictActivity.DistrictsTimeClass;
import com.example.theoakteam.ramadanapp.NavigationDrawerActivity.NavigationDrawerFragment;
import com.example.theoakteam.ramadanapp.NotificationChallenging.NotifyingService;
import com.example.theoakteam.ramadanapp.R;

import java.util.Calendar;


public class SettingsActivity
        extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    //upper code need for Drawer

    CheckBox checkBoxUser;
    private Button pickTime;
    private StringBuilder timeString;

    private  int pfinalHour;
    private int pfinalMinute;
    private int pHour;
    private int pMinute;
    private String AMPM;
    DistrictsTimeClass districtsTimeObject = new DistrictsTimeClass();
    private LinearLayout userTimeLayout;
    boolean makeSureButtonCheckhed=false;

//    private EditText tasbihEditText;
    private AutoCompleteTextView autoCompleteTextView;
//    private Button tasbihButton;
    private Button districtButton;


    static final int TIME_DIALOG_ID = 0;
    String checkShareprefernce="ok";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    private long finalHour;
    private long finalMinute;
    private int checkBoxcheck;
    private String preTime;
    boolean flagOnstop=false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        intilizationOfViews();
//        tasbihInitialization();
        districtInitialization();

        if(sharedPreferences.contains("time"))
        {  preTime=sharedPreferences.getString("time",checkShareprefernce);
            pickTime.setText(preTime);

        }
        if(sharedPreferences.contains("checkbox"))
        {
            checkBoxcheck=sharedPreferences.getInt("checkbox",6);
            if(checkBoxcheck==0)
            {
                callChechkBoxMakeGone();
            }
            else if(checkBoxcheck==1){
                callChechkBoxMakeVisibile();
            }
        }


        checkBoxUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxUser.isChecked() == false) {
                    callChechkBoxMakeGone();
                }
                if (checkBoxUser.isChecked() == true) {
                    callChechkBoxMakeVisibile();

                }
            }
        });


        pickTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                timeString = new StringBuilder();
                final Calendar cal = Calendar.getInstance();
                pHour = cal.get(Calendar.HOUR_OF_DAY);
                pMinute = cal.get(Calendar.MINUTE);
                showDialog(TIME_DIALOG_ID);
                makeSureButtonCheckhed = true;


            }
        });




        //below code use for drawer
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
        //upper code use for drawer

    }


    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_activity_quran);
                break;
            case 2:
                mTitle = getString(R.string.title_activity_hadith);
                break;
            case 3:
                mTitle = getString(R.string.title_activity_masala);
                break;
            case 4:
                mTitle = getString(R.string.title_activity_dua);
                break;
            case 5:
                mTitle = getString(R.string.title_activity_tasbih);
                break;
            case 6:
                mTitle = getString(R.string.title_activity_sehri_and_ifter_short_form);
                break;
            case 7:
                mTitle = getString(R.string.title_activity_alarm);
                break;
            case 8:
                mTitle = getString(R.string.title_activity_food_habit);
                break;
            case 9:
                mTitle = getString(R.string.title_settings);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_drawer_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            //write here youre current activity
            ((SettingsActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }





    private void callChechkBoxMakeGone() {
        checkBoxUser.setChecked(false);
        editor.putInt("checkbox", 0);
        userTimeLayout.setVisibility(View.GONE);

    }

    private void callChechkBoxMakeVisibile() {
        checkBoxUser.setChecked(true);
        editor.putInt("checkbox", 1);
        if(preTime!=null && preTime.length()!=0)
        {
            pickTime.setText(preTime);
        }
        else {
            pickTime.setText(getResources().getString(R.string.txt_time));
        }
        userTimeLayout.setVisibility(View.VISIBLE);
    }

    //Hasan's Code area start
//    public void tasbihInitialization() {
//        sharedPreferences = getSharedPreferences("RamadanAppData", Context.MODE_PRIVATE);
//        String counterString = sharedPreferences.getString("tasbihCounter","0");
//        tasbihEditText.setText(counterString);
//        System.out.println("Hungki pungki: " + counterString);
//    }
    public void districtInitialization(){
        sharedPreferences = getSharedPreferences("RamadanAppData", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String districtString = sharedPreferences.getString("DefaultDistrictName","0");
        autoCompleteTextView.setText(districtString.substring(0,1).toUpperCase() + districtString.substring(1));

        String[] districts = getResources().getStringArray(R.array.list_of_districts);
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,districts);
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        autoCompleteTextView.setAdapter(adapter);

    }
//    public void tasbihCounterSet(View v){
//        sharedPreferences = getSharedPreferences("RamadanAppData", Context.MODE_PRIVATE);
//        editor = sharedPreferences.edit();
//        String tasbihCounter = tasbihEditText.getText().toString();
//        editor.putString("tasbihCounter", tasbihCounter);
//        editor.commit();
//        System.out.println("Huh... "+sharedPreferences.getString("tasbihCounter","N/A"));
//        Toast.makeText(getApplicationContext(),"Tasbih counter is "+tasbihCounter, Toast.LENGTH_LONG).show();
//    }
    public void saveDistrict(View v){
        String districtName = autoCompleteTextView.getText().toString();
        districtName = districtsTimeObject.removeEndSpace(districtName).toLowerCase();
        sharedPreferences = getSharedPreferences("RamadanAppData", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        if(districtsTimeObject.isDistrictPresent(districtName)){
            editor.putString("DefaultDistrictName",districtName);
            editor.putString("DistrictTime", districtsTimeObject.getDistrictPlusMinusTime(districtName));
            editor.commit();
            Toast.makeText(getApplicationContext(),districtName.substring(0,1).toUpperCase() + districtName.substring(1)+" is your Default District",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(getApplicationContext(),"Your District name is not correct!",Toast.LENGTH_LONG).show();
        }
    }
    //Hasan's Code area end




    private void intilizationOfViews() {
        pickTime = (Button) findViewById(R.id.bttimeshow);
        checkBoxUser = (CheckBox) findViewById(R.id.checkBoxmain);
        userTimeLayout=(LinearLayout)findViewById(R.id.notification_user_time);
        timeString  =new StringBuilder();
        sharedPreferences = getSharedPreferences("RamadanAppData", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        //Added by Hasan
//        tasbihEditText = (EditText) findViewById(R.id.tasbihCounterSetEditText);
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
//        tasbihButton = (Button) findViewById(R.id.tasbihCounterButton);
        districtButton = (Button) findViewById(R.id.districtButton);

    }

    private void timeCalculation()
    {
        //for background thread
        final Calendar cal = Calendar.getInstance();
       int nowHour	= cal.get(Calendar.HOUR_OF_DAY);
       int nowMinute = cal.get(Calendar.MINUTE);

        finalHour=pfinalHour-nowHour;
        finalMinute=pfinalMinute-nowMinute;
        if(finalMinute<0)
        {
            finalMinute+=60;
            finalHour-=1;
        }
        if(finalHour<0)
        {
            finalHour+=24;
        }
        System.out.println("Final Minute=" + finalMinute);
        System.out.println("Final Hour=" + finalHour);
        finalMinute+=finalHour*60;
        System.out.println("Final Minute aftercalculation=" + finalMinute);
        System.out.println("Final Milliseconds aftercalculation=" + finalMinute*60*1000);


    }


    @Override
    protected void onPause() {
        super.onPause();

        if(makeSureButtonCheckhed==true ) {

            editor.putString("time", timeString.toString());
            editor.putLong("finaltime",finalMinute);
            editor.commit();
            if (finalMinute!=0)
            {Intent serviceIntent=new Intent(getApplicationContext(),NotifyingService.class);

            startService(serviceIntent);}



            makeSureButtonCheckhed=false;
        }
        else {
            editor.commit();

            Intent serviceIntent=new Intent(getApplicationContext(),NotifyingService.class);

                stopService(serviceIntent);}
        finish();

    }


    private TimePickerDialog.OnTimeSetListener mTimeSetListener =
            new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int hourOfDay, int minute ) {
                    pfinalHour=hourOfDay;
                    pfinalMinute=minute;
                    pMinute = minute;
                    if(hourOfDay<12) {
                        AMPM = " AM";
                        pHour = hourOfDay;
                    }
                    else { pHour = hourOfDay-12;
                        AMPM=" PM";
                    }
                    if(pHour==0){
                        pHour=12;
                    }
                    updateDisplay();


                }
            };
    /** Add padding to numbers less than ten */
    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }

    private void updateDisplay() {

        timeString.append(pad(pHour)).append(":");
        timeString.append(pad(pMinute));
        timeString.append(AMPM);
        if(pickTime.getContext().toString().length()>0) {
            pickTime.setText(timeString.toString());
        }
        timeCalculation();

    }





    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case TIME_DIALOG_ID:
                return new TimePickerDialog(this,
                        mTimeSetListener, pHour, pMinute+1, false);
        }
        return null;
    }



}

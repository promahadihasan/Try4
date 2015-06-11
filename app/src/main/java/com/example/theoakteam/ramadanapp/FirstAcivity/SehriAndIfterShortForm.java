package com.example.theoakteam.ramadanapp.FirstAcivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.theoakteam.ramadanapp.DistrictActivity.DistrictAllTimeShow;
import com.example.theoakteam.ramadanapp.DistrictActivity.DistrictsTimeClass;

import com.example.theoakteam.ramadanapp.DistrictActivity.InputForAllDistrictTimeActivity;
import com.example.theoakteam.ramadanapp.NavigationDrawerActivity.NavigationDrawerFragment;
import com.example.theoakteam.ramadanapp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class SehriAndIfterShortForm extends ActionBarActivity
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
  private   Intent alarmIntent;
    String DEFAULT = "N/A";
    String districtName;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    AutoCompleteTextView autoCompleteTextView;
    AutoCompleteTextView allDistrictAutoCompleteTextView;
    DistrictsTimeClass districtsTimeObject = new DistrictsTimeClass();
    TextView iftarTime;
    TextView sehriTime;
    TextView sehriNote;
    TextView englishDate;
    TextView arabicDate;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.002F);
    String hourForAlarmSeheri = "0";
    String minuteForAlarmSeheri = "00";
    String hourForAlarmIfter = "0";
    String minuteForAlarmIfter = "00";
    int flagForPostResume=0;
    private TimePickerDialog aTimePickerDialog;


    private int pHourSeheri;
    private int pHourIfter;
    private int pMinuteSehri;
    private int pMinuteIfter;
    static final int TIME_DIALOG_ID_Seheri = 0;
    static final int TIME_DIALOG_ID_Ifter = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getSharedPreferences("RamadanAppData", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String flagString = sharedPreferences.getString("DistrictInputFlag", DEFAULT);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor(getResources().getString(R.string.actionbar_color))));
        if(flagString.equals(DEFAULT)){

            setContentView(R.layout.distric_input);

            String[] districts = getResources().getStringArray(R.array.list_of_districts);
            ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,districts);
            autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
            autoCompleteTextView.setAdapter(adapter);



        }
        else{

            setContentView(R.layout.activity_sehri_and_ifter_short_form);
            drawerHelper();
            sehriActivity();

        }



    }

    private  void drawerHelper(){
    //below code use for drawer
    mNavigationDrawerFragment = (NavigationDrawerFragment)
            getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
   // mTitle = getTitle();

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
                mTitle = getString(R.string.title_activity_sehri_and_ifter_short_form);
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
                mTitle = getString(R.string.title_activity_food_habit);
                break;
            case 7:
                mTitle = getString(R.string.title_settings);
                break;

        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        //actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setHomeAsUpIndicator(R.drawable.logo);

       // actionBar.setTitle(mTitle);
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
            ((SehriAndIfterShortForm) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }




    // Hasan's Code Start

    public void showFullTime(View v){
        v.startAnimation(buttonClick);

        helpShowFullTime(districtName);

    }

    public void helpShowFullTime(String districtNameTemp){

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
                dateStringFull = "0" + String.valueOf(engDate) + "/" + engMonth + "/" + "15";
            }
            else {
                dateStringFull = String.valueOf(engDate) + "/" + engMonth + "/" + "15";

            }

            String plusMinus = districtsTimeObject.getDistrictPlusMinusTime(districtNameTemp);
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
        bundle.putString("districtName", districtNameTemp);

        Intent intent = new Intent(SehriAndIfterShortForm.this, DistrictAllTimeShow.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void showAllDistrict(View v){
        v.startAnimation(buttonClick);
        Intent intent = new Intent(SehriAndIfterShortForm.this, InputForAllDistrictTimeActivity.class);
        startActivity(intent);
    }



    public void sehriActivity(){

        iftarTime = (TextView) findViewById(R.id.iftarTextView);
        sehriTime = (TextView) findViewById(R.id.sehriTextView);
        sehriNote = (TextView) findViewById(R.id.sehri_iftar_initial_note);
        englishDate = (TextView) findViewById(R.id.dateEnglishTextView);
        arabicDate = (TextView) findViewById(R.id.arabicTextView);
        String iftarTimeString;
        String sehriTimeString;

        districtName = sharedPreferences.getString("DefaultDistrictName", "N/A");


            String dateString = getDate();
            englishDate.setText(getResources().getString(R.string.date_text_view) + " " + dateString);

            if(districtsTimeObject.isDateValid(dateString)){
                String arabicMonth = districtsTimeObject.findMonth(dateString);
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

                sehriNote.setText(districtName.substring(0, 1).toUpperCase() + districtName.substring(1) + "  " + getText(R.string.sehri_iftar_first_note));
                sehriTime.setText(sehriTimeString + " am");
                iftarTime.setText(iftarTimeString + " pm");
                String alarmTimeSeheri = districtsTimeObject.calculateTime(sehriTimeString,"-60");

                hourForAlarmSeheri = "" + alarmTimeSeheri.charAt(0);
                minuteForAlarmSeheri = alarmTimeSeheri.substring(2,4);
                String alarmTimeIfter = districtsTimeObject.calculateTime(iftarTimeString,"-05");

                hourForAlarmIfter=""+alarmTimeIfter.charAt(0);
                minuteForAlarmIfter=""+alarmTimeIfter.substring(2,4);
                //System.out.println("Testing Time -->>: "+hourForAlarmSeheri+":"+minuteForAlarmSeheri);

            }
            else{

                TextView noteTextView = (TextView) findViewById(R.id.date_validity_check_note);
                noteTextView.setText(getString(R.string.date_check_note));
            }



    }
    public void DialogeForSeheriChange(View view){
        new AlertDialog.Builder(this)
                .setTitle("Information")
                .setMessage("Oh! You can change your district from settings")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {


                    }
                })

                .setIcon(android.R.drawable.ic_dialog_info)
                .show();
    }

    public void alarmSet(View v){
       alarmIntent=new  Intent(AlarmClock.ACTION_SET_ALARM);

        if(v.getId()==R.id.alarmButton) {
            String titleSeheri = getResources().getString(R.string.sehri_last) + " " + getResources().getString(R.string.title_activity_alarm);


            //ss

            pHourSeheri =Integer.parseInt(hourForAlarmSeheri);
            pMinuteSehri = Integer.parseInt(minuteForAlarmSeheri);

            alarmIntent.putExtra(AlarmClock.EXTRA_MESSAGE, titleSeheri);
            showDialog(TIME_DIALOG_ID_Seheri);







        }
        if (v.getId()==R.id.alarmButton2){
            String titleIfter=getString(R.string.title_ifter_alarm)+" "+getString(R.string.title_activity_alarm);


            pHourIfter =Integer.parseInt(hourForAlarmIfter);
            pMinuteIfter = Integer.parseInt(minuteForAlarmIfter);
            alarmIntent.putExtra(AlarmClock.EXTRA_MESSAGE, titleIfter);

            showDialog(TIME_DIALOG_ID_Ifter);


        }



    }

private TimePickerDialog.OnTimeSetListener mTimeSetListener =
        new TimePickerDialog.OnTimeSetListener() {
public void onTimeSet(TimePicker view, int hourOfDay, int minute ) {



    alarmIntent.putExtra(AlarmClock.EXTRA_HOUR, hourOfDay);
    alarmIntent.putExtra(AlarmClock.EXTRA_MINUTES, minute);
    startActivity(alarmIntent);


        }
        };

@Override
protected Dialog onCreateDialog(int id) {
        switch (id) {
        case TIME_DIALOG_ID_Seheri:

        return new TimePickerDialog(this,
        mTimeSetListener, pHourSeheri, pMinuteSehri, false);

    case TIME_DIALOG_ID_Ifter:
    return new TimePickerDialog(this,
            mTimeSetListener, pHourIfter+12, pMinuteIfter, false);
}
        return null;
        }



    public String getDate(){
        Date date = new Date();

        SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy");

        return ft.format(date).toString();
    }

    public void  saveDistrict(View view){
        view.startAnimation(buttonClick);

        try{

            districtName = autoCompleteTextView.getText().toString();
            districtName = districtsTimeObject.removeEndSpace(districtName).toLowerCase();

            if(districtsTimeObject.isDistrictPresent(districtName)){
                editor.putString("DefaultDistrictName", districtName);
                editor.putString("DistrictTime", districtsTimeObject.getDistrictPlusMinusTime(districtName));
                editor.putString("DistrictInputFlag","true");
                editor.commit();
                Toast.makeText(getApplicationContext(),districtName.substring(0,1).toUpperCase() + districtName.substring(1)+" is your Default District",Toast.LENGTH_LONG).show();


                setContentView(R.layout.activity_sehri_and_ifter_short_form);
                sehriActivity();
                drawerHelper();
            }
            else{

                Toast.makeText(getApplicationContext(),"Your District name is not correct!",Toast.LENGTH_LONG).show();

            }

        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), "Please enter your District Name", Toast.LENGTH_LONG).show();
        }



    }



    @Override
    protected void onPostResume() {
        super.onPostResume();


        sharedPreferences = getSharedPreferences("RamadanAppData", Context.MODE_PRIVATE);

        String flagString = sharedPreferences.getString("DistrictInputFlag", DEFAULT);
        if(!flagString.equals(DEFAULT)) {
            ActionBar actionBar = getSupportActionBar();
            actionBar.setTitle(getString(R.string.title_activity_sehri_and_ifter_short_form));
            sehriActivity();
        }
    }
}


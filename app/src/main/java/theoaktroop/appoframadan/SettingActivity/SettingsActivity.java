package theoaktroop.appoframadan.SettingActivity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TimePicker;
import android.widget.Toast;

import theoaktroop.appoframadan.FirstAcivity.SehriAndIfterShortForm;
import theoaktroop.appoframadan.NotificationChallenging.AlarmReceiver;
import theoaktroop.appoframadan.NotificationChallenging.NotificationModule;
import theoaktroop.appoframadan.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Calendar;

import theoaktroop.appoframadan.DistrictActivity.DistrictsTimeClass;
import theoaktroop.appoframadan.NavigationDrawerActivity.NavigationDrawerFragment;



public class SettingsActivity
        extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    NotificationModule notificationModule;
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    //upper code need for Drawer
  private   AlarmManager alarmManager;
    CheckBox checkBoxUser;
    private Button pickTime;
    private StringBuilder timeString;

    private  int pfinalHour;
    private int pfinalMinute;
    private int pHour;
    private int pMinute;
    static final int TIME_DIALOG_ID = 0;
    private String AMPM;
    DistrictsTimeClass districtsTimeObject = new DistrictsTimeClass();
    private LinearLayout userTimeLayout;
    boolean makeSureButtonCheckhed=false;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.002F);
    private RadioButton radio1;
    private RadioButton radio2;

//    private EditText tasbihEditText;
    private AutoCompleteTextView autoCompleteTextView;
//    private Button tasbihButton;
    private Button districtButton;



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

        AdView mAdView = (AdView) findViewById(R.id.adViewSettings);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        intilizationOfViews();
        notificationModule=new NotificationModule();
//        tasbihInitialization();
        districtInitialization();


        if(sharedPreferences.contains("checkbox"))
        {
            checkBoxcheck=sharedPreferences.getInt("checkbox",0);
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

    private void callChechkBoxMakeGone() {
        checkBoxUser.setChecked(false);
        editor.putInt("checkbox", 0);
        editor.putBoolean("on",false);
        editor.commit();


    }

    private void callChechkBoxMakeVisibile() {
        checkBoxUser.setChecked(true);

        editor.putInt("checkbox", 1);
        editor.putBoolean("on",false);
        editor.commit();


    }

    public void saveRadioButton(View view){
        view.startAnimation(buttonClick);

        if(radio1.isChecked()){
            editor.putInt("DateMinus",0);
        }
        else{
            editor.putInt("DateMinus",1);
        }
        editor.commit();

        Toast.makeText(getApplicationContext(), "Saved succesfully!", Toast.LENGTH_SHORT).show();

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







    public void districtInitialization(){
        sharedPreferences = getSharedPreferences("RamadanAppData", Context.MODE_PRIVATE);
//        editor = sharedPreferences.edit();
        String districtString = sharedPreferences.getString("DefaultDistrictName","0");
        autoCompleteTextView.setText(districtString.substring(0,1).toUpperCase() + districtString.substring(1));

        String[] districts = getResources().getStringArray(R.array.list_of_districts);
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,districts);
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setSelection(autoCompleteTextView.getText().length());

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

            }
        });





//        autoCompleteTextView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (hasFocus) {
//                    autoCompleteTextView.setText("");
//                }
//            }
//
//        });
    }

    public void saveDistrict(View v){
        v.startAnimation(buttonClick);

        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

        String districtName = autoCompleteTextView.getText().toString();
        districtName = districtsTimeObject.removeEndSpace(districtName).toLowerCase();
        if(districtsTimeObject.isDistrictPresent(districtName) && districtName.length()>0){

            sharedPreferences = getSharedPreferences("RamadanAppData", Context.MODE_PRIVATE);

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

        checkBoxUser = (CheckBox) findViewById(R.id.checkBoxmain);


        sharedPreferences = getSharedPreferences("RamadanAppData", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        //Added by Hasan
//        tasbihEditText = (EditText) findViewById(R.id.tasbihCounterSetEditText);
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
//        tasbihButton = (Button) findViewById(R.id.tasbihCounterButton);
        districtButton = (Button) findViewById(R.id.districtButton);

        radio1 = (RadioButton) findViewById(R.id.first_ramadan);
        radio2 = (RadioButton) findViewById(R.id.second_ramadan);

        if(sharedPreferences.getInt("DateMinus",1)==0){
            radio1.setChecked(true);
            radio2.setChecked(false);
        }
        else {
            radio2.setChecked(true);
            radio1.setChecked(false);
        }


    }
//



    @Override
    protected void onPause() {
        super.onPause();

        sharedPreferences = getSharedPreferences("RamadanAppData", Context.MODE_PRIVATE);

        editor=sharedPreferences.edit();
        int ot = getResources().getConfiguration().orientation;
        if(ot== Configuration.ORIENTATION_LANDSCAPE){
            editor.putInt("flagOS",1);
            editor.commit();

        }
        else if(ot==Configuration.ORIENTATION_PORTRAIT && sharedPreferences.getInt("flagOS",0)!=0) {
            editor.putInt("flagOS",0);
            editor.commit();
        }

        else if(ot==Configuration.ORIENTATION_PORTRAIT && sharedPreferences.getInt("flagOS",0)==0)
        {
            finish();

        }

    }









    @Override
    protected void onPostResume() {
        super.onPostResume();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getString(R.string.action_settings));
    }


}

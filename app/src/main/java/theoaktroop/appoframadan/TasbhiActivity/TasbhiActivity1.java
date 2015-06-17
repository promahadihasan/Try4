package theoaktroop.appoframadan.TasbhiActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import theoaktroop.appoframadan.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import theoaktroop.appoframadan.NavigationDrawerActivity.NavigationDrawerFragment;


/**
 * Created by Sunny-PC on 5/24/2015.
 */
public class TasbhiActivity1
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

    ImageButton counterButton;
    Button resetButton;
    Integer currentCounter;
    TextView textView;
    TasbihClass1 tasbih=new TasbihClass1();
    String counterString;
    String DEFAULT;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
private int flagForOrientation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tasbhi_activity_xml);

        AdView mAdView = (AdView) findViewById(R.id.adView1);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        textView = (TextView) findViewById(R.id.textView);
        DEFAULT = "N/A";

        Toast.makeText(getApplicationContext(),"Click anywhere of this screen", Toast.LENGTH_LONG).show();

        sharedPreferences = getSharedPreferences("RamadanAppData", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        counterString = sharedPreferences.getString("tasbihCounter",DEFAULT);
        if(counterString.equals(DEFAULT)){
            currentCounter = 0;
            tasbih.setCounter(0);
            textView.setText("0");

        }
        else{
            textView.setText(counterString);
            currentCounter = Integer.parseInt(counterString);
            tasbih.setCounter(currentCounter);
        }
        //below code use for drawer
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
        //upper code use for drawer ddff

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
            ((TasbhiActivity1) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }




    public void tasbihCount(View v){
        currentCounter = tasbih.tasbihCounter();
        textView.setText(currentCounter.toString());
    }

//    public  void  tasbihReset(View v){
//
//
//
//    }

    @Override
    protected void onPause() {
        super.onPause();

           sharedPreferences = getSharedPreferences("RamadanAppData", Context.MODE_PRIVATE);

           editor.putString("tasbihCounter", currentCounter.toString());
           editor.commit();
        int ot = getResources().getConfiguration().orientation;
        if(ot== Configuration.ORIENTATION_LANDSCAPE){
            editor.putInt("flagO",1);
            editor.commit();

        }
        else if(ot==Configuration.ORIENTATION_PORTRAIT && sharedPreferences.getInt("flagO",0)!=0) {
            editor.putInt("flagO",0);
            editor.commit();
        }

       else if(ot==Configuration.ORIENTATION_PORTRAIT && sharedPreferences.getInt("flagO",0)==0)
        {
         finish();

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
        if (id == R.id.tasbih_reset_menu) {

            new AlertDialog.Builder(this)
                    .setTitle("Reset?")
                    .setMessage("Are you sure you want to reset Tasbih?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            tasbih.setCounter(0);
                            currentCounter=0;
                            textView.setText("0");

                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();

            return true;
        }


        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onPostResume() {
        super.onPostResume();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getString(R.string.title_activity_tasbih));}

}

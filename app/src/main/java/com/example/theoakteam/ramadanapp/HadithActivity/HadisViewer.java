package com.example.theoakteam.ramadanapp.HadithActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.theoakteam.ramadanapp.NavigationDrawerActivity.NavigationDrawerFragment;
import com.example.theoakteam.ramadanapp.R;

/**
 * Created by Sunny_PC on 6/3/2015.
 * this hadith
 */
public class HadisViewer         extends ActionBarActivity
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
    private String[] hidithTitle_details;
    private TextView noTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_details);
        Intent myIntent=getIntent();

        int i=Integer.parseInt(myIntent.getStringExtra("index"));

        hidithTitle_details = getResources().getStringArray(R.array.hadis_details);
        noTextView=(TextView)findViewById(R.id.txtview_notification);
        noTextView.setText(hidithTitle_details[i]);
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
            ((HadisViewer) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }



}

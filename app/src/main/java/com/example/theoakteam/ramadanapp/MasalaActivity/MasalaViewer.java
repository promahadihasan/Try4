package com.example.theoakteam.ramadanapp.MasalaActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.theoakteam.ramadanapp.NavigationDrawerActivity.NavigationDrawerFragment;
import com.example.theoakteam.ramadanapp.R;

/**
 * Created by Sunny_PC on 6/3/2015.
 * this hadith
 */
public class MasalaViewer   extends ActionBarActivity
{
    private String[] masalaTitle_details;
    private TextView noTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_details);
        Intent myIntent = getIntent();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor(getResources().getString(R.string.actionbar_color))));
        int i = Integer.parseInt(myIntent.getStringExtra("indexformasala"));
        System.out.println(i);
        masalaTitle_details = getResources().getStringArray(R.array.masala_details);
        noTextView = (TextView) findViewById(R.id.txtview_notification);
        noTextView.setText(masalaTitle_details[i]);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}




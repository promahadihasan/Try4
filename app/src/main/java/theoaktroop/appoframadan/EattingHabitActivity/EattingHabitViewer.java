package theoaktroop.appoframadan.EattingHabitActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import theoaktroop.appoframadan.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

/**
 * Created by Sunny_PC on 6/3/2015.
 * this hadith
 */
public class EattingHabitViewer extends ActionBarActivity
{
    private String[] EattingHabitTitle_details;
    private TextView noTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_details);
        addVisibile();
        Intent myIntent = getIntent();

//        AdView mAdView = (AdView) findViewById(R.id.adView1);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor(getResources().getString(R.string.actionbar_color))));
        int i = Integer.parseInt(myIntent.getStringExtra("indexOfEattingHabit"));

        EattingHabitTitle_details = getResources().getStringArray(R.array.eatting_details);
        noTextView = (TextView) findViewById(R.id.txtview_notification);
        noTextView.setText(EattingHabitTitle_details[i]);
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
        SharedPreferences sharedPreferences;
        sharedPreferences = getSharedPreferences("RamadanAppData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();

        int ot = getResources().getConfiguration().orientation;
        if(ot== Configuration.ORIENTATION_LANDSCAPE){
            editor.putInt("flagOEV",1);
            editor.commit();

        }
        else if(ot==Configuration.ORIENTATION_PORTRAIT && sharedPreferences.getInt("flagOEV",0)!=0) {
            editor.putInt("flagOEV",0);
            editor.commit();
        }

        else if(ot==Configuration.ORIENTATION_PORTRAIT && sharedPreferences.getInt("flagOEV",0)==0 )
        {
            finish();
        }
    }

    private void addVisibile() {

        LinearLayout adLinearLayout=(LinearLayout)findViewById(R.id.addViewNotification);
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

}

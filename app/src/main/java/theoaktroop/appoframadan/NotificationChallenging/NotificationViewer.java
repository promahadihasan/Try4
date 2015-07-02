package theoaktroop.appoframadan.NotificationChallenging;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import theoaktroop.appoframadan.FirstAcivity.SehriAndIfterShortForm;
import theoaktroop.appoframadan.R;

/**
 * Created by Sunny_PC on 6/3/2015.
 */
public class NotificationViewer extends ActionBarActivity
{
    private TextView noTextView;
    private String[] notificationStringArray;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_details);
        sharedPreferences = getSharedPreferences("RamadanAppData", Context.MODE_PRIVATE);
        addVisibile();
//        AdView mAdView = (AdView) findViewById(R.id.adView1);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);

       System.out.println("Notification Viewer");
        i=sharedPreferences.getInt("indexofnotificaton",1);

        notificationStringArray=getResources().getStringArray(R.array.notification_messages);
        noTextView=(TextView)findViewById(R.id.txtview_notification);
        System.out.println("indexofnotificaton from Viewer"+i);

        noTextView.setText(notificationStringArray[i]+"\n\n\n"+getString(R.string.notification_footnote));


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
                //NavUtils.navigateUpFromSameTask(this);
                startActivity(new Intent(this, SehriAndIfterShortForm.class));
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, SehriAndIfterShortForm.class));
        finish();
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

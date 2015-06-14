package theoaktroop.appoframadan.NotificationChallenging;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

import theoaktroop.appoframadan.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import theoaktroop.appoframadan.FirstAcivity.SehriAndIfterShortForm;

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
        AdView mAdView = (AdView) findViewById(R.id.adView1);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

System.out.println("Notification Viewer");
        i=sharedPreferences.getInt("indexofnotificaton",5)-1;

        notificationStringArray=getResources().getStringArray(R.array.notification_messages);
        noTextView=(TextView)findViewById(R.id.txtview_notification);
        System.out.println("indexofnotificaton from Viewer"+i);

        noTextView.setText(notificationStringArray[i]+"\n\n\n"+getString(R.string.notification_footnote));


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, SehriAndIfterShortForm.class));
        finish();
    }
}

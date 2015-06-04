package com.example.theoakteam.ramadanapp.NotificationChallenging;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

import com.example.theoakteam.ramadanapp.R;
import com.example.theoakteam.ramadanapp.SehriAndIfterShortForm;

/**
 * Created by Sunny_PC on 6/3/2015.
 */
public class NotificationViewer extends ActionBarActivity {

    private TextView noTextView;
    private String[] notificationStringArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_details);
        Intent myIntent=getIntent();
        int i=Integer.parseInt(myIntent.getStringExtra("indexofnotificaton"));
        notificationStringArray=getResources().getStringArray(R.array.notification_messages);
        noTextView=(TextView)findViewById(R.id.txtview_notification);
        System.out.println(i);
        noTextView.setText(notificationStringArray[i]);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, SehriAndIfterShortForm.class));
        finish();
    }
}

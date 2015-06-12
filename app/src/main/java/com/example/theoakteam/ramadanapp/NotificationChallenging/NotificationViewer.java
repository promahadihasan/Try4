package com.example.theoakteam.ramadanapp.NotificationChallenging;

import android.app.Activity;
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
import android.widget.TextView;

import com.example.theoakteam.ramadanapp.NavigationDrawerActivity.NavigationDrawerFragment;
import com.example.theoakteam.ramadanapp.R;
import com.example.theoakteam.ramadanapp.FirstAcivity.SehriAndIfterShortForm;

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

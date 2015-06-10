package com.example.theoakteam.ramadanapp.DuaDorutActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

import com.example.theoakteam.ramadanapp.R;

/**
 * Created by Sunny_PC on 6/3/2015.
 * this hadith
 */
public class DuaViewer extends ActionBarActivity {
    private String[] duaTitle_details;
    private TextView noTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_details);
        Intent myIntent=getIntent();

        int i=Integer.parseInt(myIntent.getStringExtra("indexOfdua"));
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor(getResources().getString(R.string.actionbar_color))));
        actionBar.setTitle(getString(R.string.title_activity_dua));
        duaTitle_details = getResources().getStringArray(R.array.dua_details);
        noTextView=(TextView)findViewById(R.id.txtview_notification);
        noTextView.setText(duaTitle_details[i]);

    }
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }



}

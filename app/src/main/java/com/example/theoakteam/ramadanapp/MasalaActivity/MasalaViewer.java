package com.example.theoakteam.ramadanapp.MasalaActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

import com.example.theoakteam.ramadanapp.R;

/**
 * Created by Sunny_PC on 6/3/2015.
 * this hadith
 */
public class MasalaViewer extends ActionBarActivity {

    private String[] masalaTitle_details;
    private TextView noTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_details);
        Intent myIntent=getIntent();

        int i=Integer.parseInt(myIntent.getStringExtra("indexformasala"));
        System.out.println(i);
        masalaTitle_details = getResources().getStringArray(R.array.masala_details);
        noTextView=(TextView)findViewById(R.id.txtview_notification);
        noTextView.setText(masalaTitle_details[i]);
    }


}

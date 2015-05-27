package com.example.theoakteam.ramadanapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.HashMap;

/**
 * Created by Sunny-PC on 5/24/2015.
 */
public class TasbhiActivity1 extends ActionBarActivity {

    ImageButton counterButton;
    Button resetButton;
    Integer currentCounter;
    TextView textView;
    TasbihClass1 tasbih=new TasbihClass1();
    String counterString;
    String DEFAULT;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tasbhi_activity_xml);

        textView = (TextView) findViewById(R.id.textView);
        DEFAULT = "N/A";

        sharedPreferences = getSharedPreferences("TasbihData", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        counterString = sharedPreferences.getString("tasbihCounter",DEFAULT);
        if(counterString.equals(DEFAULT)){
            tasbih.setCounter(0);
            textView.setText("0");
        }
        else{
            textView.setText(counterString);
            currentCounter = Integer.parseInt(counterString);
            tasbih.setCounter(currentCounter);
        }
    }


    public void tasbihCount(View v){
        currentCounter = tasbih.tasbihCounter();
        textView.setText(currentCounter.toString());
    }

    public  void  tasbihReset(View v){

        new AlertDialog.Builder(this)
                .setTitle("Reset?")
                .setMessage("Are you sure you want to reset Tasbih?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        tasbih.setCounter(0);
                        currentCounter=0;
                        textView.setText("0");

//                        sharedPreferences = getSharedPreferences("TasbihData", Context.MODE_PRIVATE);
//                        SharedPreferences.Editor editor = sharedPreferences.edit();
//                        editor.putString("tasbihCounter", "0");
//
//                        editor.commit();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }

    @Override
    protected void onPause() {
        super.onPause();

        //sharedPreferences = getSharedPreferences("TasbihData", Context.MODE_PRIVATE);

        editor.putString("tasbihCounter", currentCounter.toString());
        editor.commit();
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
        if (id == R.id.action_quran) {
            startActivity(new Intent(TasbhiActivity1.this, DisplaySuraActivity.class));
            finish();
            return true;
        }
        else if(id==R.id.actionseheri_ifter_time){
            startActivity(new Intent(TasbhiActivity1.this, SehriAndIfterShortForm.class));

            return true;
        }
        else if(id==R.id.action_alarm){
            Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
            startActivity(i);
            return  true;
        }

        return super.onOptionsItemSelected(item);
    }
}

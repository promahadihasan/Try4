package com.example.theoakteam.ramadanapp;

import android.content.Intent;
import android.provider.AlarmClock;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
//alarm compleate
//this is test for github
//this is text for github by Hasan Abdullah fgdfgfg
//fdgdf
public class MainActivity extends ActionBarActivity {


    public String[] arabicSuraarTitle={
            "Sura Fatiah ","Sura Fill"

    };
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=(ListView)findViewById(R.id.list_item);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,arabicSuraarTitle);
        //Typeface tf= Typeface.createFromAsset(getAssets(),"font/Rupali.ttf");

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    startActivity(new Intent(MainActivity.this, DisplaySuraActivity.class));
                }
            }
        });


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
        if (id == R.id.action_tasbhi) {
            Intent intent = new Intent(MainActivity.this, TasbhiActivity1.class);
            startActivity(intent);
            return true;
        }
       else  if (id == R.id.action_quran) {
            startActivity(new Intent(MainActivity.this, DisplaySuraActivity.class));

            return true;
        }
        else if(id==R.id.actionseheri_ifter_time){
            startActivity(new Intent(MainActivity.this, SehriAndIfterShortForm.class));

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

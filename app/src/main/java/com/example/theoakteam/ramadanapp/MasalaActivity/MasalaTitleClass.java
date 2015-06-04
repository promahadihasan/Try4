package com.example.theoakteam.ramadanapp.MasalaActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.theoakteam.ramadanapp.HadithActivity.HadisViewer;
import com.example.theoakteam.ramadanapp.HadithActivity.HadithTitleClass;
import com.example.theoakteam.ramadanapp.R;
import com.example.theoakteam.ramadanapp.SehriAndIfterShortForm;
import com.example.theoakteam.ramadanapp.SettingsActivity;
import com.example.theoakteam.ramadanapp.TasbhiActivity1;

/**
 * Created by Sunny_PC on 6/3/2015.
 */
public class MasalaTitleClass extends ActionBarActivity {

    private String[] masalaTitle;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //hadith it can be also use for masala_xml
        setContentView(R.layout.hadithtitle_xml);
        masalaTitle = getResources().getStringArray(R.array.masala_title);
        lv=(ListView)findViewById(R.id.list_item_hadith_title);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, masalaTitle);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent detailsHadithIntent=new Intent(MasalaTitleClass.this,MasalaViewer.class);
                detailsHadithIntent.putExtra("indexformasala", String.valueOf(position));
                startActivity(detailsHadithIntent);
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
            Intent intent = new Intent(this, TasbhiActivity1.class);
            startActivity(intent);
            finish();
            return true;
        }
        else if(id== R.id.actionseheri_ifter_time){
            startActivity(new Intent(this, SehriAndIfterShortForm.class));
            finish();
            return true;
        }
        else if(id== R.id.action_alarm){
            Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
            startActivity(i);
            finish();
            return  true;
        }
        else if(id== R.id.action_settings){
            Intent i = new Intent(this,SettingsActivity.class);
            startActivity(i);

            return  true;
        }
        else if(id== R.id.action_hadis){
            startActivity(new Intent(this, HadithTitleClass.class));
            finish();
            return true;
        }
        else if(id== R.id.action_masala){
            startActivity(new Intent(this, MasalaTitleClass.class));
            finish();
            return true;
        }



        return super.onOptionsItemSelected(item);

    }


}

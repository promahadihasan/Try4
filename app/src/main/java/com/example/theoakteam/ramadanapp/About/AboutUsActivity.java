package com.example.theoakteam.ramadanapp.About;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.theoakteam.ramadanapp.R;

/**
 * Created by Sunny_PC on 6/9/2015.
 * f
 */
public class AboutUsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_xml);
    }
    public void CalltoDeveloper(View view){

        Intent callIntent=new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+getString(R.string.moible_team)));
        startActivity(callIntent);
    }
    void FacebookAction2(View view){

        Intent webIntent=new Intent(Intent.ACTION_VIEW);

        webIntent.setData(Uri.parse(getString(R.string.title_team_fb)));
        startActivity(webIntent);
        }
    public void FacebookAction(View view){

        String Url = null;
        switch (view.getId()){
            case R.id.hasan_fb:
                Url=getString(R.string.title_hasan_fb);
                break;
            case R.id.sunny_fb:
                Url=getString(R.string.title_sunny_fb);
                break;
            case R.id.chisty_fb:
                Url=getString(R.string.title_chisty_fb);
                break;
        }
        Intent webIntent=new Intent(Intent.ACTION_VIEW);

        webIntent.setData(Uri.parse(Url));
        startActivity(webIntent);
    }
   public void EmailAction(View view){

       String Url = null;
       switch (view.getId()) {

           case R.id.hasan_email:
               Url = getString(R.string.mail_hasan);
               break;
           case R.id.sunny_email:
               Url = getString(R.string.mail_sunny);
               break;
           case R.id.chisty_email:
               Url = getString(R.string.mail_chisty);
               break;
       }
      Intent mailIntent=new Intent(Intent.ACTION_SEND,Uri.parse("mailto:"+Url));
       startActivity(mailIntent);

   }

}

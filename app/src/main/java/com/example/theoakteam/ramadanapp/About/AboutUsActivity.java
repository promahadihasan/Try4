package com.example.theoakteam.ramadanapp.About;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

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
    public void callToDeveloper(View view){

        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:01521208079"));
        startActivity(callIntent);

    }

    public void FacebookActionOak(View view){

        try{
            Intent webIntent=new Intent(Intent.ACTION_VIEW);
            String Url = null;
            Url = getString(R.string.title_team_fb);
            webIntent.setData(Uri.parse(Url));
            startActivity(webIntent);
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(),"Something wrong!",Toast.LENGTH_SHORT).show();

        }

    }

    public void FacebookAction(View view){

        try{

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
                case R.id.saimum_fb:
                    Url=getString(R.string.title_saimum_fb);
                    break;

            }
            Intent webIntent=new Intent(Intent.ACTION_VIEW);

            webIntent.setData(Uri.parse(Url));
            startActivity(webIntent);

        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(),"Something wrong!",Toast.LENGTH_SHORT).show();
        }


    }

    public void emailAction(View view){
        Log.i("Send email", "");
//        String[] TO = {"hasan_cse91@yahoo.com","sunny_mhs@hotmail.com","chistyinfo@gmail.com","shakirahmed1996@gmail.com"};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
//        System.out.println("dhuru ja   "+TO[0]);

        if(view.getId()==R.id.emailHasan){
            String[] TO = {"hasan_cse91@yahoo.com"};
            emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        }
        else if(view.getId()==R.id.sunny_email){
            String[] TO = {"sunny_mhs@hotmail.com"};
            emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        }
        else if(view.getId()==R.id.chisty_email){
            String[] TO = {"chistyinfo@gmail.com"};
            emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        }
        else if(view.getId()==R.id.saimum_email){
            String[] TO = {"shakirahmed1996@gmail.com"};
            emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        }
        else if(view.getId()==R.id.emailOakTeam){
            String[] TO = {"theoakteam2015@gmail.com"};
            emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        }


        //emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "About App of Ramadan");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email Body");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending email...", "");
        }
        catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(AboutUsActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

//   public void EmailAction(View view){
//
//       String Url = null;
//       switch (view.getId()) {
//
//           case R.id.hasan_email:
//               Url = getString(R.string.mail_hasan);
//               break;
//           case R.id.sunny_email:
//               Url = getString(R.string.mail_sunny);
//               break;
//           case R.id.chysti_email:
//               Url = getString(R.string.mail_chisty);
//               break;
//       }
//      Intent mailIntent=new Intent(Intent.ACTION_SEND,Uri.parse("mailto:"+Url));
//       //System.out.println("Habi jabi : "+Url);
//       startActivity(mailIntent);
//
//   }

}

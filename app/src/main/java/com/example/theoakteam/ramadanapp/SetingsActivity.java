package com.example.theoakteam.ramadanapp;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.widget.LinearLayout;
import android.widget.TimePicker;


public class SetingsActivity extends ActionBarActivity {

    CheckBox checkBoxUser;
    private Button pickTime;
    private LinearLayout userTimeLayout;
    private StringBuilder timeString;

    private  int pfinalHour;
    private int pfinalMinute;
    private int pHour;
    private int pMinute;
    private String AMPM;

    private int checkBoxcheck;
    private String preTime;
    boolean flagOnstop=false;

    boolean makeSureButtonCheckhed=false;




    static final int TIME_DIALOG_ID = 0;
    String checkShareprefernce="ok";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    //variable for Background thread
    BackgroundThread backgroundThread;

    private long finalHour;
    private long finalMinute;


    private void intilizationOfViews() {
        pickTime = (Button) findViewById(R.id.bttimeshow);
        checkBoxUser = (CheckBox) findViewById(R.id.checkBoxmain);
        userTimeLayout=(LinearLayout)findViewById(R.id.notification_user_time);
        timeString  =new StringBuilder();
        sharedPreferences = getSharedPreferences("TimeData", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        intilizationOfViews();

        if(sharedPreferences.contains("time"))
        {    preTime=sharedPreferences.getString("time",checkShareprefernce);
            pickTime.setText(preTime);

        }
        if(sharedPreferences.contains("checkbox"))
        {
            checkBoxcheck=sharedPreferences.getInt("checkbox",6);
            if(checkBoxcheck==0)
            {
                callChechkBoxMakeGone();
            }
            else if(checkBoxcheck==1){
                callChechkBoxMakeVisibile();
            }
        }


        checkBoxUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBoxUser.isChecked()==false)
                {
                    callChechkBoxMakeGone();
                }
                if (checkBoxUser.isChecked()==true)
                {
                    callChechkBoxMakeVisibile();

                }
            }
        });




        pickTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                timeString  =new StringBuilder();
                final Calendar cal = Calendar.getInstance();
                pHour = cal.get(Calendar.HOUR_OF_DAY);
                pMinute = cal.get(Calendar.MINUTE);
                showDialog(TIME_DIALOG_ID);
                makeSureButtonCheckhed=true;


            }
        });




    }
    private void callChechkBoxMakeGone() {
        checkBoxUser.setChecked(false);
        editor.putInt("checkbox", 0);
        userTimeLayout.setVisibility(View.GONE);
        flagOnstop=true;
    }

    private void callChechkBoxMakeVisibile() {
        checkBoxUser.setChecked(true);
        editor.putInt("checkbox", 1);
        if(preTime!=null && preTime.length()!=0)
        {
            pickTime.setText(preTime);
        }
        else {
            pickTime.setText(getResources().getString(R.string.txt_time));
        }
        userTimeLayout.setVisibility(View.VISIBLE);
    }




    private void timeCalculation()
    {
        //for background thread
        final Calendar cal = Calendar.getInstance();
        int nowHour	= cal.get(Calendar.HOUR_OF_DAY);
        int nowMinute = cal.get(Calendar.MINUTE);

        finalHour=pfinalHour-nowHour;
        finalMinute=pfinalMinute-nowMinute;
        if(finalMinute<0)
        {
            finalMinute+=60;
            finalHour-=1;
        }
        if(finalHour<0)
        {
            finalHour+=24;
        }
        System.out.println("Final Minute=" + finalMinute);
        System.out.println("Final Hour=" + finalHour);
        finalMinute+=finalHour*60;


    }


    @Override
    protected void onPause() {
        super.onPause();

        if(makeSureButtonCheckhed==true) {

            editor.putString("time", timeString.toString());

            editor.commit();



            makeSureButtonCheckhed=false;
        }
        else {
            editor.commit();
        }
    }


    private TimePickerDialog.OnTimeSetListener mTimeSetListener =
            new TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int hourOfDay, int minute ) {
                    pfinalHour=hourOfDay;
                    pfinalMinute=minute;
                    pMinute = minute;
                    if(hourOfDay<12) {
                        AMPM = " AM";
                        pHour = hourOfDay;
                    }
                    else { pHour = hourOfDay-12;
                        AMPM=" PM";
                    }
                    if(pHour==0){
                        pHour=12;
                    }
                    updateDisplay();


                }
            };
    /** Add padding to numbers less than ten */
    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }

    private void updateDisplay() {

        timeString.append(pad(pHour)).append(":");
        timeString.append(pad(pMinute));
        timeString.append(AMPM);
        if(pickTime.getContext().toString().length()>0) {
            pickTime.setText(timeString.toString());
        }
        timeCalculation();

    }





    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case TIME_DIALOG_ID:
                return new TimePickerDialog(this,
                        mTimeSetListener, pHour, pMinute+2, false);
        }
        return null;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(flagOnstop==true) {
            onStop();
        }
        else  if(flagOnstop==false){
            onStart();
        }

    }

    //metod for Background thread
    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        backgroundThread = new BackgroundThread();
        backgroundThread.setRunning(true);
        backgroundThread.start();
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        boolean retry = true;
        backgroundThread.setRunning(false);
        System.out.println("ON stop ");
        while(retry){
            try {
                finish();
                backgroundThread.join();
                System.out.println("ON stop try");

                retry = false;
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                System.out.println("ON stop catch");
                e.printStackTrace();
            }
        }

    }

    public class BackgroundThread extends Thread {
        boolean running = false;
        final static String ACTION = "NotifyServiceAction";
        NotificationManager notificationManager;
        Notification myNotification;
        private final String myBlog = "HEloo1";
        private static final int MY_NOTIFICATION_ID=1;

        void setRunning(boolean b){
            running = b;
        }

        @Override
        public synchronized void start() {
            // TODO Auto-generated method stub
            super.start();
            notificationManager =
                    (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            while(running && finalMinute!=0){
                try {
                    TimeUnit.MINUTES.sleep(finalMinute);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                // Send Notification
                myNotification = new Notification(R.drawable.notification_icon,
                        "Notification!",
                        System.currentTimeMillis());
                Context context = getApplicationContext();
                String notificationTitle = getResources().getString(R.string.title_notification);


                String notificationText =getResources().getString(R.string.first_notification);
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(myBlog));
                PendingIntent pendingIntent
                        = PendingIntent.getActivity(getBaseContext(),
                        0, myIntent,
                        Intent.FLAG_ACTIVITY_NEW_TASK);
                myNotification.defaults |= Notification.DEFAULT_SOUND;
                myNotification.flags |= Notification.FLAG_AUTO_CANCEL;
                myNotification.setLatestEventInfo(context,
                        notificationTitle,
                        notificationText,
                        pendingIntent);
                notificationManager.notify(MY_NOTIFICATION_ID, myNotification);
            }
        }
    }


}

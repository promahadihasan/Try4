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
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;


public class SetingsActivity extends ActionBarActivity {

    CheckBox checkBoxUser;
    private Button pickTime;
    private StringBuilder timeString;

    private  int pfinalHour;
    private int pfinalMinute;
    private int pHour;
    private int pMinute;
    private String AMPM;

    boolean makeSureButtonCheckhed=false;

    private EditText tasbihEditText;
    private AutoCompleteTextView autoCompleteTextView;
    private Button tasbihButton;
    private Button districtButton;


    static final int TIME_DIALOG_ID = 0;
    String checkShareprefernce="ok";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    //variable for Background thread
    BackgroundThread backgroundThread;

    private long finalHour;
    private long finalMinute;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        intt();
        tasbihInitialization();
        districtInitialization();

        if(sharedPreferences.contains("time"))
        {   String preTime=sharedPreferences.getString("time",checkShareprefernce);
            pickTime.setText(preTime);

        }



        pickTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                timeString  =new StringBuilder();
                final Calendar cal = Calendar.getInstance();
                pHour = cal.get(Calendar.HOUR_OF_DAY);
                pMinute = cal.get(Calendar.MINUTE);
                showDialog(TIME_DIALOG_ID);
                makeSureButtonCheckhed=true;
                backgroundThread = new BackgroundThread();
                backgroundThread.setRunning(true);
                backgroundThread.start();

            }
        });




    }

    //Hasan's Code area start
    public void tasbihInitialization() {
        sharedPreferences = getSharedPreferences("TasbihData", Context.MODE_PRIVATE);
        String counterString = sharedPreferences.getString("tasbihCounter","0");
        tasbihEditText.setText(counterString);
        System.out.println("Hungki pungki: "+counterString);
    }
    public void districtInitialization(){
        sharedPreferences = getSharedPreferences("DistrictData", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String districtString = sharedPreferences.getString("DefaultDistrictName","0");
        autoCompleteTextView.setText(districtString);
    }

    public void tasbihCounterSet(View v){
        sharedPreferences = getSharedPreferences("TasbihData", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String tasbihCounter = tasbihEditText.getText().toString();
        editor.putString("tasbihCounter", tasbihCounter);
        editor.commit();
        Toast.makeText(getApplicationContext(),"Tasbih counter is "+tasbihCounter, Toast.LENGTH_LONG).show();
    }
    public void saveDistrict(View v){

    }
    //Hasan's Code area end



    private void intt() {
        pickTime = (Button) findViewById(R.id.bttimeshow);
        checkBoxUser = (CheckBox) findViewById(R.id.checkBoxmain);
        timeString  =new StringBuilder();
        sharedPreferences = getSharedPreferences("TimeData", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        //Added by Hasan
        tasbihEditText = (EditText) findViewById(R.id.tasbihCounterSetEditText);
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        tasbihButton = (Button) findViewById(R.id.tasbihCounterButton);
        districtButton = (Button) findViewById(R.id.districtButton);


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
        backgroundThread = new BackgroundThread();
        backgroundThread.setRunning(true);
        backgroundThread.start();

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

        while(retry){
            try {
                backgroundThread.join();
                retry = false;
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
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

                    if(finalMinute==0){
                        running=false;
                    }
                    else {
                        TimeUnit.MINUTES.sleep(finalMinute);
                    }


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

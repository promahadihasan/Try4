package theoaktroop.appoframadan.NotificationChallenging;


import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.ConditionVariable;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;

import theoaktroop.appoframadan.R;


public class NotifyingService extends Service {







    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private long finalMinute;
    private long finalMilliScecond;
    long checkShareprefernce = 1;
    private String[] notificationString;
    private int i;

    @Override
    public void onCreate() {

//        sharedPreferences = getSharedPreferences("RamadanAppData", Context.MODE_PRIVATE);
//
//        if (sharedPreferences.contains("finaltime")) {
//            finalMinute = sharedPreferences.getLong("finaltime", checkShareprefernce);
//            finalMilliScecond = finalMinute * 60 * 1000;
//            notificationString = getResources().getStringArray(R.array.notification_messages);
//        }
//
//
    }

    @Override
    public void onStart(Intent intent, int startId) {

        super.onStart(intent, startId);



    }

    @Override
    public void onDestroy() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }



}




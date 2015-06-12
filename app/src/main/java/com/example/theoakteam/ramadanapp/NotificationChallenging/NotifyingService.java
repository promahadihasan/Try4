package com.example.theoakteam.ramadanapp.NotificationChallenging;


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

import com.example.theoakteam.ramadanapp.R;


public class NotifyingService extends Service {


    private static int MOOD_NOTIFICATIONS = R.layout.notification_details;


    private ConditionVariable mCondition;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private long finalMinute;
    private long finalMilliScecond;
    long checkShareprefernce=1;
    private String[] notificationString;
    @Override
    public void onCreate() {
        mNM = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        sharedPreferences = getSharedPreferences("RamadanAppData", Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        if(sharedPreferences.contains("finaltime"))
        {  finalMinute=sharedPreferences.getLong("finaltime", checkShareprefernce);
           finalMilliScecond=finalMinute*60*1000;
             notificationString = getResources().getStringArray(R.array.notification_messages);
        }



        Thread notifyingThread = new Thread(null, mTask, "NotifyingService");
        mCondition = new ConditionVariable(false);
        notifyingThread.start();
    }

    @Override
    public void onDestroy() {

        mNM.cancel(MOOD_NOTIFICATIONS);

        mCondition.open();
    }

    private Runnable mTask = new Runnable() {
        public void run() {
            for (int i = 0; i < notificationString.length; ++i) {
                sharedPreferences = getSharedPreferences("RamadanAppData", Context.MODE_PRIVATE);
                editor=sharedPreferences.edit();
                editor.putInt("indexofnotificaton", i);
                editor.commit();

              // System.out.println(i);

                System.out.println("Final Minute=" + finalMinute);
                System.out.println("Final Milliseconds=" + finalMilliScecond);
                if (mCondition.block(finalMilliScecond))
                    break;
        showNotification(R.drawable.notification_icon,
        i);
                finalMilliScecond=24*60*60*1000;
               // finalMilliScecond=7*60*30*1000;


        }

        NotifyingService.this.stopSelf();
        }
        };

@Override
public IBinder onBind(Intent intent) {
        return mBinder;
        }

private void showNotification(int moodId, int textId) {

    CharSequence text = notificationString[textId];


    Notification notification = new Notification(moodId, null, System.currentTimeMillis());

    Intent mynoIntent = new Intent(this, NotificationViewer.class);


    PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
            mynoIntent, 0);


    notification.setLatestEventInfo(this, getText(R.string.title_notification),
            text.toString(), contentIntent);



    mNM.notify(MOOD_NOTIFICATIONS, notification);
}
private final IBinder mBinder = new Binder() {
@Override
protected boolean onTransact(int code, Parcel data, Parcel reply,
        int flags) throws RemoteException {
        return super.onTransact(code, data, reply, flags);
        }
        };

private NotificationManager mNM;
        }

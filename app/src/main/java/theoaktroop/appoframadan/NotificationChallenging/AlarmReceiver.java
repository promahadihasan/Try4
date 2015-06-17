package theoaktroop.appoframadan.NotificationChallenging;

/**
 * Created by Sunny_PC on 6/14/2015.
 */
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.NotificationCompat;

import theoaktroop.appoframadan.R;


public class AlarmReceiver extends BroadcastReceiver {
private  String strtitle ;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
   private int i;
    String[] notificationString;
    Intent intent;
    private String[] notificationStringArray;
    @Override
    public void onReceive(Context arg0, Intent arg1) {

            
        
        notificationString=arg0.getResources().getStringArray(R.array.notification_messages);
        sharedPreferences = arg0.getSharedPreferences("RamadanAppData", Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        i=sharedPreferences.getInt("indexofnotificaton",1);
        editor.putBoolean("on",true);
        System.out.println("indexofnotificaton from service Viewer i= "+i);

        if(sharedPreferences.contains("indexofnotificaton"))
        {


            i++;
            System.out.println("indexofnotificaton from Notification AlarmRecevier inside contains sharedpreference "+i);
        }
        else {
            i=0;
        }
        editor.putInt("indexofnotificaton", i);
        editor.commit();

        String message=notificationString[i];
        String  strtitle=arg0.getString(R.string.title_notification);

        // Open NotificationView Class on Notification Click
        intent = new Intent(arg0, NotificationViewer.class);
        // Send data to NotificationView Class
//        intent.putExtra("title", strtitle);
//        intent.putExtra("text", message);
        // Open NotificationView.java Activity
        PendingIntent pIntent = PendingIntent.getActivity(arg0, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        // Create Notification using NotificationCompat.Builder
        NotificationCompat.Builder builder = new NotificationCompat.Builder(
                arg0)
                // Set Icon
                .setSmallIcon(R.drawable.notification_icon)
                        // Set Ticker Message
                .setTicker(message)
                        // Set Title
                .setContentTitle(strtitle)
                        // Set Text
                .setContentText(message)
                        //.setDefaults(Notification.DEFAULT_ALL)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(message))
                        // Set PendingIntent into Notification
                .setContentIntent(pIntent)
                        // Dismiss Notification
                .setAutoCancel(false);

        // Create Notification Manager
        NotificationManager notificationmanager = (NotificationManager) arg0.getSystemService(arg0.NOTIFICATION_SERVICE);
        // Build Notification with Notification Manager
        notificationmanager.notify(0, builder.build());
        System.out.println("From Alarm Recever  ");



    }


}

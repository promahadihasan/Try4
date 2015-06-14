package theoaktroop.appoframadan.NotificationChallenging;

/**
 * Created by Sunny_PC on 6/14/2015.
 */
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
    int i;
    private String[] notificationStringArray;
    @Override
    public void onReceive(Context arg0, Intent arg1) {
        notificationStringArray=arg0.getResources().getStringArray(R.array.notification_messages);
        sharedPreferences = arg0.getSharedPreferences("RamadanAppData", Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
           if(sharedPreferences.contains("indexonnotification"))
           {
               i=sharedPreferences.getInt("indexofnotificaton",5);
               i++;
           }
        else {
              i=0;
           }
        editor.putInt("indexofnotificaton", i);
        editor.commit();



            Notification(arg0, notificationStringArray[i]);



    }

    public void Notification(Context context, String message) {
        // Set Notification Title

        strtitle=context.getString(R.string.title_notification);

        // Open NotificationView Class on Notification Click
        Intent intent = new Intent(context, NotificationViewer.class);
        // Send data to NotificationView Class
//        intent.putExtra("title", strtitle);
//        intent.putExtra("text", message);
        // Open NotificationView.java Activity
        PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        // Create Notification using NotificationCompat.Builder
        NotificationCompat.Builder builder = new NotificationCompat.Builder(
                context)
                // Set Icon
                .setSmallIcon(R.drawable.notification_icon)
                        // Set Ticker Message
                .setTicker(message)
                        // Set Title
                .setContentTitle(strtitle)
                        // Set Text
                .setContentText(message)

                        // Set PendingIntent into Notification
                .setContentIntent(pIntent)
                        // Dismiss Notification
                .setAutoCancel(false);

        // Create Notification Manager
        NotificationManager notificationmanager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        // Build Notification with Notification Manager
        notificationmanager.notify(0, builder.build());
        System.out.println("From Notification Receiever ");

    }
}
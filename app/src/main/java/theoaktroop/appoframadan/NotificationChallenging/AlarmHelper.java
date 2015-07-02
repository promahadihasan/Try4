package theoaktroop.appoframadan.NotificationChallenging;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;

/**
 * Created by Sunny_PC on 6/18/2015.
 */
public class AlarmHelper {
   SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private Context context;

    public AlarmHelper(Context applicationContext) {
        this.context=applicationContext;
    }

    public void setAlarmAtFirstTime()
    {

        sharedPreferences = context.getSharedPreferences("RamadanAppData", Context.MODE_PRIVATE);
        int chcheckBoxcheck=sharedPreferences.getInt("checkbox",1);


        boolean onOff=sharedPreferences.getBoolean("on",true);
        if( !sharedPreferences.contains("indexofnotificaton")) {
            editor = sharedPreferences.edit();
            editor.putInt("indexofnotificaton", 0);
            editor.commit();
        }
       long timMs=30*60*1000;





        AlarmManager alarmManager=(AlarmManager) context.getSystemService(context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 1, intent, 0);

        if(chcheckBoxcheck==1 && onOff!=true) {
            editor = sharedPreferences.edit();
            editor.putBoolean("on", true);
            editor.commit();
            alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime() + timMs, AlarmManager.INTERVAL_DAY, pendingIntent);

            System.out.println(timMs+"ms Alarm Manager start");
        }

        else  if(chcheckBoxcheck==0)
        {
            alarmManager.cancel(pendingIntent);
            System.out.println("AlarmHelper Manager Cancel");

        }
        else System.out.println("From alarmHelper  manager on Condition");



    }


}

package theoaktroop.appoframadan.NotificationChallenging;

import android.app.AlarmManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

/**
 * Created by Sunny_PC on 6/18/2015.
 */
public class OnBootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"Notification Enable ",Toast.LENGTH_LONG).show();
        AlarmHelper alarmHelper=new AlarmHelper(context);
      SharedPreferences sharedPreferences = context.getSharedPreferences("RamadanAppData", Context.MODE_PRIVATE);
       SharedPreferences.Editor editor;
        editor = sharedPreferences.edit();

        editor.putBoolean("on",false);
        editor.commit();
        alarmHelper.setAlarmAtFirstTime();
    }
}

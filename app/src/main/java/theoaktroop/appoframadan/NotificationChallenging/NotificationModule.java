package theoaktroop.appoframadan.NotificationChallenging;



import java.util.Calendar;

/**
 * Created by Sunny_PC on 6/16/2015.
 */
public class NotificationModule
{

    private long timeMilliseceond;

    private int hOur;
    private int mUnites;

    public void sethOur(int hOur) {
        this.hOur = hOur;
    }

    public void setmUnites(int mUnites) {
        this.mUnites = mUnites;
    }

    public long getTimeMilliseceond() {

        final Calendar cal = Calendar.getInstance();
        int nowHour	= cal.get(Calendar.HOUR_OF_DAY);
        int nowMinute = cal.get(Calendar.MINUTE);

        int   finalHour=this.hOur-nowHour;
        int  finalMinute=this.mUnites-nowMinute;
        if(finalMinute<0)
        {
            finalMinute+=60;
            finalHour-=1;
        }
        if(finalHour<0)
        {
            finalHour+=24;
        }
        timeMilliseceond=finalMinute+finalHour*60;
       timeMilliseceond= timeMilliseceond*60*1000;
        System.out.println("Final Hour=" + finalHour);
        System.out.println("Final Minute=" + finalMinute);
        System.out.println("Final MilliSeconds="+timeMilliseceond);


        return timeMilliseceond;
    }


}

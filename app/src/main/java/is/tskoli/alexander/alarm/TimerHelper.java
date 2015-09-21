package is.tskoli.alexander.alarm;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by alexander on 20.9.2015.
 */
public class TimerHelper {

    private static Context context;

    //have Timers connected to Hashmaps
    public static HashMap<AlarmItem, Timer> timers = new HashMap<AlarmItem, Timer>();

    //set the context
    public static void setContext(Context c){
        context = c;
    }

    public static void save(final AlarmItem alarm){

        Date time = getTime(alarm, 0);

        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                ring(alarm);
            }
        }, time);


        timers.put(alarm, timer);

    }

    public static void dismiss(AlarmItem alarm){

        //get the timer from the list so we can modify it
        Timer timer = timers.get(alarm);

        timer.purge();
    }

    //update the timer and add 5 minutes to it
    public static void snooze(final AlarmItem alarm){

        Timer timer = timers.get(alarm);

        timer.purge();

        //snooze 5 minutes
        Date time = getTime(alarm, 5);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                ring(alarm);
            }
        }, time);

    }

    private static Date getTime(AlarmItem alarm, int minute){

        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, alarm.hour);
        c.set(Calendar.MINUTE, alarm.minute + minute);
        c.set(Calendar.SECOND, 1);

        Date time = c.getTime();

        return time;

    }

    private static void ring(AlarmItem alarm){
        Log.wtf("wtf", "RINGING");
        Log.wtf("wtf", "RINGING");

        //open the activity
        Intent intent = new Intent(context, AlarmRingActivity.class);

        intent.putExtra("id", alarm.id);

        context.startActivity(intent);

    }


}

package is.tskoli.alexander.alarm;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by alexander on 20.9.2015.
 */
public class TimerHelper {

    private static Context context;

    public static List<Timer> timers = new ArrayList<Timer>();

    //set the context
    public static void setContext(Context c){
        context = c;
    }

    public static void save(final AlarmItem alarm){

        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, alarm.hour);
        c.set(Calendar.MINUTE, alarm.minute);
        c.set(Calendar.SECOND, 1);

        Date time = c.getTime();

        Log.wtf("wtf", time.toString());

        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                ring(alarm);
            }
        }, time);

        timers.add(timer);
    }

    private static void ring(AlarmItem alarm){
        Log.wtf("wtf", "RINGING");
        Log.wtf("wtf", "RINGING");

        //open the activity
        Intent intent = new Intent(context, AlarmRingActivity.class);

        context.startActivity(intent);



    }


}

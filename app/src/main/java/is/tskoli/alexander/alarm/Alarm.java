package is.tskoli.alexander.alarm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by alexander on 10.9.2015.
 */

public class Alarm {

    private static List<AlarmItem> alarms = new ArrayList<AlarmItem>();

    //add the current instance of Alarm
    public static void save(AlarmItem alarm){

        TimerHelper.save(alarm);
        alarms.add(alarm);


    }

    //update a existing Alarm
    public static void update(int position, AlarmItem alarm){

        alarms.set(position, alarm);
    }

    public static void remove(int position){
        alarms.remove(position);
    }


    public static void snooze(AlarmItem alarm){

        AlarmItem newAlarm = alarm;
        alarm.minute += 5;

        //update the actual alarm object
        update(alarm.id-1, alarm);

        TimerHelper.snooze(alarm);

    }

    //get all alarms
    public static List<AlarmItem> get(){
        return alarms;
    }

    //get alarm by id
    public static AlarmItem find(int id){
        return alarms.get(id-1);
    }

}

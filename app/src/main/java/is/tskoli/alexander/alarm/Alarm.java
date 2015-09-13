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
        alarms.add(alarm);
    }


    public static List<AlarmItem> get(){
        return alarms;
    }

}

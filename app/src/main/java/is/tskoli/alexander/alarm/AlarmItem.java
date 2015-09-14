package is.tskoli.alexander.alarm;

import java.util.Date;

/**
 * Created by alexander on 10.9.2015.
 * Alarm object, details about every active alarm
 */
public class AlarmItem {

    protected String details = "";
    protected int hour;
    protected int minute;

    public AlarmItem(String _details, int _hour, int _minute){

        this.details = _details;
        this.hour    = _hour;
        this.minute  = _minute;

    }

}

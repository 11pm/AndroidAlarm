package is.tskoli.alexander.alarm;

import java.util.Date;

/**
 * Created by alexander on 10.9.2015.
 * Alarm object, details about every active alarm
 */
public class AlarmItem {

    protected String details = "";
    protected Integer hour;
    protected Integer minute;
    protected Integer second;

    public AlarmItem(String _details, Integer _hour, Integer _minute, Integer _second){

        this.details = _details;
        this.hour    = _hour;
        this.minute  = _minute;
        this.second  = _second;

    }

}

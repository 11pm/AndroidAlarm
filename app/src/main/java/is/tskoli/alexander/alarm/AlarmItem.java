package is.tskoli.alexander.alarm;

import java.util.Date;

/**
 * Created by alexander on 10.9.2015.
 * Alarm object, details about every active alarm
 */
public class AlarmItem {

    protected Integer id;
    protected String details = "";
    protected Date time;

    public AlarmItem(Date _time, String _details){

        this.id      = 1;
        this.time    = _time;
        this.details = _details;
    }

}

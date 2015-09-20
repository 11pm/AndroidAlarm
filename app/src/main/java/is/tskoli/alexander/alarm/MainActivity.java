package is.tskoli.alexander.alarm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    RelativeLayout layout;

    ListView list;

    Button newAlarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null){
            Log.wtf("wtf", "first");
        }
        else{
            Log.wtf("wtf", " not first");
        }


        //get the alarm list
        list = (ListView) findViewById(R.id.alarmList);

        //get the 'new alarm' button
        newAlarm = (Button) findViewById(R.id.newAlarm);

        Alarm.save(new AlarmItem("", 7, 20));

        Alarm.save(new AlarmItem("Coolio", 17, 2));

        //initialize the adapter
        final ArrayAdapter<AlarmItem> arrayAdapter = new AlarmAdapter();

        //connect the adapter to the list
        list.setAdapter(arrayAdapter);



        //set onclick listener
        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id){

                //get the clicked alarm item object
                AlarmItem clickedAlarm = Alarm.get().get(position);

                //create the intent we are moving to
                Intent intent = new Intent(MainActivity.this, AlarmActivity.class);

                //send the position of the clicked alarm item to the next activity
                intent.putExtra("position", position);

                //open the new activity
                startActivity(intent);

            }

        });

        newAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, NewAlarm.class);

                //go to the 'NewAlarm' screen
                startActivity(intent);
            }
        });

        for(AlarmItem a : Alarm.get()){
            //start the alarm timer
            TimerHelper.save(a);
        }

    }

    //when we come back we redraw
    @Override
    protected void onRestart(){

        super.onRestart();

        //initialize the adapter
        final ArrayAdapter<AlarmItem> arrayAdapter = new AlarmAdapter();

        //connect the adapter to the list
        list.setAdapter(arrayAdapter);

    }


    private class AlarmAdapter extends ArrayAdapter<AlarmItem>{

        public AlarmAdapter(){
            super(MainActivity.this, R.layout.alarm_list, Alarm.get());
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){

            View alarmView = convertView;

            //make sure we have a view
            if (alarmView == null){
                alarmView = getLayoutInflater().inflate(R.layout.alarm_list, parent, false);
            }


            //find the alarm to display
            AlarmItem curAlarm = Alarm.get().get(position);

            //add data to the view
            TextView alarmDetail = (TextView) alarmView.findViewById(R.id.alarmListDetails);
            alarmDetail.setText(curAlarm.details);

            //E H:m:s
            TextView alarmTime = (TextView) alarmView.findViewById(R.id.alarmListTime);
            String time = String.format("%1$d : %2$d", curAlarm.hour, curAlarm.minute);
            alarmTime.setText(time);


            return alarmView;

        }

    }



    public void alarmTimer(AlarmItem alarm){

        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, alarm.hour);
        c.set(Calendar.MINUTE, alarm.minute);
        c.set(Calendar.SECOND, 1);

        Date time = c.getTime();

        Log.wtf("wtf", time.toString());

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Log.wtf("wtf", "TIMES UP");
            }
        }, time);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

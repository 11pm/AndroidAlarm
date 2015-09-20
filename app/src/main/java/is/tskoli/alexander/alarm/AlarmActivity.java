package is.tskoli.alexander.alarm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

public class AlarmActivity extends AppCompatActivity {

    AlarmItem alarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        Intent intent = getIntent();
        //get the variable from MainActivity
        final int position = intent.getIntExtra("position", 0);

        //get the alarm the user clicked
        alarm = Alarm.get().get(position);

        //set alarm info into the view
        TextView details = (TextView) findViewById(R.id.alarmEditDetails);

        details.setText(alarm.details);

        final TimePicker time = (TimePicker) findViewById(R.id.alarmEditTime);

        time.setIs24HourView(true);
    
        //time.setHour does not work but this is deprecated but works
        //ignore the deprecation (i know it's code gore)

        //noinspection deprecation
        time.setCurrentHour(new Integer(alarm.hour));
        //noinspection deprecation
        time.setCurrentMinute(new Integer(alarm.minute));

        //when the user clicks the save button we redirect him back and update the alarm
        Button saveBtn = (Button) findViewById(R.id.alarmEditSave);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlarmItem newAlarm = new AlarmItem(alarm.details, time.getCurrentHour(), time.getCurrentMinute());
                Alarm.update(position, newAlarm);

                finish();
            }
        });


        Button cancelBtn = (Button) findViewById(R.id.alarmEditCancel);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

                //Intent redirect = new Intent(AlarmActivity.this, MainActivity.class);

                //s/tartActivity(redirect);

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_alarm, menu);
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

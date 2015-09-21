package is.tskoli.alexander.alarm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class AlarmRingActivity extends AppCompatActivity {

    Button dismiss;

    Button snooze;

    AlarmItem alarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_ring);

        dismiss = (Button) findViewById(R.id.alarmRingDismiss);
        snooze  = (Button) findViewById(R.id.alarmRingSnooze);

        Intent intent = getIntent();

        int id = intent.getIntExtra("id", 0);

        //get the actual alarm object
        alarm = Alarm.find(id);


        dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });


        snooze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Alarm.snooze();
                Log.wtf("wtf", alarm.details);
                Alarm.snooze(alarm);

                finish();

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_alarm_ring, menu);
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

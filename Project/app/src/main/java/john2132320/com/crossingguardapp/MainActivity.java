package john2132320.com.crossingguardapp;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private SeekBar sb = null;
    private AlarmManager alarm;
    private PendingIntent pending;

    private int progressChanged = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }

    public void HandleCheckOut(View arg0) {
        setContentView(R.layout.check_out);
        if(sb == null) {
            sb = (SeekBar) findViewById(R.id.timeSelect);

            sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    progressChanged = progress;
                }

                public void onStartTrackingTouch(SeekBar seekBar) {
                    // TODO Auto-generated method stub
                }

                public void onStopTrackingTouch(SeekBar seekBar) {
                    TextView tv = (TextView) findViewById(R.id.timeDisplay);
                    tv.setText(progressChanged + " minutes");
                }
            });
        }

    }

    public void HandleBack(View arg0) {
        setContentView(R.layout.activity_main);
    }

    public void HandleCancel(View arg0) {
        alarm.cancel(pending);
        System.exit(0);
    }

    public void HandleSetAlarm(View arg0) {
        Calendar t = Calendar.getInstance();
        t.add(Calendar.MINUTE, progressChanged);

        Intent i = new Intent(this, AlarmSound.class);
        pending = PendingIntent.getActivity(this,1235, i, PendingIntent.FLAG_CANCEL_CURRENT);

        alarm = (AlarmManager)getSystemService(Activity.ALARM_SERVICE);
        alarm.set(AlarmManager.RTC_WAKEUP, t.getTimeInMillis(), pending);

        TextView tv = (TextView) findViewById(R.id.email);
        DisplayAlert.email = tv.getText().toString();

        setContentView(R.layout.waiting);
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

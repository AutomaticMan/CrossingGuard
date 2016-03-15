package john2132320.com.crossingguardapp;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.Calendar;


public class AlarmSound extends Activity {
    private MediaPlayer player;
    private AlarmManager alarm;
    private PendingIntent pending;
    final Context context=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.alert_set);

        Calendar t = Calendar.getInstance();
        t.add(Calendar.SECOND, 5);

        Intent i = new Intent(this, DisplayAlert.class);
        pending = PendingIntent.getActivity(this,1235, i, PendingIntent.FLAG_CANCEL_CURRENT);

        alarm = (AlarmManager)getSystemService(Activity.ALARM_SERVICE);
        alarm.set(AlarmManager.RTC_WAKEUP, t.getTimeInMillis(), pending);

        play(this, getAlarmSound());
    }

    @Override
    protected void onStop() {
        player.stop();
        super.onStop();
    }

    public void HandleCheckIn(View arg0) {
        alarm.cancel(pending);
        System.exit(0);
    }

    private void play(Context context, Uri alert) {
        player = new MediaPlayer();

        try {
            player.setDataSource(context, alert);
            final AudioManager audio = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);

            if (audio.getStreamVolume(AudioManager.STREAM_ALARM) != 0) {
                player.setAudioStreamType(AudioManager.STREAM_ALARM);
                player.prepare();
                player.start();
            }
        } catch (IOException e) {

            Log.e("Error....","Check code...");

        }

    }

    private Uri getAlarmSound() {

        Uri alertSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);

        if (alertSound == null) {
            alertSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            if (alertSound == null) {
                alertSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
            }
        }

        return alertSound;

    }

}
package edu.orangecoastcollege.cs273.alohamusic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Creates the intro loading screen for the application
 */
public class SplashActivity extends AppCompatActivity {

    /**
     * Called on the showing of the activity
     *
     * Creates a timer that ???? the Intent to the main activity from being activated
     *
     * @param savedInstanceState Not used
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                finish();

                Intent intent = new Intent(SplashActivity.this, MusicActivity.class);
                intent.putExtra("Departure", SplashActivity.class.toString());
                SplashActivity.this.overridePendingTransition(0,0);
                startActivity(intent);
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, 4000);

    }
}

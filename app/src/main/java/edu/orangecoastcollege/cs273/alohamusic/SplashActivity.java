package edu.orangecoastcollege.cs273.alohamusic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

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

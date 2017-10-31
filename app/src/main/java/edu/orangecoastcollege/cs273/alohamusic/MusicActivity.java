package edu.orangecoastcollege.cs273.alohamusic;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * Activity that allows the user to play media from three available sources
 *
 */
public class MusicActivity extends AppCompatActivity {

    private static final String TAG = "AlohaMusic";

    Button ukuleleButton;
    Button ipuButton;
    Button hulaButton;

    VideoView videoView;

    MediaPlayer ukuleleMediaPlayer;
    MediaPlayer ipuMediaPlayer;

    /**
     * Called when the activity is starting
     *
     * Connects views to their programmatic counterpart
     *
     * Creates {@link MediaPlayer}s for the two audio sources
     * Creates a {@link MediaController} for the {@link VideoView}
     *
     * Connects the video to the {@link VideoView} via {@link Uri}
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        if(getIntent().getStringExtra("Departure").equals(SplashActivity.class.toString())) {
            this.overridePendingTransition(0,0);
            Log.i(TAG, "onCreate: entered");
        }

        ukuleleButton = (Button) findViewById(R.id.ukuleleButton);
        ipuButton = (Button) findViewById(R.id.ipuButton);
        hulaButton = (Button) findViewById(R.id.hulaButton);

        videoView = (VideoView) findViewById(R.id.videoView);

        ukuleleMediaPlayer = MediaPlayer.create(this, R.raw.ukulele);
        ipuMediaPlayer = MediaPlayer.create(this, R.raw.ipu);

        String uri = "android.resource://" + getPackageName() +"/" + R.raw.hula;
        videoView.setVideoURI(Uri.parse(uri));

        videoView.setMediaController(new MediaController(this));
    }

    /**
     * Based on what {@link View} called this method,
     * plays the respective multimedia, hides other buttons
     * , and allows for the pausing of the currently played media.
     *
     * @param v Clicked button
     */
    public void playMedia(View v) {
        switch (v.getId()) {
            case (R.id.ukuleleButton):
                if(ukuleleMediaPlayer.isPlaying()) {
                    ukuleleMediaPlayer.pause();
                    ukuleleButton.setText(R.string.ukulele_button_play_text);
                    ipuButton.setVisibility(View.VISIBLE);
                    hulaButton.setVisibility(View.VISIBLE);
                }
                else {
                    ukuleleMediaPlayer.start();
                    ukuleleButton.setText(R.string.ukulele_button_pause_text);
                    ipuButton.setVisibility(View.INVISIBLE);
                    hulaButton.setVisibility(View.INVISIBLE);
                }
                break;
            case (R.id.ipuButton):
                if(ipuMediaPlayer.isPlaying()) {
                    ipuMediaPlayer.pause();
                    ipuButton.setText(R.string.ipu_button_play_text);
                    ukuleleButton.setVisibility(View.VISIBLE);
                    hulaButton.setVisibility(View.VISIBLE);
                }
                else {
                    ipuMediaPlayer.start();
                    ipuButton.setText(R.string.ipu_button_pause_text);
                    ukuleleButton.setVisibility(View.INVISIBLE);
                    hulaButton.setVisibility(View.INVISIBLE);
                }
                break;
            case (R.id.hulaButton):
                if(videoView.isPlaying()){
                    videoView.pause();
                    hulaButton.setText(R.string.hula_button_watch_text);
                    ukuleleButton.setVisibility(View.VISIBLE);
                    ipuButton.setVisibility(View.VISIBLE);
                }
                else{
                    videoView.start();
                    hulaButton.setText(R.string.hula_button_pause_text);
                    ukuleleButton.setVisibility(View.INVISIBLE);
                    ipuButton.setVisibility(View.INVISIBLE);
                }
                break;
        }
    }

    /**
     * Releases the media players when the activity is stopped
     */
    @Override
    protected void onStop() {
        super.onStop();

        ukuleleMediaPlayer.release();
        ipuMediaPlayer.release();
    }
}

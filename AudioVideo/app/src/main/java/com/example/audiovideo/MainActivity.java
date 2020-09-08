package com.example.audiovideo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.VideoView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity
{
    MediaPlayer mediaPlayer;
    AudioManager audioManager;

    public void playVideo(View view)
    {
        mediaPlayer.start();
    }

    public void pauseVideo(View view)
    {
        mediaPlayer.pause();
    }

    public void controlVideo()
    {
        VideoView videoView = findViewById(R.id.videoView);
        videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.video);

        // The android.widget.MediaController is a view that contains media controls like play/pause, previous, next, fast-forward, rewind etc
        MediaController mediaController = new MediaController(this);

        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

         videoView.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // controlVideo();

        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        // Getting the max volume of the device in which this app is currently running.
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        mediaPlayer = MediaPlayer.create(this, R.raw.tum_sath_ho);

        // SeekBar is the progress line of the music which determines on which you put your hand and slides the music forward or backward.
        SeekBar volumeControl = (SeekBar) findViewById(R.id.volumeSeekBar);

        // Changing the max volume of the SeekBar to the max volume of the device we get above.
        volumeControl.setMax(maxVolume);

        // Setting the current volume progress.
        volumeControl.setProgress(currentVolume);

        volumeControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int volumePercentage, boolean b)
            {
                // percentageOver will tell you how volume percentage.
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volumePercentage, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {

            }
        });

        final SeekBar scrubSeekBar = findViewById(R.id.scrubSeekBar);

        // Set the max length to total length of the music which is being played.
        scrubSeekBar.setMax(mediaPlayer.getDuration());

        scrubSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int musicPercentage, boolean hasUserTouched)
            {
                // If we explicitly move the scrubSeekBar forward or backward then accordingly music should also have to move in the same way.
                if(hasUserTouched)
                {
                    mediaPlayer.seekTo(musicPercentage);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {

            }
        });

        final Handler seekHandler = new Handler();

        final Runnable run = new Runnable()
        {
            @Override
            public void run()
            {
                scrubSeekBar.setProgress(mediaPlayer.getCurrentPosition());

                seekHandler.postDelayed(this, 1000);
            }
        };

        seekHandler.post(run);

        // Timer will let you update the scrubSeekBar along with the music played.
//        new Timer().scheduleAtFixedRate(new TimerTask()
//        {
//            @Override
//            public void run()
//            {
//                scrubSeekBar.setProgress(mediaPlayer.getCurrentPosition());
//            }
//        },0,1000);


        Button recordActivity = findViewById(R.id.record_voice);

        recordActivity.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(MainActivity.this, AudioRecordActivity.class));
            }
        });
    }
}
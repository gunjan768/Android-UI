package com.example.audiovideo;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Random;

public class AudioRecordActivity extends AppCompatActivity implements
        MediaRecorder.OnInfoListener,
        MediaRecorder.OnErrorListener
{
    private static final String TAG = "AudioRecordActivity";

    private Button buttonStart, buttonStop, buttonPause;
    private Button buttonPlayLastRecorded, buttonPauseLastRecorded, buttonStopPlaying;

    private static double EMA = 0.0;
    private final static double EMA_FILTER = 0.6;
    public static final int REQUEST_PERMISSION_CODE = 1;

    TextView textView;
    Thread thread;
    CountDownTimer countDownTimer;
    String audioSavePathInDevice = null, RandomAudioFileName = "ABCDEFGHIJKLMNOP", recordingState = "stop", playingState = "stop";
    MediaRecorder mediaRecorder;
    MediaPlayer mediaPlayer;
    Random random;

    private double soundDB(double amplitude)
    {
        return 20 * Math.log10(getAmplitudeEMA()/amplitude);
    }

    private double getAmplitudeEMA()
    {
        double amplitude = getAmplitude();

        EMA = EMA_FILTER*amplitude + (1.0 - EMA_FILTER)*amplitude;

        return EMA;
    }

    private double getAmplitude()
    {
        if(mediaRecorder != null)
        return mediaRecorder.getMaxAmplitude();

        return 0;
    }

    private void setMediaButtons(boolean b1, boolean b2, boolean b3, boolean b4, boolean b5, boolean b6)
    {
        buttonStart.setEnabled(b1);
        buttonPause.setEnabled(b2);
        buttonStop.setEnabled(b3);

        buttonPlayLastRecorded.setEnabled(b4);
        buttonPauseLastRecorded.setEnabled(b5);
        buttonStopPlaying.setEnabled(b6);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_record);

        buttonStart = findViewById(R.id.start_recording_button);
        buttonStop = findViewById(R.id.stop_recording_button);
        buttonPlayLastRecorded = findViewById(R.id.play_recorded_button);
        buttonStopPlaying = findViewById(R.id.stop_recorded_button);
        buttonPauseLastRecorded = findViewById(R.id.pause_recorded_button);
        buttonPause = findViewById(R.id.pause_recording_button);
        textView = findViewById(R.id.text_view);
        random = new Random();

        setMediaButtons(true, false, false ,false, false, false);

        if(thread == null)
        {
            thread = new Thread()
            {
                public void run()
                {
                    while(thread != null)
                    {
                        try
                        {
                            Thread.sleep(200);
                        }
                        catch(InterruptedException e)
                        {
                            e.printStackTrace();
                        }

                        runOnUiThread(new Runnable()
                        {
                            @SuppressLint("SetTextI18n")
                            @Override
                            public void run()
                            {
                                // textView.setText(soundDB(1) + "dB");
                            }
                        });
                    }
                }
            };

            thread.start();
        }

        buttonStart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(checkPermission())
                {
                     try
                     {
                         if(recordingState.equals("stop"))
                         {
                             audioSavePathInDevice = getExternalCacheDir().getAbsolutePath();
                             audioSavePathInDevice = Environment.getExternalStorageDirectory().getAbsolutePath() +  "/" +
                                     CreateRandomAudioFileName(5) + "AudioRecord.3gpp";

                             makeMediaRecorderReady();

                             mediaRecorder.prepare();
                             mediaRecorder.start();
                         }
                         else
                         {
                            mediaRecorder.resume();
                         }

                         recordingState = "start";

                         // Log.d(TAG, "started: " + mediaRecorder);
                     }
                     catch(IllegalStateException | IOException e)
                     {
                        e.printStackTrace();
                     }

                    setMediaButtons(false, true, true ,false, false, false);

                    Toast.makeText(AudioRecordActivity.this, "Recording has been started", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    requestPermission();
                }
            }
        });

        buttonPause.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(mediaRecorder != null) 
                {
                    mediaRecorder.pause();
                    
                    recordingState = "pause";
                }

                setMediaButtons(true, false, true ,false, false, false);

                Log.d(TAG, "paused: " + mediaRecorder);
            }
        });

        buttonStop.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(mediaRecorder != null)
                {
                     //mediaRecorder.stop();

                     // Releases resources associated with this MediaRecorder object ( next time it creates a fresh new resource ).
                     mediaRecorder.release();

                     recordingState = "stop";
                }

                Log.d(TAG, "stopped: " + mediaRecorder);

                setMediaButtons(true, false, false ,true, false, false);

                Toast.makeText(AudioRecordActivity.this, "Recording has been stopped", Toast.LENGTH_SHORT).show();
            }
        });

        buttonPlayLastRecorded.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                setMediaButtons(false, false, false ,false, true, true);

                try
                {
                    if(playingState.equals("stop"))
                    {
                        mediaPlayer = new MediaPlayer();
                        mediaPlayer.setDataSource(audioSavePathInDevice);
                        mediaPlayer.prepare();
                    }

                    startTheTimer(mediaPlayer.getDuration() - mediaPlayer.getCurrentPosition());
                    mediaPlayer.start();

                    playingState = "start";
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }

                Toast.makeText(AudioRecordActivity.this, "Playing last recorded", Toast.LENGTH_SHORT).show();
            }
        });

        buttonPauseLastRecorded.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                setMediaButtons(false, false, false ,true, false, true);

                if(mediaPlayer != null)
                {
                    mediaPlayer.pause();

                    resetTimer();
                    playingState = "pause";
                }
            }
        });

        buttonStopPlaying.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                setMediaButtons(true, false, false ,true, false, false);

                if(mediaPlayer != null)
                {
                    mediaPlayer.stop();

                    resetTimer();
                    playingState = "stop";
                }
            }
        });
    }

    private void startTheTimer(long startTimer)
    {
        countDownTimer = new CountDownTimer(startTimer + 100, 400)
        {
            @Override
            public void onTick(long l)
            {

            }

            @Override
            public void onFinish()
            {
                playingState = "stop";
                setMediaButtons(true, false, false ,true, false, false);
                countDownTimer.cancel();
            }
        }.start();
    }

    private void resetTimer()
    {
        countDownTimer.cancel();
    }

    private void makeMediaRecorderReady()
    {
        mediaRecorder = new MediaRecorder();

        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);

        // Sets the format of the output file produced during recording. Call this after setAudioSource()/setVideoSource() but before prepare(). It is recommended
        // to always use 3GP format when using the H.263 video encoder and AMR audio encoder. Using an MPEG-4 container format may confuse some desktop players.
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);

        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);

        // Sets the path of the output file to be produced. Call this after setOutputFormat() but before prepare().
        mediaRecorder.setOutputFile(audioSavePathInDevice);
    }

    private void requestPermission()
    {
        ActivityCompat.requestPermissions(AudioRecordActivity.this, new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,  Manifest.permission.RECORD_AUDIO }, REQUEST_PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        
        switch(requestCode)
        {
            case REQUEST_PERMISSION_CODE:
                
                if(grantResults.length > 0)
                {
                    boolean storagePermission = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean recordPermission = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    
                    if(storagePermission && recordPermission)
                    {
                        Toast.makeText(this, "All permissions are granted", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(this, "Some permissions are still pending", Toast.LENGTH_SHORT).show();
                    }
                }

                break;
        }
    }

    private boolean checkPermission()
    {
        int storageResult = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int recordResult = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECORD_AUDIO);

        return (storageResult == PackageManager.PERMISSION_GRANTED && recordResult == PackageManager.PERMISSION_GRANTED);
    }

    public String CreateRandomAudioFileName(int length)
    {
        StringBuilder stringBuilder = new StringBuilder( length );
        int i = 0 ;

        while(i < length )
        {
            stringBuilder.append(RandomAudioFileName.charAt(random.nextInt(RandomAudioFileName.length())));

            i++ ;
        }
        return stringBuilder.toString();
    }

    @Override
    public void onInfo(MediaRecorder mediaRecorder, int i, int i1)
    {
        Log.d(TAG, "onInfo: " + mediaRecorder);
    }

    @Override
    public void onError(MediaRecorder mediaRecorder, int i, int i1)
    {

    }
}
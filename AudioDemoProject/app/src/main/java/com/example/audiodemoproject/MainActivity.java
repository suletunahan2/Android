package com.example.audiodemoproject;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private MediaPlayer mediaPlayer;


    private double startTime = 0;
    private double finalTime = 0;

    private Handler mHandler=new Handler();
    private int forwardTime=5000;
    private int backwardTime=5000;
    private SeekBar seekBar;
    private TextView textView1,textView2;

    public static int oneTimeonly=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(this, R.raw.song);
        seekBar = (SeekBar)findViewById(R.id.seekBar);
        seekBar.setClickable(false);


        mediaPlayer = MediaPlayer.create(this,R.raw.song);


        Button stopbutton=(Button)findViewById(R.id.button3);
        stopbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Pausing sound",Toast.LENGTH_SHORT).show();

                mediaPlayer.pause();
            }
        });

        Button backButton=(Button)findViewById(R.id.button2);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Playing sound",Toast.LENGTH_SHORT).show();

                mediaPlayer.start();

                finalTime = mediaPlayer.getDuration();
                startTime = mediaPlayer.getCurrentPosition();

                seekBar.setProgress((int)startTime);


                //asynchronous
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {//sarkı bitince
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                Toast.makeText(MainActivity.this, "I'm done!",Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });











    }




}
package com.example.minutework;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import android.view.View;
import android.widget.TextView;
import android.widget.SeekBar;
import android.widget.ImageView;

import java.util.ArrayList;


public class lofi extends AppCompatActivity {
    TextView timestampleft, timestampright;
    SeekBar seekbar;
    ImageView playbutton;
    MediaPlayer musicPlayer;

    ImageView backward;
    ImageView forward;
    int currentIndex = 0;
    private Runnable runnable;
    private AudioManager audiomanager;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lofi);

        timestampleft = findViewById(R.id.timeStampleft3);
        timestampright = findViewById(R.id.timeStampright3);
        seekbar = findViewById(R.id.seekBar3);
        playbutton = findViewById(R.id.playbutton3);
        backward = findViewById(R.id.backward3);
        forward = findViewById(R.id.forward3);
        musicPlayer = MediaPlayer.create(this, R.raw.lofimusic1);
        audiomanager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        String duration = millisecondsToString(musicPlayer.getDuration());
        timestampright.setText(duration);
        seekbar.setMax(musicPlayer.getDuration());

        ArrayList<Integer> songs = new ArrayList<>();

        songs.add(0, R.raw.lofimusic1);
        songs.add(0, R.raw.lofimusic2);
        songs.add(0, R.raw.lofimusic3);
        songs.add(0, R.raw.lofimusic4);



        musicPlayer = MediaPlayer.create(getApplicationContext(), songs.get(currentIndex));
        playbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (musicPlayer != null && musicPlayer.isPlaying()) {
                    musicPlayer.pause();
                    playbutton.setImageResource(R.drawable.baseline_play_circle_24);
                } else {
                    if (musicPlayer != null) {
                        musicPlayer.start();
                    }
                    playbutton.setImageResource(R.drawable.baseline_pause_circle_filled_24);

                }
            }
        });
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (musicPlayer != null) {
                    playbutton.setImageResource(R.drawable.baseline_pause_circle_filled_24);
                }
                if (currentIndex < songs.size() - 1) {
                    currentIndex++;
                } else {
                    currentIndex = 0;
                }
                assert musicPlayer != null;
                if (musicPlayer.isPlaying()) {
                    musicPlayer.stop();
                }
                musicPlayer = MediaPlayer.create(getApplicationContext(), songs.get(currentIndex));
                musicPlayer.start();
            }

        });
        backward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (musicPlayer != null) {
                    playbutton.setImageResource(R.drawable.baseline_pause_circle_filled_24);

                }
                if (currentIndex > 0) {
                    currentIndex--;
                } else {
                    currentIndex = songs.size() - 1;
                }
                if (musicPlayer != null && musicPlayer.isPlaying()) {
                    musicPlayer.stop();
                }
                musicPlayer = MediaPlayer.create(getApplicationContext(), songs.get(currentIndex));
                musicPlayer.start();
            }
        });
        musicPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                seekbar.setMax(musicPlayer.getDuration());
                musicPlayer.start();
            }
        });

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    musicPlayer.seekTo(progress);
                    seekbar.setProgress(progress);
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        new Thread(new Runnable(){
            @Override
            public void run(){
                while(musicPlayer!=null){
                    if(musicPlayer.isPlaying()){
                        try {
                            final double current = musicPlayer.getCurrentPosition();

                            final String elapsedTime = millisecondsToString((int)current);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    timestampleft.setText(elapsedTime);
                                    seekbar.setProgress((int)current);
                                }
                            });
                            Thread.sleep(1000);
                        }catch (InterruptedException e){}

                    }
                }
            }
        }).start();
    }
    public String millisecondsToString(int time){
        String elapsedTime = "";
        int minutes = time/1000/60;
        int seconds = time/1000%60;
        elapsedTime = minutes +":";
        if(seconds<10){
            elapsedTime+="0";
        }
        elapsedTime += seconds;

        return elapsedTime;
    }



}


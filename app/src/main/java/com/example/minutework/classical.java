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
import android.widget.Toast;

import java.util.ArrayList;


public class classical extends AppCompatActivity {
    TextView timestampleft, timestampright;
    SeekBar seekbar1;
    ImageView playbutton1;
    MediaPlayer musicPlayer;

    ImageView backward1;
    ImageView forward1;
    int currentIndex = 0;



    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classical);

        timestampleft = findViewById(R.id.timeStampleft1);
        timestampright = findViewById(R.id.timeStampright1);
        seekbar1 = findViewById(R.id.seekBar1);
        playbutton1 = findViewById(R.id.playbutton1);
        backward1 = findViewById(R.id.backward1);
        forward1 = findViewById(R.id.forward1);
        musicPlayer = MediaPlayer.create(this, R.raw.piano1);

        String duration = millisecondsToString(musicPlayer.getDuration());
        timestampright.setText(duration);
        seekbar1.setMax(musicPlayer.getDuration());

        ArrayList<Integer> songs = new ArrayList<>();
        songs.add(0, R.raw.piano1);
        songs.add(1, R.raw.piano2);
        songs.add(2, R.raw.piano3);
        songs.add(3, R.raw.piano4);
        songs.add(4, R.raw.piano5);
        songs.add(5, R.raw.piano6);


        musicPlayer = MediaPlayer.create(getApplicationContext(), songs.get(currentIndex));
        playbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (musicPlayer != null && musicPlayer.isPlaying()) {
                    musicPlayer.pause();
                    playbutton1.setImageResource(R.drawable.baseline_play_circle_24);
                } else {
                    musicPlayer.start();
                    playbutton1.setImageResource(R.drawable.baseline_pause_circle_filled_24);

                }
            }
        });
        forward1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (musicPlayer != null) {
                    playbutton1.setImageResource(R.drawable.baseline_pause_circle_filled_24);
                }
                if (currentIndex < songs.size() - 1) {
                    currentIndex++;
                } else {
                    currentIndex = 0;
                }
                if (musicPlayer.isPlaying()) {
                    musicPlayer.stop();
                }
                musicPlayer = MediaPlayer.create(getApplicationContext(), songs.get(currentIndex));
                musicPlayer.start();
            }

        });
        backward1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (musicPlayer != null) {
                    playbutton1.setImageResource(R.drawable.baseline_pause_circle_filled_24);

                }
                if (currentIndex > 0) {
                    currentIndex--;
                } else {
                    currentIndex = songs.size() - 1;
                }
                if (musicPlayer.isPlaying()) {
                    musicPlayer.stop();
                }
                musicPlayer = MediaPlayer.create(getApplicationContext(), songs.get(currentIndex));
                musicPlayer.start();
            }
        });
        musicPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                seekbar1.setMax(musicPlayer.getDuration());
                musicPlayer.start();
            }
        });

        seekbar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    musicPlayer.seekTo(progress);
                    seekbar1.setProgress(progress);
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
                                    seekbar1.setProgress((int)current);
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


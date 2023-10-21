package com.example.minutework;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import java.util.Random;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;


public class cornyjokes extends AppCompatActivity {

    ImageButton funny;
    TextView joke;
    TextView answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cornyjokes);

        String[] theanswer = {
                "Stable",
                "A steamed veggie",
                "In a snowbank.",
                "You push it!",
                "A meow-ntain.",
                "Because their horns don’t work.",
                "Because it was two tired.",
                "You’re pointless.",
                "I’ll let you know what comes first.",
                "Lawsuits.",
                "The elf-abet.",
                "You look flushed."

        };
        String [] thejoke = {
                "What do you call a well-balanced horse?",
                "What do you call an angry carrot?",
                "Where do polar bears keep their money?",
                "How do you make an egg-roll?",
                "What do you call a pile of cats?",
                "Why do cows wear bells?",
                "Why did the bicycle fall over?",
                "What did the triangle say to the circle?",
                "I ordered a chicken and an egg online.",
                "What do lawyers wear to court?",
                "What do elves learn in school?",
                "What did one toilet say to another?"


        };
        answer = (TextView) findViewById(R.id.answer);
        funny = (ImageButton) findViewById(R.id.jokebutton);
        joke = (TextView)findViewById(R.id.joke);

        Random random = new Random();
        funny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int myRandomNumber = random.nextInt(12);
                answer.setText(theanswer[myRandomNumber]);
                joke.setText(thejoke[myRandomNumber]);



            }
        });




    }
}
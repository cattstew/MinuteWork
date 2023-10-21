package com.example.minutework;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;

import java.util.Random;
import android.widget.ImageButton;
import android.widget.TextView;


public class affirmations extends AppCompatActivity {

    ImageButton loved;
    TextView quotes;
    TextView author;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affirmations);

        String[] theauthor = {
                "Oprah",
                "Brene Brown",
                "Heather Havrilesky",
                "Michelle Obama",
                "Kabat-Zinn",
                "Audre Lorde",
                "Tim Storey",
                "Mister Rogers",
                "Gleen Close",
                "Maya Angelou",
                "Eleanor Roosevelt",
                "Deepak Chopra"

        };
        String [] thequote = {
                "The chance to love and be loved exists no matter where you are",
                "Courage starts with showing up and letting ourselves be seen",
                "Open your heart and drink in the glorious day",
                "Am I good enough? Yes I am",
                "The perfect moment is this one",
                "I am deliberate and afraid of nothing",
                "Your life is about to be incredible",
                "Who you are inside is what helps you make and do everything in life",
                "Your perspective is unique. It's important and it counts.",
                "Nothing can dim the light that shines from within",
                "You must do the things you think you cannot do",
                "The secret of attraction is to love yourself"

        };
        author = (TextView)findViewById(R.id.person);
        quotes = (TextView) findViewById(R.id.affirmation);
        loved = (ImageButton) findViewById(R.id.affirmationbutton);


        Random random = new Random();
        loved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int myRandomNumber = random.nextInt(12);
                author.setText(theauthor[myRandomNumber]);
                quotes.setText(thequote[myRandomNumber]);


            }
        });




    }
}
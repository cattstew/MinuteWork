package com.example.minutework;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import java.util.Random;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class babyanimals extends AppCompatActivity {
    ImageView babyanimal;
    ImageButton more;
    TextView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_babyanimals);
      int[] randomanimal = {
              R.drawable.african_bat_eared_foxes,
              R.drawable.baby_axolotol,
              R.drawable.baby_keenax_fox,
              R.drawable.baby_tree_kangaroo,
              R.drawable.patagonian_mara_babies,
              R.drawable.sugar_glider,
              R.drawable.souther_pudu_fawn,
        };
        String[] randomname = {
                "This is a baby african bat eared fox",
                "This is a baby axolotl",
                "This is a baby tree kangaroo",
                "This is a baby patagonian mara",
                "This is a baby sugar_glider",
                "This is a southern pudu fawn"

        };
        name = (TextView) findViewById(R.id.animalname);
        babyanimal= (ImageView)(findViewById(R.id.animal));
        more = (ImageButton) (findViewById(R.id.animalbutton));

        Random random = new Random();
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int myRandomNumber = random.nextInt(6);
                Toast.makeText(getApplicationContext(),"You found a new animal!",Toast.LENGTH_SHORT).show();
                babyanimal.setImageResource(randomanimal[myRandomNumber]);
                name.setText(randomname[myRandomNumber]);

            }
        });




    }
}
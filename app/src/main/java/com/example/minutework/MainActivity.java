package com.example.minutework;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.content.Intent;
import android.widget.PopupMenu;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    ImageView Image1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Image1 = findViewById(R.id.violin);
        Image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, classical.class);
                startActivity(intent);
            }
        });
        ImageView Image2;
        Image2 = findViewById(R.id.brain);
        Image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, binaural.class);
                startActivity(intent);
            }
        });
        ImageView Image3;
        Image3 = findViewById(R.id.headphone);
        Image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, lofi.class);
                startActivity(intent);
            }
        });
        ImageView Image4;
        Image4 = findViewById(R.id.tree);
        Image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, nature.class);
                startActivity(intent);
            }
        });
        ImageView menu = findViewById(R.id.menuicon);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenu(v);
            }

            private void showMenu(View v) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this,v);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getItemId()==R.id.jokes){
                            Intent intent = new Intent(MainActivity.this, cornyjokes.class);
                            startActivity(intent);
                        }
                        if(item.getItemId()==R.id.animals){
                            Intent intent = new Intent(MainActivity.this, babyanimals.class);
                            startActivity(intent);
                        }
                        if(item.getItemId()==R.id.affirmations){
                            Intent intent = new Intent(MainActivity.this, affirmations.class);
                            startActivity(intent);
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
    }
}
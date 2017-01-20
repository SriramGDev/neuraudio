package com.goperez.neuraudio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    boolean isPlaying = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickPlay(View view) {
        //Play button
        android.support.design.widget.FloatingActionButton playButton = (android.support.design.widget.FloatingActionButton) findViewById(R.id.myFAB);
        //Stores state of button
        if(isPlaying) {
            isPlaying = false;
            playButton.setImageResource(R.drawable.ic_play_arrow_white_24dp);
        } else {
            isPlaying = true;
            playButton.setImageResource(R.drawable.ic_pause_white_24dp);
        }
    }
}

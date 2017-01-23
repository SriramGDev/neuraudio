package com.goperez.neuraudio;

import android.net.Uri;
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
            Uri path = Uri.parse("android.resource://com.goperez.neuraudio/raw/pianonotes/");
            String pianoNotes = path.toString();
            com.goperez.neuraudio.Instrument piano_right = new com.goperez.neuraudio.Instrument("piano_right");
            piano_right.setPath_to_notes(pianoNotes);
            piano_right.setBar("A5i C6i D6h rq A6i C6i G6i F6i E6i A5i D6i C6i A5i C6i D6h rq A6i C6i G6i F6i E6i A5i D6i C6i A5i C6i D6i A5i C6i D6i C6i A5i A6i C6i G6i F6i E6i A5i D6i C6i D5i F5i A5i F5i A5i D6i A5i D6i F6i D6i F6i D7w");
        }
    }
}

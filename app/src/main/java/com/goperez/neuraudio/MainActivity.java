package com.goperez.neuraudio;

import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;

import jm.music.data.Note;
import jm.music.data.Part;
import jm.music.data.Phrase;
import jm.music.data.Score;
import jm.util.Write;

import static jm.constants.Durations.MINIM;
import static jm.constants.Pitches.C4;

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
            playButton.setImageResource(R.drawable.ic_play_arrow_white_48dp);
        } else {
            isPlaying = true;
            playButton.setImageResource(R.drawable.ic_pause_white_24dp);
            Note[] notes = new Note[10];
            Note[] notes2 = new Note[10];
            Random rand = new Random();

            for(int i = 0; i < 10; i++) {
                notes[i] = new Note(rand.nextInt(37) + 36, new Double(.25) + new Double(2.75) * rand.nextDouble());
            }

            for(int i = 0; i < 10; i++) {
                notes2[i] = new Note(rand.nextInt(37) + 36, new Double(.25) + new Double(2.75) * rand.nextDouble());
            }

            Phrase phrase = new Phrase();
            phrase.addNoteList(notes);
            Phrase phrase2 = new Phrase();
            phrase2.addNoteList(notes2);
            Part part2 = new Part();
            part2.addPhrase(phrase2);
            Part part = new Part();
            part.addPhrase(phrase);
            Score score = new Score("Test");
            score.addPart(part);
            score.addPart(part2);
            Write.midi(score, getFilesDir() + "/test.midi");
            System.out.println(new File(getFilesDir() + "/test.midi").exists());
            playMusic();
        }
    }

    public void playMusic() {
        try {
            MediaPlayer player = new MediaPlayer();
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.setDataSource(getFilesDir() + "/test.midi");
            player.prepare();
            player.start();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}

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
            rawFilestoInternalStorage();
            produceAndCreateMusic();
            while(!(new File("/storage/emulated/0/music/song.wav").exists())) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //playMusic();
            System.out.println(new File("/storage/emulated/0/music/song.wav").exists());
        }
    }

    public void rawFilestoInternalStorage() {
        try {
            InputStream input = this.getResources().openRawResource(R.raw.a5w);
            File outputFile = new File(getFilesDir(), "a5w.wav");
            OutputStream os = new FileOutputStream(outputFile);
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void produceAndCreateMusic() {
        com.goperez.neuraudio.Instrument piano_right = new com.goperez.neuraudio.Instrument("piano_right");
        piano_right.setPath_to_notes(getFilesDir().getAbsolutePath());
        piano_right.setBar("a5w");
        Player p = new Player(this.getApplicationContext());
        p.addInstrument(piano_right);

        System.out.println(getFilesDir().getAbsolutePath());
        System.out.println(p.produceMusic("/storage/emulated/0/Music/song.wav"));
        File check = new File("/storage/emulated/0/Music/song.wav");
        System.out.println(new File(getFilesDir() + "/a5w.wav").exists());
        System.out.println(check.exists());
    }

    public void playMusic() {
        try {
            MediaPlayer player = new MediaPlayer();
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.setDataSource(getFilesDir().getAbsolutePath() + "/song.wav");
            player.prepare();
            player.start();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}

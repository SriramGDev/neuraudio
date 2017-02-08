package com.goperez.neuraudio;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
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
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import jm.music.data.Note;
import jm.music.data.Part;
import jm.music.data.Phrase;
import jm.music.data.Score;
import jm.util.Write;

import static jm.constants.Durations.MINIM;
import static jm.constants.Pitches.C4;
import static jm.constants.ProgramChanges.GUITAR;
import static jm.constants.ProgramChanges.TRUMPET;

public class MainActivity extends AppCompatActivity {
    static boolean isPlaying = false;

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
            ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
            List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = am.getRunningAppProcesses();

            Iterator<ActivityManager.RunningAppProcessInfo> iter = runningAppProcesses.iterator();

            while(iter.hasNext()){
                ActivityManager.RunningAppProcessInfo next = iter.next();

                String processName = getPackageName() + ":service";

                if(next.processName.equals(processName)){
                    android.os.Process.killProcess(next.pid);
                    break;
                }
            }
        } else {
            isPlaying = true;
            playButton.setImageResource(R.drawable.ic_pause_white_24dp);
            Intent serviceIntent = new Intent(MainActivity.this, PlayService.class);
            MainActivity.this.startService(serviceIntent);
        }
    }

    public void restartService() {
        ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = am.getRunningAppProcesses();

        Iterator<ActivityManager.RunningAppProcessInfo> iter = runningAppProcesses.iterator();

        while(iter.hasNext()){
            ActivityManager.RunningAppProcessInfo next = iter.next();

            String processName = getPackageName() + ":service";

            if(next.processName.equals(processName)){
                android.os.Process.killProcess(next.pid);
                break;
            }
        }
        Intent serviceIntent = new Intent(MainActivity.this, PlayService.class);
        startService(serviceIntent);
    }
}

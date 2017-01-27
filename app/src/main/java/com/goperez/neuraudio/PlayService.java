package com.goperez.neuraudio;

import android.app.IntentService;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.provider.MediaStore;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import jm.music.data.Note;
import jm.music.data.Part;
import jm.music.data.Phrase;
import jm.music.data.Score;
import jm.util.Play;
import jm.util.Write;

import static jm.constants.ProgramChanges.GUITAR;
import static jm.constants.ProgramChanges.TRUMPET;

/**
 * Created by sriram on 1/27/17.
 */

public class PlayService extends IntentService {
    MediaPlayer player;

    public PlayService() {
        super("Play Service");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String dataString = intent.getDataString();
        createMusic();
    }

    public void createMusic() {
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
        Part trumpet = new Part("Trumpet", TRUMPET, 1);
        trumpet.addPhrase(phrase2);
        Part guitar = new Part("Guitar", GUITAR, 0);
        guitar.addPhrase(phrase);
        Score score = new Score("Test");
        score.addPart(guitar);
        score.addPart(trumpet);
        Write.midi(score, getFilesDir() + "/test.midi");
        System.out.println(new File(getFilesDir() + "/test.midi").exists());
        playMusic();
    }

    public void playMusic() {
        try {
            player = new MediaPlayer();
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.setDataSource(getFilesDir() + "/test.midi");
            player.prepare();
            player.start();
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    player.reset();
                    player.release();
                    createMusic();
                }
            });
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        player.stop();
        player.reset();
        player.release();
        player = null;
    }
}

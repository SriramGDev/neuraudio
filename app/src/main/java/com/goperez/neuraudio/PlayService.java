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

import static jm.constants.Durations.CROTCHET;
import static jm.constants.Pitches.A4;
import static jm.constants.Pitches.A5;
import static jm.constants.Pitches.B4;
import static jm.constants.Pitches.BF4;
import static jm.constants.Pitches.BF5;
import static jm.constants.Pitches.C5;
import static jm.constants.Pitches.CS5;
import static jm.constants.Pitches.D4;
import static jm.constants.Pitches.D5;
import static jm.constants.Pitches.E4;
import static jm.constants.Pitches.E5;
import static jm.constants.Pitches.F4;
import static jm.constants.Pitches.FS4;
import static jm.constants.Pitches.FS5;
import static jm.constants.Pitches.G4;
import static jm.constants.Pitches.G5;
import static jm.constants.Pitches.GS4;
import static jm.constants.ProgramChanges.GUITAR;
import static jm.constants.ProgramChanges.PIANO;
import static jm.constants.ProgramChanges.TRUMPET;
import static jm.constants.Scales.MAJOR_SCALE;

/**
 * Created by sriram on 1/27/17.
 */

public class PlayService extends IntentService {

    static final MediaPlayer player = new MediaPlayer();
    public static final String PARAM_OUT_MSG = "Done";

    public PlayService() {
        super("Play Service");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String dataString = intent.getDataString();
        createMusic();
    }

    public void createMusic() {
        Note[] notes1 = {new Note(A4, CROTCHET), new Note(E5, CROTCHET), new Note(A5, CROTCHET), new Note(CS5, CROTCHET), new Note(D5, CROTCHET), new Note(A4, CROTCHET), new Note(B4, CROTCHET), new Note(A4, CROTCHET), new Note(G4, CROTCHET), new Note(A4, CROTCHET), new Note(BF4, CROTCHET), new Note(G5, CROTCHET)};
        Note[] notes2 = {new Note(A5, CROTCHET), new Note(CS5, CROTCHET), new Note(CS5, CROTCHET), new Note(A4, CROTCHET), new Note(D5, CROTCHET), new Note(FS4, CROTCHET), new Note(G4, CROTCHET), new Note(B4, CROTCHET), new Note(B4, CROTCHET), new Note(B4, CROTCHET), new Note(E4, CROTCHET), new Note(F4, CROTCHET)};
        Note[] notes3 = {new Note(FS5, CROTCHET), new Note(FS5, CROTCHET), new Note(C5, CROTCHET), new Note(BF5, CROTCHET), new Note(A5, CROTCHET), new Note(B4, CROTCHET), new Note(B4, CROTCHET), new Note(B4, CROTCHET), new Note(E4, CROTCHET), new Note(F4, CROTCHET), new Note(FS5, CROTCHET), new Note(FS5, CROTCHET)};
        Note[] notes4 = {new Note(C5, CROTCHET), new Note(BF5, CROTCHET), new Note(A5, CROTCHET), new Note(B4, CROTCHET), new Note(BF4, CROTCHET), new Note(G5, CROTCHET), new Note(FS5, CROTCHET), new Note(CS5, CROTCHET), new Note(CS5, CROTCHET), new Note(A4, CROTCHET), new Note(D5, CROTCHET), new Note(FS4, CROTCHET)};
        Note[] notes5 = {new Note(G4, CROTCHET), new Note(B4, CROTCHET), new Note(BF4, CROTCHET), new Note(G5, CROTCHET), new Note(G5, CROTCHET), new Note(D5, CROTCHET), new Note(A4, CROTCHET), new Note(G4, CROTCHET), new Note(D5, CROTCHET), new Note(FS4, CROTCHET), new Note(G4, CROTCHET), new Note(C5, CROTCHET)};
        Note[] notes6 = {new Note(G4, CROTCHET), new Note(C5, CROTCHET), new Note(E4, CROTCHET), new Note(F4, CROTCHET), new Note(FS5, CROTCHET), new Note(FS5, CROTCHET), new Note(C5, CROTCHET), new Note(BF5, CROTCHET), new Note(A5, CROTCHET), new Note(CS5, CROTCHET), new Note(G4, CROTCHET), new Note(C5, CROTCHET)};
        Note[] notes7 = {new Note(E4, CROTCHET), new Note(F4, CROTCHET), new Note(FS5, CROTCHET), new Note(FS5, CROTCHET), new Note(C5, CROTCHET), new Note(BF5, CROTCHET), new Note(A5, CROTCHET), new Note(CS5, CROTCHET), new Note(BF4, CROTCHET), new Note(G5, CROTCHET), new Note(BF5, CROTCHET), new Note(D5, CROTCHET)};
        Note[] notes8 = {new Note(CS5, CROTCHET), new Note(G4, CROTCHET), new Note(D5, CROTCHET), new Note(GS4, CROTCHET), new Note(G4, CROTCHET), new Note(C5, CROTCHET), new Note(BF4, CROTCHET), new Note(G5, CROTCHET), new Note(FS5, CROTCHET), new Note(D5, CROTCHET), new Note(CS5, CROTCHET), new Note(G4, CROTCHET)};
        Note[] notes9 = {new Note(CS5, CROTCHET), new Note(GS4, CROTCHET), new Note(G4, CROTCHET), new Note(A4, CROTCHET), new Note(A4, CROTCHET), new Note(A4, CROTCHET), new Note(E4, CROTCHET), new Note(F4, CROTCHET), new Note(FS5, CROTCHET), new Note(FS5, CROTCHET), new Note(D5, CROTCHET), new Note(BF5, CROTCHET)};
        Note[] notes10 = {new Note(A5, CROTCHET), new Note(A4, CROTCHET), new Note(A4, CROTCHET), new Note(A4, CROTCHET), new Note(E4, CROTCHET), new Note(F4, CROTCHET), new Note(FS5, CROTCHET), new Note(FS5, CROTCHET), new Note(D5, CROTCHET), new Note(BF5, CROTCHET), new Note(A5, CROTCHET), new Note(A4, CROTCHET)};
        Note[] notes11 = {new Note(D4, CROTCHET), new Note(G5, CROTCHET), new Note(G5, CROTCHET), new Note(D5, CROTCHET), new Note(CS5, CROTCHET), new Note(A4, CROTCHET), new Note(D5, CROTCHET), new Note(GS4, CROTCHET), new Note(G4, CROTCHET), new Note(B4, CROTCHET), new Note(D4, CROTCHET), new Note(G5, CROTCHET)};
        Note[] notes12 = {new Note(FS5, CROTCHET), new Note(C5, CROTCHET), new Note(A4, CROTCHET), new Note(G4, CROTCHET), new Note(D5, CROTCHET), new Note(GS4, CROTCHET), new Note(G4, CROTCHET), new Note(B4, CROTCHET), new Note(GS4, CROTCHET), new Note(C5, CROTCHET), new Note(E4, CROTCHET), new Note(F4, CROTCHET)};

        Random rand = new Random();
        Note[] selectedList = notes1;
        int listVal = rand.nextInt(12) + 1;
        switch (listVal) {
            case 1:
                selectedList = notes1;
                break;
            case 2:
                selectedList = notes2;
                break;
            case 3:
                selectedList = notes3;
                break;
            case 4:
                selectedList = notes4;
                break;
            case 5:
                selectedList = notes5;
                break;
            case 6:
                selectedList = notes6;
                break;
            case 7:
                selectedList = notes7;
                break;
            case 8:
                selectedList = notes8;
                break;
            case 9:
                selectedList = notes9;
                break;
            case 10:
                selectedList = notes10;
                break;
            case 11:
                selectedList = notes11;
                break;
            case 12:
                selectedList = notes12;
                break;
        }
        Phrase phrase = new Phrase();
        phrase.addNoteList(selectedList);
        Part piano = new Part("Piano", PIANO, 0);
        piano.addPhrase(phrase);
        Score score = new Score("Piano");
        score.addPart(piano);
        Write.midi(score, getFilesDir() + "/test.midi");
        System.out.println(new File(getFilesDir() + "/test.midi").exists());
        playMusic();
    }

    public void playMusic() {
        try {
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.setDataSource(getFilesDir() + "/test.midi");
            player.prepare();
            player.start();
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    System.out.println("Finished");
                    Intent broadcastIntent = new Intent();
                    broadcastIntent.setAction(ResponseReceiver.ACTION_RESP);
                    broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
                    broadcastIntent.putExtra(PARAM_OUT_MSG, "Finished");
                    sendBroadcast(broadcastIntent);
                }
            });
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}

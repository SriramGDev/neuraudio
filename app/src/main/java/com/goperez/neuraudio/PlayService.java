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
        Note[] notes = new Note[10];
        Random rand = new Random();
        boolean scaleNote = false;
        while(!scaleNote) {
            notes[0] = new Note(rand.nextInt(37) + 36, new Double(.25) + new Double(2.75) * rand.nextDouble());
            if(notes[0].isScale(MAJOR_SCALE)) {
                scaleNote = true;
            }
        }

        for(int i = 0; i < 10; i++) {
            //notes[i] = new Note(rand.nextInt(37) + 36, new Double(.25) + new Double(2.75) * rand.nextDouble());
            notes[i] = markovChain(notes[i-1]);
        }

        Phrase phrase = new Phrase();
        phrase.addNoteList(notes);
        Part guitar = new Part("Piano", PIANO, 0);
        guitar.addPhrase(phrase);
        Score score = new Score("Piano");
        score.addPart(guitar);
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

    public Note markovChain(Note currentNote) {
        float[][] transitionMatrix = new float[][] {

            //    C    C#  D    D#      E       F     F#   G       G#   A   A#  B
           /*C*/{.16f, 0, .2f, .08f, .0333f, .16337f, 0f, .13f, .2133f, 0, .1f, 0},
          /*C#*/{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
           /*D*/{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
          /*D#*/{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
           /*E*/{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
           /*F*/{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
          /*F#*/{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
           /*G*/{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
          /*G#*/{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
           /*A*/{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
          /*A#*/{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
           /*B*/{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        };
        return null;
    }
}

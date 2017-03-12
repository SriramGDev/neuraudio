# Neuraudio
A procedurally generating music app for android that demonstrates the abilities of markov chains.

## What can I use it for?
Neuraudio has uses for:
* Music Producers - need an idea for a song or melody? Fire up Neuraudio and listen until something speaks to you
* The layman - Out of data and don't have anything to listen to? Neuraudio generates music straight from the app - no internet connection required! Enjoy relaxing melodies and chords at the touch of a button.

## How does it work?
Like this:
```java
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
```
This is one of our most vital functions as it creates musical phrases with instruments and initializes the markov chain.

# TODO
## Networking
* Create neuron as interface
* Implement interfaces for network
* Calculate initial weights for network
* Develop and implement algorithm for creation

## Python
* Pull Music21 Corpus pieces through API
* Parse MusicXML files into midi
* Analyze midi to calculate weights

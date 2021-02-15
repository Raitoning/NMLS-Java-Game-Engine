package engine.audio;

import engine.gameobject.component.AudioSource;

import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.*;

/**
 * <h1>Audio</h1>
 * Audio class of the game engine.
 * <p>
 * This class is responsible for handling all the audio components of the game engine.
 * </p>
 *
 * @author  Raitoning
 * @version 2021.02.15
 * @since   2021.02.11
 */
public class Audio {

    public static Audio instance;
    private ArrayList<AudioSource> audioSources;

    private HashMap<AudioSource, Clip> sourcesVoices;

    private Audio() {

        audioSources = new ArrayList<>();
        sourcesVoices = new HashMap<AudioSource, Clip>();
    }

    public void update() {

        for (AudioSource source: audioSources) {

            if (source.isToPlay()) {

                if (sourcesVoices.containsKey(source))
                {
                    sourcesVoices.get(source).setFramePosition(0);
                }
                else
                {
                    ByteArrayInputStream inputStream = new ByteArrayInputStream(source.getSoundData());

                    AudioInputStream audioStream = null;
                    try {
                        audioStream = AudioSystem.getAudioInputStream(inputStream);
                    } catch (UnsupportedAudioFileException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    DataLine.Info info = new DataLine.Info(Clip.class, audioStream.getFormat());

                    Clip clip = null;

                    try {
                        clip = (Clip) AudioSystem.getLine(info);
                    } catch (LineUnavailableException e) {
                        e.printStackTrace();
                    }

                    try {
                        clip.open(audioStream);
                    } catch (LineUnavailableException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    sourcesVoices.put(source, clip);
                    clip.start();
                }
            }

            source.Stop();
        }

    }


    public void addAudioSource (AudioSource source)
    {
        if (!audioSources.contains(source))
        {
            audioSources.add(source);
        }
    }


    public void removeAudioSource (AudioSource source)
    {
        audioSources.remove(source);
    }


    /** Get the running Audio or instanciates a new one and return it.
     *
     * @return The running instance of Audio or a new one.
     */
    public static Audio getInstance() {

        if(instance == null) {

            instance = new Audio();
        }

        return instance;
    }
}

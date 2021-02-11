package engine.audio;

import engine.SoundFactory;
import engine.Time;
import engine.gameobject.component.AudioSource;
import sun.audio.AudioPlayer;

import javax.sound.sampled.*;
import java.applet.AudioClip;
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
 * @version 2021.02.11
 * @since   2021.02.11
 */
public class Audio {

    public static Audio instance;
    private ArrayList<AudioSource> audioSources;

    private HashMap<AudioSource, Float> sourcesTime;
    private HashMap<AudioSource, Clip> sourcesVoices;

    private final int MAX_VOICES = 32;
    private int activeVoices = 0;

    private Audio() {

        audioSources = new ArrayList<>();
        sourcesTime = new HashMap<AudioSource, Float>();
        sourcesVoices = new HashMap<AudioSource, Clip>();
    }

    /** This function is called once every frame and gather all inputs from the keyboard and the mouse and stock them.
     *
     */
    public void update() {

        for (AudioSource source: audioSources)
        {
            if (source.isToPlay())
            {
                System.out.println(source.getName());
                if (source.isPlaying())
                {
                    sourcesVoices.get(source).stop();
                    sourcesVoices.get(source).close();
                    source.setPlaying(false);
                }


                DataLine.Info info = new DataLine.Info(Clip.class, source.getFormat());

                try {
                    Clip clip = (Clip) AudioSystem.getLine(info);

                    clip.open(source.getStream());

                    clip.start();
                    activeVoices++;

                    sourcesTime.put(source, Time.frameStartTime);
                    sourcesVoices.put(source, clip);
                    source.setPlaying(true);
                } catch (LineUnavailableException | IOException e) {
                        e.printStackTrace();
                }

                source.Stop();
            }
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

package engine.gameobject.component;

import engine.Engine;
import engine.SoundFactory;
import engine.gameobject.GameObject;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * <h1>AudioSource</h1>
 * AudioSource class of the game engine.
 * <p>
 * A component used to add a sound to be played by the audio engine.
 * </p>
 *
 * @author  Raitoning
 * @version 2021.02.11
 * @since   2021.02.11
 */
public class AudioSource implements Component {

    private GameObject gameObject;
    private String name;

    private boolean toPlay = false;
    private boolean playing = false;
    private AudioInputStream stream;
    private AudioFormat format;

    /** Creates a new AudioSource given the name of the sound to use.
     *
     * @param name Name of the sound to play.
     * @param gameObject The GameObject this Component is attached to.
     */
    public AudioSource (String name, GameObject gameObject)
    {
        this.gameObject = gameObject;
        this.name = name;

        try {
            stream = AudioSystem.getAudioInputStream(SoundFactory.getInstance().getSound(name));

            format = stream.getFormat();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Engine.getInstance().getAudio().addAudioSource(this);
    }

    public void Play()
    {
        toPlay = true;
    }

    public void Stop()
    {
        toPlay = false;
    }

    public boolean isToPlay()
    {
        return toPlay;
    }

    public boolean isPlaying()
    {
        return playing;
    }

    public void setPlaying(boolean value)
    {
        playing = value;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String value)
    {
        name = value;
    }

    public AudioInputStream getStream()
    {

        try {
            stream = AudioSystem.getAudioInputStream(SoundFactory.getInstance().getSound(name));
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stream;
    }

    public AudioFormat getFormat()
    {
        return format;
    }

    @Override
    public void destroy() {

    }
}

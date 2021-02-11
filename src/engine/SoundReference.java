package engine;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * <h1>SoundReference</h1>
 * A reference to a sound file.
 *
 * <p>
 *     Makes easier to import sprites into the engine and then using them.
 * </p>
 *
 * @author  Raitoning
 * @version 2021.12.03
 * @since   2021.12.03
 */
public class SoundReference {

    private String name;
    private File sound;

    /** Constructs a new SoundReference given the desired name and the path to the file.
     *
     * @param name Desired name for the audio file.
     * @param path Path of the file to use.
     */
    SoundReference(String name, String path) {

        this.name = name;
        sound = new File (path);
    }

    SoundReference(String name, File sound) {

        this.name = name;
        this.sound = sound;
    }

    /** Get the name of the sound.
     *
     * @return The name of the sound.
     */
    public String getName() {

        return name;
    }

    /** Get the InputStream of the sound.
     *
     * @return The InputStream of the sound.
     */
    File getSound() {

        return sound;
    }
}

package engine;


import engine.exception.UnreferencedSoundException;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * <h1>SoundFactory</h1>
 * A Factory for sounds.
 *
 * <p>
 *     Allows the engine to load a sound only once and use it multiple times.
 * </p>
 *
 * @author  Raitoning
 * @version 2021.12.03
 * @since   2021.12.03
 */
public class SoundFactory {

    private static SoundFactory instance;

    private ArrayList<FileReference> fileReferences;
    private ArrayList<SoundReference> sounds;

    private SoundFactory() {

        fileReferences = new ArrayList<>();
        sounds = new ArrayList<>();
    }

    /** Add a new sound to the engine with the desired name and the path of it's file
     *
     * @param name The desired name for the sound.
     * @param path The path to the file containing the sound.
     */
        void addSound(String name, String path) {

        fileReferences.add(new FileReference(name, path));
    }

    /** Get a sound given it's name. Check if it's already loaded or load it first.
     *  May throw an UnreferencedSpriteException and return null if the sprite doesn't exists.
     *
     * @param name The name of the desired sprite
     * @return  The File of the sound or null if it doesn't exists.
     */
    public File getSound(String name) {

        // Check if the sound has already been loaded
        for (SoundReference sound : sounds) {

            if (sound.getName().equals(name)) {

                return sound.getSound();
            }
        }

        // if not already loaded, check if it's referenced and load it
        for (FileReference fileReference : fileReferences) {

            if (fileReference.getName().equals(name)) {

                sounds.add(new SoundReference(name, fileReference.getPath()));
                return getSound(name);
            }
        }

        // if not referenced, throw an exception and return null
        try {
            throw new UnreferencedSoundException(name);
        } catch (UnreferencedSoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    /** Get the unique instance of the Factory.
     *
     * @return The instance of the Factory
     */
    public static SoundFactory getInstance() {

        if (instance == null) {

            instance = new SoundFactory();
        }

        return instance;
    }
}

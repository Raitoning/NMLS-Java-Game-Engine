package engine;

import engine.exception.UnreferencedSpriteException;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * <h1>SpriteFactory</h1>
 * A Factory for sprites.
 *
 * <p>
 *     Allows the engine to load a sprite only once and use it multiple times.
 * </p>
 *
 * @author  Raitoning
 * @version 2018-11-14
 * @since   2018-11-14
 */
public class SpriteFactory {

    public static SpriteFactory instance;

    private ArrayList<FileReference> fileReferences;
    private ArrayList<SpriteReference> sprites;

    private SpriteFactory() {

        fileReferences = new ArrayList<>();
        sprites = new ArrayList<>();
    }

    /** Add a new sprite to the engine with the desired name and the path of it's file
     *
     * @param name The desired name for the sprite.
     * @param path The path to the file containing the sprite.
     */
    public void addSprite(String name, String path) {

        fileReferences.add(new FileReference(name, path));
    }

    /** Get a sprite given it's name. Check if it's already loaded or load it first.
     *  May throw an UnreferencedSpriteException and return null if the sprite doesn't exists.
     *
     * @param name The name of the desired sprite
     * @return  The BufferedImage of the sprite or null if it doesn't exists.
     */
    public BufferedImage getSprite(String name) {

        // Check if the sprite has already been loaded
        for (int i = 0; i < sprites.size(); i++) {

            if(sprites.get(i).getName().equals(name)) {

                return sprites.get(i).getSprite();
            }
        }

        // if not already loaded, check if it's referenced and load it
        for (int i = 0; i < fileReferences.size(); i++) {

            if (fileReferences.get(i).getName().equals(name)) {

                sprites.add(new SpriteReference(name, fileReferences.get(i).getPath()));
                return getSprite(name);
            }
        }

        // if not referenced, throw an exception and return null
        try {
            throw new UnreferencedSpriteException(name);
        } catch (UnreferencedSpriteException e) {
            e.printStackTrace();
        }
        return null;
    }

    /** Get the unique instance of the Factory.
     *
     * @return The instance of the Factory
     */
    public static SpriteFactory getInstance() {

        if (instance == null) {

            instance = new SpriteFactory();
        }

        return instance;
    }
}

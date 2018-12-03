package engine;

import engine.exception.UnreferencedSpriteException;
import engine.gameobject.GameObject;

import java.awt.*;
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
 * @version 2018.12.03
 * @since   2018.11.14
 */
public class SpriteFactory {

    private static SpriteFactory instance;

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
    void addSprite(String name, String path) {

        fileReferences.add(new FileReference(name, path));
    }

    /** Get a sprite given it's name. Check if it's already loaded or load it first.
     *  May throw an UnreferencedSpriteException and return null if the sprite doesn't exists.
     *
     * @param name The name of the desired sprite
     * @return  The BufferedImage of the sprite or null if it doesn't exists.
     */
    private BufferedImage getSprite(String name) {

        // Check if the sprite has already been loaded
        for (SpriteReference sprite : sprites) {

            if (sprite.getName().equals(name)) {

                return sprite.getSprite();
            }
        }

        // if not already loaded, check if it's referenced and load it
        for (FileReference fileReference : fileReferences) {

            if (fileReference.getName().equals(name)) {

                sprites.add(new SpriteReference(name, fileReference.getPath()));
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

    /** Get a rescaled sprite given it's name. Check if it's already loaded or load it first.
     *  May throw an UnreferencedSpriteException and return null if the sprite doesn't exists.
     *
     * @param name The name of the desired rescaled sprite
     * @return  The BufferedImage of the rescaled sprite or null if it doesn't exists.
     */
    // WORKAROUND: Added 1 pixel in each dimension to get rid of random black bars appearing between sprites.
    public BufferedImage getScaledSprite(String name, GameObject gameObject) {

        String scaledInstanceName = "_scaled" + name + "_" + Engine.getInstance().getRenderer().getActiveCamera().getOrthographicSize() + "_" + gameObject.getTransform().scale() + "_" + Engine.getInstance().getRenderer().getActiveCamera();

        // Check if the sprite has already been loaded
        for (SpriteReference sprite1 : sprites) {

            if (sprite1.getName().equals(scaledInstanceName)) {

                return sprite1.getSprite();
            }
        }

        BufferedImage sprite = getSprite(name);

        float scaleFactor;

        if (sprite != null) {

            scaleFactor = Engine.getInstance().getRenderer().getVerticalSpriteSizeTarget() / (float)sprite.getWidth();

            sprite = resize(sprite, (int)(sprite.getWidth() * scaleFactor * gameObject.getTransform().scale().getX()) + 1, (int)(sprite.getHeight() * scaleFactor * gameObject.getTransform().scale().getY()) + 1);
        }

        sprites.add(new SpriteReference(scaledInstanceName, sprite));

        return sprite;
    }

    private BufferedImage resize(BufferedImage img, int newW, int newH) {

        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
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

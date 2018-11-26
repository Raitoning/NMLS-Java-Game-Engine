package engine;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * <h1>SpriteReference</h1>
 * A reference to an image file containing the sprite.
 *
 * <p>
 *     Makes easier to import sprites into the engine and then using them.
 * </p>
 *
 * @author  Raitoning
 * @version 2018.11.14
 * @since   2018.11.14
 */
public class SpriteReference {

    private String name;
    private BufferedImage sprite;

    /** Constructs a new SpriteReference given the desired name and the path to the file.
     *
     * @param name Desired name for the sprite.
     * @param path Path of the file to use.
     */
    public SpriteReference(String name, String path) {

        this.name = name;

        try {
            sprite = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SpriteReference(String name, BufferedImage sprite) {

        this.name = name;
        this.sprite = sprite;
    }

    /** Get the name of the sprite.
     *
     * @return The name of the sprite.
     */
    public String getName() {
        return name;
    }

    /** Get the BufferedImage of the sprite.
     *
     * @return The BufferedImage of the sprite.
     */
    public BufferedImage getSprite() {
        return sprite;
    }
}

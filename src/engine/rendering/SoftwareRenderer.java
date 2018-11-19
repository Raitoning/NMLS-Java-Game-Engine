package engine.rendering;

import engine.Vector2;
import engine.gameobject.component.Camera;
import engine.gameobject.component.Transform;
import engine.input.Input;
import engine.gameobject.component.SpriteRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * <h1>SoftwareRenderer</h1>
 * A 2D rendering engine running on the CPU.
 * <p>
 * This class is responsible of handling the whole rendering process. It creates a JFrame to render with, cull and sort sprites before rendering them.
 * </p>
 *
 * @author  Raitoning
 * @version 2018-11-14
 * @since   2018-11-14
 */
public class SoftwareRenderer {

    private int width;
    private int height;
    private JFrame window;
    private BufferedImage outputImage;
    private Graphics2D frameBuffer;
    private int[] rawFrameBuffer;
    private int CLEARCOLOR = new Color(0,0,0).getRGB();
    private ArrayList<SpriteRenderer> sprites;
    private ArrayList<SpriteRenderer> renderQueue;
    private float aspectRatio;
    private Camera camera;
    private float verticalSpriteSizeTarget;

    /** Constructs a new SoftwareRenderer with the desired width and height of the rendering zone.
     *
     * @param w Width of the rendering zone.
     * @param h Height of the rendering zone.
     */
    public SoftwareRenderer(int w, int h) {

        width = w;
        height = h;
        outputImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        frameBuffer = outputImage.createGraphics();
        rawFrameBuffer = ((DataBufferInt) outputImage.getRaster().getDataBuffer()).getData();

        aspectRatio = (float)width/(float)height;

        window = new JFrame("Software rendering tests");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.add(new JLabel(new ImageIcon(outputImage)));
        window.pack();
        window.setVisible(true);

        window.addKeyListener(Input.getInstance().getKeyboardInput());
        window.getContentPane().addMouseListener(Input.getInstance().getMouseInput());
        sprites = new ArrayList<>();
        renderQueue = new ArrayList<>();
    }

    /** Cull, sort and render sprites to the rendering zone. Can clear the buffer with a determined color before rendering.
     *
     */
    public void update() {

        if(camera == null) {

            Arrays.fill(rawFrameBuffer, CLEARCOLOR);
        } else {

            cull();
            zSort();

            Arrays.fill(rawFrameBuffer, CLEARCOLOR);

            int x;
            int y;
            SpriteRenderer sprite;
            Vector2 projectedPosition;

            for (int i = renderQueue.size() - 1; i > -1; i--) {

                sprite = renderQueue.get(i);
                projectedPosition = camera.worldToCamera(sprite.getGameObject().getTransform().position());

                x = (int) ((width * projectedPosition.getX()) - sprite.getScaledSprite().getWidth() / 2f);
                y = (int) ((height * projectedPosition.getY()) - sprite.getScaledSprite().getHeight() / 2f);

                frameBuffer.drawImage(sprite.getScaledSprite(), null, x, y);
            }
        }

        window.repaint();
    }

    private void cull() {

         renderQueue.clear();

         SpriteRenderer sprite;
         Transform transform;

        for (int i = 0; i < sprites.size(); i++) {

            sprite = sprites.get(i);
            transform = sprite.getGameObject().getTransform();

            if( transform.position().getX() + transform.scale().getX() >= -((camera.getOrthographicSize() * aspectRatio) / 2)+camera.getGameObject().getTransform().position().getX() && transform.position().getX() - transform.scale().getX() <= ((camera.getOrthographicSize() * aspectRatio)/ 2) + camera.getGameObject().getTransform().position().getX()) {


                if(transform.position().getY() + transform.scale().getY() >= -(camera.getOrthographicSize() / 2)+camera.getGameObject().getTransform().position().getY() && transform.position().getY() - transform.scale().getY() <= (camera.getOrthographicSize() /2)+camera.getGameObject().getTransform().position().getY()) {

                    if(transform.position().getZ() >= camera.getNearClippingPlane() && transform.position().getZ() <= camera.getFarClippingPlane()) {

                    renderQueue.add(sprite);
                    }
                }
            }
        }

//         System.out.println("Sprite to render: " + renderQueue.size());
    }

    private void zSort() {

        renderQueue.sort((o1, o2) -> Float.compare(o1.getGameObject().getTransform().position().getZ(), o2.getGameObject().getTransform().position().getZ()));
    }

    /** Add a sprite to the sprite list to render. It will be culled and sorted before rendering.
     *
     * @param sprite The SpriteRenderer containing the sprite to render.
     */
    public void addSpriteToQueue(SpriteRenderer sprite) {

        sprites.add(sprite);
        sprite.rescale();
    }

    /** Remove a sprite from the rendering list.
     *
     * @param sprite The SpriteRenderer containing the sprite to remove from the rendering list.
     */
    public void removeSpriteFromQueue(SpriteRenderer sprite) {

        if(sprites.contains(sprite)) {

            sprites.remove(sprite);
        }
    }

    /** Get the aspect ratio of the rendering zone.
     *
     * @return The aspect ratio of the rendering zone.
     */
    public float getAspectRatio() {

        return aspectRatio;
    }

    /** Set the camera used to render its content.
     *
     * @param camera The camera to use for rendering.
     */
    public void setCamera(Camera camera) {

        this.camera = camera;

        verticalSpriteSizeTarget = (float)(height) / camera.getOrthographicSize();

        for (int i = 0; i < sprites.size(); i++) {

            sprites.get(i).rescale();
        }
    }

    /** Get the vertical size targeted when rescaling sprites.
     *
     * @return
     */
    public float getVerticalSpriteSizeTarget() {

        return verticalSpriteSizeTarget;
    }

    /** Get the camera used for rendering.
     *
     * @return The camera used for rendering.
     */
    public Camera getCamera() {

        return camera;
    }

    /** Get the width of the rendering zone.
     *
     * @return The width of the rendering zone.
     */
    public int getWidth() {

        return width;
    }

    /** Get the height of the rendering zone.
     *
     * @return The height of the rendering zone.
     */
    public int getHeight() {

        return height;
    }
}

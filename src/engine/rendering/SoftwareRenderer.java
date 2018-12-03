package engine.rendering;

import engine.SpriteFactory;
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
 * @version 2018.12.03
 * @since   2018.11.14
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
    private Camera activeCamera;
    private ArrayList<Camera> cameras;

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
        window.setSize(width, height);
        window.setVisible(true);

        window.addKeyListener(Input.getKeyboardInput());
        window.getContentPane().addMouseListener(Input.getMouseInput());
        sprites = new ArrayList<>();
        renderQueue = new ArrayList<>();
        cameras = new ArrayList<>();
    }

    /** Cull, sort and render sprites to the rendering zone. Can clear the buffer with a determined color before rendering.
     *
     */
    public void update() {

        Arrays.fill(rawFrameBuffer, CLEARCOLOR);

        cameraSort();

        for (int i = cameras.size() - 1; i > -1; i--) {

            activeCamera = cameras.get(i);

            cull();
            zSort();

            int x;
            int y;
            SpriteRenderer sprite;
            Vector2 projectedPosition;

            BufferedImage renderTexture = new BufferedImage((int) (width * (activeCamera.getMaxRenderArea().getX() - activeCamera.getMinRenderArea().getX())), (int) (height * (activeCamera.getMaxRenderArea().getY() - activeCamera.getMinRenderArea().getY())), BufferedImage.TYPE_INT_ARGB);
            Graphics2D renderTextureFrameBuffer = renderTexture.createGraphics();

            for (int j = renderQueue.size() - 1; j > -1; j--) {

                sprite = renderQueue.get(j);
                projectedPosition = activeCamera.worldToCamera(sprite.getGameObject().getTransform().position());

                BufferedImage spriteToRender = SpriteFactory.getInstance().getScaledSprite(sprite.getName(), sprite.getGameObject());


                x = (int) ((renderTexture.getWidth() * projectedPosition.getX()) - spriteToRender.getWidth() / 2f);
                y = (int) ((renderTexture.getHeight() * projectedPosition.getY()) - spriteToRender.getHeight() / 2f);

                renderTextureFrameBuffer.drawImage(spriteToRender, null, x, y);
            }

            x = (int) (width * activeCamera.getMinRenderArea().getX());

            y = (int) (height * ( 1f - activeCamera.getMaxRenderArea().getY()));

            frameBuffer.drawImage(renderTexture, null, x, y);

            renderTextureFrameBuffer.dispose();
        }
        window.repaint();
    }

    private void cull() {

         renderQueue.clear();

         SpriteRenderer sprite;
         Transform transform;

        for (SpriteRenderer spriteRenderer : sprites) {

            sprite = spriteRenderer;
            transform = sprite.getGameObject().getTransform();

            if (transform.position().getX() + transform.scale().getX() >= -((activeCamera.getOrthographicSize() * activeCamera.getAspectRatio()) / 2) + activeCamera.getGameObject().getTransform().position().getX() && transform.position().getX() - transform.scale().getX() <= ((activeCamera.getOrthographicSize() * activeCamera.getAspectRatio()) / 2) + activeCamera.getGameObject().getTransform().position().getX()) {


                if (transform.position().getY() + transform.scale().getY() >= -(activeCamera.getOrthographicSize() / 2) + activeCamera.getGameObject().getTransform().position().getY() && transform.position().getY() - transform.scale().getY() <= (activeCamera.getOrthographicSize() / 2) + activeCamera.getGameObject().getTransform().position().getY()) {

                    if (transform.position().getZ() >= activeCamera.getNearClippingPlane() && transform.position().getZ() <= activeCamera.getFarClippingPlane()) {

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

    private void cameraSort() {

        cameras.sort((o1, o2) -> Integer.compare(o1.getRenderPriority(), o2.getRenderPriority()));
    }

    /** Add a sprite to the sprite list to render. It will be culled and sorted before rendering.
     *
     * @param sprite The SpriteRenderer containing the sprite to render.
     */
    public void addSpriteToQueue(SpriteRenderer sprite) {

        if(!sprites.contains(sprite)) {

            sprites.add(sprite);
        }
    }

    /** Remove a sprite from the rendering list.
     *
     * @param sprite The SpriteRenderer containing the sprite to remove from the rendering list.
     */
    public void removeSpriteFromQueue(SpriteRenderer sprite) {

        sprites.remove(sprite);
    }

    /** Get the aspect ratio of the rendering zone.
     *
     * @return The aspect ratio of the rendering zone.
     */
    public float getAspectRatio() {

        return aspectRatio;
    }

    /** Get the vertical size targeted when rescaling sprites.
     *
     * @return The target size for sprites for the current rendering camera.
     */
    public float getVerticalSpriteSizeTarget() {

        return activeCamera.getVerticalSpriteSizeTarget();
    }

    /** Get the activeCamera used for rendering.
     *
     * @return The activeCamera used for rendering.
     */
    public Camera getActiveCamera() {

        return activeCamera;
    }

    public Camera getCamera(int index) {

        return cameras.get(index);
    }

    public int getNumberOfCameras() {

        return cameras.size();
    }

    /** Set the activeCamera used to render its content.
     *
     * @param camera The activeCamera to use for rendering.
     */
    public void addCamera(Camera camera) {

        if(!cameras.contains(camera)) {

            cameras.add(camera);
        }
    }

    public void removeCamera(Camera camera) {

        cameras.remove(camera);
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

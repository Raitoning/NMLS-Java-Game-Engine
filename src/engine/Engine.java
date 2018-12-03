package engine;

import engine.input.Input;
import engine.physics.Physics;
import engine.rendering.SoftwareRenderer;
import java.awt.event.KeyEvent;

/**
 * <h1>Engine</h1>
 * Main class of the game engine.
 * <p>
 * This class creates or instanciates every core components of the game engine (Renderer, Physics, Input, ...)
 * and is responsible for the "Main thread", where the game loop happen and where everything is updated once per frame.
 * </p>
 *
 * @author  Raitoning
 * @version 2018.12.03
 * @since   2018.11.14
 */
public class Engine {

    private static Engine instance;

    private int targetFrameRate = 60;
    private long startupTime;
    private long frameStartTime;
    private long deltaTime;

    private boolean ecoRenderingMode = false;
    private boolean isRunning = true;

    private SoftwareRenderer softwareRenderer;
    private Game game;
    private Physics physics;
    private Input input;

    private Engine() {

        instance = this;

        startupTime = System.nanoTime();

        Time.startupTime = nanoTimeToFloat();

        input = Input.getInstance();
        physics = Physics.getInstance();

        softwareRenderer = new SoftwareRenderer(1280,720);

        game = new Game();

        update();
    }

    private void update() {

        while(isRunning) {

            frameStartTime = System.nanoTime();

            Time.timeSinceStartup = nanoTimeToFloat();
            Time.frameStartTime = nanoTimeToFloat();

            input.update();

            if(Input.getKey(KeyEvent.VK_ESCAPE)) {

                isRunning = false;
            }

            game.update();
            physics.update();

            if(ecoRenderingMode) {

                if(Input.hasInput()) {

                    softwareRenderer.update();
                }
            } else {

                softwareRenderer.update();
            }

            deltaTime = System.nanoTime() - frameStartTime;

            Time.deltaTime = nanoTimeToFloat() - Time.frameStartTime;

            if(deltaTime/1000000 < (1000/targetFrameRate)) {

                try {

                    Thread.sleep((1000/targetFrameRate) - (deltaTime/1000000));
                } catch (InterruptedException e) {

                    e.printStackTrace();
                }
            }

            Time.deltaTime = nanoTimeToFloat() - Time.frameStartTime;

//             System.out.println("FPS: " + ((float)1/Time.deltaTime) + " Frametime: " + Time.deltaTime * 1000 + "ms");
        }

        System.exit(0);
    }

    private float nanoTimeToFloat() {

        return ((float)System.nanoTime()/1000000000);
    }

    private long floatToNanoTime(float value) {

        return (long)(value * 1000000000);
    }

    /** Get the Engine instance.
     *
     * @return The running Engine instance, or instanciates a new one.
     */
    public static Engine getInstance() {

        if(instance == null) {

            instance = new Engine();
        }

        return instance;
    }

    /** Get the Renderer instance.
     *
     * @return The running Renderer instance.
     */
    public SoftwareRenderer getRenderer() {

        return softwareRenderer;
    }

    /** Get the Input instance.
     *
     * @return The running Input instance, or instanciates a new one.
     */
    public Physics getPhysics() {

        return physics;
    }

    /** Get the Game instance.
     *
     * @return The running Game.
     */
    public Game getGame() {

        return game;
    }
}

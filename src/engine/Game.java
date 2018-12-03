package engine;

import engine.gameobject.GameObject;

import java.util.ArrayList;

/**
 * <h1>Game</h1>
 * Temporary class supposed to represent a single level (Scene)
 * <p>
 * This class is mostly used for debugging purposes.
 * </p>
 *
 * @author  Raitoning
 * @version 2018.12.03
 * @since   2018.11.14
 */
public class Game {

    private ArrayList<GameObject> gameObjects;

    /** Constructs a new level. Only once should be used at run-time.
     *
     */
    public Game() {

        gameObjects = new ArrayList<>();
    }

    /** This function is called once every frame and updates every GameObjects in the level.
     *
     */
    public void update() {

        for (GameObject gameObject : gameObjects) {

            gameObject.update();
        }
    }

    public GameObject findGameObjectByName(String name) {

        for (GameObject gameObject : gameObjects) {

            if (gameObject.getName().equals(name)) {

                return gameObject;
            }
        }

        return null;
    }
}

package engine.scene;

import engine.gameobject.GameObject;

import java.util.ArrayList;

public class Scene {

    protected String name;
    private static int numberOfScenes = 0;
    private int id;
    protected ArrayList<GameObject> gameObjects;
    protected boolean isLoaded = false;

    public Scene() {

        gameObjects = new ArrayList<>();
        id = numberOfScenes;
        numberOfScenes++;
    }

    public Scene(String name) {

        this();
        this.name = name;
    }

    protected void addGameObject(GameObject gameObject) {

        gameObjects.add(gameObject);
    }

    protected void removeGameObject(GameObject gameObject) {

        if(gameObjects.contains(gameObject)) {

            gameObjects.remove(gameObject);
        }
    }

    public String getName() {

        return name;
    }

    public void update() {

        if(isLoaded) {

            for (int i = 0; i < gameObjects.size(); i++) {

                gameObjects.get(i).update();
            }
        }
    }

    public int getId() {

        return id;
    }

    public int numberOfGameObjects() {

        return gameObjects.size();
    }

    public GameObject getGameObjectByName(String name) {

        for (int i = 0; i < gameObjects.size(); i++) {

            if(gameObjects.get(i).getName().equals(name)) {

                return gameObjects.get(i);
            }
        }

        return null;
    }

    public void unload() {

        isLoaded = false;

        for (int i = 0; i < gameObjects.size(); i++) {

            gameObjects.get(i).destroy();
        }

        gameObjects.clear();
        gameObjects = null;
    }

    public void load() {}
}

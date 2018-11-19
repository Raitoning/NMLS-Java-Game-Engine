package engine.scene;

import java.util.ArrayList;

public class SceneManager {

    private static SceneManager instance;

    private ArrayList<Scene> scenes;
    private Scene activeScene;

    private SceneManager() {

        scenes = new ArrayList<>();
        activeScene = null;
    }

    public void update() {

        if(activeScene != null) {

            activeScene.update();
        }
    }

    public void loadScene(String name) {

        if (activeScene != null) {

            activeScene.unload();
        }

        for (int i = 0; i < scenes.size(); i++) {

            if(scenes.get(i).getName().equals(name)) {

                scenes.get(i).load();
                activeScene = scenes.get(i);
            }
        }
    }

    public void unloadActiveScene() {

        activeScene.unload();
        System.gc();
    }

    public void addScene(Scene scene) {

        scenes.add(scene);

        if(activeScene == null) {

            activeScene = scene;
            loadScene(scene.getName());
        }
    }

    public Scene getActiveScene() {

        return activeScene;
    }

    public static SceneManager getInstance() {

        if(instance == null) {

            instance = new SceneManager();
        }

        return instance;
    }
}

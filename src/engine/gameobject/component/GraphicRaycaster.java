package engine.gameobject.component;

import engine.gameobject.GameObject;
import engine.input.Input;

// TODO: Update this to support multi camera rendering.
public class GraphicRaycaster implements Component {

    private GameObject gameObject;

    public GraphicRaycaster(GameObject gameObject) {

        this.gameObject = gameObject;
        Input.getInstance().getMouseInput().addListener(this);
    }

    public GameObject getGameObject() {

        return gameObject;
    }

    public void raycasted() {

        System.out.println("Raycasted !");
    }

    @Override
    public void destroy() {

        Input.getInstance().getMouseInput().removeListener(this);
        gameObject = null;
    }
}

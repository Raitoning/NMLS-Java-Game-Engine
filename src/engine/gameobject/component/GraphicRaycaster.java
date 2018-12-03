package engine.gameobject.component;

import engine.gameobject.GameObject;
import engine.input.Input;

// TODO: Update this to support multi camera rendering.

/**
 * @author  Raitoning
 * @version 2018.12.03
 * @since   2018.11.14
 */
public class GraphicRaycaster implements Component {

    private GameObject gameObject;

    public GraphicRaycaster(GameObject gameObject) {

        this.gameObject = gameObject;
        Input.getMouseInput().addListener(this);
    }

    public GameObject getGameObject() {

        return gameObject;
    }

    public void raycasted() {

        gameObject.onRaycast();
    }

    @Override
    public void destroy() {

        Input.getMouseInput().removeListener(this);
        gameObject = null;
    }
}

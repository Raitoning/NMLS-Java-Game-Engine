package engine.gameobject.component;

import engine.Engine;
import engine.gameobject.GameObject;
import engine.physics.Physics;

public abstract class Collider implements Component {

    private GameObject gameObject;
    private boolean isStatic;
    private boolean isTrigger;

    Collider(String layerName, GameObject gameObject) {

        this.gameObject = gameObject;
        Engine.getInstance().getPhysics().addCollider(this, layerName);
    }

    public void onTriggerEnter2D(Collider other) {
        gameObject.onTriggerEnter2D(other);
    }

    public void onTriggerStay2D(Collider other) {

        gameObject.onTriggerStay2D(other);
    }
    public void onTriggerExit2D(Collider other) {
        gameObject.onTriggerExit2D(other);
    }

    public GameObject getGameObject() {

        return gameObject;
    }

    public boolean isStatic() {

        return isStatic;
    }

    public void setIsStatic(boolean value) {

        isStatic = value;
    }

    public boolean isTrigger() {

        return isTrigger;
    }

    public void setTrigger(boolean value) {

        isTrigger = value;
    }

    @Override
    public void destroy() {

        Physics.getInstance().removeCollider(this);
        gameObject = null;
    }
}

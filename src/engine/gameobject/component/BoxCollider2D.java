package engine.gameobject.component;

import engine.Vector2;
import engine.gameobject.GameObject;

public class BoxCollider2D extends Collider {

    private Vector2 dimensions;

    public BoxCollider2D(String layerName, GameObject gameObject) {

        super(layerName, gameObject);
        dimensions = Vector2.one();
    }

    public float getSizeX() {

        return dimensions.getX();
    }

    public void setSizeX(float value) {

        dimensions.setX(value);
    }

    public float getSizeY() {

        return dimensions.getY();
    }

    public void setSizeY(float value) {

        dimensions.setY(value);
    }

    public Vector2 getDimensions() {

        return dimensions;
    }

    public void setDimensions(Vector2 value) {

        dimensions = value;
    }

    @Override
    public void destroy() {
        super.destroy();

        dimensions = null;
    }
}

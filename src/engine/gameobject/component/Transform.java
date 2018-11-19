package engine.gameobject.component;

import engine.Vector3;

public class Transform implements Component {

    private Vector3 position;
    private Vector3 scale;

    public Transform() {

        position = Vector3.zero();
        scale = Vector3.one();
    }

    public Transform(float x, float y, float z) {

        position = new Vector3(x, y, z);
        scale = Vector3.one();
    }

    public Transform(Vector3 position) {

        this.position = position;
        scale = Vector3.one();
    }

    public Transform(Vector3 position, Vector3 scale) {

        this.position = position;
        this.scale = scale;
    }

    public Vector3 position() {

        return position;
    }

    public void setPosition(Vector3 position) {

        this.position = position;
    }

    public void setPosition(float x, float y) {

        position.setX(x);
        position.setY(y);
    }

    public void setPosition(float x, float y, float z) {

        position.setX(x);
        position.setY(y);
        position.setZ(z);
    }

    public Vector3 scale() {

        return scale;
    }

    public void setScale(Vector3 scale) {

        this.scale = scale;
    }

    public void setScale(float x, float y) {

        scale.setX(x);
        scale.setY(y);
    }

    public void setScale(float x, float y, float z) {

        scale.setX(x);
        scale.setY(y);
        scale.setZ(z);
    }

    @Override
    public void destroy() {

        position = null;
        scale = null;
    }
}

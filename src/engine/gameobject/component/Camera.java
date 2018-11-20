package engine.gameobject.component;

import engine.Engine;
import engine.Vector2;
import engine.Vector3;
import engine.gameobject.GameObject;

import java.util.ArrayList;

public class Camera implements Component {

    private float orthographicSize;
    private int renderPriority;
    private float nearClippingPlane;
    private float farClippingPlane;
    private ArrayList<SpriteRenderer> spriteList;
    private GameObject gameObject;
    private Vector2 minRenderArea;
    private Vector2 maxRenderArea;

    public Camera(float orthographicSize, float nearClippingPlane, float farClippingPlane, GameObject gameObject) {

        this.orthographicSize = orthographicSize;
        this.nearClippingPlane = nearClippingPlane;
        this.farClippingPlane = farClippingPlane;
        renderPriority = -1;
        this.gameObject = gameObject;

        minRenderArea = Vector2.zero();
        maxRenderArea = Vector2.one();

        Engine.getInstance().getRenderer().setCamera(this);

        spriteList = new ArrayList<>();
    }

    public void setRenderPriority(int value) {

        renderPriority = value;
    }

    public int getRenderPriority() {

        return renderPriority;
    }

    public void setMinRenderArea(Vector2 minRenderArea) {

        this.minRenderArea = minRenderArea;
    }

    public Vector2 getMinRenderArea() {

        return minRenderArea;
    }

    public void setMaxRenderArea(Vector2 maxRenderArea) {

        this.maxRenderArea = maxRenderArea;
    }

    public Vector2 getMaxRenderArea() {

        return maxRenderArea;
    }

    public void addSprite(SpriteRenderer value) {

        spriteList.add(value);
    }

    public void removeSprite(SpriteRenderer value) {

        if(spriteList.contains(value)) {

            spriteList.remove(value);
        }
    }

    public Vector2 worldToCamera(Vector3 position) {

        return new Vector2( (((position.getX() - (gameObject.getTransform().position().getX()  - (orthographicSize * Engine.getInstance().getRenderer().getAspectRatio()) / 2)) / (orthographicSize * Engine.getInstance().getRenderer().getAspectRatio()))),
                1f - (position.getY()  - (gameObject.getTransform().position().getY()  - orthographicSize / 2)) / orthographicSize);
    }

    public Vector2 cameraToWorld(Vector2 cameraCoordinates) {

        return new Vector2 ((orthographicSize * cameraCoordinates.getX()) - (orthographicSize /2f) + gameObject.getTransform().position().getX(),
                (orthographicSize * cameraCoordinates.getY()) - (orthographicSize /2f) + gameObject.getTransform().position().getY());
    }

    public float getOrthographicSize() {

        return orthographicSize;
    }

    public GameObject getGameObject() {

        return gameObject;
    }

    public float getNearClippingPlane() {

        return nearClippingPlane;
    }

    public float getFarClippingPlane() {

        return farClippingPlane;
    }

    @Override
    public void destroy() {

        spriteList.clear();
        spriteList = null;
        gameObject = null;
        Engine.getInstance().getRenderer().setCamera(null);
    }
}

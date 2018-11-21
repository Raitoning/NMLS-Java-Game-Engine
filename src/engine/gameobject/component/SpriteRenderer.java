package engine.gameobject.component;

import engine.Engine;
import engine.SpriteFactory;
import engine.gameobject.GameObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SpriteRenderer implements Component {

    private GameObject gameObject;
    private String name;

    public SpriteRenderer(String name, GameObject gameObject) {

        this.gameObject = gameObject;
        this.name = name;

        Engine.getInstance().getRenderer().addSpriteToQueue(this);
    }

    public GameObject getGameObject() {

        return gameObject;
    }

    public String getName() {

        return name;
    }

    @Override
    public void destroy() {

        Engine.getInstance().getRenderer().removeSpriteFromQueue(this);
        name = null;
        gameObject = null;
    }
}

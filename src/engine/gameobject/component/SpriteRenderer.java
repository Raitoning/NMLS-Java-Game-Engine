package engine.gameobject.component;

import engine.Engine;
import engine.SpriteFactory;
import engine.gameobject.GameObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SpriteRenderer implements Component {

    private BufferedImage sprite;
    private GameObject gameObject;
    private String name;

    public SpriteRenderer(String name, GameObject gameObject) {

        this.gameObject = gameObject;
        this.name = name;

        sprite = SpriteFactory.instance.getSprite(name);

        Engine.getInstance().getRenderer().addSpriteToQueue(this);
    }

    public GameObject getGameObject() {

        return gameObject;
    }

    public BufferedImage getScaledSprite() {

        return sprite;
    }

    // WORKAROUND: Added 1 pixel in each dimension to get rid of random black bars appearing between sprites.
    public void rescale() {

        float scaleFactor = Engine.getInstance().getRenderer().getVerticalSpriteSizeTarget() / (float)sprite.getWidth();

        sprite = resize(SpriteFactory.getInstance().getSprite(name), (int)(sprite.getWidth() * scaleFactor * gameObject.getTransform().scale().getX()) + 1, (int)(sprite.getHeight() * scaleFactor * gameObject.getTransform().scale().getY()) + 1);
    }

    public BufferedImage resize(BufferedImage img, int newW, int newH) {

        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }


    @Override
    public void destroy() {

        Engine.getInstance().getRenderer().removeSpriteFromQueue(this);
        sprite = null;
        name = null;
        gameObject = null;
    }
}

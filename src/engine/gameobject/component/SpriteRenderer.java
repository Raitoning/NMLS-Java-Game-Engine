package engine.gameobject.component;

import engine.Engine;
import engine.gameobject.GameObject;

/**
 * <h1>SpriteRenderer</h1>
 * A component used to add a sprite to a GameObject and displays it.
 *
 * @author  Raitoning
 * @version 2018.12.03
 * @since   2018.11.14
 */
public class SpriteRenderer implements Component {

    private GameObject gameObject;
    private String name;

    /** Creates a new SpriteRenderer given the name of the sprite to use.
     *
     * @param name Name of the sprite to display.
     * @param gameObject The GameObject this Component is attached to.
     */
    public SpriteRenderer(String name, GameObject gameObject) {

        this.gameObject = gameObject;
        this.name = name;

        Engine.getInstance().getRenderer().addSpriteToQueue(this);
    }

    /** Return the GameObject this Component is attached to.
     *
     * @return the GameObject this Component is attached to.
     */
    public GameObject getGameObject() {

        return gameObject;
    }

    /** Set the name of the sprite to use.
     *
     * @param value The name of the sprite to use.
     */
    public void setName(String value) {

        name = value;
    }

    /** Get the name of the used sprite.
     *
     * @return The name of the used sprite.
     */
    public String getName() {

        return name;
    }

    /** Destroy this component.
     *
     * @deprecated STILL EXPERIMENTAL, DO NOT USE IT.
     */
    @Override
    public void destroy() {

        Engine.getInstance().getRenderer().removeSpriteFromQueue(this);
        name = null;
        gameObject = null;
    }
}

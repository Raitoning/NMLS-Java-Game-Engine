package engine.gameobject;

import engine.Engine;
import engine.Vector3;
import engine.gameobject.component.Collider;
import engine.gameobject.component.Component;
import engine.gameobject.component.Transform;

import java.util.ArrayList;

/**
 * <h1>GameObject</h1>
 * Base class for all entities in the game engine.
 *
 * <b>Note:</b> <a href="https://docs.unity3d.com/ScriptReference/GameObject.html">https://docs.unity3d.com/ScriptReference/GameObject.html</a>
 *
 * @author  Raitoning
 * @version 2018.11.26
 * @since   2018.11.14
 */
public class GameObject {

    protected String name;
    protected String tag;
    protected Transform transform;
    protected ArrayList<Component> components;

    /** Constructs a new GameObject, with default name.
     * Transform is always added to the GameObject that is being created. The version with just a single string argument just adds this and the Transform. Finally, the third version allows the name to be specified but also coordinates to be passed.
     */
    public GameObject() {

        name = "GameObject";
        transform = new Transform();
        components = new ArrayList<>();
        components.add(transform);
    }

    /** Constructs a new GameObject with the desired name.
     * The version with just a single string argument just adds this and the Transform.
     *
     * @param name The deried name for the GameObject.
     */
    public GameObject(String name) {

        this();
        this.name = name;
    }

    /** Constructs a new GameObject with the desired position.
     * Finally, the third version allows the name to be specified but also coordinates to be passed.
     *
     * @param x The X component of the position.
     * @param y The Y component of the position.
     * @param z The Z component of the position.
     */
    public GameObject(float x, float y, float z) {

        this();
        transform.setPosition(new Vector3(x, y, z));
    }

    /** The update() function is called once every frame. This is where you put all your logic, for instance movements or inputs.
     *
     */
    public void update() {}

    public void onTriggerEnter2D(Collider other) {}

    /** Sent each frame where another object is within a trigger collider attached to this object (2D physics only).
     * Further information about the other collider is reported in the Collider2D parameter passed during the call.
     *
     * @param other The other Collider2D involved in this collision.
     */
    public void onTriggerStay2D(Collider other) {}

    public void onTriggerExit2D(Collider other) {}


    /** Sent each frame where a click happened on this GameObject.
     * Determines if the cursor is over a Graphics element in the Scene.
     *
     */
    public void onRaycast() {}

    /** Get the Transform attached to this GameObject.
     *
     * @return  The Transform attached to this GameObject.
     */
    public Transform getTransform() {

        return transform;
    }

    /** Get the name of this GameObject.
     *
     * @return  The name of this GameObject.
     */
    public String getName() {

        return name;
    }

    /** Set the name of this GameObject.
     *
     * @param value The new name for this GameObject.
     */
    public void setName(String value) {

        name = value;
    }

    /** Get the Tag of this GameObject.
     *
     * @return  The Tag of this GameObject.
     */
    public String getTag() {

        return tag;
    }

    /** Set the Tag of this GameObject.
     *
     * @param value The new Tag for this GameObject.
     */
    public void setTag(String value) {

        tag = value;
    }

    /** Adds a component class named className to the game object.
     *
     * @param value The component to add to this GameObject.
     */
    public void addComponent(Component value) {

        components.add(value);
    }

    /** Returns the component of Type type if the game object has one attached, null if it doesn't.
     *
     * @param typeOfComponent The type of component asked.
     * @return The first component with coresponding type, null if no component has the requested type.
     */
    public Component getComponent(Class typeOfComponent) {

        for (int i = 0; i < components.size(); i++) {

            if (components.get(i).getClass() == typeOfComponent) {

                return components.get(i);
            }
        }

        return null;
    }

    /** Finds a GameObject by name and returns it.
     *
     * @param name The name of the GameObject.
     * @return The GameObject if found, null otherwise.
     * @deprecated STILL EXPERIMENTAL, DO NOT USE IT.
     */
    public static GameObject findByName(String name) {

        return Engine.getInstance().getGame().findGameObjectByName(name);
    }

    /** Removes a GameObject.
     *
     */
    public void destroy() {

        for (int i = 0; i < components.size(); i++) {

            components.get(i).destroy();
        }
    }
}

package engine.physics;

import engine.gameobject.component.Collider;
import java.util.ArrayList;

/**
 * <h1>PhysicsLayer</h1>
 * A layer to speratate GameObjects for physics simulation.
 * <p>
 *
 * @author  Raitoning
 * @version 2018.11.14
 * @since   2018.11.14
 */
public class PhysicsLayer {

    private String name;
    private ArrayList<Collider> colliders;

    /** Constructs a new PhysicsLayer with the desired name.
     *
     * @param name The desired name for the new PhysicsLayer.
     */
    public PhysicsLayer(String name) {

        this.name = name;
        colliders = new ArrayList<>();
    }

    /** Get the name of the PhysicsLayer.
     *
     * @return The name of the PhysicsLayer.
     */
    public String getName() {

        return name;
    }

    /** Get the number of Colliders in this PhysicsLayer.
     *
     * @return The number of Colliders in this PhysicsLayer.
     */
    public int size() {

        return colliders.size();
    }

    /** Get a Collider by it's index.
     *
     * @param index The index of the Collider.
     * @return The Collider.
     */
    public Collider getCollider(int index) {

    return colliders.get(index);
    }

    /** Add a new Collider to the layer.
     *
     * @param value The Collider to add.
     */
    public void addCollider(Collider value) {

        colliders.add(value);
    }

    /** Check if the layer contains a specified Collider.
     *
     * @param value The Collider to check.
     * @return True if the layers contains the Collider, else if it doesn't.
     */
    public boolean contains(Collider value) {

        return colliders.contains(value);
    }

    /** Remove a Collider from the layer.
     *
     * @param value The Collider to remove.
     */
    public void removeCollider(Collider value) {

        if (colliders.contains(value)) {

            colliders.remove(value);
        }
    }
}

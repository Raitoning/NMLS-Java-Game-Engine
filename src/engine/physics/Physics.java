package engine.physics;

import engine.exception.UnknownPhysicsLayerException;
import engine.gameobject.component.BoxCollider2D;
import engine.gameobject.component.Collider;
import engine.gameobject.component.Transform;
import java.util.ArrayList;

/**
 * <h1>Physics</h1>
 * Global physics properties and helper methods.
 * <p>
 * </p>
 * <b>Note:</b> <a href="https://docs.unity3d.com/ScriptReference/Physics.html">https://docs.unity3d.com/ScriptReference/Physics.html</a>
 *
 * @author  Raitoning
 * @version 2018.12.03
 * @since   2018.11.14
 */
public class Physics {

    private static Physics instance;

    private ArrayList<PhysicsLayer> layers;

    private ArrayList<Collider> triggeredColliders;

    private Physics() {

        layers = new ArrayList<>();
        triggeredColliders = new ArrayList<>();
    }

    // TODO: Janky AF Physics, find some better ways.
    // TODO: Fix the trigger detection.
    /** The update() function is responsible for handling all physics calculations and is called once per frame.
     * It checks if 2 Colliders intersects and sends messages to their GameObjects in case of trigger.
     *
     */
    public void update() {

        for (int i = 0; i < layers.size() - 1; i++) {

            for (int j = i + 1; j < layers.size(); j++) {

                for (int k = 0; k < layers.get(i).size(); k++) {

                    Collider a = layers.get(i).getCollider(k);

                    for (int l = 0; l < layers.get(j).size(); l++) {

                        Collider b = layers.get(j).getCollider(l);

                        boxCollision((BoxCollider2D)a, (BoxCollider2D) b);
                    }
                }
            }
        }
    }


    private boolean doBoxesIntersect(BoxCollider2D a, BoxCollider2D b) {

        Transform aTransform = a.getGameObject().getTransform();
        Transform bTransform = b.getGameObject().getTransform();

        return (Math.abs(aTransform.position().getX() - bTransform.position().getX()) * 2 < (a.getSizeX() * aTransform.scale().getX() + b.getSizeX() * bTransform.scale().getX())) &&
                (Math.abs(aTransform.position().getY() - bTransform.position().getY()) * 2 < (a.getSizeY() * aTransform.scale().getY() + b.getSizeY() * bTransform.scale().getY()));
    }

    private void boxCollision(BoxCollider2D a, BoxCollider2D b) {

        if(doBoxesIntersect(a, b)) {

            if (!a.isTrigger() && !b.isTrigger()) {

                Transform aTransform = a.getGameObject().getTransform();
                Transform bTransform = b.getGameObject().getTransform();

                float horizontalDistance = Math.abs(aTransform.position().getX() - bTransform.position().getX());
                float verticalDistance = Math.abs(aTransform.position().getY() - bTransform.position().getY());

                float horizontalColliderSize = (b.getSizeX() * bTransform.scale().getX() + a.getSizeX() * aTransform.scale().getX()) / 2f;
                float verticalColliderSize = (b.getSizeY() * bTransform.scale().getY() + a.getSizeY() * aTransform.scale().getY()) / 2f;

                // If Collider A isn't static but Collider B is
                // Set A's GameObject position right next to B.
                if (!a.isStatic() && b.isStatic()) {

                    if (horizontalDistance > verticalDistance) {

                        if (aTransform.position().getX() < bTransform.position().getX()) {

                            aTransform.position().setX(bTransform.position().getX() - horizontalColliderSize);
                        } else {

                            aTransform.position().setX(bTransform.position().getX() + horizontalColliderSize);
                        }
                    } else {

                        if (aTransform.position().getY() < bTransform.position().getY()) {

                            aTransform.position().setY(bTransform.position().getY() - verticalColliderSize);
                        } else {

                            aTransform.position().setY(bTransform.position().getY() + verticalColliderSize);
                        }
                    }
                } else if (a.isStatic() && !b.isStatic()) {

                    // If A is static and B isn't
                    // Set B's GameObject position next to A's.
                    if (horizontalDistance > verticalDistance) {

                        if (bTransform.position().getX() < aTransform.position().getX()) {

                            bTransform.position().setX(aTransform.position().getX() - horizontalColliderSize);
                        } else {

                            bTransform.position().setX(aTransform.position().getX() + horizontalColliderSize);
                        }
                    } else {

                        if (bTransform.position().getY() < aTransform.position().getY()) {

                            bTransform.position().setY(aTransform.position().getY() - verticalColliderSize);
                        } else {

                            bTransform.position().setY(aTransform.position().getY() + verticalColliderSize);
                        }
                    }
                }
            } else {

                if(a.isTrigger()) {

//                    if(!triggeredColliders.contains(a)) {
//
//                        triggeredColliders.add(a);
//                        a.onTriggerEnter2D(b);
//                    } else {
//
//                        a.onTriggerStay2D(b);
//                    }

                    a.onTriggerStay2D(b);

                }

                if(b.isTrigger()) {

//                    if(!triggeredColliders.contains(b)) {
//
//                        triggeredColliders.add(b);
//                        b.onTriggerEnter2D(a);
//                    } else {

                        b.onTriggerStay2D(a);
//                    }
                }
            }
        }

//            if(a.isTrigger()) {
//
//                if(triggeredColliders.contains(a)) {
//
//                    triggeredColliders.remove(a);
//                    a.onTriggerExit2D(b);
//                }
//            }
//
//            if(b.isTrigger()) {
//
//                if(triggeredColliders.contains(b)) {
//
//                    triggeredColliders.remove(b);
//                    b.onTriggerExit2D(a);
//                }
//            }
    }

    /** Add a Collider to the specified PhysicsLayer. May throw an UnknownPhysicsLayerException if the PhysicsLayer doesn't exists.
     *
     * @param collider The collider to add.
     * @param layerName The desired PhysicsLayer.
     */
    public void addCollider(Collider collider, String layerName) {

        // If the layer exists, add the collider in it.
        for (PhysicsLayer layer : layers) {

            if (layer.getName().equals(layerName)) {

                layer.addCollider(collider);

                return;
            }
        }

        // Else throw an exception.
        try {
            throw new UnknownPhysicsLayerException(layerName);
        } catch (UnknownPhysicsLayerException e) {
            e.printStackTrace();
        }
    }

    /** Add a new PhysicsLayer.
     *
     * @param name The name of the new PhysicsLayer.
     */
    public void addLayer(String name) {

        layers.add(new PhysicsLayer(name));
    }

    /** Remove a collider from the physics simulation.
     *
     * @param value The Collider to remove.
     */
    public void removeCollider(Collider value) {

        for (PhysicsLayer layer : layers) {

            if (layer.contains(value)) {

                layer.removeCollider(value);
            }
        }
    }

    /** Get the instance of the running Physics, or instanciates a new one and returns it.
     *
     * @return The running Physics instance.
     */
    public static Physics getInstance() {

        if(instance == null) {

            instance = new Physics();
        }

        return instance;
    }
}

package engine.exception;

public class UnknownPhysicsLayerException extends Exception {

    public UnknownPhysicsLayerException(String name) {

        super("The physics layer \"" + name + "\" doesn't exists. Create a PhysicsLayer with this name before using it.");
    }
}

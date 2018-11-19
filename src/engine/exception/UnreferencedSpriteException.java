package engine.exception;

public class UnreferencedSpriteException extends Exception {

    public UnreferencedSpriteException(String name) {

        super("The sprite named \"" + name + "\" isn't referenced. Create a SpriteReference for this sprite first before using it.");
    }
}

package engine.exception;

public class GameObjectNotFoundException extends Exception {

    public GameObjectNotFoundException(String name) {

        super("The GameObject named \"" + name + "\" doesn't exists.");
    }
}

package engine.exception;

public class UnreferencedSoundException extends Exception {

    public UnreferencedSoundException(String name) {

        super("The sound named \"" + name + "\" isn't referenced. Create a SoundReference for this sound first before using it.");
    }
}

package engine.audio;

/**
 * <h1>Audio</h1>
 * Audio class of the game engine.
 * <p>
 * This class is responsible for handling all the audio components of the game engine.
 * </p>
 *
 * @author  Raitoning
 * @version 2021.02.11
 * @since   2021.02.11
 */
public class Audio {

    public static Audio instance;

    private Audio() {

    }

    /** This function is called once every frame and gather all inputs from the keyboard and the mouse and stock them.
     *
     */
    public void update() {

    }

    /** Get the running Input or instanciates a new one and return it.
     *
     * @return The running instance of Input or a new one.
     */
    public static Audio getInstance() {

        if(instance == null) {

            instance = new Audio();
        }

        return instance;
    }
}

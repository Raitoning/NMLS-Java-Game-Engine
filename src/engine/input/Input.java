package engine.input;

/**
 * <h1>Vector2</h1>
 * Representation of 2D vectors and points.
 * <p>
 * This structure is used in some places to represent 2D positions and vectors (e.g. texture coordinates). In the majority of other cases a Vector3 is used.
 * </p>
 * <b>Note:</b> <a href="https://docs.unity3d.com/ScriptReference/Vector2.html">https://docs.unity3d.com/ScriptReference/Vector2.html</a>
 *
 * @author  Raitoning
 * @version 2018-11-14
 * @since   2018-11-14
 */
public class Input {

    private static MouseInput mouseInput;
    private static KeyboardInput keyboardInput;

    public static Input instance;

    private Input() {

        mouseInput = new MouseInput();
        keyboardInput = new KeyboardInput();
    }

    /** This function is called once every frame and gather all inputs from the keyboard and the mouse and stock them.
     *
     */
    public void update() {

        keyboardInput.update();
    }

    /** Get the running Input or instanciates a new one and return it.
     *
     * @return The running instance of Input or a new one.
     */
    public static Input getInstance() {

        if(instance == null) {

            instance = new Input();
        }

        return instance;
    }

    /** Get the running KeyboardInput or instanciates a new one and return it.
     *
     * @return The running instance of KeyboardInput or a new one.
     */
    public static KeyboardInput getKeyboardInput() {

        return keyboardInput;
    }

    /** Get the running MouseInput or instanciates a new one and return it.
     *
     * @return The running instance of MouseInput or a new one.
     */
    public static MouseInput getMouseInput() {

        return mouseInput;
    }

    /** Get the value of an axis based on it's name. May return an UnknownAxis if no axis with the desired name exists.
     *
     * @param axisName The name of the desired Axis.
     * @return The value of the Axis.
     */
    public static float getAxis(String axisName) {

        return keyboardInput.getAxis(axisName);
    }

    /** Get if the desired key is pushed or not.
     *
     * @param key The KeyCode of the key.
     * @return Returns if the key is pushed or not.
     */
    public static boolean getKey(int key) {

        return keyboardInput.getKey(key);
    }

    /** Returns if the desired mouse button is clicked or not.
     *
     * @param button The identifiant of the mouse button.
     * @return Returns if the desired mouse button is clicked or not.
     */
    public static boolean getMouseButton(int button) {

        return mouseInput.getMouseButton(button);
    }

    /** Returns if any key or mouse buttons are clicked/pushed.
     *
     * @return Returns if any key or mouse buttons are clicked/pushed.
     */
    public static boolean hasInput() {

        return keyboardInput.hasInput() || mouseInput.hasInput();
    }
}

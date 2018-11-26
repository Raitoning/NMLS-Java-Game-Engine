package engine;

/**
 * <h1>Vector2</h1>
 * Representation of 2D vectors and points.
 * <p>
 * This structure is used in some places to represent 2D positions and vectors (e.g. texture coordinates). In the majority of other cases a Vector3 is used.
 * </p>
 * <b>Note:</b> <a href="https://docs.unity3d.com/ScriptReference/Vector2.html">https://docs.unity3d.com/ScriptReference/Vector2.html</a>
 *
 * @author  Raitoning
 * @version 2018.11.22
 * @since   2018.11.14
 */
public class Vector2 {

    private float x;
    private float y;

    /** Constructs a new vector with given x, y components.
     *
     * @param x X component of the vector.
     * @param y Y component of the vector.
     */
    public Vector2(float x, float y) {

        this.x = x;
        this.y = y;
    }

    /** Get the X component of an existing Vector2.
     *
     * @return X component of the vector.
     */
    public float getX() {

        return x;
    }

    /** Get the Y component of an existing Vector2.
     *
     * @return Y component of the vector.
     */
    public float getY() {

        return y;
    }

    /** Set the X component of an existing Vector2.
     *
     * @param x X component of the vector.
     */
    public void setX(float x) {

        this.x = x;
    }


    /** Set the Y component of an existing Vector2.
     *
     * @param y Y component of the vector.
     */
    public void setY(float y) {

        this.y = y;
    }

    /** Shorthand for writing Vector2(0, 0).
     *
     * @return A Vector2 with both X and Y components set to 0.
     */
    public static Vector2 zero() {

        return new Vector2(0f ,0f);
    }

    /** Shorthand for writing Vector2(1, 1).
     *
     * @return A Vector2 with both X and Y components set to 1.
     */
    public static Vector2 one() {

        return new Vector2(1f, 1f);
    }

    /** Return a readable string of the Vector2 values.
     *
     * @return Return a readable string of the Vector2 values.
     */
    public String toString() {

        return "(X: " + x + "; Y: " + y + ")";
    }
}

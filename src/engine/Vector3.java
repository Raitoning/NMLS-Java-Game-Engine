package engine;

/**
 * <h1>Vector3</h1>
 * Representation of 3D vectors and points.
 * <p>
 * This structure is used throughout the game engine to pass 3D positions and directions around. It also contains functions for doing common vector operations.
 * </p>
 * <b>Note:</b> <a href="https://docs.unity3d.com/ScriptReference/Vector3.html">https://docs.unity3d.com/ScriptReference/Vector3.html</a>
 *
 * @author  Raitoning
 * @version 2018.11.22
 * @since   2018.11.14
 */
public class Vector3 {

    private float x;
    private float y;
    private float z;

    /** Creates a new vector with given x, y, z components.
     *
     * @param x X component of the vector.
     * @param y Y component of the vector.
     * @param z Z component of the vector.
     */
    public Vector3(float x, float y, float z) {

        this.x = x;
        this.y = y;
        this.z = z;
    }

    /** Get the X component of a vector
     *
     * @return  X component of the vector.
     */
    public float getX() {

        return x;
    }

    /** Get the Y component of a vector
     *
     * @return  Y component of the vector.
     */
    public float getY() {

        return y;
    }

    /** Get the Z component of a vector
     *
     * @return  Z component of the vector.
     */
    public float getZ() {

        return z;
    }

    /** Set the X component of an existing Vector3.
     *
     * @param value X component of the vector.
     */
    public void setX(float value) {

        x = value;
    }

    /** Set the Y component of an existing Vector3.
     *
     * @param value Y component of the vector.
     */
    public void setY(float value) {

        y = value;
    }

    /** Set the Z component of an existing Vector3.
     *
     * @param value Z component of the vector.
     */
    public void setZ(float value) {

        z = value;
    }

    /** Shorthand for writing Vector3(0, 0, 0).
     *
     * @return A vector with the X, Y and Z components set to 0.
     */
    public static Vector3 zero() {

        return new Vector3(0f, 0f, 0f);
    }

    /** Shorthand for writing Vector3(1, 1, 1).
     *
     * @return A vector with the X, Y and Z components set to 1.
     */
    public static Vector3 one() {

        return new Vector3(1f, 1f, 1f);
    }

    /** Return a readable string of the Vector3 values.
     *
     * @return Return a readable string of the Vector3 values.
     */
    @Override
    public String toString() {

        return "(X: " + x + ";Y: " + y + ";Z: " + z +")";
    }
}

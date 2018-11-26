package engine;

/**
 * <h1>Mathf</h1>
 * A collection of common math functions.
 * <b>Note:</b> <a href="https://docs.unity3d.com/ScriptReference/Mathf.html">https://docs.unity3d.com/ScriptReference/Mathf.html</a>
 *
 * @author  Raitoning
 * @version 2018.11.26
 * @since   2018.11.14
 */
public class Mathf {

    /** Clamps a value between a minimum float and maximum float value.
     *
     * @param in The value to clamp.
     * @param min The minimum value
     * @param max The maximum value
     * @return The clamped value.
     */
    public static float clamp(float in, float min, float max) {

        if(in < min) {

            in = min;
        } else if (in > max) {

            in = max;
        }

        return in;
    }

    /** Linearly interpolates between a and b by t.
     *
     * @param a The value to interpolate from.
     * @param b The value to interpolate to.
     * @param t The value by how much to interpolate.
     * @return The interpolated value.
     */
    public static float lerp(float a, float b, float t) {

        return (1 - t) * a + t * b;
    }
}

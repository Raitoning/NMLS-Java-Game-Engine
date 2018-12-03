package engine.gameobject.component;

/**
 * <h1>Component</h1>
 * Base interface for everything attached to GameObjects.
 *
 * Note that your code will never directly create a Component.
 * Instead, you write script code, and attach the script to a GameObject.
 *
 * <b>Note:</b> <a href="https://docs.unity3d.com/ScriptReference/Component.html">https://docs.unity3d.com/ScriptReference/Component.html</a>
 *
 * @author  Raitoning
 * @version 2018.11.14
 * @since   2018.11.14
 */
public interface Component {

    /** Destroy the component by nullifying every of it's components and references.
     *
     */
    void destroy();
}

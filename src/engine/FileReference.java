package engine;

/**
 * <h1>FileReference</h1>
 * A reference to a file.
 * <p>
 * Makes it easier to deal with files in the engine.
 * </p>
 *
 * @author  Raitoning
 * @version 2018.11.14
 * @since   2018.11.14
 */
public class FileReference {

    private String name;
    private String path;

    /** Construcs a reference to a file with a desired name and the path to the file.
     *
     * @param name  The desired name.
     * @param path  The path to the file.
     */
    public FileReference(String name, String path) {

        this.name = name;
        this.path = path;
    }

    /** Get the name of the reference.
     *
     * @return  The name of the reference.
     */
    public String getName() {

        return name;
    }

    /** Get the path of the referenced file.
     *
     * @return  The path of the referenced file.
     */
    public String getPath() {

        return path;
    }
}

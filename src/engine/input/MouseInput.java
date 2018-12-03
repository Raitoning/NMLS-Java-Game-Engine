package engine.input;

import engine.Engine;
import engine.Vector2;
import engine.gameobject.component.Camera;
import engine.gameobject.component.GraphicRaycaster;
import engine.gameobject.component.Transform;
import engine.rendering.SoftwareRenderer;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * @author  Raitoning
 * @version 2018.12.03
 * @since   2018.11.14
 */
public class MouseInput extends MouseAdapter {

    private ArrayList<Integer> inputs;
    private ArrayList<GraphicRaycaster> listeners;

    MouseInput() {

        inputs = new ArrayList<>();
        listeners = new ArrayList<>();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);

        SoftwareRenderer renderer = Engine.getInstance().getRenderer();

        int screenWidth = renderer.getWidth();
        int screenHeight = renderer.getHeight();

        for (int i = 0; i < renderer.getNumberOfCameras(); i++) {

            if (e.getX() >= (int) (screenWidth * renderer.getCamera(i).getMinRenderArea().getX())) {

                if (e.getX() <= (int) (screenWidth * renderer.getCamera(i).getMaxRenderArea().getX())) {

                    if (e.getY() >= (int) (screenHeight * renderer.getCamera(i).getMinRenderArea().getY())) {

                        if (e.getY() <= (int) (screenHeight * renderer.getCamera(i).getMaxRenderArea().getY())) {

                            System.out.println("Camera #" + i);

                            System.out.println("X: " + e.getX() + "(" + (screenWidth * renderer.getCamera(i).getMinRenderArea().getX()) + " - " + (screenWidth * renderer.getCamera(i).getMaxRenderArea().getX()) + ")");

                            System.out.println("X: " + e.getY() + "(" + (screenWidth * renderer.getCamera(i).getMinRenderArea().getY()) + " - " + (screenWidth * renderer.getCamera(i).getMaxRenderArea().getY()) + ")");

                            raycast(screenToCamera(e.getX(), e.getY(), i), i);
//                            return;
                        }
                    }
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);

        if(!inputs.contains(e.getButton())) {

            inputs.add(e.getButton());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);

        inputs.remove(Integer.valueOf(e.getButton()));
    }

    boolean getMouseButton(int button) {

        return inputs.contains(button);
    }

    boolean hasInput() {

        return inputs.size() != 0;
    }

    public void addListener(GraphicRaycaster value) {

        listeners.add(value);
    }

    public void removeListener(GraphicRaycaster value) {

        listeners.remove(value);
    }

    private void raycast(Vector2 coordinates, int indexOfCamera) {

        Vector2 worldCoordinates =
                Engine.getInstance().getRenderer().getCamera(indexOfCamera).cameraToWorld(coordinates);

        for (GraphicRaycaster listener : listeners) {

            Transform transform = listener.getGameObject().getTransform();

            if (worldCoordinates.getX() >= (transform.position().getX() - (transform.scale().getX() / 2f)) &&
                    worldCoordinates.getX() <= (transform.position().getX() + (transform.scale().getX() / 2f))) {

                if (worldCoordinates.getY() >= transform.position().getY() - (transform.scale().getY() / 2f) &&
                        worldCoordinates.getY() <= transform.position().getY() + (transform.scale().getY() / 2f)) {

                    listener.raycasted();
                }
            }
        }
    }

    // TODO: Find the correct horizontal projection formula
    private Vector2 screenToCamera(int x, int y, int indexOfCamera) {

        SoftwareRenderer renderer = Engine.getInstance().getRenderer();
        Camera camera = renderer.getCamera(indexOfCamera);

        int screenWidth = renderer.getWidth();
        int screenHeight = renderer.getHeight();

        float cameraX =
                (x - (screenWidth * camera.getMinRenderArea().getX())) / (screenWidth * (camera.getMaxRenderArea().getX() - camera.getMinRenderArea().getX()));

        float cameraY =
                1f - ((y - (screenHeight * camera.getMinRenderArea().getY())) / (screenHeight * (camera.getMaxRenderArea().getY() - camera.getMinRenderArea().getY())));

        System.out.println(new Vector2(cameraX, cameraY));

        return new Vector2(cameraX, cameraY);
    }
}

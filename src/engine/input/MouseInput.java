package engine.input;

import engine.Engine;
import engine.Vector2;
import engine.gameobject.GameObject;
import engine.gameobject.component.GraphicRaycaster;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

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

        raycast(screenToCamera(e.getX(), e.getY()));
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);

        if(!inputs.contains(Integer.valueOf(e.getButton()))) {

            inputs.add(e.getButton());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);

        if(inputs.contains(Integer.valueOf(e.getButton()))) {

            inputs.remove(Integer.valueOf(e.getButton()));
        }
    }

    boolean getMouseButton(int button) {

        if(inputs.contains(Integer.valueOf(button))) {

            return true;
        } else {

            return false;
        }
    }

    boolean hasInput() {

        if(inputs.size() == 0) {

            return false;
        } else {

            return true;
        }
    }

    public void addListener(GraphicRaycaster value) {

        listeners.add(value);
    }

    public void removeListener(GraphicRaycaster value) {

        if(listeners.contains(value)) {

            listeners.remove(value);
        }
    }

    private void raycast(Vector2 coordinates) {

        Vector2 worldCoordinates = Engine.getInstance().getRenderer().getActiveCamera().cameraToWorld(coordinates);

        for (int i = 0; i < listeners.size(); i++) {

            GameObject gameObject = listeners.get(i).getGameObject();

            if (worldCoordinates.getX() >= (gameObject.getTransform().position().getX() - (gameObject.getTransform().scale().getX() /2f)) && worldCoordinates.getX() <= (gameObject.getTransform().position().getX() + (gameObject.getTransform().scale().getX() / 2f))) {

                if(worldCoordinates.getY() >= gameObject.getTransform().position().getY() - (gameObject.getTransform().scale().getY() /2f) && worldCoordinates.getY() <= gameObject.getTransform().position().getY() + (gameObject.getTransform().scale().getY() / 2f)) {

                    listeners.get(i).raycasted();
                }
            }
        }
    }

	// TODO: Find the correct horizontal projection formula
    private Vector2 screenToCamera(int x, int y) {

        float cameraX = (float)x / Engine.getInstance().getRenderer().getWidth();
        float cameraY = 1f - ((float)y / Engine.getInstance().getRenderer().getHeight());

        return new Vector2(cameraX, cameraY);
    }
}

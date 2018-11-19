package engine.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class KeyboardInput extends KeyAdapter {

    private ArrayList<Integer> inputs;
    private ArrayList<InputAxis> axis;

    KeyboardInput() {

        inputs = new ArrayList<>();
        axis = new ArrayList<>();

        ArrayList<Integer> negativeHorizontal = new ArrayList<>(2);
        negativeHorizontal.add(KeyEvent.VK_Q);
        negativeHorizontal.add(KeyEvent.VK_LEFT);

        ArrayList<Integer> positiveHorizontal = new ArrayList<>(2);
        positiveHorizontal.add(KeyEvent.VK_D);
        positiveHorizontal.add(KeyEvent.VK_RIGHT);

        axis.add(new InputAxis("Horizontal", positiveHorizontal, negativeHorizontal));

        ArrayList<Integer> negativeVertical = new ArrayList<>(2);
        negativeVertical.add(KeyEvent.VK_S);
        negativeVertical.add(KeyEvent.VK_DOWN);

        ArrayList<Integer> positiveVertical = new ArrayList<>(2);
        positiveVertical.add(KeyEvent.VK_Z);
        positiveVertical.add(KeyEvent.VK_UP);

        axis.add(new InputAxis("Vertical", positiveVertical, negativeVertical));
    }

    void update() {

        for (int i = 0; i < axis.size(); i++) {

            axis.get(i).update(inputs);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);

        if(!inputs.contains(e.getKeyCode())) {

            inputs.add(e.getKeyCode());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        super.keyReleased(e);

        if(inputs.contains(e.getKeyCode())) {

            inputs.remove(Integer.valueOf(e.getKeyCode()));
        }
    }

    boolean getKey(int key) {

        return (inputs.contains(Integer.valueOf(key)));
    }

    float getAxis(String axisName) {

        for (int i = 0; i < axis.size(); i++) {

            InputAxis tmp = axis.get(i);

            if(tmp.getName().equals(axisName)) {

                return tmp.getValue();
            }
        }

        return 0f;
    }

    boolean hasInput() {

        if(inputs.size() == 0) {

            return false;
        } else {

            return true;
        }
    }
}

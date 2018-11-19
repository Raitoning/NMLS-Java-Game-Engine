package engine.input;

import engine.Mathf;

import java.util.ArrayList;

public class InputAxis {

    private String name;
    private float value;
    private ArrayList<Integer> positiveModifiers;
    private ArrayList<Integer> negativeModifiers;

    InputAxis(String name, ArrayList<Integer> positiveModifiers, ArrayList<Integer> negativeModifiers) {

        this.name = name;
        this.positiveModifiers = positiveModifiers;
        this.negativeModifiers = negativeModifiers;
    }

    void update(ArrayList<Integer> keyEvents) {

        boolean isModified = false;

        for (int i = 0; i < keyEvents.size(); i++) {

            if(positiveModifiers.contains(Integer.valueOf(keyEvents.get(i)))) {

                isModified = true;
                value = Mathf.clamp(value + 1f, -1f, 1f);
            } else if(negativeModifiers.contains(Integer.valueOf(keyEvents.get(i)))) {

                isModified = true;
                value = Mathf.clamp(value - 1f, -1f, 1f);
            }
        }

        if(!isModified) {

            value = 0f;
        }
    }

    float getValue() {

        return value;
    }

    String getName() {

        return name;
    }
}

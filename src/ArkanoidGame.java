//211788625 Evyatar Paz

import Animation.AnimationRunner;
import Base.LevelInformation;
import Game.GameFlow;
import Game.Level1;
import Game.Level2;
import Game.Level3;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Ass 6 game.
 */
public class ArkanoidGame {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", 800, 600);
        AnimationRunner ar = new AnimationRunner(gui);
        KeyboardSensor keyboard = gui.getKeyboardSensor();
        List<LevelInformation> levels = new ArrayList<>();
        GameFlow gameFlow = new GameFlow(ar, keyboard, 800, 600);
        if (args.length == 0) {
            levels = levelList();
        } else {
            for (String arg : args) {
                if (arg.equals("1")) {
                    levels.add(new Level1());
                }
                if (arg.equals("2")) {
                    levels.add(new Level2());
                }
                if (arg.equals("3")) {
                    levels.add(new Level3());
                }
            }
            if (levels.size() == 0) {
                levels = levelList();
            }
        }
        gameFlow.runLevels(levels);
        gui.close();
    }
    /**
     * Level list.
     *
     * @return the list
     */
    public static List<LevelInformation> levelList() {
        List<LevelInformation> listOfLevels = new ArrayList<>();
        listOfLevels.add(new Level1());
        listOfLevels.add(new Level2());
        listOfLevels.add(new Level3());
        return listOfLevels;
    }
}

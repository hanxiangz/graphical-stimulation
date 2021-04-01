import bagel.AbstractGame;
import bagel.Image;
import bagel.Input;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/** This is the main class which contains the main method
 * @author Hanxiang Zhang
 */

public class ShadowLife extends AbstractGame {
    /** This is the default tile size.
     */
    public static final int TILE_SIZE = 64;

    private long lastTick = 0;
    private int numTicksPassed = 0;

    /** These 2 variables are taken from command line arguments.
     */
    public static Long TICK_RATE;
    public static Integer MAX_TICKS;

    /** These are the arrays which store our actors.
     */
    public static ArrayList<Actor> actors = new ArrayList<Actor>();
    public static ArrayList<Actor> clones = new ArrayList<Actor>();

    private final Image background = new Image("res/images/background.png");
    /** This is a command line argument.
     */
    public static String WORLD_FILE;

    /** This method loads the actors from the world file.
     */
    private void loadActors() {

        // go through the csv file
        try (BufferedReader reader = new BufferedReader(new FileReader(WORLD_FILE))) {

            String line;
            int lineNum = 0;
            // go through each line of the csv file
            while ((line = reader.readLine()) != null) {
                // Line format is: type,x,y
                String[] parts = line.split(",");
                if (parts.length != 3) {
                    String.format("error: in file \"%s\" at line %d", WORLD_FILE, lineNum);
                    System.exit(-1);
                }

                String type = parts[0];

                // if second two parts are not valid integers, exit
                if (!isInteger(parts[1]) || !isInteger(parts[2])) {
                    String.format("error: in file \"%s\" at line %d", WORLD_FILE, lineNum);
                    System.exit(-1);
                }
                int x = Integer.parseInt(parts[1]);
                int y = Integer.parseInt(parts[2]);

                // determine the type of actor just read
                switch (type) {
                    case Tree.TYPE:
                        actors.add(new Tree(x, y));
                        break;
                    case Gatherer.TYPE:
                        actors.add(new Gatherer(x, y));
                        break;
                    case "GoldenTree":
                        actors.add(new Actor("res/images/gold-tree.png", x, y));
                        break;
                    case StockPile.TYPE:
                        actors.add(new StockPile(x, y));
                        break;
                    case Hoard.TYPE:
                        actors.add(new Hoard(x, y));
                        break;
                    case Pad.TYPE:
                        actors.add(new Pad(x, y));
                        break;
                    case "Fence":
                        actors.add(new Actor("res/images/fence.png", "Fence", x, y));
                        break;
                    case SignRight.TYPE:
                        actors.add(new SignRight(x, y));
                        break;
                    case SignLeft.TYPE:
                        actors.add(new SignLeft(x, y));
                        break;
                    case SignUp.TYPE:
                        actors.add(new SignUp(x, y));
                        break;
                    case SignDown.TYPE:
                        actors.add(new SignDown(x, y));
                        break;
                    case Mitosis.TYPE:
                        actors.add(new Mitosis(x, y));
                        break;
                    case Thief.TYPE:
                        actors.add(new Thief(x, y));
                        break;
                    default: String.format("error: in file \"%s\" at line %d", WORLD_FILE, lineNum);
                        System.exit(-1);
                }
                lineNum++;
            }
        } catch (IOException e) {
            e.printStackTrace();
            String.format("error: file \"%s\" not found", WORLD_FILE);
            System.exit(-1);
        }
    }

    /** This is the constructor for this class, making use of loadActor method.
     */
    public ShadowLife() {
        loadActors();
    }

    @Override
    protected void update(Input input) {
        int x = 0;
        int y = 0;

        // If enough time has passed, run the next tick
        if (System.currentTimeMillis() - lastTick > TICK_RATE) {
            numTicksPassed++;
            if (numTicksPassed > MAX_TICKS) {
                System.out.println("Timed out");
                System.exit(-1);
            }

            lastTick = System.currentTimeMillis();
            // update the actors and clones
            for (Actor actor : actors) {
                if (actor != null && actor.destroy == false) {
                    actor.tick();
                }
            }
            for (Actor actor : clones) {
                if (actor != null && actor.destroy == false) {
                    actor.tick();
                }
            }
        }

        // add clones to the actor list, then clear the clone list
        for (Actor actor: clones) {
            actors.add(actor);
        }
        clones.clear();

        // Draw all elements
        background.drawFromTopLeft(0, 0);
        for (Actor actor : actors) {
            if (actor != null && actor.destroy == false) {
                actor.render();
            }
        }

        // simulation should halt once gatherers and thieves reach the fence
        boolean halt = false;
        for (Actor actor: actors) {
            if (actor.active = true) {
                halt = true;
            }
        }
        if (halt) {
            System.out.println(numTicksPassed);
        }

    }

    /** Returns true if the string is an integer.
     */
    public static boolean isInteger(String number){
        try {
            Integer.parseInt(number);
        } catch (Exception e ){
            return false;
        }
        return true;
    }

    /** This is the main method.
     */
    public static void main(String[] args) {
        if (args.length < 3 || args.length > 3) {
            System.out.println("usage: ShadowLife <tick rate> <max ticks> <world file>");
            System.exit(-1);
        }
        if (Integer.parseInt(args[0]) < 0 || Integer.parseInt(args[1]) < 0) {
            System.out.println("usage: ShadowLife <tick rate> <max ticks> <world file>");
            System.exit(-1);
        }

        TICK_RATE = Long.parseLong(args[0]);
        MAX_TICKS = Integer.parseInt(args[1]);
        WORLD_FILE = args[2];
        new ShadowLife().run();
    }
}

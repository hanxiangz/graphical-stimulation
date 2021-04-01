/** A gatherer collects cherries
 */
public class Gatherer extends Actor implements Clonable, Moveable, Rotatable {
    /** This is the class identifier.
     */
    public static final String TYPE = "Gatherer";
    private int direction;
    private boolean carrying;

    /** This is the gatherer constructor.
     * It begins by going left, without carrying any fruits
     */
    public Gatherer(int x, int y) {
        super("res/images/gatherer.png", TYPE, x, y);
        direction = Direction.LEFT;
        carrying = false;
        active = true;
    }

    /** A gatherer does not need to use this method
     */
    @Override
    public void setNumFruit(int n) {
    }

    /** A gatherer does not need to use this method
     */
    @Override
    public int getNumFruit() {
        return 0;
    }

    /** Sets the direction the gatherer walks
     */
    @Override
    public void setDirection(int n) {
        direction = n;
    }

    /** Returns the direction which the gatherer is currently moving
     */
    @Override
    public int getDirection() {
        return direction;
    }

    /** Create 2 clones for the gatherer, initially moves in opposite directions
     */
    @Override
    public void createTwoClones() {
        // Create two clone gatherers at its position
        ShadowLife.clones.add(new Gatherer(x, y));
        ShadowLife.clones.add(new Gatherer(x, y));
        int i = ShadowLife.clones.size() - 1;
        ShadowLife.clones.get(i).setDirection(direction);
        ShadowLife.clones.get(i-1).setDirection(direction);
        // rotate the first clone gatherer 90 degrees counterclockwise
        rotate270clock(ShadowLife.clones.get(i));
        // rotate the second clone gatherer 90 degrees clockwise
        rotate90clock(ShadowLife.clones.get(i-1));
        // move clones 1 tile in their direction
        moveInDirection(ShadowLife.clones.get(i), ShadowLife.clones.get(i).getDirection());
        moveInDirection(ShadowLife.clones.get(i-1), ShadowLife.clones.get(i-1).getDirection());

    }

    /** This method updates the position of the gatherer.
     * It also checks to see if the gatherer is standing on another actor.
     */
    @Override
    public void update() {

        // keeps track of the previous location gatherer moved from
        int prevX = x;
        int prevY = y;

        if (active) {
            moveInDirection(this, direction);
        }

        // check to see if gatherer is standing on a fence
        for (Actor actor: ShadowLife.actors) {
            if (actor.type.equals("Fence") && x == actor.x && y == actor.y) {
                active = false;
                x = prevX;
                y = prevY;
            }
        }

        // check to see if gatherer is standing on a mitosis pool
        for (Actor actor: ShadowLife.actors) {
            if (actor.type.equals(Mitosis.TYPE) && x == actor.x && y == actor.y) {
                createTwoClones();
                active = false;
                destroy = true;
            }
        }

        // check to see if gatherer is standing on a sign
        for (Actor actor: ShadowLife.actors) {
            if (actor.type.equals(SignUp.TYPE) && x == actor.x && y == actor.y) {
                direction = Direction.UP;
            }
            if (actor.type.equals(SignDown.TYPE) && x == actor.x && y == actor.y) {
                direction = Direction.DOWN;
            }
            if (actor.type.equals(SignLeft.TYPE) && x == actor.x && y == actor.y) {
                direction = Direction.LEFT;
            }
            if (actor.type.equals(SignRight.TYPE) && x == actor.x && y == actor.y) {
                direction = Direction.RIGHT;
            }
        }

        // check to see if gatherer is standing on a tree and not carrying a fruit
        for (Actor actor: ShadowLife.actors) {
            // standing on a normal tree
            if (actor.type.equals(Tree.TYPE) && x == actor.x && y == actor.y && carrying == false) {
                if (actor.getNumFruit() > 0) {
                    actor.setNumFruit(actor.getNumFruit() - 1);
                    carrying = true;
                    // rotate direction of gatherer by 180 degrees
                    rotate180(this);
                }
            }
            // standing on a golden tree
            if (actor.type.equals("GoldenTree") && x == actor.x && y == actor.y) {
                carrying = true;
                // rotate direction of gatherer by 180 degrees
                rotate180(this);
            }
        }

        // check to see if gatherer is on a hoard or stockpile
        for (Actor actor: ShadowLife.actors) {
            if (actor.type.equals(Hoard.TYPE) || actor.type.equals(StockPile.TYPE)) {
                // gatherer is standing on one of them
                if (x == actor.x && y == actor.y) {
                    if (carrying) {
                        carrying = false;
                        // standing on a hoard
                        if (actor.type.equals(Hoard.TYPE)) {
                            actor.setNumFruit(actor.getNumFruit() + 1);
                        }
                        // standing on a stockpile
                        if (actor.type.equals(StockPile.TYPE)) {
                            actor.setNumFruit(actor.getNumFruit() + 1);
                        }
                    }
                    // rotate direction by 180 degrees
                    rotate180(this);
                }
            }
        }
    }

}

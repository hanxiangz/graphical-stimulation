/** A thief steals cherries
 */
public class Thief extends Actor implements Clonable, Moveable, Rotatable {
    /** This is the class identifier.
     */
    public static final String TYPE = "Thief";
    private int direction;
    private boolean carrying;
    private boolean consuming;

    /** Constructor. The thief initially moves up, not consuming or carrying anything.
     */
    public Thief(int x, int y) {
        super("res/images/thief.png", TYPE, x, y);
        direction = Direction.UP;
        carrying = false;
        consuming = false;
        active = true;
    }

    /** Does not implement this method.
     */
    @Override
    public void setNumFruit(int n) {
    }

    /** Does not implement this method.
     */
    @Override
    public int getNumFruit() {
        return 0;
    }

    /** This method change the direction of the thief.
     */
    @Override
    public void setDirection(int n) {
        direction = n;
    }

    /** This method returns the direction of the thief.
     */
    @Override
    public int getDirection() {
        return direction;
    }

    /** This method creates 2 clones of a thief.
     */
    @Override
    public void createTwoClones() {
        // Create 2 clones at the original actor's position
        ShadowLife.clones.add(new Thief(x, y));
        ShadowLife.clones.add(new Thief(x, y));
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

    /** This method changes the position of the thief.
     */
    @Override
    public void update() {
        // keeps track of the previous location thief moved from
        int prevX = x;
        int prevY = y;
        if (active) {
            moveInDirection(this, direction);
        }

        // check to see if thief is standing on a fence
        for (Actor actor: ShadowLife.actors) {
            if (actor.type.equals("Fence") && x == actor.x && y == actor.y) {
                active = false;
                x = prevX;
                y = prevY;
            }
        }

        // check to see if thief is standing on a mitosis pool
        for (Actor actor: ShadowLife.actors) {
            if (actor.type.equals(Mitosis.TYPE) && x == actor.x && y == actor.y) {
                // create two clones and destroy current thief
                createTwoClones();
                active = false;
                destroy = true;
            }
        }

        // check to see if thief is standing on a sign
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

        // check to see if thief is standing on a pad
        for (Actor actor: ShadowLife.actors) {
            if (actor.type.equals(Pad.TYPE) && x == actor.x && y == actor.y) {
                // a pad is delicious for a thief
                consuming = true;
            }
        }

        // check to see if thief is standing on a gatherer
        for (Actor actor: ShadowLife.actors) {
            if (actor.type.equals(Gatherer.TYPE) && x == actor.x && y == actor.y) {
                rotate270clock(this);
            }
        }

        // check to see if thief is standing on a tree and not carrying anything
        for (Actor actor: ShadowLife.actors) {
            // thief is standing on a normal tree
            if (actor.type.equals(Tree.TYPE) && x == actor.x && y == actor.y && carrying == false) {
                if (actor.getNumFruit() > 0) {
                    actor.setNumFruit(actor.getNumFruit() - 1);
                    carrying = true;
                }
            }
            // standing on a golden tree
            if (actor.type.equals("GoldenTree") && x == actor.x && y == actor.y && carrying == false) {
                carrying = true;
            }
        }

        // check to see if thief is standing on a hoard
        for (Actor actor: ShadowLife.actors) {
            if (actor.type.equals(Hoard.TYPE) && x == actor.x && y == actor.y) {
                if (consuming) {
                    consuming = false;
                    if (!carrying) {
                        if (actor.getNumFruit() > 0) {
                            carrying = true;
                            actor.setNumFruit(actor.getNumFruit() - 1);
                        } else {
                            rotate90clock(this);
                        }
                    }
                } else if (carrying) {
                    carrying = false;
                    actor.setNumFruit(actor.getNumFruit() + 1);
                    // rotate direction by 90 degrees clockwise
                    rotate90clock(this);
                }
            }
        }

        // check to see if thief is standing on a stockpile
        for (Actor actor: ShadowLife.actors) {
            if (actor.type.equals(StockPile.TYPE) && x == actor.x && y == actor.y) {
                // if thief is empty handed, take fruit from stockpile (if there is any)
                if (!carrying) {
                    if (actor.getNumFruit() > 0) {
                        carrying = true;
                        consuming = false;
                        actor.setNumFruit(actor.getNumFruit() - 1);
                        // rotate direction by 90 degrees clockwise
                        rotate90clock(this);
                    }
                } else {
                    // rotate direction by 90 degrees clockwise
                    rotate90clock(this);
                }
            }
        }

    }


}

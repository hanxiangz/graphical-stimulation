import bagel.Font;

/** A tree grows cherries
 */
public class Tree extends Actor {
    /** This is the class identifier.
     */
    public static final String TYPE = "Tree";
    private int numFruit;

    /** Constructor.
     */
    public Tree(int x, int y) {
        super("res/images/tree.png", TYPE, x, y);
        numFruit = 3;
    }

    /** Does not implement this method.
     */
    @Override
    public void setDirection(int n) {
    }

    /** Does not implement this method.
     */
    @Override
    public int getDirection() {
        return 0;
    }

    /** This method changes the number of fruits a tree holds.
     */
    @Override
    public void setNumFruit(int n) {
        numFruit = n;
    }

    /** This method changes the number of fruits a tree holds.
     */
    @Override
    public int getNumFruit() {
        return numFruit;
    }

    /** This method draws a tree together with the number of fruits it contains.
     */
    @Override
    public void render() {
        Font font = new Font("res/VeraMono.ttf", 24);
        String str = Integer.toString(numFruit);
        image.drawFromTopLeft(x, y);
        font.drawString(str, x, y);
    }

    /** A tree does not move.
     */
    @Override
    public void update() {
    }

}

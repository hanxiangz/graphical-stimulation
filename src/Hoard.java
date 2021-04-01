import bagel.Font;

/** A hoard stores cherries
 */
public class Hoard extends Actor {
    /** This is the class identifier.
     */
    public static final String TYPE = "Hoard";
    private int numFruit;

    /** Constructor. Initially starts with 0 fruit.
     */
    public Hoard(int x, int y) {
        super("res/images/hoard.png", TYPE, x, y);
        numFruit = 0;
    }

    /** A hoard does not use this method.
     */
    @Override
    public void setDirection(int n) {
    }

    /** A hoard does not use this method.
     */
    @Override
    public int getDirection() {
        return 0;
    }

    /** This method changes the number of fruit a hoard holds.
     */
    @Override
    public void setNumFruit(int n) {
        numFruit = n;
    }

    /** This method returns the number of fruit a hoard holds.
     */
    @Override
    public int getNumFruit() {
        return numFruit;
    }

    /** This method draws the hoard together with its number of fruits.
     */
    @Override
    public void render() {
        Font font = new Font("res/VeraMono.ttf", 24);
        String str = Integer.toString(numFruit);
        image.drawFromTopLeft(x, y);
        font.drawString(str, x, y);
    }

    /** A hoard does not need to update its position since it's stationary.
     */
    @Override
    public void update() {
    }

}

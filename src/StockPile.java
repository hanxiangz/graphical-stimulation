import bagel.*;

/** A stockpile stores cherries
 */
public class StockPile extends Actor {
    /** This is the class identifier.
     */
    public static final String TYPE = "Stockpile";
    private int numFruit;

    /** Constructor.
     */
    public StockPile(int x, int y) {
        super("res/images/cherries.png", TYPE, x, y);
        numFruit = 0;
    }

    /** This method returns the number of fruit the stockpile contains.
     */
    @Override
    public int getNumFruit() {
        return numFruit;
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

    /** This method alters the number of fruit.
     */
    @Override
    public void setNumFruit(int n) {
        numFruit = n;
    }

    /** This method draws a stockpile and the number of fruits it contains.
     */
    @Override
    public void render() {
        Font font = new Font("res/VeraMono.ttf", 24);
        String str = Integer.toString(numFruit);
        image.drawFromTopLeft(x, y);
        font.drawString(str, x, y);
    }

    /** Does not implement this method.
     */
    @Override
    public void update() {
    }

}

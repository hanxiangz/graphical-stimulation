/** A pad is a stationary object which a thief consumes
 */
public class Pad extends Actor {
    /** This is the class identifier.
     */
    public static final String TYPE = "Pad";

    /** Constructor.
     */
    public Pad(int x, int y) {
        super("res/images/pad.png", TYPE, x, y);
    }

    /** A pad does not implement this method
     */
    @Override
    public void update() {
    }

    /** A pad does not implement this method
     */
    @Override
    public int getNumFruit() {
        return 0;
    }

    /** A pad does not implement this method
     */
    @Override
    public void setNumFruit(int n) {
    }

    /** A pad does not implement this method
     */
    @Override
    public void setDirection(int n) {
    }

    /** A pad does not implement this method
     */
    @Override
    public int getDirection() {
        return 0;
    }

}

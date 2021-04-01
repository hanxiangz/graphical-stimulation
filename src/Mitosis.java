/** A mitosis pool clones gatherers/thieves with step on it
 */
public class Mitosis extends Actor {
    /** This is the class identifier.
     */
    public static final String TYPE = "Pool";

    /** Constructor.
     */
    public Mitosis(int x, int y) {
        super("res/images/pool.png", TYPE, x, y);
    }

    /** A mitosis pool does not need to be updated.
     */
    @Override
    public void update() {
    }

    /** A mitosis pool does not contain any fruits.
     */
    @Override
    public int getNumFruit() {
        return 0;
    }

    /** A mitosis pool does not contain any fruits.
     */
    @Override
    public void setNumFruit(int n) {
    }

    /** A mitosis pool does not implement this method.
     */
    @Override
    public void setDirection(int n) {
    }

    /** A mitosis pool does not implement this method.
     */
    @Override
    public int getDirection() {
        return 0;
    }
}

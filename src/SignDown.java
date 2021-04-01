/** A down sign
 */
public class SignDown extends Actor {
    /** Keep track of the direction of this sign.
     */
    public int direction = Direction.DOWN;

    /** This is the class identifier.
     */
    public static final String TYPE = "SignDown";

    /** Constructor.
     */
    public SignDown(int x, int y) {
        super("res/images/down.png", TYPE, x, y);
    }

    /** A sign does not need to update its position.
     */
    @Override
    public void update() {
    }

    /** A sign contains no fruit.
     */
    @Override
    public int getNumFruit() {
        return 0;
    }

    /** A sign contains no fruit.
     */
    @Override
    public void setNumFruit(int n) {
    }

    /** A sign does not change its direction.
     */
    @Override
    public void setDirection(int n) {
    }

    /** This method returns the direction of a sign.
     */
    @Override
    public int getDirection() {
        return direction;
    }
}

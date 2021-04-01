/** A right sign
 */
public class SignRight extends Actor {
    /** Keep track of the direction of this sign.
     */
    public int direction = Direction.RIGHT;

    /** This is the class identifier.
     */
    public static final String TYPE = "SignRight";

    /** Constructor.
     */
    public SignRight(int x, int y) {
        super("res/images/right.png", TYPE, x, y);
    }

    /** A sign does not need to change its position.
     */
    @Override
    public void update() {
    }

    /** Does not implement this method.
     */
    @Override
    public int getNumFruit() {
        return 0;
    }

    /** Does not implement this method.
     */
    @Override
    public void setNumFruit(int n) {
    }

    /** A sign does not change its direction.
     */
    @Override
    public void setDirection(int n) {
    }

    /** This method returns the direction of the sign.
     */
    @Override
    public int getDirection() {
        return direction;
    }

}
/** An up sign
 */
public class SignUp extends Actor {
    /** Keeps track of the direction of the sign.
     */
    public int direction = Direction.UP;

    /** This is the class identifier.
     */
    public static final String TYPE = "SignUp";

    /** Constructor.
     */
    public SignUp(int x, int y) {
        super("res/images/up.png", TYPE, x, y);
    }

    /** Does not implement this method.
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

    /** This method returns the direction of this sign.
     */
    @Override
    public int getDirection() {
        return direction;
    }
}

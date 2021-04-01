import bagel.Image;

/** An actor is an object in the simulation
 */
public abstract class Actor {
    /** i chose to make these attributes public so the subclasses of actor can access them
     */
    public int x;
    public int y;
    public final Image image;
    public final String type;
    public boolean active;
    public boolean destroy;

    /** constructor.
     */
    public Actor(String filename, String type, int x, int y) {
        image = new Image(filename);
        this.type = type;
        this.x = x;
        this.y = y;
        destroy = false;
    }

    /** This method is used by the gatherer/thief.
     */
    public void move(int deltaX, int deltaY) {
        x += deltaX;
        y += deltaY;
    }

    /** This method updates the position of the actor per tick
     */
    public final void tick() {
        update();
    }

    /** This method updates the actor
     */
    public abstract void update();

    /** This method draws the actor over the background
     */
    public void render() {
        image.drawFromTopLeft(x, y);
    }

    /** This method changes the number of fruit.
     */
    public abstract void setNumFruit(int n);

    /** This method returns the number of fruit.
     */
    public abstract int getNumFruit();

    /** This method changes the direction of an actor.
     */
    public abstract void setDirection(int n);

    /** This method returns the direction of an actor.
     */
    public abstract int getDirection();

}

public interface Rotatable {

    default void rotate180(Actor actor) {
        if (actor.getDirection() == Direction.UP) {
            actor.setDirection(Direction.DOWN);
        } else if (actor.getDirection() == Direction.DOWN) {
            actor.setDirection(Direction.UP);
        } else if (actor.getDirection() == Direction.LEFT) {
            actor.setDirection(Direction.RIGHT);
        } else {
            actor.setDirection(Direction.LEFT);
        }
    }

    default void rotate90clock(Actor actor) {
        if (actor.getDirection() == Direction.UP) {
            actor.setDirection(Direction.RIGHT);
        } else if (actor.getDirection() == Direction.RIGHT) {
            actor.setDirection(Direction.DOWN);
        } else if (actor.getDirection() == Direction.DOWN) {
            actor.setDirection(Direction.LEFT);
        } else {
            actor.setDirection(Direction.UP);
        }
    }

    default void rotate270clock(Actor actor) {
        if (actor.getDirection() == Direction.UP) {
            actor.setDirection(Direction.LEFT);
        } else if (actor.getDirection() == Direction.RIGHT) {
            actor.setDirection(Direction.UP);
        } else if (actor.getDirection() == Direction.DOWN) {
            actor.setDirection(Direction.RIGHT);
        } else {
            actor.setDirection(Direction.DOWN);
        }
    }

}

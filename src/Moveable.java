public interface Moveable {
    default void moveInDirection(Actor actor, int direction) {
        switch (direction) {
            case Direction.UP:
                actor.move(0, -ShadowLife.TILE_SIZE);
                break;
            case Direction.DOWN:
                actor.move(0, ShadowLife.TILE_SIZE);
                break;
            case Direction.LEFT:
                actor.move(-ShadowLife.TILE_SIZE, 0);
                break;
            case Direction.RIGHT:
                actor.move(ShadowLife.TILE_SIZE, 0);
                break;
        }
    }
}

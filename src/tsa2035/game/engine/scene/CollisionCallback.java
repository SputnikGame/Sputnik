package tsa2035.game.engine.scene;

import tsa2035.game.engine.bounding.Side;

public interface CollisionCallback {
	public void collisionOccured(Sprite registeredObject, Sprite withObject, Side onSide);
}

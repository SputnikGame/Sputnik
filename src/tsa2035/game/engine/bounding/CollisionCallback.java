package tsa2035.game.engine.bounding;

import tsa2035.game.engine.scene.Sprite;

public interface CollisionCallback {
	public void collisionOccured(Sprite registeredObject, Sprite withObject, Side onSide);
}

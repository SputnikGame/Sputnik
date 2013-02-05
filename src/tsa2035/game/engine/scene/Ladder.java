package tsa2035.game.engine.scene;

import tsa2035.game.engine.texture.Texture;

public class Ladder extends Sprite {
	String playerName;
	public Ladder(float x, float y, Texture t, String playerName) {
		super(x, y, t);
		this.playerName = playerName;
	}
	
	public void render(Scene parent)
	{
		super.render(parent);
		Player player = (Player)parent.getObject(playerName);
		player.setGravity(!player.contacting(this));
		player.setJumpingDisabled(player.contacting(this));
	}

}

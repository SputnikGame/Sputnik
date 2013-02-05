package tsa2035.game.engine.scene;

import tsa2035.game.engine.texture.Texture;

public class Ladder extends Sprite {
	String playerName;
	boolean wasUsing = false;
	public Ladder(float x, float y, Texture t, String playerName) {
		super(x, y, t);
		this.playerName = playerName;
	}
	
	public void render(Scene parent)
	{
		super.render(parent);

		Player player = (Player)parent.getObject(playerName);
		boolean contacting = player.contacting(this);
		if ( wasUsing && !contacting )
		{
			player.setGravity(true);
			player.setJumpingDisabled(false);
		}
		else if ( contacting )
		{
			player.setGravity(false);
			player.setJumpingDisabled(true);
		}
		wasUsing = contacting;
	}

}

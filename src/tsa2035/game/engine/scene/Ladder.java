package tsa2035.game.engine.scene;

import tsa2035.game.engine.bounding.BoundingBox;
import tsa2035.game.engine.texture.Texture;

public class Ladder extends Sprite {
	String playerName;
	String platformName = null;
	boolean wasUsing = false;
	
	public Ladder(float x, float y, Texture t, String playerName) {
		super(x, y, t);
		this.playerName = playerName;
	}
	
	public Ladder(float x, float y, Texture t, String playerName, String platformName) {
		super(x, y, t);
		this.playerName = playerName;
		this.platformName = platformName;
	}
	
	public void render(Scene parent)
	{
		super.render(parent);
	
		Player player = (Player)parent.getObject(playerName);
		Sprite platform = null;
		
		if ( platformName != null )
		 platform = parent.getObject(platformName);
		
		boolean contacting = player.contacting(this);
		if ( wasUsing && !contacting )
		{
			player.setGravity(true);
			if ( platformName != null )
				platform.setSolid(true);
		}
		else if ( contacting )
		{
			BoundingBox playerBox = player.getBoundingBox();
			
			if ( playerBox.getLowestY() > getBoundingBox().getLowestY()+0.08f &&  playerBox.getLowestY() < getBoundingBox().getHighestY()-0.08f )
			{
				player.setX(getX());
			}
			player.setGravity(false);
			if ( platformName != null )
				platform.setSolid(false);
		}
		wasUsing = contacting;
	}
	

}

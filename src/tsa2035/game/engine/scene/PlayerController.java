package tsa2035.game.engine.scene;

import java.util.Iterator;

import org.lwjgl.input.Keyboard;
import tsa2035.game.engine.bounding.Side;


public class PlayerController {
	Sprite player;
	boolean handleGravity;
	float fallRate = 0.00005f;
	public PlayerController(Sprite player)
	{
		this(player,true);
	}
	
	public PlayerController(Sprite player, boolean enableGravity)
	{
		this.player = player;
		this.handleGravity = enableGravity;
	}
	
	public void poll(Scene scene)
	{
		Iterator<Sprite> sceneObjects = scene.iterator();
		boolean hitSides[] = new boolean[4];
		boolean freefall = false;
		
		while ( sceneObjects.hasNext() )
		{
			Sprite thisObj = sceneObjects.next();

			if ( !player.equals(thisObj) && !thisObj.isHidden() )
			{
				if ( Keyboard.isKeyDown(Keyboard.KEY_E) )
					thisObj.interact(player);
					
				if ( thisObj.isSolid() )
				{
					Side hitSide = player.sideOfContact(thisObj);
					
					if ( hitSide != Side.NONE )
						hitSides[hitSide.ordinal()] = true;
				}
			}
		}
		
		freefall = (!hitSides[Side.TOP.ordinal()] && handleGravity);
		
		if ( freefall )
			fallRate += (float) Math.sqrt(fallRate)/450;
		else
			fallRate = 0.00005f;
		
		if ( fallRate > 0.01 )
			fallRate = 0.01f;
		if ( freefall )
		{
			player.setY(player.getY()-fallRate);
		}
		
		if ( !hitSides[Side.RIGHT.ordinal()] && Keyboard.isKeyDown(Keyboard.KEY_A) )
		{
			player.setX(player.getX()-0.005f);
		}
		
		if ( !hitSides[Side.TOP.ordinal()] && Keyboard.isKeyDown(Keyboard.KEY_S) && !freefall )
		{
			player.setY(player.getY()-0.005f);
		}

		if ( !hitSides[Side.LEFT.ordinal()] && Keyboard.isKeyDown(Keyboard.KEY_D) )
		{
			player.setX(player.getX()+0.005f);
		}
	}
}

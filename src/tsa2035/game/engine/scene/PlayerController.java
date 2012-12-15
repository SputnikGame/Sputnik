package tsa2035.game.engine.scene;

import java.util.Iterator;

import org.lwjgl.input.Keyboard;
import tsa2035.game.engine.bounding.Side;


public class PlayerController {
	Sprite player;
	
	public PlayerController(Sprite player)
	{
		this.player = player;
	}
	
	public void poll(Scene scene)
	{
		Iterator<Sprite> sceneObjects = scene.iterator();
		boolean hitSides[] = new boolean[4];
		while ( sceneObjects.hasNext() )
		{
			Sprite thisObj = sceneObjects.next();
			
			if ( thisObj.isSolid() && !player.equals(thisObj) )
			{
				Side hitSide = player.sideOfContact(thisObj);
				if ( hitSide != Side.NONE )
					hitSides[hitSide.ordinal()] = true;
			}
		}
		
		if ( !hitSides[Side.BOTTOM.ordinal()] && Keyboard.isKeyDown(Keyboard.KEY_W) )
		{
			player.setY(player.getY()+0.05f);
		}
		
		if ( !hitSides[Side.RIGHT.ordinal()] && Keyboard.isKeyDown(Keyboard.KEY_A) )
		{
			player.setX(player.getX()-0.05f);
		}
		
		if ( !hitSides[Side.TOP.ordinal()] && Keyboard.isKeyDown(Keyboard.KEY_S) )
		{
			player.setY(player.getY()-0.05f);
		}

		if ( !hitSides[Side.LEFT.ordinal()] && Keyboard.isKeyDown(Keyboard.KEY_D) )
		{
			player.setX(player.getX()+0.05f);
		}
	}
}
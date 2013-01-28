package tsa2035.game.engine.scene;

import java.util.Iterator;

import org.lwjgl.input.Keyboard;

import tsa2035.game.engine.bounding.Side;
import tsa2035.game.engine.texture.Texture;

public class Player extends Sprite {

	boolean handleGravity;
	float fallRate = 0.00005f;
	
	public Player(float x, float y, Texture t, boolean handleGravity) {
		super(x, y, t);
		this.handleGravity = handleGravity;
	}
	
	public void render(Scene scene)
	{
		Iterator<Sprite> sceneObjects = scene.iterator();
		boolean hitSides[] = new boolean[4];
		boolean freefall = false;
		
		while ( sceneObjects.hasNext() )
		{
			Sprite thisObj = sceneObjects.next();
 
			if ( !this.equals(thisObj) && !thisObj.isHidden() )
			{
				if ( Keyboard.isKeyDown(Keyboard.KEY_E) )
					thisObj.interact(this);
					
				if ( thisObj.isSolid() )
				{
					Side hitSide = sideOfContact(thisObj);
					
					
					
					if ( thisObj.isPushable() )
					{
						if ( hitSide == Side.LEFT )
						{
							thisObj.setX(getX()+(getWidth()*2));
						}
						else if ( hitSide == Side.RIGHT )
						{
							thisObj.setX(getX()-(getWidth()*2));
						}
					}
					else if ( hitSide != Side.NONE )
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
			setY(getY()-fallRate);
		}
		
		if ( !hitSides[Side.RIGHT.ordinal()] && Keyboard.isKeyDown(Keyboard.KEY_A) )
		{
			setX(getX()-0.005f);
		}
		
		if ( !hitSides[Side.TOP.ordinal()] && Keyboard.isKeyDown(Keyboard.KEY_S) && !freefall )
		{
			setY(getY()-0.005f);
		}

		if ( !hitSides[Side.LEFT.ordinal()] && Keyboard.isKeyDown(Keyboard.KEY_D) )
		{
			setX(getX()+0.005f);
		}
		
		super.render(scene);
	}
	
}

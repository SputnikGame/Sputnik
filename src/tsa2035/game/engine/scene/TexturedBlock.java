package tsa2035.game.engine.scene;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2f;

import java.util.Iterator;

import tsa2035.game.engine.bounding.Side;
import tsa2035.game.engine.core.Renderer;
import tsa2035.game.engine.texture.Texture;

public class TexturedBlock extends Sprite {

	float width,height;
	
	public TexturedBlock(float x, float y, float width, float height, Texture t, boolean solid) {
		super(x, y, t, solid);
		this.width = width;
		this.height = height;
	}
	
	public TexturedBlock(float x, float y, float width, float height, Texture t) {
		super(x, y, t);
		this.width = width;
		this.height = height;
	}
	
	public void setWidth(float width)
	{
		this.width = width;
	}

	public void setHeight(float height)
	{
		this.height = height;
	}
	
	public float getWidth()
	{
		return width;
	}
	
	public float getHeight()
	{
		return height;
	}
	
	public void render(Scene parent)
	{
		if ( isHidden() ) return;
		
		if ( texture == null )
			return;

		float x = getWidth();
		float y = getHeight();
		
		float points[][] = {
				{ x, y },
				{ -x,y },
				{ -x,-y },
				{ x, -y }	
		};
		
		for ( int i = 0; points.length > i; i++ )
		{
			points[i][0] += xPos;
			points[i][1] += yPos;
		}
		
		boundingBox.setPoints(points);
		
		Iterator<Sprite> sceneObjects = parent.iterator();
		
		while( sceneObjects.hasNext() )
		{
			Sprite obj = sceneObjects.next();
			if ( obj.isSolid() )
			{
				Side sideOfHit = Side.NONE;
				if ( !obj.equals(this) && (sideOfHit = sideOfContact(obj)) != Side.NONE )
				{
					doHit(obj,sideOfHit);
					Iterator<CollisionCallback> callbackIt = collisionCallbacks.iterator();
					while ( callbackIt.hasNext() )
						callbackIt.next().collisionOccured(this, obj, sideOfHit);
				}
			}
		}
		
		float xRepeatFactor = (float)(width*Renderer.getScreenX())/(float)texture.getWidth();
		float yRepeatFactor = (float)(height*Renderer.getScreenY())/(float)texture.getHeight();
		texture.bind();
		glColor3f(1,1,1);
		glBegin(GL_QUADS);
		
		
		glTexCoord2f(xRepeatFactor, yRepeatFactor);
		glVertex2f(points[3][0], points[3][1]);

		glTexCoord2f(0, yRepeatFactor);
		glVertex2f(points[2][0], points[2][1]);

		glTexCoord2f(0, 0);
		glVertex2f(points[1][0], points[1][1]);

		glTexCoord2f(xRepeatFactor, 0);	
		glVertex2f(points[0][0], points[0][1]);
		
		glEnd();
	}
}

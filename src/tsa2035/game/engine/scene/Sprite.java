package tsa2035.game.engine.scene;

import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;
import java.util.Iterator;

import org.newdawn.slick.geom.Point;

import tsa2035.game.engine.bounding.BoundingBox;
import tsa2035.game.engine.bounding.CollisionCallback;
import tsa2035.game.engine.bounding.Side;
import tsa2035.game.engine.core.Renderer;
import tsa2035.game.engine.texture.Texture;

public class Sprite {
	private Texture texture = null;
	private float scale;
	private float xPos, yPos;
	
	private boolean solid = false;
	
	private BoundingBox boundingBox = new BoundingBox();
	
	private ArrayList<CollisionCallback> callbacks = new ArrayList<CollisionCallback>();
	
	public Sprite(float x, float y, Texture t)
	{
		this(x,y,t,false);
	}
	
	public Sprite(float x, float y, Texture t, boolean solid)
	{
		texture = t;
		
		this.xPos = x;
		this.yPos = y;
		
		setScale(1);
	}
	
	public void setPosition(float x, float y)
	{
		xPos = x;
		yPos = y;
	}
	
	public Point getPosition()
	{
		return new Point(xPos, yPos);
	}
	
	public void setX(float x)
	{
		xPos = x;
	}
	
	public void setY(float y)
	{
		yPos = y;
	}
	
	public float getX()
	{
		return xPos;
	}
	
	public float getY()
	{
		return yPos;
	}
	
	public float getHeight()
	{
		return ((float)texture.getHeight()/(float)Renderer.getScreenY())*scale;
	}
	
	public float getWidth()
	{
		return ((float)texture.getWidth()/(float)Renderer.getScreenX())*scale;
	}
	
	public void render(Scene parent)
	{
		if ( texture == null )
			return;

		float x = getWidth();
		float y = getHeight();
		
		x= x/2;
		y= y/2;
		
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
			Side sideOfHit = Side.NONE;
			if ( !obj.equals(this) && (sideOfHit = sideOfContact(obj)) != Side.NONE )
			{
				Iterator<CollisionCallback> callbackIt = callbacks.iterator();
				while ( callbackIt.hasNext() )
					callbackIt.next().collisionOccured(this, obj, sideOfHit);
			}
		}
		
		texture.bind();
		glColor3f(1,1,1);
		glBegin(GL_QUADS);
		
		
		glTexCoord2f(1, 1);
		glVertex2f(points[3][0], points[3][1]);

		glTexCoord2f(0, 1);
		glVertex2f(points[2][0], points[2][1]);

		glTexCoord2f(0, 0);
		glVertex2f(points[1][0], points[1][1]);

		glTexCoord2f(1, 0);	
		glVertex2f(points[0][0], points[0][1]);
		
		glEnd();
	}
	
	public void registerCallback(CollisionCallback callback)
	{
		callbacks.add(callback);
	}
	
	public void setScale(float scale)
	{
		this.scale = scale+1;
	}
	
	public boolean contacting(Sprite other)
	{
		return boundingBox.contacting(other.getBoundingBox());
	}
	
	public Side sideOfContact(Sprite other)
	{
		return boundingBox.sideOfContact(other.getBoundingBox());
	}
	
	public BoundingBox getBoundingBox()
	{
		return boundingBox;
	}
	
	public boolean isSolid()
	{
		return solid;
	}
	
	public void setSolid(boolean state)
	{
		solid = state;
	}
}

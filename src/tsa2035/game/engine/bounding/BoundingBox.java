package tsa2035.game.engine.bounding;

import static org.lwjgl.opengl.GL11.glVertex2f;

import org.lwjgl.opengl.GL11;

public class BoundingBox {
	float lowestX,lowestY,highestX,highestY;
	
	public BoundingBox()
	{
		
	}
	
	public BoundingBox(float points[][])
	{
		this();
		setPoints(points);
	}
	
	public void setPoints(float points[][])
	{
		lowestX = 999;
		lowestY = 999;
		highestX = -999;
		highestY = -999;
		for ( int i = 0; i < points.length; i++ )
		{
			if ( lowestX > points[i][0] ) 
				lowestX = points[i][0];
			if ( highestX < points[i][0] ) 
				highestX = points[i][0];
			
			if ( lowestY > points[i][1] ) 
				lowestY = points[i][1];
			if ( highestY < points[i][1] ) 
				highestY = points[i][1];
		}
	}
	
	public void renderBoxes()
	{
		GL11.glBegin(GL11.GL_QUADS);
		glVertex2f(lowestX, lowestY);
		glVertex2f(lowestX, highestY);
		glVertex2f(highestX, highestY);
		glVertex2f(highestX, lowestY);
		GL11.glEnd();
	}
	
	public float getHighestY()
	{
		return highestY;
	}
	
	public float getLowestY()
	{
		return lowestY;
	}

	public float getLowestX()
	{
		return lowestX;
	}
	
	public float getHighestX()
	{
		return highestX;
	}
	
	public float getWidth()
	{
		return getHighestX()-getLowestX();
	}
	
	public float getHeight()
	{
		return getHighestY()-getLowestY();
	}
	
	public float getX()
	{
		return getLowestX()+(getWidth()/2);
	}
	
	public float getY()
	{
		return getLowestY()+(getHeight()/2);
	}
	
	public boolean contacting(BoundingBox box)
	{
		return sideOfContact(box) != Side.NONE;
	}
	
	public Side sideOfContact(BoundingBox box)
	{
		float w = (float)0.5f*(getWidth()+box.getWidth());
		float h = (float)0.5f*(getHeight()+box.getHeight());
		float dx = getX()-box.getX();
		float dy = getY()-box.getY();
		
		if ( Math.abs(dx) <= w && Math.abs(dy) <= h )
		{
			float wy = w*dy;
			float hx = h*dx;
			
			if ( wy > hx )
			{
				if ( wy > -hx )
					return Side.TOP;
				else
					return Side.LEFT;
			}
			else
			{
				if ( wy > -hx )
					return Side.RIGHT;
				else
					return Side.BOTTOM;
			}
		}
		
		return Side.NONE;
	}
}

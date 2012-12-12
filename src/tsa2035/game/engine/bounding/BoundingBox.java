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
	
	public boolean contacting(BoundingBox box)
	{
		boolean checks[] = new boolean[4];
		checks[0] = getLowestX()<box.getHighestX();
		checks[1] = getHighestX()>box.getLowestX();
		checks[2] = getHighestY()>box.getLowestY();
		checks[3] = getLowestY()<box.getHighestY();
		
		return checks[0] && checks[1] && checks[2] && checks[3];
	}
}

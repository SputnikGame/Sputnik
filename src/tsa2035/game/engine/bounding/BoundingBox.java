package tsa2035.game.engine.bounding;

public class BoundingBox {
	float lowestX = -999, lowestY = -999,
			  highestX = -999, highestY = -999;
	
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
		return lowestY;
	}
	
	public float getHighestX()
	{
		return highestX;
	}
	
	public boolean contacting(BoundingBox box)
	{
		if ( getLowestX() < box.getHighestX() && getHighestX() > box.getLowestX() &&
			    getHighestY() > box.getLowestY() && getLowestY() < box.getHighestY() )
			return true;
		return false;
	}
}

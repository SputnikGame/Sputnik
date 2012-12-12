package tsa2035.game.engine.scene;

import static org.lwjgl.opengl.GL11.*;

import org.newdawn.slick.geom.Point;

import tsa2035.game.engine.bounding.BoundingBox;
import tsa2035.game.engine.core.Renderer;
import tsa2035.game.engine.texture.Texture;

public class Sprite {
	Texture texture = null;
	float scale;
	float xPos, yPos;
	
	BoundingBox boundingBox = new BoundingBox();
	
	public Sprite(float x, float y, Texture t)
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
	
	public void render()
	{
		if ( texture == null )
			return;

		float x = (float)texture.getWidth()/(float)Renderer.getScreenX();
		float y = (float)texture.getHeight()/(float)Renderer.getScreenY();
		
		x *= scale;
		y *= scale;
		
		float xHalf = x/2;
		float yHalf = y/2;
		
		float points[][] = {
				{ xHalf, yHalf },
				{ -xHalf,yHalf },
				{ -xHalf,-yHalf },
				{ xHalf, -yHalf }	
		};
		
		for ( int i = 0; points.length > i; i++ )
		{
			points[i][0] += xPos;
			points[i][1] += yPos;
		}
		/*
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
		*/
		
		float lowestX = 999;
		float lowestY = 999;
		float highestX = -999;
		float highestY = -999;
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
		
		boundingBox.setPoints(points);
		glPointSize(10.0f); 
		
		glBegin(GL_POINTS);
		glColor3f(0.0f, 1.0f,0.0f);
		glVertex2f(boundingBox.getHighestX(), boundingBox.getHighestY());
		glVertex2f(boundingBox.getLowestX(), boundingBox.getLowestY());
		glEnd();
		
		boundingBox.renderBoxes();
	}
	
	public void setScale(float scale)
	{
		this.scale = scale+1;
	}
	
	public boolean contacting(Sprite other)
	{
		return boundingBox.contacting(other.getBoundingBox());
	}
	
	public BoundingBox getBoundingBox()
	{
		return boundingBox;
	}
}

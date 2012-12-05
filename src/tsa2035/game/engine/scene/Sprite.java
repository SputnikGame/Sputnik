package tsa2035.game.engine.scene;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.geom.Point;

import tsa2035.game.engine.core.Renderer;
import tsa2035.game.engine.texture.Texture;

public class Sprite {
	Texture texture = null;
	float scale;
	float xPos, yPos;
	
	public Sprite(float x, float y, Texture t)
	{
		texture = t;
		
		xPos = x;
		yPos = y;
		
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

		texture.bind();
		glColor3f(1,1,1);
		glBegin(GL_QUADS);
		float x = (float)texture.getWidth()/(float)Renderer.getScreenX();
		float y = (float)texture.getHeight()/(float)Renderer.getScreenY();
		
		
		
		float points[][] = {
				{ 0,0 },
				{ x,0 },
				{ x,y },
				{ 0,y }
		};
		
		for ( int i = 0; i < points.length; i++ )
		{
			points[i][0] *= scale;
			points[i][1] *= scale;
		}
		
		glTexCoord2f(1, 1);
		glVertex2f(points[0][0], points[0][1]);

		glTexCoord2f(0, 1);
		glVertex2f(points[1][0], points[1][1]);

		glTexCoord2f(0, 0);
		glVertex2f(points[2][0], points[2][1]);

		glTexCoord2f(1, 0);	
		glVertex2f(points[3][0], points[3][1]);
		
		glEnd();
	}
	
	public void setScale(float scale)
	{
		this.scale = scale+1;
	}
}

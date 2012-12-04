package tsa2035.game.engine.scene;

import static org.lwjgl.opengl.GL11.*;

import org.newdawn.slick.geom.Point;

import tsa2035.game.engine.core.Renderer;
import tsa2035.game.engine.texture.Texture;

public class Sprite {
	Texture texture = null;
	double scale = 1.0;
	float xPos, yPos;
	
	public Sprite(float x, float y, Texture t)
	{
		texture = t;
		
		xPos = x;
		yPos = y;
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
		
		x += xPos;
		y += yPos;
		
		glTexCoord2f(1, 1);
		glVertex2f(-y, -x);

		glTexCoord2f(0, 1);
		glVertex2f(y, -x);

		glTexCoord2f(0, 0);
		glVertex2f(y, x);

		glTexCoord2f(1, 0);	
		glVertex2f(-y, x);
		
		glEnd();
	}
	
	public void setScale(double scale)
	{
		this.scale = scale;
	}
}

package tsa2035.game.engine.scene.background;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2f;

import tsa2035.game.engine.core.Renderer;
import tsa2035.game.engine.texture.Texture;

public class SpriteBackground implements Background {

	Texture texture;
	public SpriteBackground(Texture bg)
	{
		texture = bg;
	}
	
	@Override
	public void render() 
	{
		texture.bind();
		glColor3f(1,1,1);
		glBegin(GL_QUADS);
		
		float xRepeatFactor = (float)Renderer.getScreenX()/(float)texture.getWidth();
		float yRepeatFactor = (float)Renderer.getScreenY()/(float)texture.getHeight();
		
		glTexCoord2f(xRepeatFactor, yRepeatFactor);
		glVertex2f(-1,-1);

		glTexCoord2f(0,yRepeatFactor);
		glVertex2f(1, -1);

		glTexCoord2f(0, 0);
		glVertex2f(1,1);

		glTexCoord2f(xRepeatFactor, 0);	
		glVertex2f(-1,1);
		
		glEnd();
	}

}

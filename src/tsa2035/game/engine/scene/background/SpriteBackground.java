package tsa2035.game.engine.scene.background;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2f;
import tsa2035.game.engine.texture.Texture;

public class SpriteBackground implements Background {

	Texture texture;
	public SpriteBackground(Texture bg)
	{
		texture = bg;
		
		texture.setRepeat();
	}
	
	@Override
	public void render() {
		texture.bind();
		glColor3f(1,1,1);
		glBegin(GL_QUADS);
		
		
		glTexCoord2f(1, 1);
		glVertex2f(-1,-1);

		glTexCoord2f(0, 1);
		glVertex2f(1, -1);

		glTexCoord2f(0, 0);
		glVertex2f(1,1);

		glTexCoord2f(1, 0);	
		glVertex2f(-1,1);
		
		glEnd();
	}

}

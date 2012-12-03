package tsa2035.game.engine.scene.background;

import org.lwjgl.opengl.GL11;
import java.awt.Color;

public class SolidBackground implements Background {
	Color color;
	public SolidBackground(Color color)
	{
		this.color = color;
	}

	
	@Override
	public void render() {
		GL11.glClearColor((float)color.getRed()/255, (float)color.getGreen()/255, (float)color.getBlue()/255, 0);
	}

}

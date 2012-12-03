package tsa2035.game.engine.core;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import tsa2035.game.engine.scene.Scene;

public class Renderer {
	private static int screenL, screenW;
	private static Scene currentScene = null;
	public static void init(Scene firstScene, int screenL, int screenW) throws LWJGLException
	{
		Renderer.screenL = screenL;
		Renderer.screenW = screenW;
		Display.setDisplayMode(new DisplayMode(screenL, screenW));
		Display.create();
		
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glOrtho(0, screenL, screenW, 0, 0, 0);
		
		setScene(firstScene);
	}
	
	public static void renderLoop()
	{
		while ( !Display.isCloseRequested() )
		{
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
	        GL11.glLoadIdentity();
	        
	        if ( currentScene != null )
	        {
	        	currentScene.render();
	        	GL11.glFlush();
	        	Display.sync(10);
	        	Display.update();
	        }
		}
		
		Display.destroy();
		System.exit(0);
	}
	
	public static int getScreenLength()
	{
		return screenL;
	}
	
	public static int getScreenWidth()
	{
		return screenW;
	}
	
	public static void setScene(Scene s)
	{
		currentScene = s;
	}
	
	public static Scene getScene()
	{
		return currentScene;
	}
}
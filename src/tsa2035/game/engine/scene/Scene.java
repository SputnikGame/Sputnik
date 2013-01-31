package tsa2035.game.engine.scene;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2f;

import java.awt.Color;
import java.util.HashMap;
import java.util.Iterator;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import tsa2035.game.engine.audio.SceneAudioManager;
import tsa2035.game.engine.scene.background.Background;
import tsa2035.game.engine.scene.background.SolidBackground;

public abstract class Scene {
	private HashMap<String, Sprite> objects = new HashMap<String, Sprite>();
	private Background bg = new SolidBackground(Color.BLACK);
	SceneAudioManager audioManager = new SceneAudioManager();
	private float sceneFade = 0;
	public Scene()
	{

	}
	
	public void setBackground(Background bg)
	{
		this.bg = bg;
	}
	
	public Sprite addToScene(String name, Sprite sprite)
	{
		objects.put(name, sprite);
		return sprite;
	}
	
	public Sprite getObject(String name)
	{
		return objects.get(name);
	}
	
	public abstract void sceneLogic();
	
	private void renderSceneFade()
	{
		glColor4f(0,0,0,sceneFade);
		glBegin(GL_QUADS);
		
		glVertex2f(1, 1);
		glVertex2f(1, -1);
		glVertex2f(-1, -1);
		glVertex2f(-1, 1);

		glEnd();
	}
	
	public void render()
	{
		Keyboard.poll();
		
		bg.render();
		
		Iterator<Sprite> it = objects.values().iterator();
		while ( it.hasNext() )
		{
			GL11.glPushMatrix();
			Sprite obj = it.next();
			obj.render(this);
			GL11.glPopMatrix();
		}
		renderSceneFade();
		sceneLogic();
	}
	
	public Iterator<Sprite> iterator()
	{
		return objects.values().iterator();
	}
	
	public SceneAudioManager getAudioManager()
	{
		return audioManager;
	}
	
	public void setSceneFade(float alpha)
	{
		sceneFade = alpha;
	}
}

package tsa2035.game.engine.scene;

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
	
	public void massSetAlpha(float alpha)
	{
		Iterator<Sprite> it = iterator();
		while ( it.hasNext() )
		{
			it.next().setAlpha(alpha);
		}
	}
}

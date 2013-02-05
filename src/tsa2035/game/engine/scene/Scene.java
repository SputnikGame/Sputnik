package tsa2035.game.engine.scene;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import tsa2035.game.engine.audio.SceneAudioManager;
import tsa2035.game.engine.scene.background.Background;
import tsa2035.game.engine.scene.background.SolidBackground;

public abstract class Scene {
	private ArrayList<Sprite> objects = new ArrayList<Sprite>();
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
		sprite.setName(name);
		objects.add(sprite);
		Collections.sort(objects, new Comparator<Sprite>() {

	        public int compare(Sprite o1, Sprite o2) {
	            return o1.getLayer()-o2.getLayer();
	        }
	    });
		return sprite;
	}
	
	public Sprite getObject(String name)
	{
		Iterator<Sprite> it = iterator();
		while ( it.hasNext() )
		{
			Sprite thisSprite = (Sprite) it.next();
			if ( thisSprite.getName().equals(name) )
				return thisSprite;
		}
		return null;
	}
	
	public abstract void sceneLogic();
	
	public void render()
	{
		Keyboard.poll();
		
		bg.render();
		
		Iterator<Sprite> it = objects.iterator();
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
		return objects.iterator();
	}
	
	public SceneAudioManager getAudioManager()
	{
		return audioManager;
	}
	
	public void cleanUp()
	{
		Iterator<Sprite> it = iterator();
		while ( it.hasNext() )
		{
			it.next().cleanUp();
		}
				
	}
}

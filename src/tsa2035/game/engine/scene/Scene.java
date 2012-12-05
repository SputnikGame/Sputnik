package tsa2035.game.engine.scene;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.lwjgl.opengl.GL11;

import tsa2035.game.engine.scene.background.Background;
import tsa2035.game.engine.scene.background.SolidBackground;

public abstract class Scene {
	private HashMap<String, Sprite> objects = new HashMap<String, Sprite>();
	private Background bg = new SolidBackground(Color.WHITE);
	
	public Scene()
	{
		
	}
	
	public void setBackground(Background bg)
	{
		this.bg = bg;
	}
	
	public void addToScene(String name, Sprite sprite)
	{
		objects.put(name, sprite);
	}
	
	public Sprite getObject(String name)
	{
		return objects.get(name);
	}
	
	public void render()
	{
		bg.render();
		
		Iterator<Sprite> it = objects.values().iterator();
		while ( it.hasNext() )
		{
			GL11.glPushMatrix();
			Sprite obj = it.next();
			obj.render();
			GL11.glPopMatrix();
		}
	}
}

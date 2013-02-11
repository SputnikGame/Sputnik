package tsa2035.game.engine.texture;

import java.util.HashMap;
import java.util.Iterator;

public class MultiTexture implements Texture {
	HashMap<String, Texture> textures = new HashMap<String, Texture>();
	String active = null;
	public MultiTexture()
	{
		
	}
	
	public void addTexture(String name, Texture txt)
	{
		textures.put(name,txt);
		
		if ( active == null )
			active = name;
	}
	
	public void switchTo(String name)
	{
		active = name;
	}	
	
	public void bind()
	{
		if ( active == null )
			return;
		textures.get(active).bind();
	}
	
	public Texture getCurrent()
	{
		return textures.get(active);
	}

	@Override
	public int getWidth() {
		return textures.get(active).getWidth();
	}

	@Override
	public int getHeight() {
		return textures.get(active).getHeight();
	}

	@Override
	public void fire() {
		textures.get(active).fire();
	}

	@Override
	public void start() {
		textures.get(active).start();
	}

	@Override
	public void stop() {
		textures.get(active).stop();
	}

	@Override
	public void unload() {
		Iterator<Texture> it = textures.values().iterator();
		while ( it.hasNext() )
		{
			TextureManager.unloadTexture(it.next());
		}
		
	}
}

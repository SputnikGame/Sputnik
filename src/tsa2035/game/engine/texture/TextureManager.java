package tsa2035.game.engine.texture;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class TextureManager {
	private static HashMap<String, StaticTexture> textures = new HashMap<String, StaticTexture>();
	
	public static StaticTexture getTexture(String path) throws IOException
	{
		if ( textures.containsKey(path) )
			return textures.get(path);
		try
		{
			StaticTexture t = new StaticTexture(path);
			textures.put(path, t);
			return t;
		}
		catch( IOException e )
		{
			System.out.println("Failed to load texture "+path);
			throw e;
		}
	}

	public static StaticTexture getTextureFromResource(String path) throws IOException
	{
		if ( textures.containsKey(path) )
			return textures.get(path);
		System.out.println("Loading texture "+path);
		try
		{
			StaticTexture t = new StaticTexture(TextureManager.class.getResourceAsStream(path));
			textures.put(path, t);
			return t;
		}
		catch ( IOException e )
		{
			System.out.println("Failed to load texture "+path);
			throw e;
		}
	}
	
	public static void unloadTexture(String name)
	{
		textures.get(name).unload();
		textures.remove(name);
		System.out.println("Unloading texture "+name);
	}
	
	public static void unloadTexture(Texture obj)
	{
		HashMap<String, StaticTexture> clone = new HashMap<String, StaticTexture>(textures);
		Iterator<Entry<String, StaticTexture>> it = clone.entrySet().iterator();
		while ( it.hasNext() )
		{
			Entry<String, StaticTexture> thisEntry = it.next();
			if ( thisEntry.getValue().equals(obj) )
			{
				thisEntry.getValue().doUnload();
				textures.remove(thisEntry.getKey());
				System.out.println("Unloading texture "+thisEntry.getKey());
			}
		}
	}
}

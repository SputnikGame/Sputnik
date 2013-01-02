package tsa2035.game.engine.texture;

import java.io.IOException;
import java.util.HashMap;

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
}

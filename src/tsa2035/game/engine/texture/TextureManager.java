package tsa2035.game.engine.texture;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

public class TextureManager {
	private static HashMap<String, StaticTexture> textures = new HashMap<String, StaticTexture>();
	
	public static StaticTexture getTexture(String path) throws FileNotFoundException, IOException
	{
		if ( textures.containsKey(path) )
			return textures.get(path);
		StaticTexture t = new StaticTexture(path);
		textures.put(path, t);
		return t;
	}

	public static StaticTexture getTextureFromResource(String path) throws FileNotFoundException, IOException
	{
		if ( textures.containsKey(path) )
			return textures.get(path);
		StaticTexture t = new StaticTexture(TextureManager.class.getResourceAsStream(path));
		textures.put(path, t);
		return t;
	}
}

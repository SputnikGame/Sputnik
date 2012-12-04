package tsa2035.game.engine.texture;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

public class TextureManager {
	private static HashMap<String, Texture> textures = new HashMap<String, Texture>();
	
	public static Texture getTexture(String path) throws FileNotFoundException, IOException
	{
		if ( textures.containsKey(path) )
			return textures.get(path);
		Texture t = new Texture(path);
		textures.put(path, t);
		return t;
	}

	public static Texture getTextureFromResource(String path) throws FileNotFoundException, IOException
	{
		if ( textures.containsKey(path) )
			return textures.get(path);
		Texture t = new Texture(TextureManager.class.getResourceAsStream(path));
		textures.put(path, t);
		return t;
	}
}

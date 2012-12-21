package tsa2035.game.engine.texture;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class AnimatedTexture implements Texture {
	ArrayList<StaticTexture> textures = new ArrayList<StaticTexture>();
	int current = 0;
	public AnimatedTexture(String basePath, int numberOfFrames) throws FileNotFoundException, IOException
	{
		for ( int i = 0; i < numberOfFrames; i++ )
		{
			textures.add(TextureManager.getTextureFromResource((basePath+i)));
		}
	}
	
	public void bind()
	{
		if ( current > textures.size() )
			current = 0;
		textures.get(current).bind();
	}
}

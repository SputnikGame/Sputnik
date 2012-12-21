package tsa2035.game.engine.texture;

import java.util.HashMap;

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
}

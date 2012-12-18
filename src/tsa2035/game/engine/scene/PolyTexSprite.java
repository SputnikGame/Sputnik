package tsa2035.game.engine.scene;

import java.util.HashMap;

import tsa2035.game.engine.texture.Texture;

public class PolyTexSprite extends Sprite {
	HashMap<String, Texture> textures = new HashMap<String, Texture>();
	public PolyTexSprite(float x, float y, Texture t) {
		super(x, y, t);
		addTexture("default", t);
	}
	
	public PolyTexSprite(float x, float y, Texture t, boolean solid) {
		super(x, y, t, solid);
		addTexture("default", t);
	}
	
	public PolyTexSprite addTexture(String name, Texture t)
	{
		textures.put(name, t);
		return this;
	}
	
	public PolyTexSprite setTexture(String name)
	{
		setTexture(textures.get(name));
		return this;
	}
}

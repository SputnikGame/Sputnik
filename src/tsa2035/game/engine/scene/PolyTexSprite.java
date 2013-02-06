package tsa2035.game.engine.scene;

import java.util.HashMap;

import tsa2035.game.engine.texture.StaticTexture;

public class PolyTexSprite extends Sprite {
	HashMap<String, StaticTexture> textures = new HashMap<String, StaticTexture>();
	String currentTexture = "default";
	public PolyTexSprite(float x, float y, StaticTexture t) {
		super(x, y, t);
		addTexture("default", t);
	}
	
	public PolyTexSprite(float x, float y, StaticTexture t, boolean solid) {
		super(x, y, t, solid);
		addTexture("default", t);
	}
	
	public PolyTexSprite(float x, float y, String textureName, StaticTexture t, boolean solid)
	{
		this(x,y,t, solid);
		addTexture(textureName, t);
		currentTexture = textureName;
	}
	
	public PolyTexSprite addTexture(String name, StaticTexture t)
	{
		textures.put(name, t);
		return this;
	}
	
	public PolyTexSprite setTexture(String name)
	{
		setTexture(textures.get(name));
		currentTexture = name;
		return this;
	}
	
	public String getTextureName()
	{
		return currentTexture;
	}
}

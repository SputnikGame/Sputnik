package tsa2035.game.engine.texture;

public interface Texture {
	public void bind();
	
	public int getWidth();
	public int getHeight();
	
	public void fire();
	public void start();
	public void stop();
	
	public void unload();
	
}

package tsa2035.game.engine.texture;

import java.io.FileNotFoundException;
import java.io.IOException;

public class LoopedAnimatedTexture extends AnimatedTexture {

	public LoopedAnimatedTexture(String basePath, String animationName, int numberOfFrames, int fps) throws FileNotFoundException, IOException {
		super(basePath, animationName, numberOfFrames, fps);
		start();
	}

	public void bind()
	{
		if ( running && System.currentTimeMillis() > nextSwitch )
		{
			nextSwitch = System.currentTimeMillis()+switchRate;
			current++;
		}
		
		if ( current > (textures.size()-1) )
		{
			current = 0;
		}
		textures.get(current).bind();
	}
	
	public void fire()
	{
		running = true;
		nextSwitch = System.currentTimeMillis()+switchRate;
	}
	
	
	public void start()
	{
		running = true;
	}
	
	public void stop()
	{
		running = false;
		current = 0;
	}
	
}

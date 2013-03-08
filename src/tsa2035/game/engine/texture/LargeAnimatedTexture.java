package tsa2035.game.engine.texture;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

public class LargeAnimatedTexture extends AnimatedTexture {

	StaticTexture lastFrame = null;
	boolean isFirstLoop = true;
	public LargeAnimatedTexture(String basePath, String animationName, int numberOfFrames, int fps, boolean preload) throws FileNotFoundException, IOException {
		super(basePath, animationName, numberOfFrames, fps, preload);	
		lastFrame = TextureManager.getTextureFromResource(basePath+"/"+animationName+getNumberWithLeadingZeros(1)+".png");
		current = 2;
	}
	

	public void bind()
	{
		if ( running && System.currentTimeMillis() > nextSwitch )
		{
			nextSwitch = System.currentTimeMillis()+switchRate;
			current++;
		}

		if ( current > (numberOfFrames-1) )
		{
			running = false;
			current--;
			Iterator<AnimationFinishedCallback> it = callbacks.iterator();
			while ( it.hasNext() )
			{
				it.next().animationFinished(this);
			}
		}
		
		try {
			if ( lastFrame != null && !isFirstLoop && current < (numberOfFrames-1) )
				lastFrame.unload();
			lastFrame = TextureManager.getTextureFromResource(basePath+"/"+animationName+getNumberWithLeadingZeros(current+1)+".png");
			lastFrame.bind();
			isFirstLoop = false;
		} catch (IOException e) {
			System.out.println("Texture load failed");
			e.printStackTrace();
		}
	}
	
	
	public void fire()
	{
		current = 1;
		running = true;
		nextSwitch = System.currentTimeMillis()+switchRate;
	}
	
	public void reset()
	{
		current = 1;
	}
	
	@Override
	public int getWidth() {
		return lastFrame.getWidth();
	}

	@Override
	public int getHeight() {
		return lastFrame.getHeight();
	}
	
	public void next()
	{
		if ( current < (numberOfFrames-1) )
			current++;
	}
	
	public boolean atEnd()
	{
		return current >= (numberOfFrames-1);
	}
}

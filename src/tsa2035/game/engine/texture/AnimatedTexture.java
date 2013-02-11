package tsa2035.game.engine.texture;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class AnimatedTexture implements Texture {
	ArrayList<StaticTexture> textures = new ArrayList<StaticTexture>();
	ArrayList<AnimationFinishedCallback> callbacks = new ArrayList<AnimationFinishedCallback>();
	long switchRate = 0;
	int current = 0;
	long nextSwitch = 0;
	int numberOfFrames;
	
	boolean running = false;
	
	public AnimatedTexture(String basePath, String animationName, int numberOfFrames, int fps) throws FileNotFoundException, IOException
	{
		switchRate = 1000/fps;
		this.numberOfFrames = numberOfFrames;
		for ( int i = 0; i < numberOfFrames; i++ )
		{
			textures.add(TextureManager.getTextureFromResource(basePath+"/"+animationName+getNumberWithLeadingZeros(i+1)+".png"));
		}
	}
	
	public void registerFinishedCallback(AnimationFinishedCallback callback)
	{
		callbacks.add(callback);
	}
	
	public String getNumberWithLeadingZeros(int num)
	{
		if ( num < 10)
			return "000"+num;
		else if ( num <= 99 )
			return "00"+num;
		else if ( num <= 999 )
			return "0"+num;
		return String.valueOf(num);
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
			running = false;
			current--;
			Iterator<AnimationFinishedCallback> it = callbacks.iterator();
			while ( it.hasNext() )
			{
				it.next().animationFinished(this);
			}
		}
		textures.get(current).bind();
	}
	
	public void fire()
	{
		current = 0;
		running = true;
		nextSwitch = System.currentTimeMillis()+switchRate;
	}
	
	public void next()
	{
		if ( current < (textures.size()-1) )
			current++;
	}
	
	public boolean atEnd()
	{
		return current >= (textures.size()-1);
	}
	
	public void reset()
	{
		current = 0;
	}
	
	@Override
	public int getWidth() {
		return textures.get(current).getWidth();
	}

	@Override
	public int getHeight() {
		return textures.get(current).getHeight();
	}

	@Override
	public void start() {
		System.out.println("The start() call is not supported on AnimatedTexture objects. Use LoopedAnimatedTexture instead.");
	}

	@Override
	public void stop() {
		System.out.println("The stop() call is not supported on AnimatedTexture objects. Use LoopedAnimatedTexture instead.");
	}
	
	public int getNumberOfFrames()
	{
		return numberOfFrames;
	}

	@Override
	public void unload() {
		Iterator<StaticTexture> it = textures.iterator();
		while ( it.hasNext() )
		{
			TextureManager.unloadTexture(it.next());
		}
	}
}

package tsa2035.game.engine.scene;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import tsa2035.game.engine.texture.AnimatedTexture;

public class TimerObject extends Sprite {

	Timer timer = null;
	AnimatedTexture texture = null;
	long changeRate;
	ArrayList<FinishedCallback> callbacks = new ArrayList<FinishedCallback>();
	public TimerObject(float x, float y, AnimatedTexture texture, long endTime)
	{
		super(x,y,texture);
		this.texture = texture;
		texture.reset();
		changeRate = endTime/texture.getNumberOfFrames();
	}
	
	public void start()
	{
		if ( timer == null )
		{
			(timer = new Timer()).schedule(new TimerTask()
			{

				@Override
				public void run() {
					if ( texture.atEnd() )
					{
						System.out.println("End!");
						timer.cancel();
						timer = null;
						Iterator<FinishedCallback> it = callbacks.iterator();
						while ( it.hasNext() )
						{
							it.next().timerFinished(TimerObject.this);
						}
						
					}
					texture.next();

					
				}
				
			}, changeRate, changeRate);
		}
	}
	
	public void stop()
	{
		if ( timer != null )
			timer.cancel();
	}
	
	public void reset()
	{
		stop();
		texture.reset();
	}
	
	public void registerFinishedCallback(FinishedCallback callback)
	{
		callbacks.add(callback);
	}
	
	public void cleanUp()
	{
		stop();
		texture.reset();
	}
	

}

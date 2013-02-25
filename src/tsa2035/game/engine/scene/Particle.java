package tsa2035.game.engine.scene;

import java.util.Timer;
import java.util.TimerTask;

import tsa2035.game.engine.texture.Texture;

public class Particle extends Sprite {

	long lifeTime = 0;
	float velocity = 0;
	long startTime;
	float xHeading, yHeading;
	public Particle(float x, float y, Texture t, float minLife, float maxLife, float minVelocity, float maxVelocity) {
		super(x, y, t);
		lifeTime = (long) (Math.floor(Math.random()*maxLife)+minLife);
		velocity = (float) (Math.random()*maxVelocity)+minVelocity;
		
		xHeading = (float) (Math.random()*velocity);
		yHeading = (float) (Math.random()*velocity);
		
		if ( Math.floor(Math.random()*10) < 5 )
			xHeading = -xHeading;
		if ( Math.floor(Math.random()*10) < 5 )
			yHeading = -yHeading;
		
		startTime = System.currentTimeMillis();
	}
	
	public void render(Scene parent)
	{
		setX(getX()+xHeading);
		setY(getY()+yHeading);
		super.render(parent);
	}
	
	public boolean isDead()
	{
		return System.currentTimeMillis() > startTime+lifeTime;
	}

}

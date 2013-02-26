package tsa2035.game.engine.particlefx;

import tsa2035.game.engine.scene.Scene;
import tsa2035.game.engine.scene.Sprite;
import tsa2035.game.engine.texture.Texture;

public class FireParticle extends Sprite {

	long lifeTime = 0;
	float velocity = 0;
	long startTime;
	float xHeading, yHeading;
	float xVelocity = 0, yVelocity = 0;
	public FireParticle(float x, float y, Texture t, float minLife, float maxLife, float minVelocity, float maxVelocity) {
		super(x, y, t);
		lifeTime = (long) (Math.floor(Math.random()*maxLife)+minLife);
		velocity = (float) (Math.random()*maxVelocity)+minVelocity;
		
		xVelocity = velocity;
		yVelocity = velocity;
		
		xHeading = (float) (Math.random()*xVelocity);
		yHeading = (float) (Math.random()*yVelocity);
		
		setX(getX()+xHeading);
		setY(getY()+yHeading);
		
		if ( Math.floor(Math.random()*10) < 5 )
			xHeading = -xHeading;
		
		startTime = System.currentTimeMillis();
	}
	
	public void render(Scene parent)
	{
		setY(getY()+yHeading);
		super.render(parent);
	}
	
	public boolean isDead()
	{
		return System.currentTimeMillis() > startTime+lifeTime;
	}

}

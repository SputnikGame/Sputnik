package tsa2035.game.engine.particlefx;

import java.util.ArrayList;
import tsa2035.game.engine.scene.Scene;
import tsa2035.game.engine.scene.Sprite;
import tsa2035.game.engine.texture.Texture;

public class FireEffect extends Sprite implements Runnable {
	ArrayList<FireParticle> particles = new ArrayList<FireParticle>();
	Object lock = new Object();
	Texture tex = null;
	Thread spawnThread = new Thread(this);
	
	long minSpawnTime, maxSpawnTime;
	float minLife, maxLife, minVelocity, maxVelocity, lengthMax;
	public FireEffect(float x, float y, Texture tex, long minSpawnTime, long maxSpawnTime, float minLife, float maxLife, float minVelocity, float maxVelocity, float lengthMax)
	{
		super(x, y, null);
		this.tex = tex;
		
		this.minSpawnTime = minSpawnTime;
		this.maxSpawnTime = maxSpawnTime;
		
		this.minLife = minLife;
		this.maxLife = maxLife;
		
		this.minVelocity = minVelocity;
		this.maxVelocity = maxVelocity;
		
		this.lengthMax = lengthMax;
		
		spawnThread.start();
	}
	
	public synchronized void render(Scene parent)
	{
		ArrayList<FireParticle> deadParticles = new ArrayList<FireParticle>();
		for ( FireParticle p : particles )
		{
			p.render(parent);
			if ( p.isDead() )
			{
				deadParticles.add(p);
			}
		}
		
		for ( FireParticle p : deadParticles )
		{
			particles.remove(p);
		}
	}

	
	public synchronized void addParticle(FireParticle p)
	{
		particles.add(p);
	}
	
	@Override
	public void run() {
		while ( true )
		{
			long waitTime = (long) Math.floor(Math.random()*maxSpawnTime)+minSpawnTime;
			float xPos = (float) (Math.random()*lengthMax);
			if ( Math.random() < 0.5f )
				xPos = -xPos;
			addParticle(new FireParticle(getX()+xPos, getY(), tex, minLife, maxLife, minVelocity, maxVelocity));
			try {
				Thread.sleep(waitTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

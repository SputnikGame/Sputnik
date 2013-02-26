package tsa2035.game.engine.particlefx;

import java.util.ArrayList;
import tsa2035.game.engine.scene.Scene;
import tsa2035.game.engine.scene.Sprite;
import tsa2035.game.engine.texture.Texture;

public class SparkEffect extends Sprite implements Runnable {
	ArrayList<SparkParticle> particles = new ArrayList<SparkParticle>();
	Object lock = new Object();
	Texture tex = null;
	Thread spawnThread;
	
	long minSpawnTime, maxSpawnTime;
	float minLife, maxLife, minVelocity, maxVelocity;
	
	boolean runFx = false;
	
	public SparkEffect(float x, float y, Texture tex, long minSpawnTime, long maxSpawnTime, float minLife, float maxLife, float minVelocity, float maxVelocity)
	{
		super(x, y, null);
		this.tex = tex;
		
		this.minSpawnTime = minSpawnTime;
		this.maxSpawnTime = maxSpawnTime;
		
		this.minLife = minLife;
		this.maxLife = maxLife;
		
		this.minVelocity = minVelocity;
		this.maxVelocity = maxVelocity;
		
		startFx();
	}
	
	public synchronized void render(Scene parent)
	{
		ArrayList<SparkParticle> deadParticles = new ArrayList<SparkParticle>();
		for ( SparkParticle p : particles )
		{
			p.render(parent);
			if ( p.isDead() )
			{
				deadParticles.add(p);
			}
		}
		
		for ( SparkParticle p : deadParticles )
		{
			particles.remove(p);
		}
	}

	
	public synchronized void addParticle(SparkParticle p)
	{
		particles.add(p);
	}
	
	public void stopFx()
	{
		runFx = false;
	}
	
	public void startFx()
	{
		if ( runFx )
			return;
		
		(spawnThread = new Thread(this)).start();
		runFx = true;
	}
	
	@Override
	public void run() {
		while ( runFx )
		{
			long waitTime = (long) Math.floor(Math.random()*maxSpawnTime)+minSpawnTime;
			addParticle(new SparkParticle(getX(), getY(), tex, minLife, maxLife, minVelocity, maxVelocity));
			try {
				Thread.sleep(waitTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

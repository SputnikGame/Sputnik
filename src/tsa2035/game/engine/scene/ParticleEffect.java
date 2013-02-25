package tsa2035.game.engine.scene;

import java.io.IOException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Iterator;

import tsa2035.game.engine.texture.StaticTexture;
import tsa2035.game.engine.texture.Texture;
import tsa2035.game.engine.texture.TextureManager;

public class ParticleEffect extends Sprite implements Runnable {
	ArrayList<Particle> particles = new ArrayList<Particle>();
	Object lock = new Object();
	Texture tex = null;
	Thread spawnThread = new Thread(this);
	
	long minSpawnTime, maxSpawnTime;
	float minLife, maxLife, minVelocity, maxVelocity;
	public ParticleEffect(float x, float y, Texture tex, long minSpawnTime, long maxSpawnTime, float minLife, float maxLife, float minVelocity, float maxVelocity)
	{
		super(x, y, null);
		this.tex = tex;
		
		this.minSpawnTime = minSpawnTime;
		this.maxSpawnTime = maxSpawnTime;
		
		this.minLife = minLife;
		this.maxLife = maxLife;
		
		this.minVelocity = minVelocity;
		this.maxVelocity = maxVelocity;
		
		spawnThread.start();
	}
	
	public synchronized void render(Scene parent)
	{
		Iterator<Particle> it = particles.iterator();
		ArrayList<Particle> deadParticles = new ArrayList<Particle>();
		for ( Particle p : particles )
		{
			p.render(parent);
			if ( p.isDead() )
			{
				deadParticles.add(p);
			}
		}
		
		for ( Particle p : deadParticles )
		{
			particles.remove(p);
		}
	}

	
	public synchronized void addParticle(Particle p)
	{
		particles.add(p);
	}
	
	@Override
	public void run() {
		while ( true )
		{
			long waitTime = (long) Math.floor(Math.random()*maxSpawnTime)+minSpawnTime;
			addParticle(new Particle(getX(), getY(), tex, minLife, maxLife, minVelocity, maxVelocity));
			try {
				Thread.sleep(waitTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

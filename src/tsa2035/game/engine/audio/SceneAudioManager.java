package tsa2035.game.engine.audio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.newdawn.slick.openal.Audio;

public class SceneAudioManager {
	public HashMap<String, Audio> backgroundSounds = new HashMap<String, Audio>();
	public ArrayList<Audio> activeEffects = new ArrayList<Audio>();
	public float backgroundVolume = 1.0f;
	public float effectVolume = 1.0f;
	
	public float getBackgroundMusicVolume()
	{
		return backgroundVolume;
	}
	
	public void setBackgroundMusicVolume(float volume)
	{
		backgroundVolume = volume;
	}
	
	public float getEffectVolume()
	{
		return effectVolume;
	}
	
	public void setEffectVolume(float volume) 
	{
		effectVolume = volume;
	}
	
	public void addBackgroundMusic(String name, Audio a)
	{
		backgroundSounds.put(name, a);
		a.playAsMusic(1.0f, getBackgroundMusicVolume(), true);
	}
	
	public void stopAll()
	{
		Iterator<Audio> it = backgroundSounds.values().iterator();
		while ( it.hasNext() )
		{
			it.next().stop();
		}
		
		it = activeEffects.iterator();
		while ( it.hasNext() )
		{
			it.next().stop();
		}
	}
	
	public void playEffect(Audio a)
	{
		a.playAsSoundEffect(1.0f, getEffectVolume(), false);
	}
	
	
	public void update()
	{
		Iterator<Audio> it = activeEffects.iterator();
		while ( it.hasNext() )
		{
			Audio thisSound = it.next();
			if ( !thisSound.isPlaying() )
				it.remove();
		}
	}
	
	public void cleanUp()
	{
		stopAll();
	}
}

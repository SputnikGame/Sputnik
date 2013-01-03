package tsa2035.game.engine.audio;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;


public class AudioManager {
	private static HashMap<String, Audio> sounds = new HashMap<String, Audio>();
	
	public static Audio getAudio(String format, String path) throws IOException
	{
		if ( sounds.containsKey(path) )
			return sounds.get(path);
		try
		{
			Audio a = AudioLoader.getAudio(format, new FileInputStream(path));
			sounds.put(path, a);
			return a;
		}
		catch( IOException e )
		{
			System.out.println("Failed to load sound "+path);
			throw e;
		}
	}

	public static Audio getAudioFromResource(String format, String path) throws IOException
	{
		if ( sounds.containsKey(path) )
			return sounds.get(path);
		System.out.println("Loading sound "+path);
		try
		{
			Audio a = AudioLoader.getAudio(format, AudioManager.class.getResourceAsStream(path));
			sounds.put(path, a);
			return a;
		}
		catch ( IOException e )
		{
			System.out.println("Failed to load sound "+path);
			throw e;
		}
	}
}

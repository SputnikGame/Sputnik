package tsa2035.game.content.core;

import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.WaveData;

import tsa2035.game.content.levels.cutscenes.LoadingScreen;
import tsa2035.game.engine.audio.AudioManager;
import tsa2035.game.engine.core.Renderer;
import tsa2035.game.engine.texture.AnimatedTexture;

public class Game {

	/**
	 * @param args
	 * @throws LWJGLException
	 * @throws IOException 
	 */
	
	public static Audio soundtrack = null;
	public static AirMeter airMeter = null;
	public static IntBuffer soundtrackSource = BufferUtils.createIntBuffer(1);
	
	public static void startSoundtrack() throws IOException
	{
		try
		{
			 FloatBuffer sourcePos = (FloatBuffer)BufferUtils.createFloatBuffer(3).put(new float[] { 0.0f, 0.0f, 0.0f }).rewind();
			 
			  FloatBuffer sourceVel = (FloatBuffer)BufferUtils.createFloatBuffer(3).put(new float[] { 0.0f, 0.0f, 0.1f }).rewind();
			 
			  FloatBuffer listenerPos = (FloatBuffer)BufferUtils.createFloatBuffer(3).put(new float[] { 0.0f, 0.0f, 0.0f }).rewind();
			 
			  FloatBuffer listenerVel = (FloatBuffer)BufferUtils.createFloatBuffer(3).put(new float[] { 0.0f, 0.0f, 0.0f }).rewind();
			 
			  FloatBuffer listenerOri = (FloatBuffer)BufferUtils.createFloatBuffer(6).put(new float[] { 0.0f, 0.0f, -1.0f, 0.0f, 1.0f, 0.0f }).rewind();
			 
			
			AL.create();
			WaveData wave = WaveData.create(AudioManager.class.getResource("/tsa2035/game/content/audio/game.wav"));
			IntBuffer buffer = BufferUtils.createIntBuffer(1);

			AL10.alGenBuffers(buffer);
			AL10.alBufferData(buffer.get(0), wave.format, wave.data, wave.samplerate);
			
			AL10.alGenSources(soundtrackSource);
			AL10.alSourcei(soundtrackSource.get(0), AL10.AL_BUFFER, buffer.get(0));
		    AL10.alSourcef(soundtrackSource.get(0), AL10.AL_PITCH, 1.0f);
		    AL10.alSourcef(soundtrackSource.get(0), AL10.AL_GAIN, 1.0f);
		    AL10.alSource (soundtrackSource.get(0), AL10.AL_POSITION, sourcePos);
		    AL10.alSource (soundtrackSource.get(0), AL10.AL_VELOCITY, sourceVel);
		    AL10.alSourcei(soundtrackSource.get(0), AL10.AL_LOOPING,  AL10.AL_TRUE);
		    AL10.alListener(AL10.AL_POSITION,    listenerPos);
		    AL10.alListener(AL10.AL_VELOCITY,    listenerVel);
		    AL10.alListener(AL10.AL_ORIENTATION, listenerOri);
			AL10.alSourcePlay(soundtrackSource.get(0));
			
		} catch ( Exception e) 
		{
			e.printStackTrace();
			System.out.println("Audio failed to init... update your sound drivers?");
		}
	}
	
	public static Audio getSoundtrack()
	{
		return soundtrack;
	}
	
	public static AirMeter getAirMeter()
	{
		return airMeter;
	}
	
	public static void setMusicVolume(float value)
	{
		 AL10.alSourcef(soundtrackSource.get(0), AL10.AL_GAIN, 1.0f-value);
	}
	
	
	public static void main(String[] args) throws LWJGLException, IOException {
			
		Renderer.init(800, 600, "/tsa2035/game/content/images/common/icon");
		Renderer.setWindowTitle("Sputnik");
		airMeter = new AirMeter(0.85f, 0.83f, new AnimatedTexture("/tsa2035/game/content/images/airmeter", "airmeter", 54, 1), 120000);
		airMeter.setScale(0.7f);
		
		startSoundtrack();	
		Renderer.renderLoop(new LoadingScreen());
	}

}

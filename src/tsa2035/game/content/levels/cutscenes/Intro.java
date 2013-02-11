package tsa2035.game.content.levels.cutscenes;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.lwjgl.openal.AL10;

import tsa2035.game.content.core.Game;
import tsa2035.game.content.levels.Level4;
import tsa2035.game.engine.core.Renderer;
import tsa2035.game.engine.scene.Scene;
import tsa2035.game.engine.scene.Sprite;
import tsa2035.game.engine.texture.AnimatedTexture;
import tsa2035.game.engine.texture.AnimationFinishedCallback;

public class Intro extends Scene implements AnimationFinishedCallback {
	public Intro()
	{
		AL10.alSourceStop(Game.soundtrackSource.get(0));
		try {
			AnimatedTexture video = new AnimatedTexture("/tsa2035/game/content/images/introanimation", "animation", 205, 15);
			video.registerFinishedCallback(this);
			video.fire();
			addToScene("video", new Sprite(0,0,video));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void sceneLogic() {
		
	}
	
	@Override
	public void animationFinished(AnimatedTexture animation) {
		AL10.alSourcePlay(Game.soundtrackSource.get(0));
		
		Game.getAirMeter().reset();
		Game.getAirMeter().start();
		Renderer.animatedSceneSwitch(new Level4());
	}
	
	public void cleanUp()
	{
		getObject("video").getTexture().unload();
	}
}

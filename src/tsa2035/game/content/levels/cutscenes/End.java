package tsa2035.game.content.levels.cutscenes;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import tsa2035.game.content.core.Menu;
import tsa2035.game.engine.core.Renderer;
import tsa2035.game.engine.scene.Scene;
import tsa2035.game.engine.scene.Sprite;
import tsa2035.game.engine.texture.TextureManager;

public class End extends Scene {

	Timer endTime = new Timer(); 
	boolean isFinished = false;
	public void loadScene()
	{
		try {
			addToScene("message", new Sprite(0,0, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/completion.png")));
			
			endTime.schedule(new TimerTask(){

				@Override
				public void run() {
				
					isFinished = true;
				}}
			, 5000);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void sceneLogic() {
		if ( isFinished )
		{
			Renderer.animatedSceneSwitch(new Menu());
			isFinished = false;
		}
	}

}

package tsa2035.game.content.levels.cutscenes;

import java.io.IOException;

import tsa2035.game.content.core.Menu;
import tsa2035.game.engine.core.Renderer;
import tsa2035.game.engine.scene.Scene;
import tsa2035.game.engine.scene.Sprite;
import tsa2035.game.engine.texture.TextureManager;

public class LoadingScreen extends Scene {
	boolean doneLoading = false;
	boolean isFirstLoop = true;
	public LoadingScreen()
	{
		try {
			addToScene("img", new Sprite(0,0,TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/loadingImg.png")));
		} catch (IOException e) {
			System.out.println("Failed to load textures");
			e.printStackTrace();
		}
	}

	@Override
	public void sceneLogic() {
		if ( isFirstLoop )
		{
			Renderer.skipAnimation();
		}
		
		if ( !doneLoading && !isFirstLoop )
		{
			try {
				//new AnimatedTexture("/tsa2035/game/content/images/introanimation", "animation", 205, 15);
			} catch (Exception e) {
				System.out.println("Failed to preload animation");
				e.printStackTrace();
			}
			doneLoading = true;
			Renderer.animatedSceneSwitch(new Menu());
		}
		isFirstLoop = false;
	}
}

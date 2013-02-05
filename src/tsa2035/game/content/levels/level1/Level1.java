package tsa2035.game.content.levels.level1;

import java.io.IOException;

import tsa2035.game.content.levels.MainCharacter;
import tsa2035.game.engine.bounding.Side;
import tsa2035.game.engine.core.Renderer;
import tsa2035.game.engine.scene.CollisionCallback;
import tsa2035.game.engine.scene.InteractionCallback;
import tsa2035.game.engine.scene.Ladder;
import tsa2035.game.engine.scene.PolyTexSprite;
import tsa2035.game.engine.scene.Scene;
import tsa2035.game.engine.scene.Sprite;
import tsa2035.game.engine.scene.background.SpriteBackground;
import tsa2035.game.engine.texture.LoopedAnimatedTexture;
import tsa2035.game.engine.texture.TextureManager;

public class Level1 extends Scene {
	
	public Level1()
	{
		try {
			setBackground(new SpriteBackground(TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/wallpanels.png")));
			addToScene("character", new MainCharacter(-0.7f, -0.5f)).setLayer(10);
			addToScene("floor", new Sprite(0f, -0.98f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/floor.png"))).setSolid(true);
			addToScene("pipes", new Sprite(0f, 0.89f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/pipes.png"))).setSolid(true).setLayer(11);
			addToScene("vents", new Sprite(0f, 0.7f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/vents.png"))).setSolid(true).setLayer(10);
			addToScene("wasd", new Sprite(-0.7f, 0.1f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/wasd.png")));
			addToScene("ladder", new Ladder(0.45f, -0.46f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/ladder_long.png"), "character", "platform")).setInteractable(true);
			addToScene("platform", new Sprite(0.6f, 0f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/platform200.png"))).setSolid(true);
			addToScene("steps", new Sprite(-0.5f, -0.62f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/steps.png"))).setScale(0.8f);
			
			addToScene("door", new Sprite(0.65f, 0.368f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/door.png"))).setInteractable(true);
			getObject("door").registerInteractionCallback(new InteractionCallback()
			{

				@Override
				public void interactionOccured(Sprite registeredObject,
						Sprite withObject) {
					Renderer.animatedSceneSwitch(new Level2());
					
				}
				
			});
			
		} catch (IOException e) {
			System.out.println("Texture loading failed!");
			e.printStackTrace();
		}
	}
	
	@Override
	public void sceneLogic() {
		// This function is called every render loop
		// Note: callbacks are the prefered way to do collision/interaction checking, not polling
	}

}

package tsa2035.game.content.levels.level1;

import java.io.IOException;

import tsa2035.game.content.levels.MainCharacter;
import tsa2035.game.engine.core.Renderer;
import tsa2035.game.engine.scene.InteractionCallback;
import tsa2035.game.engine.scene.Scene;
import tsa2035.game.engine.scene.Sprite;
import tsa2035.game.engine.scene.background.SpriteBackground;
import tsa2035.game.engine.texture.TextureManager;

public class Level2 extends Scene {
	
	public Level2()
	{
		try {
			setBackground(new SpriteBackground(TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/wallpanels.png")));
			addToScene("character", new MainCharacter(-0.85f, -0.5f)).setLayer(10);
			addToScene("floor", new Sprite(0f, -0.98f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/floor.png"))).setSolid(true);
			addToScene("pipes", new Sprite(0f, 0.89f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/pipes.png"))).setSolid(true).setLayer(0);
			addToScene("vents", new Sprite(0f, 0.7f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/vents.png"))).setSolid(true).setLayer(-1);
			addToScene("space", new Sprite(-0.75f, 0.4f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/space.png")));
			addToScene("door", new Sprite(0.75f, -0.58f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/door.png"))).setLayer(-2).setInteractable(true);
			addToScene("crate1", new Sprite(0f, -0.85f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/crate.png"))).setScale(0.5f).setSolid(true);
			addToScene("crate2", new Sprite(-0.2f, -0.85f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/crate.png"))).setScale(0.5f).setSolid(true);
			addToScene("crate3", new Sprite(-0.4f, -0.85f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/crate.png"))).setScale(0.5f).setSolid(true);
			addToScene("crate4", new Sprite(-0.6f, -0.85f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/crate.png"))).setScale(0.5f).setSolid(true);
			addToScene("crate5", new Sprite(0.2f, -0.85f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/crate.png"))).setScale(0.5f).setSolid(true);
			addToScene("crate6", new Sprite(0.4f, -0.85f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/crate.png"))).setScale(0.5f).setSolid(true);
			addToScene("crate7", new Sprite(-0.3f, -0.645f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/crate.png"))).setScale(0.5f).setSolid(true);
			addToScene("crate8", new Sprite(0.1f, -0.645f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/crate.png"))).setScale(0.5f).setSolid(true);
			addToScene("crate9", new Sprite(-0.3f, -0.645f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/crate.png"))).setScale(0.5f).setSolid(true);
			addToScene("crate9", new Sprite(-0.5f, -0.645f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/crate.png"))).setScale(0.5f).setSolid(true);
			addToScene("crate10", new Sprite(0f, -0.43f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/crate.png"))).setScale(0.5f).setSolid(true);
			//addToScene("crate11", new Sprite(-0.2f, -0.43f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/crate.png"))).setScale(0.5f).setSolid(true);
			addToScene("crate12", new Sprite(-0.4f, -0.43f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/crate.png"))).setScale(0.5f).setSolid(true);
			addToScene("crate13", new Sprite(-0.39f, -0.22f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/crate.png"))).setScale(0.5f).setSolid(true);
			addToScene("crate14", new Sprite(0.15f, -0.22f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/crate.png"))).setScale(0.5f).setSolid(true);
			addToScene("crate15", new Sprite(0.3f, -0.645f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/crate.png"))).setScale(0.5f).setSolid(true);
			addToScene("crate16", new Sprite(0.2f, -0.43f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/crate.png"))).setScale(0.5f).setSolid(true);
			addToScene("crate17", new Sprite(-0.1f, -0.645f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/crate.png"))).setScale(0.5f).setSolid(true);
			addToScene("gosign", new Sprite(-0.1f, 0.3f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/go.png"))).setScale(0.8f);

			getObject("door").registerInteractionCallback(new InteractionCallback()
			{

				@Override
				public void interactionOccured(Sprite registeredObject,
						Sprite withObject) {
					Renderer.animatedSceneSwitch(new Level3());
					
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

package tsa2035.game.content.levels.level1;

import java.io.IOException;

import tsa2035.game.content.levels.MainCharacter;
import tsa2035.game.engine.bounding.Side;
import tsa2035.game.engine.core.Renderer;
import tsa2035.game.engine.scene.CollisionCallback;
import tsa2035.game.engine.scene.InteractionCallback;
import tsa2035.game.engine.scene.PolyTexSprite;
import tsa2035.game.engine.scene.Scene;
import tsa2035.game.engine.scene.Sprite;
import tsa2035.game.engine.scene.background.SpriteBackground;
import tsa2035.game.engine.texture.LoopedAnimatedTexture;
import tsa2035.game.engine.texture.TextureManager;

public class Level3 extends Scene {
	
	public Level3()
	{
		try {
			setBackground(new SpriteBackground(TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/wallpanels.png")));
			addToScene("character", new MainCharacter(-0.7f, -0.5f)).setLayer(10);
			addToScene("floor", new Sprite(0f, -0.98f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/floor.png"))).setSolid(true);
			addToScene("pipes", new Sprite(0f, 0.89f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/pipes.png"))).setSolid(true).setLayer(0);
			addToScene("vents", new Sprite(0f, 0.7f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/vents.png"))).setSolid(true).setLayer(-1);
			addToScene("interact", new Sprite(-0.7f, 0.2f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/interact.png")));
			addToScene("door", new Sprite(0.75f, -0.58f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/door.png"))).setLayer(-2).setInteractable(true);
			
			getObject("door").registerInteractionCallback(new InteractionCallback()
			{

				@Override
				public void interactionOccured(Sprite registeredObject,
						Sprite withObject) {
					Renderer.animatedSceneSwitch(new Level3());
					
				}
				
			});
			
			final PolyTexSprite box = new PolyTexSprite(-0.3f,-0.4f, "closed", TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/accessdoor_all_closed.png"), false);
			addToScene("auxbox", box).setScale(0.5f).setInteractable(true);
			box.addTexture("open", TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/accessdoor_auxpwr_open.png"));
			box.registerInteractionCallback(new InteractionCallback(){
			@Override
			public void interactionOccured(Sprite interacter,
			Sprite interactee) 
			{
			box.setTexture("open");}});

			
			final PolyTexSprite main = new PolyTexSprite(0.15f,-0.4f, "closed", TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/accessdoor_all_closed.png"), false);
			addToScene("auxbox", main).setScale(0.5f).setInteractable(true);
			main.addTexture("open", TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/accessdoor_mainpwr_open.png"));
			main.registerInteractionCallback(new InteractionCallback(){
			@Override
			public void interactionOccured(Sprite interacter,
			Sprite interactee) 
			{
			main.setTexture("open");}});
			
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

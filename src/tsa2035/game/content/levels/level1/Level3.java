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
import tsa2035.game.engine.texture.AnimatedTexture;
import tsa2035.game.engine.texture.AnimationFinishedCallback;
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
			addToScene("interact", new Sprite(-0.75f, 0.4f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/interact.png")));
			addToScene("door", new Sprite(0.75f, -0.58f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/door.png"))).setLayer(-2).setInteractable(true);
			addToScene("noentry", new Sprite(0.75f, -0.1f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/noentry.png"))).setScale(0.8f);
			addToScene("gosign", new Sprite(-0.559f, 0f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/go.png"))).setScale(0.8f);
						
			getObject("door").registerInteractionCallback(new InteractionCallback()
			{

				@Override
				public void interactionOccured(Sprite registeredObject,
						Sprite withObject) {
					Renderer.animatedSceneSwitch(new Level4());
					
				}
				
			});
			
			final PolyTexSprite aux = new PolyTexSprite(-0.25f,-0.4f, "closed", TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/accessdoor_all_closed.png"), false);
			addToScene("auxbox", aux).setScale(0.5f).setInteractable(true);
			aux.addTexture("open", TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/accessdoor_auxpwr_open.png"));
			aux.registerInteractionCallback(new InteractionCallback(){
			@Override
			public void interactionOccured(Sprite interacter,
			Sprite interactee) 
			{
			aux.setTexture("open");}});

			final PolyTexSprite main = new PolyTexSprite(-0.75f,-0.4f, "closed", TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/accessdoor_all_closed.png"), false);
			addToScene("mainbox", main).setScale(0.5f).setInteractable(true);
			main.addTexture("open", TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/accessdoor_mainpwr_open.png"));
			main.registerInteractionCallback(new InteractionCallback(){
			@Override
			public void interactionOccured(Sprite interacter,
			Sprite interactee) 
			{
			main.setTexture("open");}});
			
			final PolyTexSprite switchbox = new PolyTexSprite(0.15f,-0.45f, "off", TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/switch_off.png"), false);
			addToScene("switch", switchbox).setScale(0.5f).setInteractable(true);
			switchbox.addTexture("on", TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/switch_on.png"));
			switchbox.registerInteractionCallback(new InteractionCallback(){
			@Override
			public void interactionOccured(Sprite interacter,
			Sprite interactee) 
			{
			switchbox.setTexture("on");
				((AnimatedTexture)getObject("gate").getTexture()).fire();
			}});
			
			
			addToScene("gate", new Sprite(0.45f, -0.68f, new AnimatedTexture("/tsa2035/game/content/images/gate", "gate", 35, 12))).setSolid(true);
			((AnimatedTexture)getObject("gate").getTexture()).registerFinishedCallback(new AnimationFinishedCallback(){

				@Override
				public void animationFinished(AnimatedTexture animation) {
					getObject("gate").setSolid(false);
					
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

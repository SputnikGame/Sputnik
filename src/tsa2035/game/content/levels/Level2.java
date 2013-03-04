package tsa2035.game.content.levels;

import java.io.IOException;

import tsa2035.game.content.core.Game;
import tsa2035.game.engine.core.Renderer;
import tsa2035.game.engine.scene.FinishedCallback;
import tsa2035.game.engine.scene.InteractionCallback;
import tsa2035.game.engine.scene.Ladder;
import tsa2035.game.engine.scene.PolyTexSprite;
import tsa2035.game.engine.scene.Scene;
import tsa2035.game.engine.scene.Sprite;
import tsa2035.game.engine.scene.TimerObject;
import tsa2035.game.engine.scene.background.SpriteBackground;
import tsa2035.game.engine.texture.AnimatedTexture;
import tsa2035.game.engine.texture.AnimationFinishedCallback;
import tsa2035.game.engine.texture.TextureManager;

public class Level2 extends Scene {
	
	public Level2()
	{
		try {
			setBackground(new SpriteBackground(TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/wallpanels.png")));
			
			addToScene("airmeter", Game.getAirMeter()).setLayer(99);
			
			Game.getAirMeter().setCurrentCallback(new FinishedCallback(){
				@Override
				public void timerFinished(TimerObject parent) {
					Renderer.animatedSceneSwitch(new Level1());
					Game.getAirMeter().reset();
					Game.getAirMeter().start();
				}
			});
			
			addToScene("character", new MainCharacter(-0.45f, -0.6f)).setLayer(10);
			addToScene("floor", new Sprite(0f, -0.98f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/floor.png"))).setSolid(true);
			addToScene("pipes", new Sprite(0f, 0.89f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/pipes.png"))).setSolid(true).setLayer(0);
			addToScene("vents", new Sprite(0f, 0.7f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/vents.png"))).setLayer(-1);
			addToScene("door", new Sprite(0.75f, -0.58f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/door.png"))).setLayer(-2).setInteractable(true);
			
			addToScene("gosign", new Sprite(-0.1f, -0.5f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/go.png"))).setScale(0.8f);
			addToScene("platform1", new Sprite(-0.64f, 0f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/platform250.png"))).setSolid(true);
			addToScene("ladder1", new Ladder(-0.85f, -0.46f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/ladder_long.png"), "character", "platform1")).setInteractable(true);
			addToScene("ladder3", new Ladder(-0.4f, -0.1f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/ladder_extrashort.png"), "character", "platform1")).setInteractable(true);
			addToScene("platform2", new Sprite(0.01f, -0.23f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/platform400.png"))).setSolid(true).setLayer(-10);
			addToScene("ladder2", new Ladder(0.42f, -0.02f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/ladder_short.png"), "character", "platform3")).setInteractable(true);
			addToScene("platform3", new Sprite(0.725f, 0.15f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/platform300.png"))).setSolid(true).setLayer(-10);

			final PolyTexSprite switchbox = new PolyTexSprite(0.93f, 0.4f, "off", TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/switch_off.png"), false);
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
			
			addToScene("gate", new Sprite(0.3f, -0.68f,  new AnimatedTexture("/tsa2035/game/content/images/gate", "gate", 35, 12))).setSolid(true);
			((AnimatedTexture)getObject("gate").getTexture()).registerFinishedCallback(new AnimationFinishedCallback(){

				@Override
				public void animationFinished(AnimatedTexture animation) {
					getObject("gate").setSolid(false);
					
				}
			});
			
			
			
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
	}

}

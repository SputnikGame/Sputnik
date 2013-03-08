package tsa2035.game.content.levels;

import java.io.IOException;

import tsa2035.game.content.core.Game;
import tsa2035.game.engine.core.Renderer;
import tsa2035.game.engine.scene.FinishedCallback;
import tsa2035.game.engine.scene.InteractionCallback;
import tsa2035.game.engine.scene.PolyTexSprite;
import tsa2035.game.engine.scene.Scene;
import tsa2035.game.engine.scene.Sprite;
import tsa2035.game.engine.scene.TimerObject;
import tsa2035.game.engine.scene.background.SpriteBackground;
import tsa2035.game.engine.texture.AnimatedTexture;
import tsa2035.game.engine.texture.AnimationFinishedCallback;
import tsa2035.game.engine.texture.TextureManager;

public class Level4 extends Scene {
	
	public Level4()
	{
		try {
			setBackground(new SpriteBackground(TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/wallpanels.png")));
			
			addToScene("airmeter", Game.getAirMeter()).setLayer(99);
			
			Game.getAirMeter().setCurrentCallback(new FinishedCallback(){
				@Override
				public void timerFinished(TimerObject parent) {
					Renderer.animatedSceneSwitch(new Level3());
					Game.getAirMeter().reset();
					Game.getAirMeter().start();
				}
			});
			
			
			addToScene("character", new MainCharacter(-0.2f, 0.6f)).setLayer(10);
			addToScene("floor", new Sprite(0f, -0.98f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/floor.png"))).setSolid(true);
			addToScene("pipes", new Sprite(0f, 0.89f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/pipes.png"))).setLayer(2);
			addToScene("vents", new Sprite(0f, 0.7f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/vents.png"))).setLayer(2);
			addToScene("door", new Sprite(-0.8f, 0.5f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/door.png"))).setInteractable(true);
			
			addToScene("platform", new Sprite(-0.48f, 0.15f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/platform400.png"))).setSolid(true);
			
			addToScene("stack1crate1", new Sprite(-0.89f, -0.92f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/halfcrate.png"))).setScale(0.5f).setSolid(true);
			addToScene("stack1crate2", new Sprite(-0.67f, -0.92f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/halfcrate.png"))).setScale(0.5f).setSolid(true);
			addToScene("stack1crate3", new Sprite(-0.8f, -0.76f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/crate.png"))).setScale(0.5f).setSolid(true);
			addToScene("stack2crate1", new Sprite(-0.4f, -0.67f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/crate.png"))).setScale(0.5f).setSolid(true);
			addToScene("stack2crate2", new Sprite(-0.41f, -0.88f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/crate.png"))).setScale(0.5f).setSolid(true);
			addToScene("stack3crate1", new Sprite(0.37f, -0.57f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/crate.png"))).setScale(0.5f).setSolid(true);
			addToScene("stack3crate2", new Sprite(0.4f, -0.78f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/crate.png"))).setScale(0.5f).setSolid(true);
			addToScene("stack3crate3", new Sprite(0.39f, -0.94f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/halfcrate.png"))).setScale(0.5f).setSolid(true);
			addToScene("stack4crate1", new Sprite(0.68f, -0.058f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/crate.png"))).setScale(0.5f).setSolid(true);
			addToScene("stack4crate2", new Sprite(0.69f, -0.25f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/crate.png"))).setScale(0.5f).setSolid(true);
			addToScene("stack4crate3", new Sprite(0.68f, -0.46f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/crate.png"))).setScale(0.5f).setSolid(true);
			addToScene("stack4crate4", new Sprite(0.665f, -0.67f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/crate.png"))).setScale(0.5f).setSolid(true);
			addToScene("stack4crate5", new Sprite(0.68f, -0.88f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/crate.png"))).setScale(0.5f).setSolid(true);
			addToScene("stack5crate1", new Sprite(0.86f, -0.25f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/crate.png"))).setScale(0.5f).setSolid(true);
			addToScene("stack5crate2", new Sprite(0.89f, -0.46f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/crate.png"))).setScale(0.5f).setSolid(true);
			addToScene("stack5crate3", new Sprite(0.87f, -0.67f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/crate.png"))).setScale(0.5f).setSolid(true);
			addToScene("stack5crate4", new Sprite(0.89f, -0.88f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/crate.png"))).setScale(0.5f).setSolid(true);
			
			final PolyTexSprite switchbox = new PolyTexSprite(-0.8f,-0.0f, "off", TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/switch_off.png"), false);
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
			
			addToScene("gate", new Sprite(-0.52f, 0.425f, new AnimatedTexture("/tsa2035/game/content/images/gate", "gate", 35, 12))).setSolid(true);
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
					Renderer.animatedSceneSwitch(new Level5());
					
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

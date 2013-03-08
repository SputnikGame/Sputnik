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
import tsa2035.game.engine.texture.TextureManager;

public class Level3 extends Scene {
	
	public Level3()
	{
		try {
			setBackground(new SpriteBackground(TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/wallpanels.png")));
			
			addToScene("airmeter", Game.getAirMeter()).setLayer(99);
			
			Game.getAirMeter().setCurrentCallback(new FinishedCallback(){
				@Override
				public void timerFinished(TimerObject parent) {
					Renderer.animatedSceneSwitch(new Level2());
					Game.getAirMeter().reset();
					Game.getAirMeter().start();
				}
			});
			
			
			addToScene("character", new MainCharacter(0.65f, -0.6f)).setLayer(10);
			
			addToScene("floor", new Sprite(0f, -0.98f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/floor.png"))).setSolid(true);
			addToScene("pipes", new Sprite(0f, 0.89f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/pipes.png"))).setLayer(2);
			addToScene("vents", new Sprite(0f, 0.7f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/vents.png"))).setLayer(2);
			addToScene("door", new PolyTexSprite(-0.78f, -0.58f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/doororiginal.png"))).setInteractable(false);
			((PolyTexSprite)getObject("door")).addTexture("active", TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/door.png"));
			
			
			addToScene("gosign", new Sprite(0f, 0.35f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/goleft.png"))).setScale(0.8f);
			addToScene("wallpipes", new Sprite(0f, 0f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/wallpipes.png"))).setLayer(1);
		
			final PolyTexSprite odist = new PolyTexSprite(0.498f,-0.4f, "closed", TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/accessdoor_dist_closed.png"), false);
			final PolyTexSprite auxpwr = new PolyTexSprite(0.15f,-0.4f, "closed", TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/accessdoor_all_closed.png"), false);			
			final PolyTexSprite mainpwr = new PolyTexSprite(-0.2f,-0.4f, "closed", TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/accessdoor_all_closed.png"), false);
			
			addToScene("mainpower", mainpwr).setScale(0.5f).setInteractable(true);
			mainpwr.addTexture("open", TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/accessdoor_mainpwr_open.png"));
			mainpwr.registerInteractionCallback(new InteractionCallback(){
			@Override
				public void interactionOccured(Sprite interacter, Sprite interactee) 
				{
					mainpwr.setTexture("open");
					if ( mainpwr.getTextureName().equals("open") && auxpwr.getTextureName().equals("open") && odist.getTextureName().equals("open") )
					{
						odist.setTexture("openrdy");
						getObject("door").setInteractable(true);
						((PolyTexSprite)getObject("door")).setTexture("active");
					}
				}
			});
			
			
			addToScene("auxpower", auxpwr).setScale(0.5f).setInteractable(true);
			auxpwr.addTexture("open", TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/accessdoor_auxpwr_open.png"));
			auxpwr.registerInteractionCallback(new InteractionCallback(){
			@Override
			public void interactionOccured(Sprite interacter,
				Sprite interactee) 
				{
					auxpwr.setTexture("open");
					if ( mainpwr.getTextureName().equals("open") && auxpwr.getTextureName().equals("open") && odist.getTextureName().equals("open") )
					{
						odist.setTexture("openrdy");
						getObject("door").setInteractable(true);
						((PolyTexSprite)getObject("door")).setTexture("active");
					}
				}
			});

		addToScene("oxygendistributor", odist).setScale(0.5f).setInteractable(true);
			odist.addTexture("open", TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/accessdoor_dist_open_nready.png"));
			odist.addTexture("openrdy", TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/accessdoor_dist_open_ready.png"));
			odist.registerInteractionCallback(new InteractionCallback(){
			@Override
			public void interactionOccured(Sprite interacter,
			Sprite interactee) 
			{
				odist.setTexture("open");
				if ( mainpwr.getTextureName().equals("open") && auxpwr.getTextureName().equals("open") && odist.getTextureName().equals("open") )
				{
					odist.setTexture("openrdy");
					getObject("door").setInteractable(true);
					((PolyTexSprite)getObject("door")).setTexture("active");
				}
			}});
			
			getObject("door").registerInteractionCallback(new InteractionCallback()
			{

				@Override
				public void interactionOccured(Sprite registeredObject,
						Sprite withObject) {
					Renderer.animatedSceneSwitch(new Level4());
					
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

package tsa2035.game.content.levels;

import java.io.IOException;

import tsa2035.game.engine.scene.InteractionCallback;
import tsa2035.game.engine.scene.Ladder;
import tsa2035.game.engine.scene.PolyTexSprite;
import tsa2035.game.engine.scene.Scene;
import tsa2035.game.engine.scene.Sprite;
import tsa2035.game.engine.scene.background.SpriteBackground;
import tsa2035.game.engine.texture.LoopedAnimatedTexture;
import tsa2035.game.engine.texture.TextureManager;

public class DemoLevel extends Scene {
	
	public DemoLevel()
	{
		try {
			setBackground(new SpriteBackground(TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/wallpanels.png")));
			addToScene("character", new MainCharacter(-0.7f, 0.15f)).setLayer(10);
			addToScene("floor", new Sprite(0f, -0.98f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/floor.png"))).setSolid(true);
			addToScene("ladder1", new Ladder(-0.55f, -0.64f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/ladder_medium.png"), "character", "platform1")).setInteractable(true);
			addToScene("ladder2", new Ladder(0.45f, -0.46f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/ladder_long.png"), "character", "platform2")).setInteractable(true);
			addToScene("pipes", new Sprite(0f, 0.89f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/pipes.png"))).setSolid(true).setLayer(0);
			addToScene("vents", new Sprite(0f, 0.7f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/vents.png"))).setSolid(true).setLayer(-1);
			addToScene("door", new Sprite(0.75f, 0.315f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/door.png"))).setLayer(1);
			addToScene("platform1", new Sprite(-0.7f, -0.34f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/platform200.png"))).setSolid(true);
			addToScene("platform2", new Sprite(0.645f, 0f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/platform250.png"))).setSolid(true);
			addToScene("crate", new Sprite(-0.75f, -0.85f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/crate.png"))).setScale(0.5f);
			addToScene("statuspanel", new Sprite(-0.75f, 0.2f, new LoopedAnimatedTexture("/tsa2035/game/content/images/common", "statuspanel", 2, 2))).setScale(0.5f);
		
			final PolyTexSprite box = new PolyTexSprite(0.15f,-0.4f, "closed", TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/accessdoor_all_closed.png"), false);
			addToScene("auxbox", box).setScale(0.5f).setInteractable(true);
			box.addTexture("open", TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/accessdoor_auxpwr_open.png"));
			box.registerInteractionCallback(new InteractionCallback(){
			@Override
			public void interactionOccured(Sprite interacter,
			Sprite interactee) 
			{
			box.setTexture("open");}});

			final PolyTexSprite main = new PolyTexSprite(-0.15f,-0.4f, "closed", TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/accessdoor_all_closed.png"), false);
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

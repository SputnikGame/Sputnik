package tsa2035.game.content.levels.level1;

import java.awt.Color;
import java.io.IOException;

import tsa2035.game.content.levels.MainCharacter;
import tsa2035.game.engine.scene.InteractionCallback;
import tsa2035.game.engine.scene.Scene;
import tsa2035.game.engine.scene.Sprite;
import tsa2035.game.engine.scene.background.SolidBackground;
import tsa2035.game.engine.texture.TextureManager;

public class Level1 extends Scene {
	
	public Level1()
	{
		try {
			setBackground(new SolidBackground(Color.BLACK));
			addToScene("character", new MainCharacter(0,0));
			addToScene("floor", new Sprite(0f, -0.5f, TextureManager.getTextureFromResource("/tsa2035/game/content/levels/level1/floor.png"))).setSolid(true);
			addToScene("block", new Sprite(0.5f,-0.33f,TextureManager.getTextureFromResource("/tsa2035/game/content/levels/Character.png"))).setInteractable(true);
		} catch (IOException e) {
			System.out.println("Texture loading failed!");
			e.printStackTrace();
		}
		
		// Press E while contacting
		getObject("block").registerInteractionCallback(new InteractionCallback(){

			@Override
			public void interactionOccured(Sprite interacter, Sprite interactee) {
				System.out.println("Interaction!");
				getObject("block").setHidden(true);
			}
			
		});
	}
	
	@Override
	public void sceneLogic() {
		// This function is called every render loop
		// Note: callbacks are the prefered way to do collision/interaction checking, not polling
	}

}

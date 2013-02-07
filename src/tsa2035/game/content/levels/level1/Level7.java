package tsa2035.game.content.levels.level1;

import java.io.IOException;

import tsa2035.game.content.levels.MainCharacter;
import tsa2035.game.content.levels.meta.Status1;
import tsa2035.game.content.levels.meta.Status2;
import tsa2035.game.content.levels.puzzles.Puzzle1;
import tsa2035.game.engine.core.Renderer;
import tsa2035.game.engine.scene.InteractionCallback;
import tsa2035.game.engine.scene.PolyTexSprite;
import tsa2035.game.engine.scene.Scene;
import tsa2035.game.engine.scene.Sprite;
import tsa2035.game.engine.scene.background.SpriteBackground;
import tsa2035.game.engine.texture.LoopedAnimatedTexture;
import tsa2035.game.engine.texture.TextureManager;

public class Level7 extends Scene {
	
	Puzzle1 puzzle = new Puzzle1(this);
	
	public Level7()
	{
		try {
			setBackground(new SpriteBackground(TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/wallpanels.png")));
			addToScene("character", new MainCharacter(-0.75f, -0.6f)).setLayer(10);
			addToScene("floor", new Sprite(0f, -0.98f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/floor.png"))).setSolid(true);
			addToScene("pipes", new Sprite(0f, 0.89f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/pipes.png"))).setLayer(2);
			addToScene("vents", new Sprite(0f, 0.7f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/vents.png"))).setLayer(2);
			addToScene("door", new PolyTexSprite(0.75f, -0.58f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/doororiginal.png"))).setLayer(-2).setInteractable(false);
			((PolyTexSprite)getObject("door")).addTexture("ready", TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/door.png"));
			
			addToScene("gosign", new Sprite(0f, 0.35f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/go.png"))).setScale(0.8f);
			addToScene("wallpipes", new Sprite(0f, 0f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/wallpipes2.png"))).setLayer(1);
			addToScene("ogenerator", new Sprite(-0.48f, -0.5f, TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/oxygengenerator.png")));
			addToScene("statuspanel", new Sprite(-0.88f, -0.45f, new LoopedAnimatedTexture("/tsa2035/game/content/images/common", "statuspanel", 2, 2))).setScale(0.5f).setInteractable(true);

			final PolyTexSprite regen = new PolyTexSprite(0.1f,-0.42f, "closed", TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/accessdoor_all_closed.png"), false);
			addToScene("regenpanel", regen).setScale(0.5f).setInteractable(true);
			regen.addTexture("open", TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/accessdoor_regen_open.png"));
			regen.addTexture("solved", TextureManager.getTextureFromResource("/tsa2035/game/content/images/common/accessdoor_regen_open_notext.png"));
			regen.registerInteractionCallback(new InteractionCallback(){
			@Override
			public void interactionOccured(Sprite interacter,
			Sprite interactee) 
			{
				if ( regen.getTextureName().equals("open") && !puzzle.isSolved() )
				{
					Renderer.animatedSceneSwitch(puzzle);
				}
				else
					regen.setTexture("open");
			}});
			
			getObject("door").registerInteractionCallback(new InteractionCallback()
			{

				@Override
				public void interactionOccured(Sprite registeredObject,
						Sprite withObject) {
					Renderer.animatedSceneSwitch(new Level8());
					
				}
				
			});
			
			getObject("statuspanel").registerInteractionCallback(new InteractionCallback(){

				@Override
				public void interactionOccured(Sprite interacter,
						Sprite interactee) {
					if ( puzzle.isSolved() )
					{
						((PolyTexSprite)getObject("door")).setTexture("ready");
						((PolyTexSprite)getObject("door")).setInteractable(true);
						Renderer.animatedSceneSwitch(new Status2(Level7.this));
					}
					else
						Renderer.animatedSceneSwitch(new Status1(Level7.this));
					
				}});
			
		} catch (IOException e) {
			System.out.println("Texture loading failed!");
			e.printStackTrace();
		}
	}
	
	@Override
	public void sceneLogic() {
		if ( puzzle.isSolved()) 
			((PolyTexSprite)getObject("regenpanel")).setTexture("solved");
	}

}

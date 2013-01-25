package tsa2035.game.content.levels;

import java.io.IOException;

import tsa2035.game.engine.scene.Player;
import tsa2035.game.engine.texture.Texture;
import tsa2035.game.engine.texture.TextureManager;

public class MainCharacter extends Player {

	public MainCharacter(float x, float y) throws IOException {
		super(x, y, TextureManager.getTextureFromResource("/tsa2035/game/content/levels/Character.png"), true);
	}
	
}

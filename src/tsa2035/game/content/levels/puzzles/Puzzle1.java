package tsa2035.game.content.levels.puzzles;

import java.awt.Color;
import java.io.IOException;

import org.lwjgl.input.Keyboard;

import tsa2035.game.engine.core.Renderer;
import tsa2035.game.engine.scene.PolyTexSprite;
import tsa2035.game.engine.scene.Scene;
import tsa2035.game.engine.scene.SinglePressKeyboard;
import tsa2035.game.engine.scene.Sprite;
import tsa2035.game.engine.scene.background.SolidBackground;
import tsa2035.game.engine.texture.TextureManager;

public class Puzzle1 extends Scene {
	PolyTexSprite parts[] = new PolyTexSprite[4];
	int angles[] = new int[4];
	SolidBackground RedBG = new SolidBackground(Color.WHITE);
	SolidBackground GreenBG = new SolidBackground(Color.GREEN);
	
	SinglePressKeyboard keyZ = new SinglePressKeyboard(Keyboard.KEY_Z);
	SinglePressKeyboard keyX = new SinglePressKeyboard(Keyboard.KEY_X);
	SinglePressKeyboard keyC = new SinglePressKeyboard(Keyboard.KEY_C);
	SinglePressKeyboard keyV = new SinglePressKeyboard(Keyboard.KEY_V);
	Scene parent = null;
	boolean startedFadeout = false;
	public Puzzle1(Scene parent)
	{
		this.parent = parent;
		setBackground(RedBG);
		angles[0] = 90;
		angles[1] = 270;
		angles[2] = 180;
		angles[3] = 90;
		
		
		try {
			parts[0] = new PolyTexSprite(-0.344f, 0.458f, "0", TextureManager.getTextureFromResource("/tsa2035/game/content/images/oxygenpuzzle/oxygenschematicA0.png"), false);
			parts[0].addTexture("90", TextureManager.getTextureFromResource("/tsa2035/game/content/images/oxygenpuzzle/oxygenschematicA90.png"));
			parts[0].addTexture("180", TextureManager.getTextureFromResource("/tsa2035/game/content/images/oxygenpuzzle/oxygenschematicA180.png"));
			parts[0].addTexture("270", TextureManager.getTextureFromResource("/tsa2035/game/content/images/oxygenpuzzle/oxygenschematicA270.png"));
			
			parts[1] = new PolyTexSprite(0.344f, 0.458f, "0", TextureManager.getTextureFromResource("/tsa2035/game/content/images/oxygenpuzzle/oxygenschematicB0.png"), false);
			parts[1].addTexture("90", TextureManager.getTextureFromResource("/tsa2035/game/content/images/oxygenpuzzle/oxygenschematicB90.png"));
			parts[1].addTexture("180", TextureManager.getTextureFromResource("/tsa2035/game/content/images/oxygenpuzzle/oxygenschematicB180.png"));
			parts[1].addTexture("270", TextureManager.getTextureFromResource("/tsa2035/game/content/images/oxygenpuzzle/oxygenschematicB270.png"));
			
			
			parts[2] = new PolyTexSprite(-0.344f, -0.458f, "0", TextureManager.getTextureFromResource("/tsa2035/game/content/images/oxygenpuzzle/oxygenschematicC0.png"), false);
			parts[2].addTexture("90", TextureManager.getTextureFromResource("/tsa2035/game/content/images/oxygenpuzzle/oxygenschematicC90.png"));
			parts[2].addTexture("180", TextureManager.getTextureFromResource("/tsa2035/game/content/images/oxygenpuzzle/oxygenschematicC180.png"));
			parts[2].addTexture("270", TextureManager.getTextureFromResource("/tsa2035/game/content/images/oxygenpuzzle/oxygenschematicC270.png"));
			
			parts[3] = new PolyTexSprite(0.344f, -0.458f, "0", TextureManager.getTextureFromResource("/tsa2035/game/content/images/oxygenpuzzle/oxygenschematicD0.png"), false);
			parts[3].addTexture("90", TextureManager.getTextureFromResource("/tsa2035/game/content/images/oxygenpuzzle/oxygenschematicD90.png"));
			parts[3].addTexture("180", TextureManager.getTextureFromResource("/tsa2035/game/content/images/oxygenpuzzle/oxygenschematicD180.png"));
			parts[3].addTexture("270", TextureManager.getTextureFromResource("/tsa2035/game/content/images/oxygenpuzzle/oxygenschematicD270.png"));
			
			addToScene("overlay", new Sprite(0,0,TextureManager.getTextureFromResource("/tsa2035/game/content/images/oxygenpuzzle/overlay.png"))).setLayer(5);

			for ( int i = 0; i<parts.length; i++ )
			{
				parts[i].setTexture(String.valueOf(angles[i]));
				addToScene("p"+i, parts[i]);
			}	
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getRandomAngle()
	{
		int angle = (int) Math.ceil(Math.random()*3);
		System.out.println(angle);
		int angles[] = {0,90,180,270};
		return angles[angle];
	}
	
	protected int constrain(int a)
	{
		if (a >= 360)
			return 0;
		return a;
	}
	
	@Override
	public void sceneLogic() {
		setBackground(isSolved()?GreenBG:RedBG);
		
		if ( keyZ.check() )
		{
			angles[0] += 90;
			angles[0] = constrain(angles[0]);
		}
		
		if ( keyX.check() )
		{
			angles[1] += 90;
			angles[1] = constrain(angles[1]);
		}
		
		if ( keyC.check() )
		{
			angles[2] += 90;
			angles[2] = constrain(angles[2]);
		}
		
		if ( keyV.check() )
		{
			angles[3] += 90;
			angles[3] = constrain(angles[3]);
		}
		
		for ( int i = 0; i < 4; i++ )
			parts[i].setTexture(String.valueOf(angles[i]));
		
		if ( isSolved() && !startedFadeout )
		{
			startedFadeout = true;
			Renderer.animatedSceneSwitch(parent);
		}
	}
	
	public boolean isSolved()
	{
		for ( int i = 0; i<parts.length; i++ )
		{
			if ( !((PolyTexSprite)getObject("p"+i)).getTextureName().equals("0") )
				return false;
		}
		return true;
	}

}

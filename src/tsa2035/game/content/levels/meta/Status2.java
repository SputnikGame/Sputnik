package tsa2035.game.content.levels.meta;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.lwjgl.input.Keyboard;

import tsa2035.game.engine.core.Renderer;
import tsa2035.game.engine.scene.Scene;
import tsa2035.game.engine.scene.Sprite;
import tsa2035.game.engine.texture.AnimatedTexture;

public class Status2 extends Scene {
	Scene returnTo = null;
	public Status2(Scene returnTo)
	{
		this.returnTo = returnTo;
		try {
			addToScene("status", new Sprite(0,0,new AnimatedTexture("/tsa2035/game/content/images/statusInterface/airOnline", "status", 3,1)));
			((AnimatedTexture)getObject("status").getTexture()).fire();
		} catch (FileNotFoundException e) {
			System.out.println("Texture loading failed");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Texture loading failed");
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void sceneLogic() 
	{
		if ( getInteractKey().check() )
			Renderer.animatedSceneSwitch(returnTo);
	}
	
	
}

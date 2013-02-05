package tsa2035.game.content.core;

import org.lwjgl.LWJGLException;

import tsa2035.game.content.levels.level1.DemoLevel;
import tsa2035.game.content.levels.level1.Level1;
import tsa2035.game.engine.core.Renderer;

public class Game {

	/**
	 * @param args
	 * @throws LWJGLException 
	 */
	public static void main(String[] args) throws LWJGLException {
		Renderer.init(800, 600);
		Renderer.renderLoop(new DemoLevel());
	}

}

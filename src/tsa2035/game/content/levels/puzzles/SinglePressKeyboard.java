package tsa2035.game.content.levels.puzzles;

import org.lwjgl.input.Keyboard;

public class SinglePressKeyboard {
	private int key = 0;
	private boolean wasDownLastLoop = false;
	public SinglePressKeyboard(int key)
	{
		this.key = key;
	}
	
	public boolean check()
	{
		if ( Keyboard.isKeyDown(key) )
		{
			if ( wasDownLastLoop )
				return false;
			else
			{
				wasDownLastLoop = true;
				return true;
			}
		}
		else 
			wasDownLastLoop = false;
		return false;
	}
}

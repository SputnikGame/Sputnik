package tsa2035.game.engine.scene;

import tsa2035.game.engine.texture.LoopedAnimatedTexture;
import tsa2035.game.engine.texture.MultiTexture;

public class AnimatedPlayer extends Player {

	MultiTexture multiTex = new MultiTexture();
	LoopedAnimatedTexture leftAnimation,rightAnimation;

	public AnimatedPlayer(float x, float y, LoopedAnimatedTexture leftAnimation, LoopedAnimatedTexture rightAnimation, boolean handleGravity) {
		super(x, y, null, handleGravity);
		multiTex.addTexture("left", leftAnimation);
		multiTex.addTexture("right", rightAnimation);
		multiTex.switchTo("right");
		setTexture(multiTex);
		this.handleGravity = handleGravity;
		this.rightAnimation = rightAnimation;
		this.leftAnimation = leftAnimation;
	}
	
	public AnimatedPlayer(float x, float y, LoopedAnimatedTexture leftAnimation, LoopedAnimatedTexture rightAnimation, boolean handleGravity, float jumpHeight, float jumpRate)
	{
		this(x,y, leftAnimation, rightAnimation, handleGravity);
		this.jumpHeight = jumpHeight;
		this.jumpRate = jumpRate;
	}
	
	public void setAnimations(LoopedAnimatedTexture leftAnimation, LoopedAnimatedTexture rightAnimation)
	{
		multiTex.addTexture("left", leftAnimation);
		multiTex.addTexture("right", rightAnimation);
		this.leftAnimation = leftAnimation;
		this.rightAnimation = rightAnimation;
	}
	
	public void render(Scene scene)
	{
		super.render(scene);
		if ( isWalking && !onLadder )
		{
			leftAnimation.start();
			rightAnimation.start();
		}
		else
		{
			leftAnimation.stop();
			rightAnimation.stop();
		}
		
		if ( leftWalking )
			multiTex.switchTo("left");
		else
			multiTex.switchTo("right");
		
	}
	
}

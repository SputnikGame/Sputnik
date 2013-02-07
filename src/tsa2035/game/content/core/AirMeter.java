package tsa2035.game.content.core;

import tsa2035.game.engine.scene.FinishedCallback;
import tsa2035.game.engine.scene.TimerObject;
import tsa2035.game.engine.texture.AnimatedTexture;

public class AirMeter extends TimerObject implements FinishedCallback {

	FinishedCallback currentFinishedCallback = null;
	
	public AirMeter(float x, float y, AnimatedTexture texture, long endTime) {
		super(x, y, texture, endTime);
		registerFinishedCallback(this);
	}

	@Override
	public void timerFinished(TimerObject parent) {
		if ( currentFinishedCallback != null )
			currentFinishedCallback.timerFinished(parent);
	}
	
	public void setCurrentCallback(FinishedCallback cb)
	{
		currentFinishedCallback = cb;
	}
}

package tsa2035.game.engine.texture;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class AnimatedTexture implements Texture {
	ArrayList<StaticTexture> textures = new ArrayList<StaticTexture>();
	long switchRate = 0;
	int current = 0;
	long nextSwitch = 0;
	public AnimatedTexture(String basePath, String animationName, int numberOfFrames, int fps) throws FileNotFoundException, IOException
	{
		switchRate = 1000/fps;
		nextSwitch = System.currentTimeMillis()+switchRate;
		for ( int i = 0; i < numberOfFrames; i++ )
		{
			textures.add(TextureManager.getTextureFromResource(basePath+"/"+animationName+"/"+animationName+getNumberWithLeadingZeros(i+1)+".png"));
		}
	}
	
	public String getNumberWithLeadingZeros(int num)
	{
		if ( num < 10)
			return "000"+num;
		else if ( num <= 99 )
			return "00"+num;
		else if ( num <= 999 )
			return "0"+num;
		return String.valueOf(num);
	}
	
	public void bind()
	{
		if ( System.currentTimeMillis() > nextSwitch )
		{
			nextSwitch = System.currentTimeMillis()+switchRate;
			current++;
		}
		
		if ( current > (textures.size()-1) )
			current = 0;
		textures.get(current).bind();
		System.out.println(System.currentTimeMillis());
	}

	@Override
	public int getWidth() {
		return textures.get(current).getWidth();
	}

	@Override
	public int getHeight() {
		return textures.get(current).getHeight();
	}
}

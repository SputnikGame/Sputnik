package tsa2035.game.engine.fontparser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class BitmapFontData {
	private HashMap<Character, HashMap<String, Integer>> glyphData = new HashMap<Character, HashMap<String, Integer>>();
	
	public BitmapFontData(String filePath) throws IOException
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(BitmapFontData.class.getResourceAsStream(filePath)));
		while ( reader.ready() )
		{
			String line = reader.readLine();
			String words[] = line.replaceAll("\\s+", " ").split(" ");
			
			if ( words[0].equals("char") )
			{
				HashMap<String, Integer> glyph = new HashMap<String, Integer>();
				for ( int i = 1; i < words.length; i++ )
				{
					String thisValue[] = words[i].split("=");
					glyph.put(thisValue[0], Integer.parseInt(thisValue[1]));
				}
				glyphData.put((char)Character.toChars(glyph.get("id"))[0], glyph);
			}
			
		}
	}
	
	public HashMap<String, Integer> getDataForGlyph(char character)
	{
		return glyphData.get(character);
	}
}

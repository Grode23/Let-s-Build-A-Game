/*import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class AudioPlayer {
	
	public static Map<String, Sound> soundMap = new HashMap<>(); 
	
	public static void load() {	
		try {
			
			soundMap.put("click", new Sound("res/Click_Button.wav"));
			
		} catch (SlickException e) {
			
			e.printStackTrace();
			
		}
		
	}

	public static Sound getSound(String key) {
		return soundMap.get(key);
	}

}
*/
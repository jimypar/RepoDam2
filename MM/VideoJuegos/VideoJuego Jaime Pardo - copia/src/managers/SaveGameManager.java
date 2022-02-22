package managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import game.Parametros;

public class SaveGameManager {

	public static void loadAllParameters() {
		
		//This will get your preferences from storage
		Preferences prefs = Gdx.app.getPreferences("GameData");
		
		//Get value from a preference key "key" (must not be empty)
		Parametros.musicVolume = prefs.getFloat("musicVolume"); 
		Parametros.soundVolume = prefs.getFloat("soundVolume");
		Parametros.level1Unlocked = prefs.getBoolean("level1Unlocked");
		Parametros.boss1Unlocked = prefs.getBoolean("boss1Unlocked");
		Parametros.level2Unlocked = prefs.getBoolean("level2Unlocked");
		Parametros.boss2Unlocked = prefs.getBoolean("boss2Unlocked");		

		prefs.flush();
		
	}

	public static void saveAllParameters() {
		
		Preferences prefs = Gdx.app.getPreferences("GameData");
				
		prefs.putFloat("musicVolume", Parametros.musicVolume);
		prefs.putFloat("soundVolume", Parametros.soundVolume);
		prefs.putBoolean("fullscreen", Parametros.fullscreen);
		prefs.putBoolean("level1Unlocked", Parametros.level1Unlocked);
		prefs.putBoolean("boss1Unlocked", Parametros.boss1Unlocked);
		prefs.putBoolean("level2Unlocked", Parametros.level2Unlocked);
		prefs.putBoolean("boss2Unlocked", Parametros.boss2Unlocked);
		
		prefs.flush();
		
	}
	
		public static void resetAllParameters() {
		
		Preferences prefs = Gdx.app.getPreferences("GameData");
				
		prefs.remove("musicVolume");
		prefs.remove("soundVolume");
		prefs.remove("fullscreen");
		prefs.remove("level1Unlocked");
		prefs.remove("boss1Unlocked");
		prefs.remove("level2Unlocked");
		prefs.remove("boss2Unlocked");
		
		prefs.flush();
		
	}

}

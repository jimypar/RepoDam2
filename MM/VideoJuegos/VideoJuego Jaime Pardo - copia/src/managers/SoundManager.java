package managers;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

import game.Parametros;

public class SoundManager {
	static Music currentMusic=null;
	static String currentMusicName="";
	static Sound sound;

	static public void playMusic(String path) {
		try {
			currentMusic.stop();
		} catch (Exception e) {
		}
		currentMusicName = path;
		currentMusic = ResourceManager.getMusic(path);
		currentMusic.setVolume(Parametros.musicVolume);
		currentMusic.setLooping(true);
		currentMusic.play();

	}

	static public void stopMusic(String path) {
		currentMusic = ResourceManager.getMusic(path);
		currentMusic.stop();
	}

	static public void playSound(String path) {

		sound = ResourceManager.getSound(path);
		sound.play(Parametros.soundVolume);

	}

	static public void playSoundLoop(String path) {

		sound = ResourceManager.getSound(path);
		sound.setLooping(1000, true);
		sound.play(Parametros.soundVolume);

	}

}

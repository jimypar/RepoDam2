package managers;


import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public final class ResourceManager {
	private ResourceManager() {}
	public static AssetManager assets=new AssetManager();
	
	public static void loadAllResources(){

		
		
		assets.load("Cuphead/Idle/spriteIdleL.png", Texture.class);
		assets.load("Cuphead/Idle/spriteIdleR.png", Texture.class);
		assets.load("Cuphead/Run/Normal/spriteRunL.png", Texture.class);
		assets.load("Cuphead/Run/Normal/spriteRunR.png", Texture.class);
		assets.load("Cuphead/Jump/Cuphead/spriteJumpL.png", Texture.class);
		assets.load("Cuphead/Jump/Cuphead/spriteJumpR.png", Texture.class);

		
		//mapas
		//assets.setLoader(TiledMap.class, new TmxMapLoader());
		//assets.load("maps/00.tmx", TiledMap.class);
	
        //elementos de mapa
		assets.load("maps/background1.png", Texture.class);
		assets.load("maps/images/grass.png", Texture.class);
        //assets.load("maps/Images/arbol.png", Texture.class);
        //assets.load("maps/images/barrel.png", Texture.class);
        //enemigos
        //assets.load("enemys/blob.png",Texture.class);
        //assets.load("enemys/blob_jump.png",Texture.class);
        //jugador
        assets.load("player/grande.png",Texture.class);
        assets.load("player/frenteGrande.png",Texture.class);
        assets.load("player/izquieredawalk.png",Texture.class);
        assets.load("player/derechawalk.png",Texture.class);
        
        //objetos
        //assets.load("objects/bomb.png",Texture.class);
        //assets.load("objects/hookl.png",Texture.class);
        //assets.load("objects/sword.png",Texture.class);
        //assets.load("objects/swordA.png",Texture.class);
        
        
        //Audio
        //assets.load("Audio/Sound/knifeSlice.ogg", Sound.class);
      //assets.load("Audio/Sound/knifeSlice.ogg", Music.class);
        
        //UI
        //assets.load("ui/barraVida/Sprite 1.png", Texture.class);
 
	//añadir más elementos
	
	}
	
	public static boolean update(){
		return assets.update();
	}
	
	/*public static TextureAtlas getAtlas(String path){
		return assets.get(path, TextureAtlas.class);
		
	}*/
	
	public static Texture getTexture(String path) {
		return assets.get(path, Texture.class);
	}
	
	public static Music getMusic(String path){
		return assets.get(path, Music.class);
	}
	
	public static Sound getSound(String path)
	{
		return assets.get(path, Sound.class);
	}
	
	public static TiledMap getMap(String path){
		return assets.get(path, TiledMap.class);
	}

	public static void dispose(){
		assets.dispose();
	}
}

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

		
		
		//mapas
		assets.setLoader(TiledMap.class, new TmxMapLoader());
		assets.load("maps/tutorial/tutorial.tmx", TiledMap.class);
		assets.load("maps/Level1/Level1.tmx", TiledMap.class);
	
		assets.load("maps/tutorial/assets/Drawing/tutorial_room_front_layer_0001.png", Texture.class);
		
		assets.load("Cuphead/Idle/spriteIdleL.png", Texture.class);
		assets.load("Cuphead/Idle/spriteIdleR.png", Texture.class);
		assets.load("Cuphead/Run/Normal/spriteRunL.png", Texture.class);
		assets.load("Cuphead/Run/Normal/spriteRunR.png", Texture.class);
		assets.load("Cuphead/Jump/Cuphead/spriteJumpL.png", Texture.class);
		assets.load("Cuphead/Jump/Cuphead/spriteJumpR.png", Texture.class);
		assets.load("Cuphead/Duck/Idle/duckIdleL.png", Texture.class);
		assets.load("Cuphead/Duck/Idle/duckIdleR.png", Texture.class);
		assets.load("Cuphead/Shoot/Up/shootUpR.png", Texture.class);
		assets.load("Cuphead/Shoot/Up/shootUpL.png", Texture.class);
		assets.load("Cuphead/Shoot/Straight/shootStraightL.png", Texture.class);
		assets.load("Cuphead/Shoot/Straight/shootStraightR.png", Texture.class);
		assets.load("Cuphead/Duck/Shoot/shootDuckL.png", Texture.class);
		assets.load("Cuphead/Duck/Shoot/shootDuckR.png", Texture.class);
		assets.load("Cuphead/Run/Shooting/Straight/shootRunL.png", Texture.class);
		assets.load("Cuphead/Run/Shooting/Straight/shootRunR.png", Texture.class);
		assets.load("Cuphead/Run/Shooting/Diagonal Up/shootDiagonalRunL.png", Texture.class);
		assets.load("Cuphead/Run/Shooting/Diagonal Up/shootDiagonalRunR.png", Texture.class);
		assets.load("Cuphead/Dash/Ground/dashGR.png", Texture.class);
		assets.load("Cuphead/Dash/Ground/dashGL.png", Texture.class);
		assets.load("Cuphead/Hit/Ground/hitR.png", Texture.class);
		assets.load("Cuphead/Hit/Ground/hitL.png", Texture.class);
				
		assets.load("Bullets/bulletsL.png", Texture.class);
		assets.load("Bullets/bulletsR.png", Texture.class);
		assets.load("Bullets/bulletsUP.png", Texture.class);
		assets.load("Bullets/buleltsLUP.png", Texture.class);
		assets.load("Bullets/buleltsRUP.png", Texture.class);

		assets.load("Coin/CoinStages.png", Texture.class);
		assets.load("Coin/CoinExplode.png", Texture.class);
		
		//Enemies
		assets.load("Enemies/Die/dieDust.png", Texture.class);
		
		assets.load("Enemies/Flower/flowerR.png", Texture.class);
		assets.load("Enemies/Flower/flowerL.png", Texture.class);
		
		assets.load("Enemies/Blueball/bolaR.png", Texture.class);
		assets.load("Enemies/Blueball/bolaL.png", Texture.class);
		
		assets.load("Enemies/Planta/flyingPlant.png", Texture.class);

		
		
		
		//mapas
		//assets.setLoader(TiledMap.class, new TmxMapLoader());
		//assets.load("maps/00.tmx", TiledMap.class);
	
        //elementos de mapa
        //assets.load("maps/Images/arbol.png", Texture.class);
        //assets.load("maps/images/barrel.png", Texture.class);
        //enemigos
        //assets.load("enemys/blob.png",Texture.class);
        //assets.load("enemys/blob_jump.png",Texture.class);
        //jugador
        //objetos
        //assets.load("objects/bomb.png",Texture.class);
        //assets.load("objects/hookl.png",Texture.class);
        //assets.load("objects/sword.png",Texture.class);
        //assets.load("objects/swordA.png",Texture.class);
        
        
        //Audio
        assets.load("Sound/sfx_player_shoot_hit_01.wav", Sound.class);
        assets.load("Sound/sfx_coin_pickup_01.wav", Sound.class);
        assets.load("Sound/sfx_platforming_flowergrunt_death_01.wav", Sound.class);
        assets.load("Sound/sfx_player_jump_01.wav", Sound.class);
        assets.load("Sound/sfx_player_weapon_charge_fire_small_001.wav", Sound.class);
        assets.load("Sound/sfx_player_hit_02.wav", Sound.class);
        assets.load("Sound/sfx_player_dash_01.wav", Sound.class);

        assets.load("maps/Level1/music/MUS_ForestFollies.wav", Music.class);
        
        //UI
        assets.load("Lives/hp3.png", Texture.class);
        assets.load("Lives/hp2.png", Texture.class);
        assets.load("Lives/hp1.png", Texture.class);
        assets.load("Lives/hp0.png", Texture.class);

        
        
 
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

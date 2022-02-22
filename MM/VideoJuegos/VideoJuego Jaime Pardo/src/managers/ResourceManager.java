package managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

public final class ResourceManager {
	private ResourceManager() {
	}

	public static AssetManager assets = new AssetManager();
	public static LabelStyle buttonStyle;
	public static TextButtonStyle textButtonStyle;
	public static LabelStyle buttonStyle2;
	public static TextButtonStyle textButtonStyle2;
	public static LabelStyle buttonStyleBlock;
	public static TextButtonStyle textButtonStyleBlock;
	public static TextButtonStyle textButtonStylePressed;
	public static TextButtonStyle textButtonStylePressed2;

	public static void loadAllResources() {

		// mapas
		assets.setLoader(TiledMap.class, new TmxMapLoader());
		assets.load("maps/tutorial/tutorial.tmx", TiledMap.class);
		assets.load("maps/Level1/Level1.tmx", TiledMap.class);
		assets.load("maps/Level2/Level2.tmx", TiledMap.class);
		assets.load("maps/Boss1/Boss1.tmx", TiledMap.class);
		assets.load("maps/Boss2/Boss2.tmx", TiledMap.class);

		assets.load("maps/tutorial/assets/Drawing/tutorial_room_front_layer_0001.png", Texture.class);
		assets.load("Menu/menuInGameSmall.png", Texture.class);

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

		// Enemies
		assets.load("Enemies/Die/dieDust.png", Texture.class);

		assets.load("Enemies/Flower/flowerR.png", Texture.class);
		assets.load("Enemies/Flower/flowerL.png", Texture.class);

		assets.load("Enemies/Blueball/bolaR.png", Texture.class);
		assets.load("Enemies/Blueball/bolaL.png", Texture.class);

		assets.load("Enemies/Planta/flyingPlant.png", Texture.class);

		assets.load("Enemies/Barril/down.png", Texture.class);
		assets.load("Enemies/Barril/up.png", Texture.class);

		assets.load("Enemies/Dragon/moveL.png", Texture.class);
		assets.load("Enemies/Dragon/moveR.png", Texture.class);
		assets.load("Enemies/Dragon/atack.png", Texture.class);
		assets.load("Enemies/Dragon/fireball.png", Texture.class);
		assets.load("Enemies/Dragon/explosion.png", Texture.class);

		assets.load("Enemies/Bees/moveL.png", Texture.class);

		// BOSSES

		assets.load("Bosses/BossBola/Phase2/idleL.png", Texture.class);
		assets.load("Bosses/BossBola/Phase2/idleR.png", Texture.class);
		assets.load("Bosses/BossBola/Phase3/stoneR.png", Texture.class);
		assets.load("Bosses/BossBola/Phase3/stoneL.png", Texture.class);
		assets.load("Bosses/BossBola/Phase3/smash.png", Texture.class);
		assets.load("Bosses/BossBola/Phase3/death.png", Texture.class);
		// mapas
		// assets.setLoader(TiledMap.class, new TmxMapLoader());
		// assets.load("maps/00.tmx", TiledMap.class);

		// elementos de mapa
		// assets.load("maps/Images/arbol.png", Texture.class);
		// assets.load("maps/images/barrel.png", Texture.class);
		// enemigos
		// assets.load("enemys/blob.png",Texture.class);
		// assets.load("enemys/blob_jump.png",Texture.class);
		// jugador
		// objetos
		// assets.load("objects/bomb.png",Texture.class);
		// assets.load("objects/hookl.png",Texture.class);
		// assets.load("objects/sword.png",Texture.class);
		// assets.load("objects/swordA.png",Texture.class);

		// Audio
		assets.load("Sound/sfx_player_shoot_hit_01.wav", Sound.class);
		assets.load("Sound/sfx_coin_pickup_01.wav", Sound.class);
		assets.load("Sound/sfx_platforming_flowergrunt_death_01.wav", Sound.class);
		assets.load("Sound/sfx_player_jump_01.wav", Sound.class);
		assets.load("Sound/sfx_player_weapon_charge_fire_small_001.wav", Sound.class);
		assets.load("Sound/sfx_player_hit_02.wav", Sound.class);
		assets.load("Sound/sfx_player_dash_01.wav", Sound.class);
		assets.load("Sound/sfx_vinyl_scratch_01.wav", Sound.class);
		assets.load("Bosses/BossBola/sfx_slime_jump.wav", Sound.class);
		assets.load("Bosses/BossBola/sfx_slime_tombstone_splat.wav", Sound.class);
		assets.load("Sound/Menu_Move.wav", Sound.class);
		
		
		assets.load("maps/tutorial/music/MUS_Tutorial.wav", Music.class);
		assets.load("maps/Level1/music/MUS_ForestFollies.wav", Music.class);
		assets.load("maps/Boss1/music/MUS_Slime.wav", Music.class);
		assets.load("maps/Level2/music/MUS_BotanicPanic.wav", Music.class);
		assets.load("Menu/MUS_InkwellIsleOne.wav", Music.class);
		assets.load("WinScreen/MUS_VictoryScreen.wav", Music.class);
		
		
		// UI
		assets.load("Lives/hp4.png", Texture.class);
		assets.load("Lives/hp3.png", Texture.class);
		assets.load("Lives/hp2.png", Texture.class);
		assets.load("Lives/hp1.png", Texture.class);
		assets.load("Lives/hp0.png", Texture.class);
		assets.load("Lives/cuphead_death.png", Texture.class);

		// añadir más elementos
		

	}

	public static boolean update() {
		return assets.update();
	}

	public static void botones() {

		// estilo para botones
		FreeTypeFontGenerator ftfg = new FreeTypeFontGenerator(Gdx.files.internal("fuente/CupheadFelix-Regular.otf"));
		FreeTypeFontParameter ftfp = new FreeTypeFontParameter();
		
		FreeTypeFontGenerator ftfg2 = new FreeTypeFontGenerator(Gdx.files.internal("fuente/Dimbo.ttf"));
		FreeTypeFontParameter ftfp2 = new FreeTypeFontParameter();
		
		FreeTypeFontGenerator ftfg3 = new FreeTypeFontGenerator(Gdx.files.internal("fuente/Dimbo.ttf"));
		FreeTypeFontParameter ftfp3 = new FreeTypeFontParameter();
		
		FreeTypeFontGenerator ftfg4 = new FreeTypeFontGenerator(Gdx.files.internal("fuente/CupheadFelix-Regular.otf"));
		FreeTypeFontParameter ftfp4 = new FreeTypeFontParameter();
		
		FreeTypeFontGenerator ftfg5 = new FreeTypeFontGenerator(Gdx.files.internal("fuente/Dimbo.ttf"));
		FreeTypeFontParameter ftfp5 = new FreeTypeFontParameter();

		ftfp.size = 36;
		ftfp.color = Color.WHITE;
		ftfp.borderColor = Color.BLACK;
		ftfp.borderWidth = 2;
		
		ftfp2.size = 50;
		ftfp2.color = Color.WHITE;
		ftfp2.borderColor = Color.BLACK;
		ftfp2.borderWidth = 2;
		
		ftfp3.size = 50;
		ftfp3.color = Color.GRAY;
		ftfp3.borderColor = Color.BLACK;
		ftfp3.borderWidth = 2;

		ftfp4.size = 40;
		ftfp4.color = Color.GOLDENROD;
		ftfp4.borderColor = Color.BLACK;
		ftfp4.borderWidth = 2;
		

		ftfp5.size = 60;
		ftfp5.color = Color.GOLDENROD;
		ftfp5.borderColor = Color.BLACK;
		ftfp5.borderWidth = 2;


		BitmapFont fuentePropia = ftfg.generateFont(ftfp);
		buttonStyle = new LabelStyle();
		buttonStyle.font = fuentePropia;
		textButtonStyle = new TextButtonStyle();
		textButtonStyle.font = fuentePropia;

		BitmapFont fuentePropia2 = ftfg2.generateFont(ftfp2);
		buttonStyle2 = new LabelStyle();
		buttonStyle2.font = fuentePropia2;
		textButtonStyle2 = new TextButtonStyle();
		textButtonStyle2.font = fuentePropia2;
		
		BitmapFont fuenteBlock = ftfg3.generateFont(ftfp3);
		buttonStyleBlock = new LabelStyle();
		buttonStyleBlock.font = fuenteBlock;
		textButtonStyleBlock = new TextButtonStyle();
		textButtonStyleBlock.font = fuenteBlock;
		
		BitmapFont fuentePressed = ftfg4.generateFont(ftfp4);
		textButtonStylePressed = new TextButtonStyle();
		textButtonStylePressed.font = fuentePressed;
		
		BitmapFont fuentePressed2 = ftfg5.generateFont(ftfp5);
		textButtonStylePressed2 = new TextButtonStyle();
		textButtonStylePressed2.font = fuentePressed2;
		
	}

	/*
	 * public static TextureAtlas getAtlas(String path){ return assets.get(path,
	 * TextureAtlas.class);
	 * 
	 * }
	 */

	public static Texture getTexture(String path) {
		return assets.get(path, Texture.class);
	}

	public static Music getMusic(String path) {
		return assets.get(path, Music.class);
	}

	public static Sound getSound(String path) {
		return assets.get(path, Sound.class);
	}

	public static TiledMap getMap(String path) {
		return assets.get(path, TiledMap.class);
	}

	public static void dispose() {
		assets.dispose();
	}
}

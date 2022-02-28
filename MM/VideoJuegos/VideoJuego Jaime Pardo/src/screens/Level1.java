package screens;

import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

import elements.Element;
import elements.npc.Blueball;
import elements.npc.Enemigo;
import elements.npc.Flower;
import elements.npc.FlyingPlant;
import elements.objects.Bramble;
import elements.objects.Cloud;
import elements.objects.Coin;
import elements.objects.ImagenCapa;
import elements.objects.Marker;
import elements.objects.Solid;
import elements.objects.Wall;
import elements.other.Clock;
import elements.player.Bala;
import elements.player.Player;
import game.Demo;
import game.Parametros;
import managers.OrthogonalTiledMapRendererWithSprites;
import managers.ResourceManager;
import managers.SoundManager;
import screens.endScreens.WinScreen;
import screens.optionScreens.InGameScreen;
import ui.Ui;

public class Level1 extends BScreen {

	Stage mainStage;
	
	public Array<Solid> suelo;
	public Array<Wall> muros;
	public Array<Bramble> brambles;
	public Array<Enemigo> enemigos;
	public Array<Cloud> nubes;
	Array<ImagenCapa> imagenes;
	Array<Marker> markers;
	OrthographicCamera camara;
	private Ui vida;
	Music music_background;
	float time;

	private TiledMap map;
	private int tileWidth, tileHeight, mapWidthInTiles, mapHeightInTiles, mapWidthInPixels, mapHeightInPixels;
	private OrthogonalTiledMapRenderer renderer;

	private Player player;
	private float inicioX;
	private float inicioY;

	private Coin coin;
	public Element door;
	public Element fin;

	InGameScreen menu;


	public Level1(Demo game) {

		super(game);
		mainStage = new Stage();

		cargarMapaTiled();

		cargarMusica();		
		renderer = new OrthogonalTiledMapRendererWithSprites(map);

		camara = (OrthographicCamera) mainStage.getCamera();
		camara.setToOrtho(false, Parametros.getAnchoPantalla() * Parametros.zoom,
				Parametros.getAltoPantalla() * Parametros.zoom);
		camara.position.x = inicioX;
		camara.position.y = 430;

		Parametros.vida = 3;
		Parametros.powerUpDisparo = false;
		Parametros.powerUpVida = false;
		
		player = new Player(inicioX, inicioY, mainStage);
		player.setPolygon(10);
		
		Parametros.pausa = false;
		Parametros.pacifico=true;
				
		uiStage=new Stage();
		vida= new Ui(Parametros.getAnchoPantalla()/50,Parametros.getAltoPantalla()/10,this.uiStage);

		time=0;
	}

	private void cargarMusica() {
		SoundManager.playMusic("maps/Level1/music/MUS_ForestFollies.ogg");
	}

	@Override
	public void render(float delta) {

		super.render(delta);
		
		pauseButton();
		
		if (!Parametros.pausa) {
			
			mainStage.act();
			uiStage.act();
			
			if (player.muerto) {
				game.setScreen(new Level1(game));
			}
			
			Parametros.playerX = player.getX();
			Parametros.playerY = player.getY();

			colide();

			centrarCamara();
			
			time+=delta;
			
		}
		
		
		renderer.setView(camara);
		renderer.render();		
		mainStage.draw();	
		uiStage.draw();

	}

	private void pauseButton() {
		
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			if (!Parametros.pausa) {
				Parametros.pausa=true;
				menu = new InGameScreen(0, 0, this.uiStage, game);
			}
		}
		
	}

	public void colide() {

		for (Solid s : suelo) {

			if (s.getEnabled() && s.overlaps(player)) {
				player.preventOverlap(s);
			}

			if (player.pies.overlaps(s)) {
				player.tocoSuelo = true;
			}
		}
		
		for (Cloud n : nubes) {
			
			if (n.getEnabled() && n.overlaps(player) && player.pies.overlaps(n)) {
				player.preventOverlap(n);
			}

			if (player.pies.overlaps(n)) {
				player.tocoSuelo = true;
			}
		}

		for (Wall w : muros) {

			if (w.getEnabled() && w.overlaps(player)) {
				player.getActions().clear();
			}

		}

		if (!Parametros.nohit) {
			for (Enemigo e : enemigos) {
				if (!e.dying) {
					if (player.overlaps(e)) {
						player.getHit();	
					}
				}

			}
		}

		for (Bala bala : player.getBalas()) {

			for (Enemigo e : enemigos) {
				if (!e.dying && !e.inmortal) {
					if (bala.getEnabled() && bala.overlaps(e)) {
						bala.setEnabled(false);
						SoundManager.playSound("Sound/sfx_platforming_flowergrunt_death_01.wav");
						Parametros.pacifico=false;
						e.damage(1);
					}
				}
			}

			for (Wall w : muros) {
				if (bala.getEnabled() && bala.overlaps(w)) {
					bala.setEnabled(false);
					SoundManager.playSound("Sound/sfx_player_shoot_hit_01.wav");
				}
			}

		}

		if (coin.getEnabled() && coin.overlaps(player)) {
			if (!coin.explode) {
				coin.getCoin();
				Parametros.powerUpDisparo = true;
			}
		}
		
		for (Bramble bramble : brambles) {
			if (player.overlaps(bramble)) {
				player.setPosition(Parametros.playerX - 200, Parametros.playerY + 500);
			}
		}

		if (player.overlaps(door)) {
			player.door = true;
		}
		
		if (player.overlaps(fin)) {
			Parametros.playTime=time;
			Parametros.boss1Unlocked=true;
			game.setScreen(new WinScreen(game, 1));
		}

	}

	public void centrarCamara() {
		if (this.player.getX() > inicioX && this.player.getX() < 11500) {
			this.camara.position.x = player.getX();
			camara.update();
		}
	}

	public ArrayList<MapObject> getRectangleList(String propertyName) {
		ArrayList<MapObject> list = new ArrayList<MapObject>();
		for (MapLayer layer : map.getLayers()) {
			for (MapObject obj : layer.getObjects()) {
				if (!(obj instanceof RectangleMapObject))
					continue;
				MapProperties props = obj.getProperties();
				if (props.containsKey("name") && props.get("name").equals(propertyName)) {
					list.add(obj);
				}
			}

		}

		return list;
	}

	public ArrayList<MapObject> getEnemyList() {
		ArrayList<MapObject> list = new ArrayList<MapObject>();
		for (MapLayer layer : map.getLayers()) {
			for (MapObject obj : layer.getObjects()) {
				if (!(obj instanceof RectangleMapObject))
					continue;
				MapProperties props = obj.getProperties();
				if (props.containsKey("enemy")) {
					list.add(obj);
				}
			}

		}

		return list;
	}

	private void cargarMapaTiled() {

		map = ResourceManager.getMap("maps/Level1/Level1.tmx");

		MapProperties properties = map.getProperties();

		tileWidth = properties.get("tilewidth", Integer.class);
		tileHeight = properties.get("tileheight", Integer.class);
		mapWidthInTiles = properties.get("width", Integer.class);
		mapHeightInTiles = properties.get("height", Integer.class);
		mapWidthInPixels = tileWidth * mapWidthInTiles;
		mapHeightInPixels = tileHeight * mapHeightInTiles;

		ArrayList<MapObject> elementos = getRectangleList("Inicio");
		MapProperties props;
		props = elementos.get(0).getProperties();
		inicioX = (float) props.get("x");
		inicioY = (float) props.get("y");

		elementos = getRectangleList("Coin");
		props = elementos.get(0).getProperties();
		coin = new Coin((float) props.get("x"), (float) props.get("y"), mainStage);

		elementos = getRectangleList("Door");
		props = elementos.get(0).getProperties();
		door = new Element((float) props.get("x"), (float) props.get("y"), mainStage, (float) props.get("width"),
				(float) props.get("height"));
		door.setRectangle();
		
		elementos = getRectangleList("Fin");
		props = elementos.get(0).getProperties();
		fin = new Element((float) props.get("x"), (float) props.get("y"), mainStage, (float) props.get("width"),
				(float) props.get("height"));
		fin.setRectangle();

		elementos = getRectangleList("Solid");

		Solid solido;
		suelo = new Array<Solid>();
		for (MapObject solid : elementos) {
			props = solid.getProperties();
			solido = new Solid((float) props.get("x"), (float) props.get("y"), mainStage, (float) props.get("width"),
					(float) props.get("height"));
			if (solid.getName() != null) {
				solido.setName(solid.getName());
			}
			suelo.add(solido);
		}

		elementos = getRectangleList("Wall");

		Wall wall;
		muros = new Array<Wall>();
		for (MapObject muro : elementos) {
			props = muro.getProperties();
			wall = new Wall((float) props.get("x"), (float) props.get("y"), mainStage, (float) props.get("width"),
					(float) props.get("height"));
			muros.add(wall);
		}

		elementos = getRectangleList("Bramble");

		Bramble b;
		brambles = new Array<Bramble>();
		for (MapObject bramble : elementos) {
			props = bramble.getProperties();
			b = new Bramble((float) props.get("x"), (float) props.get("y"), mainStage, (float) props.get("width"),
					(float) props.get("height"));
			brambles.add(b);
		}
		
		elementos = getRectangleList("Cloud");
		
		Cloud n;
		nubes = new Array<Cloud>();
		for (MapObject nube : elementos) {
			props = nube.getProperties();
			n = new Cloud((float) props.get("x"), (float) props.get("y"), mainStage, (float) props.get("width"),
					(float) props.get("height"));
			nubes.add(n);
		}

		elementos = getEnemyList();

		enemigos = new Array<Enemigo>();
		for (MapObject enemy : elementos) {
			props = enemy.getProperties();
			switch (props.get("enemy").toString()) {
			case "Flower":
				Flower flower = new Flower((float) props.get("x"), (float) props.get("y"), mainStage, this);
				enemigos.add(flower);
				break;
			case "Blueball":
				Blueball blueball = new Blueball((float) props.get("x"), (float) props.get("y"), mainStage, this);
				enemigos.add(blueball);
				break;
			case "FlyingPlant":
				FlyingPlant flyingPlant = new FlyingPlant((float) props.get("x"), (float) props.get("y"), mainStage,
						this);
				enemigos.add(flyingPlant);
				break;
			default:
				break;
			}
		}

	}

}

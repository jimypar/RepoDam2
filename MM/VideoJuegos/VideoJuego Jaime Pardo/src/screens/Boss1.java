package screens;

import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;

import com.badlogic.gdx.Gdx;
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

import elements.Bala;
import elements.Blueball;
import elements.Boss;
import elements.BossBola;
import elements.Bramble;
import elements.Clock;
import elements.Coin;
import elements.Element;
import elements.Enemigo;
import elements.Flower;
import elements.FlyingPlant;
import elements.ImagenCapa;
import elements.Marker;
import elements.Player;
import elements.Solid;
import elements.Wall;
import game.Demo;
import game.Parametros;
import managers.OrthogonalTiledMapRendererWithSprites;
import managers.ResourceManager;
import managers.SoundManager;
import ui.BarraVida;

public class Boss1 extends BScreen {

	Stage mainStage;
	Stage uiStage;
	public Array<Solid> suelo;
	public Array<Wall> muros;
	public Array<Boss> boss;
	Array<ImagenCapa> imagenes;
	OrthographicCamera camara;
	private BarraVida barra;
	Music music_background;

	private TiledMap map;
	private int tileWidth, tileHeight, mapWidthInTiles, mapHeightInTiles, mapWidthInPixels, mapHeightInPixels;
	private OrthogonalTiledMapRenderer renderer;

	private Player player;
	private float inicioX;
	private float inicioY;


	public Boss1(Demo game) {

		super(game);
		mainStage = new Stage();

//		try {
//			Display.setDisplayModeAndFullscreen(Display.getDesktopDisplayMode());
//		} catch (LWJGLException e) {
//			e.printStackTrace();
//		}

		cargarMapaTiled();

		cargarMusica();		
		renderer = new OrthogonalTiledMapRendererWithSprites(map);

		camara = (OrthographicCamera) mainStage.getCamera();
		camara.setToOrtho(false, Parametros.getAnchoPantalla() * Parametros.zoom,
				Parametros.getAltoPantalla() * Parametros.zoom);
		camara.position.x = inicioX;
		camara.position.y = 430;

		Parametros.vida = 3;	
		
		player = new Player(inicioX, inicioY, mainStage);
		player.setPolygon(10);
				

		uiStage=new Stage();
		barra= new BarraVida(Parametros.getAnchoPantalla()/50,Parametros.getAltoPantalla()/10,this.uiStage);
		

	}

	private void cargarMusica() {
		SoundManager.playMusic("maps/Boss1/music/MUS_Slime.wav");
	}

	@Override
	public void render(float delta) {

		super.render(delta);
		mainStage.act();
		uiStage.act();
		
		if (player.muerto) {
			game.setScreen(new Boss1(game));
		}
		
		Parametros.playerX = player.getX();
		Parametros.playerY = player.getY();

		colide();

		centrarCamara();

		renderer.setView(camara);
		renderer.render();

		mainStage.draw();
		uiStage.draw();
		

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

		for (Wall w : muros) {

			if (w.getEnabled() && w.overlaps(player)) {
				player.getActions().clear();
			}

		}

		if (!Parametros.nohit) {
			for (Boss b : boss) {
				if (!b.dying) {
					if (player.overlaps(b)) {
						player.getHit();	
					}
				}

			}
		}

		for (Bala bala : player.getBalas()) {

			for (Boss b : boss) {
				if (!b.dying) {
					if (bala.overlaps(b)) {
						bala.setEnabled(false);
						SoundManager.playSound("Sound/sfx_platforming_flowergrunt_death_01.wav");
					}
				}
			}

			for (Wall w : muros) {
				if (bala.getEnabled() && bala.overlaps(w)) {
					bala.setEnabled(false);
				}
			}

		}

	
	}

	public void centrarCamara() {
		if (this.player.getX() > inicioX && this.player.getX() < 100) {
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

		map = ResourceManager.getMap("maps/Boss1/Boss1.tmx");

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

//		elementos = getRectangleList("Coin");
//		props = elementos.get(0).getProperties();
//		coin = new Coin((float) props.get("x"), (float) props.get("y"), mainStage);

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

		elementos = getEnemyList();

		boss = new Array<Boss>();
		for (MapObject enemy : elementos) {
			props = enemy.getProperties();
			switch (props.get("enemy").toString()) {
			case "boss":
				BossBola b = new BossBola((float) props.get("x"), (float) props.get("y"), mainStage, this);
				boss.add(b);
				break;
			}
		}

	}

}

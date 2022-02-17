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
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
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

public class TutorialScreen extends BScreen {

	Stage mainStage;
	Array<Solid> suelo;
	Array<Wall> muros;
	Array<ImagenCapa> imagenes;
	Array<Marker> markers;	
	OrthographicCamera camara;
	SpriteBatch batch;
	ShaderProgram shader;
	float time;
	Texture vigneteTexture;
	Sprite vigneteSprite;
	Music music_background;
	
	
	private TiledMap map;
	private int tileWidth, tileHeight, mapWidthInTiles, mapHeightInTiles, mapWidthInPixels, mapHeightInPixels;
	private OrthogonalTiledMapRenderer renderer;

	private Player player;
	private float inicioX;
	private float inicioY;
	
	private Coin coin;
	public Element door;
	
	
	

	public TutorialScreen(Demo game) {
		

		super(game);
		mainStage = new Stage();
		
//		try {
//			Display.setDisplayModeAndFullscreen(Display.getDesktopDisplayMode());
//		} catch (LWJGLException e) {
//			e.printStackTrace();
//		}
		
		batch = new SpriteBatch();
		
		
		shader = new ShaderProgram(batch.getShader().getVertexShaderSource(), Gdx.files.internal("oldfilm.frag").readString());
        if (!shader.isCompiled()){
            System.out.println(shader.getLog());
        }
        
		
		vigneteTexture = new Texture("maps/tutorial/assets/Drawing/tutorial_room_front_layer_0001.png");
		
		cargarMapaTiled();
		
		renderer = new OrthogonalTiledMapRendererWithSprites(map);

		camara = (OrthographicCamera) mainStage.getCamera();
		camara.setToOrtho(false, Parametros.getAnchoPantalla() * Parametros.zoom,
				Parametros.getAltoPantalla() * Parametros.zoom);
		camara.position.x = inicioX;
		camara.position.y = 400;

	
		player = new Player(inicioX, inicioY, mainStage);
		player.setPolygon(10);
		
		Parametros.vida=3;
		
		SoundManager.playMusic("maps/tutorial/music/MUS_Tutorial.wav");
	}


	@Override
	public void render(float delta) {

		super.render(delta);
		mainStage.act();

		Parametros.playerX = player.getX();
		Parametros.playerY = player.getY();

		time+=delta;
		
		colide();

		centrarCamara();
		
		renderer.setView(camara);
		renderer.render();
		
		
		shader.begin();
		shader.setUniformf("u_time", time);		
		mainStage.getBatch().setShader(null);
		mainStage.draw();
		mainStage.getBatch().begin();
	
		mainStage.getBatch().draw(vigneteTexture, camara.position.x-(camara.viewportWidth/2), camara.position.y-(camara.viewportHeight/2), camara.viewportWidth, camara.viewportHeight);  
		
		mainStage.getBatch().end();
		mainStage.getBatch().setShader(shader);
		
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
		
		for (Bala bala : player.getBalas()) {
			if (bala.getEnabled() && bala.overlaps(markers.get(0))) {
				renderer = new OrthogonalTiledMapRendererWithSprites(map,2);
				for (Solid s : suelo) {
					if (s.getName()!=null && s.getName().equals("Solid2")) {
						s.setBounds(1,1,0,0);
					}
				}
				
				bala.setEnabled(false);
			} 
			if (bala.getEnabled() && bala.overlaps(markers.get(1))) {
				renderer = new OrthogonalTiledMapRendererWithSprites(map,1);
				for (Solid s : suelo) {
					if (s.getName()!=null && s.getName().equals("Solid1")) {
						s.setBounds(1, 1,0,0);
					}
				}
				bala.setEnabled(false);
			}
			
			for (Wall w : muros) {
				if (bala.getEnabled() && bala.overlaps(w)) {
					bala.setEnabled(false);
				}
			}

		}
		
		if (coin.getEnabled() && coin.overlaps(player)) {
			if (!coin.explode) {
				coin.getCoin();
				Parametros.powerUpDisparo=true;
			}
		}
		
		if (player.overlaps(door)) {
			player.door=true;
			game.setScreen(new Level1(game));
		}
		

	}

	public void centrarCamara() {
		if (this.player.getX()>inicioX && this.player.getX()<6500 )  {
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
	
	
	

	private void cargarMapaTiled() {

		
		map = ResourceManager.getMap("maps/tutorial/tutorial.tmx");

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
		door = new Element((float) props.get("x"),(float) props.get("y"), mainStage,(float) props.get("width"),(float) props.get("height"));
		door.setRectangle();

		elementos = getRectangleList("Solid");

		Solid solido;
		suelo = new Array<Solid>();
		for (MapObject solid : elementos) {
			props = solid.getProperties();
			solido = new Solid((float) props.get("x"), (float) props.get("y"), mainStage, (float) props.get("width"),
					(float) props.get("height"));
			if (solid.getName()!=null) {
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
				
		elementos = getRectangleList("Marker");
		
		Marker marker;
		markers = new Array<Marker>();
		for (MapObject mark : elementos) {
			props = mark.getProperties();
			marker = new Marker((float) props.get("x"), (float) props.get("y"), mainStage, (float) props.get("width"),
					(float) props.get("height"));
			markers.add(marker);
		}
		
		
		
	}
	
	@Override
	public void dispose() {
		super.dispose();
		batch.dispose();
        shader.dispose();
	}



}

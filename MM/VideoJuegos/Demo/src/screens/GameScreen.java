package screens;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

import elements.Barril;
import elements.Player;
import elements.Solid;
import game.Demo;
import game.Parametros;
import managers.ResourceManager;


public class GameScreen extends BScreen{
	
Stage mainStage;
private Player player;
Array<Barril> barriles;
ArrayList<Solid> suelo;
OrthographicCamera camara;
private TiledMap map;
private int tiledWidth, tiledHeight, mapWidthInTiles, mapHeightInTiles, mapWidthInPixels, mapHeightInPixels;

private OrthogonalTiledMapRenderer renderer;


	public GameScreen(Demo game) {
		
		super(game);
		float inicioX;
		float inicioY;
		
		mainStage=new Stage();
		
		map= ResourceManager.getMap("maps/mapa.tmx");
		
		MapProperties properties = map.getProperties();
		
		tiledWidth = properties.get("tilewidth", Integer.class);
		tiledHeight = properties.get("tileheight", Integer.class);
		mapWidthInTiles = properties.get("width", Integer.class);
		mapHeightInTiles = properties.get("height", Integer.class);
		mapWidthInPixels = tiledWidth*mapWidthInTiles;
		mapHeightInPixels = tiledHeight*mapHeightInTiles;
		
		renderer = new OrthogonalTiledMapRenderer(map,mainStage.getBatch());
		
		camara = (OrthographicCamera) mainStage.getCamera();
		camara.setToOrtho(false, Parametros.getAnchoPantalla()*Parametros.zoom,Parametros.getAltoPantalla()*Parametros.zoom);
		
		ArrayList<MapObject> elementos = getRectangleList("Inicio");
		MapProperties props;
		props= elementos.get(0).getProperties();
		inicioX=(float) props.get("x");
		inicioX=(float) props.get("y");

		
		
		elementos=getRectangleList("Solid");
		Solid solido;
		suelo = new ArrayList<>();
		for (MapObject solid : elementos) {
			props=solid.getProperties();
			solido= new Solid((float) props.get("w"),(float) props.get("y"),mainStage, (float) props.get("width"),(float) props.get("height"));
			suelo.add(solido);
		}
		
		player = new Player(100,500,mainStage);
		
		barriles = new Array<>();
		for (int i = 0; i < 20; i++) {
			barriles.add(new Barril(i*70,0,mainStage));
		}
		
	}
	@Override
	public void render(float delta) {
		
		super.render(delta);
	    mainStage.act();
	    colide();
	    centrarCamara();
	    renderer.setView(camara);
	    renderer.render();
	    mainStage.draw();
	    
	    

	}
	
	private void centrarCamara() {
		
		this.camara.position.x = player.getX();
		this.camara.position.y = player.getY();
		this.camara.update();
		
	}
	public void colide() {
	
		for (Barril barril : barriles) {
			if (barril.getEnabled() && barril.overlaps(player)) {
				player.preventOverlap(barril);
			}
			
			if (player.pies.overlaps(barril)) {
				player.tocoSuelo = true;
			}
			
		}
		
		
		
	}
	
	
	public ArrayList<MapObject> getRectangleList(String propertyName){
		ArrayList<MapObject> list = new ArrayList<>();
		
		for(MapLayer layer: map.getLayers()) {
			for (MapObject obj : layer.getObjects()) {
				if (obj instanceof RectangleMapObject)
					continue;
				MapProperties prop = obj.getProperties();
				if (prop.containsKey("name") && prop.get("name").equals(propertyName)) {
					list.add(obj);
				}
			}
		}
		return list;
		
	}
	
}

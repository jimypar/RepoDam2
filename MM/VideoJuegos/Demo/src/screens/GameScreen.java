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
import elements.Element;
import elements.Player;
import elements.Solid;
import game.Demo;
import game.Parametros;
import managers.ResourceManager;


public class GameScreen extends BScreen{
	
Stage mainStage;
Array<Barril> barriles;
Array<Solid> suelo;

OrthographicCamera camara;
private TiledMap map;
private int tileWidth, tileHeight, mapWidthInTiles, mapHeightInTiles,
mapWidthInPixels, mapHeightInPixels;



private OrthogonalTiledMapRenderer renderer;
	   
private Player player;

	public GameScreen(Demo game) {
	
		super(game);
		mainStage=new Stage();
		float inicioX;
		float inicioY;
		map= ResourceManager.getMap("maps/mapa0.tmx");
		
		
		
		MapProperties properties=map.getProperties();
		
		tileWidth=properties.get("tilewidth", Integer.class);
		tileHeight=properties.get("tileheight", Integer.class);
		mapWidthInTiles=properties.get("width", Integer.class);
		mapHeightInTiles=properties.get("height", Integer.class);
		mapWidthInPixels=tileWidth*mapWidthInTiles;
		mapHeightInPixels=tileHeight*mapHeightInTiles;
		
		
		renderer=new OrthogonalTiledMapRenderer(map,mainStage.getBatch());
		
		
		camara=(OrthographicCamera) mainStage.getCamera();
		camara.setToOrtho(false, Parametros.getAnchoPantalla()*Parametros.zoom, Parametros.getAltoPantalla()*Parametros.zoom);
		
		
		ArrayList<MapObject> elementos=getRectangleList("Inicio");
		MapProperties props;
		props=elementos.get(0).getProperties();
		inicioX=(float)props.get("x");
		inicioY=(float)props.get("y");
		
		
		elementos=getRectangleList("Solid");
		
		Solid solido;
		suelo=new Array<Solid>();
		for(MapObject solid:elementos) {
			props=solid.getProperties();
			solido=new Solid((float)props.get("x"),(float)props.get("y"), mainStage,
					(float)props.get("width"),(float)props.get("height"));
			
			suelo.add(solido);
			
		}
		
		
		
		player=new Player(inicioX,inicioY,mainStage);
	barriles= new Array<Barril>();
		for(int i=0; i<10; i++) {
			barriles.add(new Barril(i*14,30,mainStage));
		}
		
		
		
		
		
		
	}
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		super.render(delta);
	     mainStage.act();
	    colide();
	    
	    Parametros.playerX = player.getX();
	    Parametros.playerY = player.getY();
	    
	    centrarCamara();
	    renderer.setView(camara);
	    renderer.render();
	    
	    mainStage.draw();
	    

	}
	
	public void colide() {
		player.tocoSuelo=false;
		for(Barril b:barriles) {
			
			
			if(b.getEnabled() && b.overlaps(player)) {
				player.preventOverlap(b);
				
				//b.preventOverlap(player);
				
			}
			
			
			if(player.pies.overlaps(b)) {
				player.tocoSuelo=true;
			}
			
		}
		
for(Solid b:suelo) {
			
			
			if(b.getEnabled() && b.overlaps(player)) {
				player.preventOverlap(b);
				
				//b.preventOverlap(player);
				
			}
			
			
			if(player.pies.overlaps(b)) {
				player.tocoSuelo=true;
			}
			
		}
		
	}
	
	public void centrarCamara() {
		this.camara.position.x=player.getX();
		this.camara.position.y=player.getY();
		camara.update();
		
	}
	
	
	public ArrayList<MapObject> getRectangleList(String propertyName){
		ArrayList<MapObject> list =new ArrayList<MapObject>();
		for(MapLayer layer: map.getLayers()) {
			for(MapObject obj: layer.getObjects()) {
				if(!(obj instanceof RectangleMapObject))
					continue;
				MapProperties props= obj.getProperties();
				if(props.containsKey("name") &&  props.get("name").equals(propertyName))
				{
					list.add(obj);
				}
				
			}
			
		}
		
		return list;
	}
	
}

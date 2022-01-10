package screens;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

import elements.Barril;
import elements.Player;
import game.Demo;
import game.Parametros;


public class GameScreen extends BScreen{
	
Stage mainStage;
private Player player;
Array<Barril> barriles;
OrthographicCamera camara;
	   
	public GameScreen(Demo game) {
		
		super(game);
		mainStage=new Stage();
		
		camara = (OrthographicCamera) mainStage.getCamera();
		camara.setToOrtho(false, Parametros.getAnchoPantalla()*Parametros.zoom,Parametros.getAltoPantalla()*Parametros.zoom);
		
		player = new Player(100,100,mainStage);
		
		barriles = new Array<>();
		for (int i = 0; i < 20; i++) {
			barriles.add(new Barril(i*70+20,100,mainStage));
		}
		
	}
	@Override
	public void render(float delta) {
		
		super.render(delta);
	    mainStage.act();
	    colide();
	    mainStage.draw();
	    centrarCamara();
	    

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
	
	
}

package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

import elements.Player;
import elements.Suelo;
import game.Demo;
import game.Parametros;

public class GameScreen extends BScreen {

	Stage mainStage;
	Array<Suelo> suelos;
	OrthographicCamera camara;
	Texture backgroundTexture;

	private Player player;

	public GameScreen(Demo game) {

		super(game);
		mainStage = new Stage();

		backgroundTexture = new Texture(Gdx.files.internal("maps/background1.png"));

		camara = (OrthographicCamera) mainStage.getCamera();
		camara.setToOrtho(false, Parametros.getAnchoPantalla() * Parametros.zoom,
				Parametros.getAltoPantalla() * Parametros.zoom);

		player = new Player(50, 150, mainStage);
		player.setPolygon(8);
		suelos = new Array<Suelo>();
		for (int i = 0; i < 25; i++) {
			suelos.add(new Suelo(i * 80, 150, mainStage));
			suelos.get(i).setVisible(false);
		}
		
		

	}

	@Override
	public void render(float delta) {

		super.render(delta);
		mainStage.act();
		colide();

		Parametros.playerX = player.getX();
	    Parametros.playerY = player.getY();
		
		centrarCamara();
		
		mainStage.getBatch().begin();
		mainStage.getBatch().draw(backgroundTexture, 0, 0,Parametros.getAnchoPantalla(), Parametros.getAltoPantalla());
		mainStage.getBatch().end();
		
		mainStage.draw();

	}

	public void colide() {
		player.tocoSuelo = false;
		for (Suelo s : suelos) {

			if (s.getEnabled() && s.overlaps(player)) {
				player.preventOverlap(s);
			}

			if (player.pies.overlaps(s)) {
				player.tocoSuelo = true;
			}

		}

	}

	public void centrarCamara() {
		this.camara.position.x = player.getX();
		camara.update();
	}

}

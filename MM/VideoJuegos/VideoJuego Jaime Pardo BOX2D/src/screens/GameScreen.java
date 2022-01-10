package screens;

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

		backgroundTexture = new Texture("maps/background1.png");

		camara = (OrthographicCamera) mainStage.getCamera();
		camara.setToOrtho(false, Parametros.getAnchoPantalla() * Parametros.zoom,
				Parametros.getAltoPantalla() * Parametros.zoom);

		player = new Player(50, 100, mainStage);
		player.setPolygon(8);
		suelos = new Array<Suelo>();
		for (int i = -10; i < 10; i++) {
			suelos.add(new Suelo(i * 80, 30, mainStage));
		}

	}

	@Override
	public void render(float delta) {

		super.render(delta);
		mainStage.act();
		colide();

		// centrarCamara();

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
		this.camara.position.y = player.getY();
		camara.update();

	}

}

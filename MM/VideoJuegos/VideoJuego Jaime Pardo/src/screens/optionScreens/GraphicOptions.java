package screens.optionScreens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import game.Demo;
import game.Parametros;
import managers.ResourceManager;
import managers.SoundManager;
import screens.BScreen;
import screens.TitleScreen;

public class GraphicOptions extends BScreen {
	private Table tabla;
	private Texture background;
	private float volMusic;
	private float volSound;
	Label res;
	Label fullscreen;
	private ArrayList<Integer> resWidth;
	private ArrayList<Integer> resHeight;
	int i = 1;

	public GraphicOptions(Demo game) {
		super(game);

		resWidth = new ArrayList<>();
		resHeight = new ArrayList<>();

		setResolutions();

		tabla = new Table();
		tabla.setFillParent(true);

		this.uiStage.addActor(tabla);

		background = new Texture("Menu/Background.png");

		tabla.row().colspan(3);
		Label musica = new Label("Resolucion", ResourceManager.buttonStyle);
		tabla.add(musica);
		tabla.row();
		TextButton botonBajar = new TextButton("<-", ResourceManager.textButtonStyle2);
		botonBajar.addListener((Event e) -> {
			if (!(e instanceof InputEvent) || !((InputEvent) e).getType().equals(Type.touchDown))
				return false;
			if (i >= 0) {
				Parametros.setAnchoPantalla(this.resWidth.get(i));
				Parametros.setAltoPantalla(this.resHeight.get(i));
				i--;
			}
			System.out.println(i);
			return false;
		});
		tabla.add(botonBajar);
		res = new Label("", ResourceManager.buttonStyle2);
		tabla.add(res);
		TextButton botonSubir = new TextButton("->", ResourceManager.textButtonStyle2);
		botonSubir.addListener((Event e) -> {
			if (!(e instanceof InputEvent) || !((InputEvent) e).getType().equals(Type.touchDown))
				return false;
			if (i <= this.resHeight.size()) {
				Parametros.setAnchoPantalla(this.resWidth.get(i));
				Parametros.setAltoPantalla(this.resHeight.get(i));
				i++;
			}
			System.out.println(i);
			return false;
		});
		tabla.add(botonSubir);
		tabla.row().colspan(3);
		Label efectos = new Label("Pantalla Completa", ResourceManager.buttonStyle);
		tabla.add(efectos);
		tabla.row();
		TextButton botonBajar2 = new TextButton("<-", ResourceManager.textButtonStyle2);
		botonBajar2.addListener((Event e) -> {
			if (!(e instanceof InputEvent) || !((InputEvent) e).getType().equals(Type.touchDown))
				return false;
			if (Parametros.fullscreen) {
				Parametros.fullscreen = false;
			} else {
				Parametros.fullscreen = true;
			}
			return false;
		});
		tabla.add(botonBajar2);
		fullscreen = new Label("", ResourceManager.buttonStyle2);
		tabla.add(fullscreen);
		TextButton botonSubir2 = new TextButton("->", ResourceManager.textButtonStyle2);
		botonSubir2.addListener((Event e) -> {
			if (!(e instanceof InputEvent) || !((InputEvent) e).getType().equals(Type.touchDown))
				return false;
			if (Parametros.fullscreen) {
				Parametros.fullscreen = false;
			} else {
				Parametros.fullscreen = true;
			}
			return false;
		});
		tabla.add(botonSubir2);
		tabla.row().colspan(3);
		TextButton botonSalir = new TextButton("Volver", ResourceManager.textButtonStyle);
		botonSalir.addListener((Event e) -> {
			if (!(e instanceof InputEvent) || !((InputEvent) e).getType().equals(Type.touchDown))
				return false;
			this.dispose();
			game.setScreen(new OptionScreen(game));
			return false;
		});
		tabla.add(botonSalir);
	}

	@Override
	public void render(float delta) {
		super.render(delta);

		if (Parametros.fullscreen) {
			fullscreen.setText("SI");
		} else {
			fullscreen.setText("NO");
		}
		
		this.res.setText(Parametros.getAnchoPantalla()+" X "+Parametros.getAltoPantalla());

		uiStage.act();

		uiStage.getBatch().begin();
		uiStage.getBatch().draw(background, 0, 0, Parametros.getAnchoPantalla(), Parametros.getAltoPantalla());
		uiStage.getBatch().end();

		uiStage.draw();

	}

	private void setResolutions() {

		resWidth.add(1280);
		resHeight.add(720);

		resWidth.add(1920);
		resHeight.add(1080);

		resWidth.add(3000);
		resHeight.add(2000);

	}

}

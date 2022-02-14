package screens.optionScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
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
import screens.BScreen;
import screens.TitleScreen;

public class SoundOptions extends BScreen{
private Table tabla;
private Texture background;
private float volMusic;
private float volSound;
Label vol1;
	public SoundOptions(Demo game) {
		super(game);
		// TODO Auto-generated constructor stub
		
		this.volMusic = 1;
		this.volSound = 1;
		
		tabla= new Table();
		tabla.setFillParent(true);
		
		this.uiStage.addActor(tabla);
		
		background = new Texture("Menu/Background.png");
		
		tabla.row().colspan(3).expandX().fillX();
		Label musica=new Label("Musica",ResourceManager.buttonStyle);
		tabla.add(musica);
		tabla.row().expandX().fillX();
		TextButton botonBajar=new TextButton("<-",ResourceManager.textButtonStyle2);
		botonBajar.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.touchDown))
					return false;
					this.volMusic -=0.1f;
				return false;
				});
		tabla.add(botonBajar);
		vol1=new Label("", ResourceManager.buttonStyle2);
		tabla.add(vol1);
		TextButton botonSubir=new TextButton("->",ResourceManager.textButtonStyle2);
		botonSubir.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.touchDown))
					return false;
					this.volMusic +=0.1f;
				return false;
				});
		tabla.add(botonSubir);
		tabla.row().colspan(3).expandX().fillX();
		TextButton boton2=new TextButton("Efectos",ResourceManager.textButtonStyle);
		boton2.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.touchDown))
					return false;
				this.dispose();
				game.setScreen(new SoundOptions(game));
				return false;
				});
		tabla.add(boton2);
		tabla.row().colspan(3).expandX().fillX();
		TextButton botonSalir=new TextButton("Volver", ResourceManager.textButtonStyle);
		botonSalir.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.touchDown))
					return false;
				this.dispose();
				game.setScreen(new OptionScreen(game));
				return false;
				});
		tabla.add(botonSalir);
	}

	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		super.render(delta);
		
		this.vol1.setText(String.valueOf(volMusic));
	    
	     uiStage.act();
	     
	     uiStage.getBatch().begin();
	     uiStage.getBatch().draw(background, 0, 0, Parametros.getAnchoPantalla(), Parametros.getAltoPantalla());
	     uiStage.getBatch().end();
	     
	     uiStage.draw();

	}
	
	
	
}

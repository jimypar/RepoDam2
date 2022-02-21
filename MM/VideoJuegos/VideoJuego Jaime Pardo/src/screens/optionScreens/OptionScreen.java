package screens.optionScreens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import game.Demo;
import game.Parametros;
import managers.ResourceManager;
import managers.SoundManager;
import screens.BScreen;
import screens.TitleScreen;

public class OptionScreen extends BScreen{
private Table tabla;
private Texture background;
	public OptionScreen(Demo game) {
		super(game);
		// TODO Auto-generated constructor stub
		
		
		tabla= new Table();
		tabla.setFillParent(true);
		
		this.uiStage.addActor(tabla);
		
		background = new Texture("Menu/Background.png");
		
		TextButton boton=new TextButton("Graficos",ResourceManager.textButtonStyle);
		boton.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.touchDown))
					return false;
				this.dispose();
				game.setScreen(new GraphicOptions(game));
				return false;
				});
		boton.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.enter))
					return false;
				boton.setStyle(ResourceManager.textButtonStylePressed);
				SoundManager.playSound("Sound/Menu_Move.wav");
				return false;
				});
		boton.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.exit))
					return false;
				boton.setStyle(ResourceManager.textButtonStyle);
				SoundManager.playSound("Sound/Menu_Move.wav");
				return false;
				});
		tabla.add(boton);
		tabla.row();
		TextButton boton2=new TextButton("Sonidos",ResourceManager.textButtonStyle);
		boton2.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.touchDown))
					return false;
				this.dispose();
				game.setScreen(new SoundOptions(game));
				return false;
				});
		boton2.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.enter))
					return false;
				boton2.setStyle(ResourceManager.textButtonStylePressed);
				SoundManager.playSound("Sound/Menu_Move.wav");
				return false;
				});
		boton2.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.exit))
					return false;
				boton2.setStyle(ResourceManager.textButtonStyle);
				SoundManager.playSound("Sound/Menu_Move.wav");
				return false;
				});
		tabla.add(boton2);
		tabla.row();
		TextButton botonSalir=new TextButton("Volver", ResourceManager.textButtonStyle);
		botonSalir.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.touchDown))
					return false;
				this.dispose();
				game.setScreen(new TitleScreen(game));
				return false;
				});
		botonSalir.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.enter))
					return false;
				botonSalir.setStyle(ResourceManager.textButtonStylePressed);
				SoundManager.playSound("Sound/Menu_Move.wav");
				return false;
				});
		botonSalir.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.exit))
					return false;
				botonSalir.setStyle(ResourceManager.textButtonStyle);
				SoundManager.playSound("Sound/Menu_Move.wav");
				return false;
				});
		tabla.add(botonSalir);
	}

	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		super.render(delta);
	    
	     uiStage.act();
	     
	     uiStage.getBatch().begin();
	     uiStage.getBatch().draw(background, 0, 0, Parametros.getAnchoPantalla(), Parametros.getAltoPantalla());
	     uiStage.getBatch().end();
	     
	     uiStage.draw();

	}
	
	
	
}

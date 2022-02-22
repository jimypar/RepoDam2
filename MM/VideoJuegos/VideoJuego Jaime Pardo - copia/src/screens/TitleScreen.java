package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import game.Demo;
import game.Parametros;
import managers.ResourceManager;
import managers.SaveGameManager;
import managers.SoundManager;
import screens.optionScreens.OptionScreen;
import screens.optionScreens.PlayScreen;

public class TitleScreen extends BScreen{
private Table tabla;
private Texture background;
	public TitleScreen(Demo game) {
		super(game);
		if (SoundManager.currentMusicName!="Menu/MUS_InkwellIsleOne.wav") {
			SoundManager.playMusic("Menu/MUS_InkwellIsleOne.wav");
		}
		
		
		tabla= new Table();
		tabla.setFillParent(true);
		
		this.uiStage.addActor(tabla);
		
		background = new Texture("Menu/Background.png");
		
		TextButton boton=new TextButton("Jugar",ResourceManager.textButtonStyle);
		boton.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.touchDown))
					return false;
				this.dispose();
				game.setScreen(new PlayScreen(game));
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
		TextButton botonOpciones=new TextButton("Opciones",ResourceManager.textButtonStyle);
		botonOpciones.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.touchDown))
					return false;
				this.dispose();
				game.setScreen(new OptionScreen(game));
				return false;
				});
		botonOpciones.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.enter))
					return false;
				botonOpciones.setStyle(ResourceManager.textButtonStylePressed);
				SoundManager.playSound("Sound/Menu_Move.wav");
				return false;
				});
		botonOpciones.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.exit))
					return false;
				botonOpciones.setStyle(ResourceManager.textButtonStyle);
				SoundManager.playSound("Sound/Menu_Move.wav");
				return false;
				});
		tabla.add(botonOpciones);
		tabla.row();
		TextButton botonSave=new TextButton("Guardar",ResourceManager.textButtonStyle);
		botonSave.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.touchDown))
					return false;
				this.dispose();
				SaveGameManager.saveAllParameters();
				botonSave.setText("Guardado");
				return false;
				});
		botonSave.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.enter))
					return false;
				botonSave.setStyle(ResourceManager.textButtonStylePressed);
				SoundManager.playSound("Sound/Menu_Move.wav");
				return false;
				});
		botonSave.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.exit))
					return false;
				botonSave.setStyle(ResourceManager.textButtonStyle);
				SoundManager.playSound("Sound/Menu_Move.wav");
				return false;
				});
		tabla.add(botonSave);
		tabla.row();
		TextButton botonSalir=new TextButton("Salir", ResourceManager.textButtonStyle);
		botonSalir.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.touchDown))
					return false;
				this.dispose();
			Gdx.app.exit();
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

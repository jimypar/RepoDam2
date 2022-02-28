package screens.optionScreens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import game.Demo;
import game.Parametros;
import managers.ResourceManager;
import managers.SoundManager;
import screens.BScreen;
import screens.Boss1;
import screens.Boss2;
import screens.Level1;
import screens.Level2;
import screens.TitleScreen;
import screens.TutorialScreen;

public class PlayScreen extends BScreen{
private Table tabla;
private Texture background;
	public PlayScreen(Demo game) {
		super(game);
		// TODO Auto-generated constructor stub
		
		tabla= new Table();
		tabla.setFillParent(true);
		
		this.uiStage.addActor(tabla);
		
		background = new Texture("Menu/Background.png");
		
		TextButton boton=new TextButton("Tutorial",ResourceManager.textButtonStyle2);
		boton.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.touchDown))
					return false;
				this.dispose();
				game.setScreen(new TutorialScreen(game));
				return false;
				});
		boton.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.enter))
					return false;
				boton.setStyle(ResourceManager.textButtonStylePressed2);
				SoundManager.playSound("Sound/Menu_Move.wav");
				return false;
				});
		boton.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.exit))
					return false;
				boton.setStyle(ResourceManager.textButtonStyle2);
				SoundManager.playSound("Sound/Menu_Move.wav");
				return false;
				});
		tabla.add(boton).colspan(2);
		tabla.row();	
		TextButton boton2=new TextButton("Nivel 1   ",ResourceManager.textButtonStyle2);
		if (!Parametros.level1Unlocked) {
			boton2.setStyle(ResourceManager.textButtonStyleBlock);
			boton2.setTouchable(Touchable.disabled);
		}
		boton2.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.touchDown))
					return false;
				this.dispose();
				game.setScreen(new Level1(game));
				return false;
				});
		boton2.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.enter))
					return false;
				boton2.setStyle(ResourceManager.textButtonStylePressed2);
				SoundManager.playSound("Sound/Menu_Move.wav");
				return false;
				});
		boton2.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.exit))
					return false;
				boton2.setStyle(ResourceManager.textButtonStyle2);
				SoundManager.playSound("Sound/Menu_Move.wav");
				return false;
				});
		tabla.add(boton2);
		TextButton boton3=new TextButton(" Jefe 1",ResourceManager.textButtonStyle2);
		if (!Parametros.boss1Unlocked) {
			boton3.setStyle(ResourceManager.textButtonStyleBlock);
			boton3.setTouchable(Touchable.disabled);
		}
		boton3.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.touchDown))
					return false;
				this.dispose();
				game.setScreen(new Boss1(game));
				return false;
				});
		boton3.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.enter))
					return false;
				boton3.setStyle(ResourceManager.textButtonStylePressed2);
				SoundManager.playSound("Sound/Menu_Move.wav");
				return false;
				});
		boton3.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.exit))
					return false;
				boton3.setStyle(ResourceManager.textButtonStyle2);
				SoundManager.playSound("Sound/Menu_Move.wav");
				return false;
				});
		tabla.add(boton3);
		tabla.row();
		TextButton boton4=new TextButton("Nivel 2    ",ResourceManager.textButtonStyle2);
		if (!Parametros.level2Unlocked) {
			boton4.setStyle(ResourceManager.textButtonStyleBlock);
			boton4.setTouchable(Touchable.disabled);
		}
		boton4.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.touchDown))
					return false;
				this.dispose();
				game.setScreen(new Level2(game));
				return false;
				});
		boton4.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.enter))
					return false;
				boton4.setStyle(ResourceManager.textButtonStylePressed2);
				SoundManager.playSound("Sound/Menu_Move.wav");
				return false;
				});
		boton4.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.exit))
					return false;
				boton4.setStyle(ResourceManager.textButtonStyle2);
				SoundManager.playSound("Sound/Menu_Move.wav");
				return false;
				});
		tabla.add(boton4);
		TextButton boton5=new TextButton(" Jefe 2",ResourceManager.textButtonStyle2);
		if (!Parametros.boss2Unlocked) {
			boton5.setStyle(ResourceManager.textButtonStyleBlock);
			boton5.setTouchable(Touchable.disabled);
		}
		boton5.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.touchDown))
					return false;
				this.dispose();
				LevelBlocked l = new LevelBlocked(0, 0, uiStage, game);
				return false;
				});
		boton5.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.enter))
					return false;
				boton5.setStyle(ResourceManager.textButtonStylePressed2);
				SoundManager.playSound("Sound/Menu_Move.wav");
				return false;
				});
		boton5.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.exit))
					return false;
				boton5.setStyle(ResourceManager.textButtonStyle2);
				SoundManager.playSound("Sound/Menu_Move.wav");
				return false;
				});
		tabla.add(boton5);
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
		tabla.add(botonSalir).colspan(2);
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

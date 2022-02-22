package screens.optionScreens;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

import elements.Element;
import elements.other.FondoMenu;
import game.Demo;
import game.Parametros;
import managers.ResourceManager;
import managers.SoundManager;
import screens.BScreen;
import screens.TitleScreen;

public class InGameScreen extends Element{
public Table tabla;
private Texture background;
private Stage uiStage;
public FondoMenu fondo;

	public InGameScreen(float x, float y, Stage s, Demo game) {
		super(x,y,s);
		
		this.uiStage = s;
		// TODO Auto-generated constructor stub
		
		
		tabla= new Table();
		tabla.setFillParent(true);
		
		fondo = new FondoMenu((Parametros.getAnchoPantalla()/2),Parametros.getAltoPantalla()/4, s);
		
		this.uiStage.addActor(tabla);
		
		
		TextButton boton=new TextButton("Reanudar",ResourceManager.textButtonStyle);
		boton.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.touchDown))
					return false;
				this.tabla.setVisible(false);
				fondo.setEnabled(false);
				Parametros.pausa=false;
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
				this.tabla.setVisible(false);
				fondo.setEnabled(false);
				new SoundOptionsInGame(x,y,s,game);
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
		TextButton botonSalir=new TextButton("Salir", ResourceManager.textButtonStyle);
		botonSalir.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.touchDown))
					return false;
				fondo.setEnabled(false);
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
	    
		 if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
	    	 this.tabla.setVisible(false);
	    	 fondo.setEnabled(false);
	    	 Parametros.pausa=false;
		}
		
	     uiStage.act();
	     
	     uiStage.draw();
	     
	    

	}


	@Override
	public void draw(Batch batch, float parentAlpha) {
		// TODO Auto-generated method stub
		super.draw(batch, parentAlpha);
	}
	
	
	
	
	
}

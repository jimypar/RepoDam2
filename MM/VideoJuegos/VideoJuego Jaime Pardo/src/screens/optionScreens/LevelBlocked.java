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

public class LevelBlocked extends Element{
public Table tabla;
private Texture background;
private Stage uiStage;
public FondoMenu fondo;

	public LevelBlocked(float x, float y, Stage s, Demo game) {
		super(x,y,s);
		
		this.uiStage = s;
		
		tabla= new Table();
		tabla.setFillParent(true);
		
		fondo = new FondoMenu((Parametros.getAnchoPantalla()/2),Parametros.getAltoPantalla()/4, s);
		
		this.uiStage.addActor(tabla);
		
		
		TextButton boton2=new TextButton("Este nivel esta \n en desarrollo",ResourceManager.textButtonStyle2);
		tabla.add(boton2);
		tabla.row().pad(20);
		TextButton botonSalir=new TextButton("Volver", ResourceManager.textButtonStyle);
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

package screens.optionScreens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import elements.Element;
import elements.other.FondoMenu;
import game.Demo;
import game.Parametros;
import managers.ResourceManager;
import managers.SoundManager;
import screens.BScreen;
import screens.TitleScreen;

public class SoundOptionsInGame extends Element{
private Table tabla;
private Texture background;
private Stage uiStage;
private float volMusic;
private float volSound;
Label vol1;
Label vol2;

	public SoundOptionsInGame(float x, float y, Stage s, Demo game) {
		super(x,y,s);
		
		this.uiStage = s;
		// TODO Auto-generated constructor stub
		
		
		tabla= new Table();
		tabla.setFillParent(true);
		
		FondoMenu fondo = new FondoMenu((Parametros.getAnchoPantalla()/2),Parametros.getAltoPantalla()/4, s);
		
		this.uiStage.addActor(tabla);
		
		tabla.row().colspan(3);
		Label musica=new Label("Musica",ResourceManager.buttonStyle);
		tabla.add(musica);
		tabla.row();
		TextButton botonBajar=new TextButton("<-",ResourceManager.textButtonStyle2);
		botonBajar.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.touchDown))
					return false;
				if (Parametros.musicVolume>0.01) {
					Parametros.musicVolume -= 0.1f;
				}
				SoundManager.applyVolume();
				this.vol1.setText(String.format("%.1f", Parametros.musicVolume));
				return false;
				});
		tabla.add(botonBajar);
		vol1=new Label(String.format("%.1f", Parametros.musicVolume), ResourceManager.buttonStyle2);
		tabla.add(vol1);
		TextButton botonSubir=new TextButton("->",ResourceManager.textButtonStyle2);
		botonSubir.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.touchDown))
					return false;
				if (Parametros.musicVolume<1) {
					Parametros.musicVolume += 0.1f;
				}
				SoundManager.applyVolume();
				this.vol1.setText(String.format("%.1f", Parametros.musicVolume));
				return false;
				});
		tabla.add(botonSubir);
		tabla.row().colspan(3);
		Label efectos=new Label("Efectos",ResourceManager.buttonStyle);
		tabla.add(efectos);
		tabla.row();
		TextButton botonBajar2=new TextButton("<-",ResourceManager.textButtonStyle2);
		botonBajar2.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.touchDown))
					return false;
				if (Parametros.soundVolume>0.01) {
					Parametros.soundVolume -= 0.1f;
				}
				SoundManager.playDemoSound();
				this.vol2.setText(String.format("%.1f", Parametros.soundVolume));
				return false;
				});
		tabla.add(botonBajar2);
		vol2=new Label(String.format("%.1f", Parametros.soundVolume), ResourceManager.buttonStyle2);
		tabla.add(vol2);
		TextButton botonSubir2=new TextButton("->",ResourceManager.textButtonStyle2);
		botonSubir2.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.touchDown))
					return false;
				if (Parametros.soundVolume<1) {
					Parametros.soundVolume += 0.1f;
				}
				SoundManager.playDemoSound();
				this.vol2.setText(String.format("%.1f", Parametros.soundVolume));
				return false;
				});
		tabla.add(botonSubir2);
		tabla.row().colspan(3);
		TextButton botonSalir=new TextButton("Volver", ResourceManager.textButtonStyle);
		botonSalir.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.touchDown))
					return false;
				this.tabla.setVisible(false);
				fondo.setEnabled(false);
				new InGameScreen(x,y,s,game);
				return false;
				});
		tabla.add(botonSalir);
	}

	
	@Override
	public void render(float delta) {
		
		
		
	    
	     uiStage.act();
	     
//	     uiStage.getBatch().begin();
//	     uiStage.getBatch().draw(background, 0, 0, Parametros.getAnchoPantalla(), Parametros.getAltoPantalla());
//	     uiStage.getBatch().end();
	     
	     uiStage.draw();

	}


	@Override
	public void draw(Batch batch, float parentAlpha) {
		// TODO Auto-generated method stub
		super.draw(batch, parentAlpha);
	}
	
	
	
	
	
}

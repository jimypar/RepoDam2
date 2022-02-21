package screens.optionScreens;

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

public class SoundOptions extends BScreen{
private Table tabla;
private Texture background;
private float volMusic;
private float volSound;
Label vol1;
Label vol2;

	public SoundOptions(Demo game) {
		super(game);
		// TODO Auto-generated constructor stub
		
		
		tabla= new Table();
		tabla.setFillParent(true);
		
		this.uiStage.addActor(tabla);
		
		background = new Texture("Menu/Background.png");
		
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
				return false;
				});
		botonBajar.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.enter))
					return false;
				botonBajar.setStyle(ResourceManager.textButtonStylePressed2);
				SoundManager.playSound("Sound/Menu_Move.wav");
				return false;
				});
		botonBajar.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.exit))
					return false;
				botonBajar.setStyle(ResourceManager.textButtonStyle2);
				SoundManager.playSound("Sound/Menu_Move.wav");
				return false;
				});
		tabla.add(botonBajar);
		vol1=new Label("", ResourceManager.buttonStyle2);
		tabla.add(vol1);
		TextButton botonSubir=new TextButton("->",ResourceManager.textButtonStyle2);
		botonSubir.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.touchDown))
					return false;
				if (Parametros.musicVolume<1) {
					Parametros.musicVolume += 0.1f;
				}
				SoundManager.applyVolume();
				return false;
				});
		botonSubir.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.enter))
					return false;
				botonSubir.setStyle(ResourceManager.textButtonStylePressed2);
				SoundManager.playSound("Sound/Menu_Move.wav");
				return false;
				});
		botonSubir.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.exit))
					return false;
				botonSubir.setStyle(ResourceManager.textButtonStyle2);
				SoundManager.playSound("Sound/Menu_Move.wav");
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
				return false;
				});
		botonBajar2.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.enter))
					return false;
				botonBajar2.setStyle(ResourceManager.textButtonStylePressed2);
				SoundManager.playSound("Sound/Menu_Move.wav");
				return false;
				});
		botonBajar2.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.exit))
					return false;
				botonBajar2.setStyle(ResourceManager.textButtonStyle2);
				SoundManager.playSound("Sound/Menu_Move.wav");
				return false;
				});
		tabla.add(botonBajar2);
		vol2=new Label("", ResourceManager.buttonStyle2);
		tabla.add(vol2);
		TextButton botonSubir2=new TextButton("->",ResourceManager.textButtonStyle2);
		botonSubir2.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.touchDown))
					return false;
				if (Parametros.soundVolume<1) {
					Parametros.soundVolume += 0.1f;
				}
				SoundManager.playDemoSound();
				return false;
				});
		botonSubir2.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.enter))
					return false;
				botonSubir2.setStyle(ResourceManager.textButtonStylePressed2);
				SoundManager.playSound("Sound/Menu_Move.wav");
				return false;
				});
		botonSubir2.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.exit))
					return false;
				botonSubir2.setStyle(ResourceManager.textButtonStyle2);
				SoundManager.playSound("Sound/Menu_Move.wav");
				return false;
				});
		tabla.add(botonSubir2);
		tabla.row().colspan(3);
		TextButton botonSalir=new TextButton("Volver", ResourceManager.textButtonStyle);
		botonSalir.addListener(
				(Event e)->{if(!(e instanceof InputEvent)|| !((InputEvent)e).getType().equals(Type.touchDown))
					return false;
				this.dispose();
				game.setScreen(new OptionScreen(game));
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
		
		this.vol1.setText(String.format("%.1f", Parametros.musicVolume));
		this.vol2.setText(String.format("%.1f", Parametros.soundVolume));
		
	     uiStage.act();
	     
	     uiStage.getBatch().begin();
	     uiStage.getBatch().draw(background, 0, 0, Parametros.getAnchoPantalla(), Parametros.getAltoPantalla());
	     uiStage.getBatch().end();
	     
	     uiStage.draw();

	}
	
	
	
}

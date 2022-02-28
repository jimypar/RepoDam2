package screens.endScreens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;

import game.Demo;
import game.Parametros;
import managers.ResourceManager;
import managers.SoundManager;
import screens.BScreen;
import screens.TitleScreen;

public class WinScreen extends BScreen{
private Table tabla;
private Texture background;
private int maxVidas;
private String pacifico;
private int notaNum;
private String nota;
private float tiempo1;
private float tiempo2;
private float tiempo3;



	public WinScreen(Demo game, int level) {
		super(game);
		
		SoundManager.playMusic("WinScreen/MUS_VictoryScreen.ogg");
				
		this.notaNum=100;
		
		calcularNota(level);
		
		tabla= new Table();
		tabla.setFillParent(true);
		tabla.pad(30);
		
		this.uiStage.addActor(tabla);
		
		background = new Texture("WinScreen/winscreenbg.png");
		
		Label txt1=new Label("Tiempo: ",ResourceManager.buttonStyle2);
		tabla.add(txt1);
		
		Label tiempo=new Label(makeReadable((int) Parametros.playTime),ResourceManager.buttonStyle2);
		tabla.add(tiempo);
		
		tabla.row();
		
		Label txt2=new Label("Vidas: ",ResourceManager.buttonStyle2);
		tabla.add(txt2);
		
		Label vida=new Label(Parametros.vida+"/"+this.maxVidas,ResourceManager.buttonStyle2);
		tabla.add(vida);
		
		tabla.row();
		
		Label txt3=new Label("Pacifico: ",ResourceManager.buttonStyle2);
		tabla.add(txt3);
		
		Label pacifico=new Label(this.pacifico,ResourceManager.buttonStyle2);
		tabla.add(pacifico);
		
		tabla.row().padTop(30).padBottom(30);
		
		Label txt4=new Label("NOTA: ",ResourceManager.buttonStyle2);
		tabla.add(txt4);
		
		Label note=new Label(this.nota,ResourceManager.buttonStyle2);
		tabla.add(note);
		
		tabla.row().spaceBottom(40);
		
		TextButton botonSalir=new TextButton("Continuar", ResourceManager.textButtonStyle);
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
	
	
	
	private void calcularNota(int level) {
		
		switch (level) {
		case 0:
			this.tiempo1=15;
			this.tiempo2=20;
			this.tiempo3=30;
			this.maxVidas=3;
			break;
		case 1:
			this.tiempo1=40;
			this.tiempo2=100;
			this.tiempo3=150;
			this.maxVidas=3;
			break;
		case 2:
			this.tiempo1=100;
			this.tiempo2=150;
			this.tiempo3=200;
			this.maxVidas=3;
			break;
		case 3:
			this.tiempo1=45;
			this.tiempo2=120;
			this.tiempo3=200;
			this.maxVidas=4;
			break;
		case 4:
			this.tiempo1=45;
			this.tiempo2=120;
			this.tiempo3=200;
			this.maxVidas=3;
			break;
		default:
			break;
		}
		
		if (Parametros.playTime>tiempo3) {
			this.notaNum-=60;
		}
		if (Parametros.playTime<tiempo3 && Parametros.playTime>tiempo2) {
			this.notaNum-=40;
		}
		if (Parametros.playTime<tiempo2 && Parametros.playTime>tiempo1) {
			this.notaNum-=20;
		}
		
		if (Parametros.pacifico==true) {
			this.pacifico="SI";
		}else {
			this.notaNum-=20;
			this.pacifico="NO";
		}
		
		if (Parametros.vida==1) {
			this.notaNum-=20;
		}
		if (Parametros.vida==2) {
			this.notaNum-=10;
		}
		if (Parametros.vida==4) {
			this.notaNum+=20;
		}
		
		
		if (this.notaNum<25) {
			this.nota="D";
		}
		if (this.notaNum>25 && this.notaNum<=50) {
			this.nota="C";
		}
		if (this.notaNum>50 && this.notaNum<=75) {
			this.nota="B";
		}
		if (this.notaNum>75 && this.notaNum<=100) {
			this.nota="A";
		}
		if (this.notaNum>100) {
			this.nota="A+";
		}
		
	}
	
	public static String makeReadable(int seconds) {
	    
	    return String.format("%02d:%02d", (seconds % 3600) / 60, (seconds % 60));
	    
	  }
	
	
}

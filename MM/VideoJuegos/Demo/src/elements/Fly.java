package elements;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.Array;

import game.Parametros;
import screens.GameScreen;

public class Fly extends Enemigo{

	private float tiempoComportamiento;
	private float cuentaComportamiento;
	
	private float distanciaVision;
	private float aceleracion;
	
	private float tiempoDisparo;
	private float cuentaDisparo;
	private Array<Bala> balas;
	private int numBalas;
	private int balaActual;
	private int numero;
	private GameScreen nivel;
	private int dano=5;
	
	boolean persiguiendo;
	LabelStyle mensajeStyle;
	private Label mensaje;
	

	
	public Fly(float x, float y, Stage s, GameScreen nivel) {
		super(x, y, s, nivel);
		// TODO Auto-generated constructor stub
		persiguiendo=false;
		loadFullAnimation("enemies/blob_jump.png", 1, 7, 0.15f, true);
		cuentaComportamiento=0;
		tiempoComportamiento=0.5f;
		//distanciaVision=72;
		distanciaVision=72;
		numBalas=5;
		balaActual=0;
		balas=new Array<Bala>();
		tiempoDisparo=3;
		cuentaDisparo=0;
		
		aceleracion=50;
		this.maxSpeed=20;
		
		this.nivel=nivel;
		
		
		
		BitmapFont font=new BitmapFont();
		font.getData().setScale(0.3f);
		mensajeStyle=new LabelStyle(font,Color.ORANGE); 
		
		
	mensaje= new Label("",mensajeStyle);
	s.addActor(mensaje);
	
	
		for(int i=0; i<numBalas; i++) {
			balas.add(new Bala(0,0,s,100));
			
		}
		
	}
	
	
	
	
	
	@Override
	public void act(float delta) {
		// TODO Auto-generated method stub
		super.act(delta);
		if(this.getEnabled()) {
			
			if(persiguiendo) {
				perseguir();
				if(cuentaDisparo<=0) {
					System.out.println("Te disparo");
					mensaje.setText("Te disparo");
					mensaje.setPosition(this.getX(), this.getY()+this.getHeight());
					disparar();
				}
				else {
					if(cuentaDisparo<tiempoDisparo/2) {
						mensaje.setText("");
					}
					cuentaDisparo-=delta;
					
				}
				
			}else {
				if(Math.sqrt(Math.pow(this.getX()-Parametros.jugadorx, 2)
						+Math.pow(this.getY()-Parametros.jugadory, 2))<distanciaVision) {
					System.out.println("Te persigo");
					persiguiendo=true;				
					
				}
				else { 
					if(cuentaComportamiento<=0) {
					
					numero=(int)Math.floor(Math.random()*5+1);
					cuentaComportamiento=tiempoComportamiento;
					}else {
						cuentaComportamiento-=delta;
					}
					
					switch(numero) {
					case 1: acceleration.add(-aceleracion, 0);
						break;
					case 2: acceleration.add(aceleracion, 0);
					break;
					case 3: acceleration.add(0, -aceleracion);
					break;
					case 4: acceleration.add(0, aceleracion);
					break;
					case 5: acceleration.add(0, 0);
						this.cuentaComportamiento=0f;
					break;
					
					}
					
					
				}
			
				
				
				
			}
			this.applyPhysics(delta);
			
			
			
			
			
			
			
			
			
		}
		for(Bala bala:balas) {
			if(bala.getEnabled() && bala.overlaps(nivel.player)) {
				nivel.player.recibeDano(dano);
				bala.setEnabled(false);
				
				
			}
			
		}
		
	
		
	}




	public void perseguir() {
		
		if( Parametros.jugadorx<this.getX()) {
			acceleration.add(-aceleracion,0);
			
			
		} else if( Parametros.jugadorx>this.getX()) {
			
			acceleration.add(aceleracion,0);
			
		}
		if(Parametros.jugadory<this.getY()) {
			acceleration.add(0,-aceleracion);
		} else if(Parametros.jugadory>this.getY()) {
			acceleration.add(0,aceleracion);}
		
		
		
		
		
	}
	

	public void disparar() {
		
		float x = Parametros.jugadorx-this.getX();
		
		float y = Parametros.jugadory-this.getY();
		
		
		Vector2 vector=new Vector2(x,y).nor();
		cuentaDisparo=tiempoDisparo;
		balas.get(this.balaActual).disparar(vector.x,vector.y, this.getX(),this.getY());
		balaActual++;
		balaActual=balaActual%numBalas;
		
		
		
	}
	
	

}

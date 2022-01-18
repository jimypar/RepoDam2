package elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

import game.Parametros;

public class Player extends Element{
private Animation<TextureRegion> frente;
private Animation<TextureRegion> espalda;
private Animation<TextureRegion> drcha;
private Animation<TextureRegion> izqda;
private Array<Bala> balas;
public Element pies;
public boolean tocoSuelo;


private int totalBalas = 15;
private int balaActual = 0;
private float cooldownDisparo =0.35f;
private float tiempoDisparo = cooldownDisparo*2;


private float walkingSpeed=150;


	public Player(float x, float y, Stage s) {
		super(x, y, s);
		this.maxSpeed=150;
		this.deceleration=200;
		tocoSuelo=false;
		
		// TODO Auto-generated constructor stub
		frente=loadFullAnimation("player/frenteWalk.png",4,1,0.2f,true);
		espalda=loadFullAnimation("player/espaldaWalk.png",1,4,0.2f,true);
		drcha=this.loadFullAnimation("player/derechawalk.png", 1, 4, 0.2f, true);
		izqda=this.loadFullAnimation("player/izquieredawalk.png", 4, 1, 0.2f, true);
		this.setPolygon(8);
		
		
		pies=new Element(0, 0, s,this.getWidth() ,this.getHeight()/10 );
		pies.setRectangle();
		
		
		balas = new Array<Bala>();
		for (int i = 0; i < totalBalas; i++) {
			balas.add(new Bala(0,0,s));
		}
		
	}


	@Override
	public void act(float delta) {
		// TODO Auto-generated method stub
		super.act(delta);
		
		
		controles();
		
		//aplico graviedad
		this.acceleration.add(0,Parametros.gravedad);
		this.applyPhysics(delta);
		colocarPies();
		
		this.tiempoDisparo+=delta;
		
	}
	
	private void controles() {
		/*if(Gdx.input.isKeyPressed(Keys.UP)) {
			this.setAnimation(espalda);
			this.acceleration.add(0,walkingSpeed);
		}
	if(Gdx.input.isKeyPressed(Keys.DOWN)) {
			this.setAnimation(frente);
			this.acceleration.add(0,-walkingSpeed);
		}*/
		
	if(Gdx.input.isKeyPressed(Keys.LEFT)) {
		this.setAnimation(izqda);
		this.acceleration.add(-walkingSpeed,0);
	}
	
	if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
		this.setAnimation(drcha);
		this.acceleration.add(walkingSpeed,0);
	}
	if(Gdx.input.isKeyJustPressed(Keys.SPACE) && tocoSuelo) {
		salta();
		
	}
	if(Gdx.input.isKeyPressed(Keys.Z) && tiempoDisparo>=cooldownDisparo) {
		dispara();
		
	}
	
		
	}
	
	
	
	private void dispara() {
		
		balas.get(this.balaActual).disparar();
		
		balaActual = (balaActual+1)%this.totalBalas;
		
		tiempoDisparo = 0;
	}


	private void colocarPies() {
		this.pies.setPosition(this.getX(), this.getY());
		
		
	}
	
	private void salta() {
		this.acceleration.add(0,20000);
		this.tocoSuelo=false;
		
		
	}
	
	

}

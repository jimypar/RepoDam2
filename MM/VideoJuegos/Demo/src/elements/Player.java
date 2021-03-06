package elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

import game.Parametros;

public class Player extends Element{
private Animation<TextureRegion> frente;
private Animation<TextureRegion> espalda;
private Animation<TextureRegion> drcha;
private Animation<TextureRegion> izqda;
private Array<Bala> balas;

//gesti?n de vectores
private int totalBalas=10;
private int balaActual=0;



//gesti?n de tiempos
private float cooldownDisparo=0.01f;
private float tiempoDisparo=cooldownDisparo*2;

public Element pies;
public boolean tocoSuelo;

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
		
		
		pies=new Element(0, 0, s,this.getWidth()/4 ,this.getHeight()/10 );
		pies.setRectangle();
		
		
		
		
		balas=new Array<Bala>();
		for(int i=0;i<this.totalBalas;i++) {
			balas.add(new Bala(0, 0, s,1));
			
			
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
		if(Gdx.input.isKeyJustPressed(Keys.UP)) {
			Parametros.vida+=5;
		}
	if(Gdx.input.isKeyJustPressed(Keys.DOWN)) {
		Parametros.vida-=5;
		}
		
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
	
	
	if(Gdx.input.isKeyJustPressed(Keys.Z) && tiempoDisparo>=cooldownDisparo ) {
		dispara();
		
	}
	
		
	}
	
	
	
	@Override
	public void applyPhysics(float dt) {
		// TODO Auto-generated method stub
		// apply acceleration
       
		  if(tocoSuelo & this.getVelocity().y<0) {
				
				this.acceleration.y=0;
	        }
		
		
		velocity.add( acceleration.x * dt, acceleration.y * dt );
      
        float speed = velocity.len();

        
        
      
        
        // decrease speed (decelerate) when not accelerating
      
        
        if (acceleration.len() == 0)
            speed -= deceleration * dt;
       

        // keep speed within set bounds
        speed = MathUtils.clamp(speed, 0, maxSpeed);

        // update velocity
        if (velocity.len() == 0)
            velocity.set(speed, 0);
        else
            velocity.setLength(speed);
    

        // update position according to value stored in velocity vector
        moveBy( velocity.x * dt, velocity.y * dt );

        // reset acceleration
        acceleration.set(0,0);  
		
		
		
		
		
			
			
		
	}


	public void colocarPies() {
		this.pies.setPosition(this.getX()+this.getWidth()/2-this.getWidth()/8, this.getY());
		
		
	}
	
	private void salta() {
		this.acceleration.add(0,20000);
		this.tocoSuelo=false;
		
		
	}
	
	private void dispara() {
		balas.get(this.balaActual).disparar(150,0, this.getX(),this.getY());
		balaActual=(balaActual+1)%this.totalBalas;
		tiempoDisparo=0;
		
		
	}

	public void recibeDano(int dano) {
		Parametros.vida-=dano;
		
	}
}

package elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;

import game.Parametros;

public class Player extends Element {

	private Animation<TextureRegion> frente;
	private Animation<TextureRegion> espalda;
	private Animation<TextureRegion> derecha;
	private Animation<TextureRegion> izquierda;
	public Element pies;
	public boolean tocoSuelo;
	private float walkingSpeed = 200;

	public Player(float x, float y, Stage s) {
		super(x, y, s);

		this.maxSpeed = 500;
		this.deceleration = 200000;
		tocoSuelo = false;
		
		
		frente = loadFullAnimation("player/frenteWalk.png", 4, 1, 0.2f, true);
		espalda = loadFullAnimation("player/espaldaWalk.png", 1, 4, 0.2f, true);
		derecha = loadFullAnimation("player/derechawalk.png", 1, 4, 0.2f, true);
		izquierda = loadFullAnimation("player/izquieredawalk.png", 4, 1, 0.2f, true);

		this.setPolygon(8);
		
		this.setScale(5);
		
		pies = new Element(0,0,s,this.getWidth(),this.getHeight()/10);
		pies.setRectangle();
		

	}

	@Override
	public void act(float delta) {
		super.act(delta);
		this.acceleration.add(0,Parametros.gravedad);
		controles();
		this.applyPhysics(delta);
		colocarPies();

	}

	private void controles() {

		if (Gdx.input.isKeyPressed(Keys.W) || Gdx.input.isKeyPressed(Keys.UP)) {
			this.setAnimation(espalda);
//			this.acceleration.add(0,walkingSpeed);
		}

		if (Gdx.input.isKeyPressed(Keys.S) || Gdx.input.isKeyPressed(Keys.DOWN)) {
			this.setAnimation(frente);
//			this.acceleration.add(0,-walkingSpeed);
		}

		if (Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.LEFT)) {
			this.setAnimation(izquierda);
			this.acceleration.add(-walkingSpeed,0);

		}

		if (Gdx.input.isKeyPressed(Keys.D) || Gdx.input.isKeyPressed(Keys.RIGHT)) {
			this.setAnimation(derecha);
			this.acceleration.add(walkingSpeed,0);
		}
		
		if (Gdx.input.isKeyJustPressed(Keys.SPACE) && tocoSuelo) {
			salta();
		}

	}
	
	private void colocarPies() {
		
		this.pies.setPosition(this.getX(), this.getY()-(this.getHeight()+20));
		
	}
	
	private void salta() {
			this.acceleration.add(0,20000);
			tocoSuelo = false;		
	}
}

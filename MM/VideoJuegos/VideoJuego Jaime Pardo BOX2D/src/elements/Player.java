package elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;

import game.Parametros;

public class Player extends Element {
	private Animation<TextureRegion> stayR;
	private Animation<TextureRegion> stayL;
	private Animation<TextureRegion> runR;
	private Animation<TextureRegion> runL;
	private Animation<TextureRegion> jumpR;
	private Animation<TextureRegion> jumpL;

	public Element pies;
	public boolean tocoSuelo;
	public boolean direccion;

	private float walkingSpeed = 500;

	public Player(float x, float y, Stage s) {
		super(x, y, s);
		this.maxSpeed = 100;
		this.deceleration = 0;
		tocoSuelo = false;
		
	
		
		this.setScale(0.5f);
		

		cargarAnimaciones();

		pies = new Element(0, 0, s, this.getWidth() / 2, this.getHeight() / 20);
		pies.setRectangle();
	}

	private void cargarAnimaciones() {

		stayR = this.loadFullAnimation("Cuphead/Idle/spriteIdleR.png", 8, 1, 0.1f, true);
		stayL = this.loadFullAnimation("Cuphead/Idle/spriteIdleL.png", 8, 1, 0.1f, true);
		runL = this.loadFullAnimation("Cuphead/Run/Normal/spriteRunR.png", 16, 1, 0.1f, true);
		runR = this.loadFullAnimation("Cuphead/Run/Normal/spriteRunL.png", 16, 1, 0.1f, true);
		jumpR = this.loadFullAnimation("Cuphead/Jump/Cuphead/spriteJumpR.png", 8, 1, 0.1f, true);
		jumpL = this.loadFullAnimation("Cuphead/Jump/Cuphead/spriteJumpL.png", 8, 1, 0.1f, true);

	}

	@Override
	public void act(float delta) {
		super.act(delta);
		controles();

		// aplico graviedad
		this.acceleration.add(0, Parametros.gravedad);
		this.applyPhysics(delta);
		colocarPies();

	}

	private void controles() {
		
		
		if (Gdx.input.isKeyPressed(Keys.A) && !tocoSuelo) {
			direccion = false;
			this.acceleration.add(-walkingSpeed, 0);
		}
								
		if (Gdx.input.isKeyPressed(Keys.A) && tocoSuelo) {
			direccion = false;
			this.setAnimation(runR);
			this.acceleration.add(-walkingSpeed, 0);
		}
		
		if (Gdx.input.isKeyPressed(Keys.D) && !tocoSuelo) {
			direccion = true;
			this.acceleration.add(walkingSpeed, 0);
		}

		if (Gdx.input.isKeyPressed(Keys.D) && tocoSuelo) {
			direccion = true;
			this.setAnimation(runL);
			this.acceleration.add(walkingSpeed, 0);
		}

		if (Gdx.input.isKeyPressed(Keys.SPACE) && tocoSuelo) {
			salta();
		}

		if (!Gdx.input.isKeyPressed(Keys.D) && !Gdx.input.isKeyPressed(Keys.A) && tocoSuelo) {
			if (direccion) {
				this.setAnimation(stayL);
				this.acceleration.add(0, 0);
			} else {
				this.setAnimation(stayR);
				this.acceleration.add(0, 0);
			}

		}

	}

	private void colocarPies() {
		this.pies.setPosition(this.getX() + (this.getWidth() / 4), this.getY() + (this.getHeight() / 4));
	}

	private void salta() {
		if (direccion) {
			this.setAnimation(jumpR);
			this.acceleration.add(walkingSpeed, 3000000);
		} else {
			this.setAnimation(jumpL);
			this.acceleration.add(-walkingSpeed, 3000000);
		}
		
		this.tocoSuelo = false;

	}

}

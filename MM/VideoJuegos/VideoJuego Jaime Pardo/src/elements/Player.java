package elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import game.Parametros;
import managers.SoundManager;

public class Player extends Element {
	// Animaciones Player
	private Animation<TextureRegion> stayR;
	private Animation<TextureRegion> stayL;
	private Animation<TextureRegion> runR;
	private Animation<TextureRegion> runL;
	private Animation<TextureRegion> jumpR;
	private Animation<TextureRegion> jumpL;
	private Animation<TextureRegion> duckIdleR;
	private Animation<TextureRegion> duckIdleL;
	private Animation<TextureRegion> shootUpIdleR;
	private Animation<TextureRegion> shootUpIdleL;
	private Animation<TextureRegion> shootIdleR;
	private Animation<TextureRegion> shootIdleL;
	private Animation<TextureRegion> shootDuckR;
	private Animation<TextureRegion> shootDuckL;
	private Animation<TextureRegion> shootRunR;
	private Animation<TextureRegion> shootRunL;
	private Animation<TextureRegion> shootDiagonalRunR;
	private Animation<TextureRegion> shootDiagonalRunL;
	private Animation<TextureRegion> dashL;
	private Animation<TextureRegion> dashR;
	private Animation<TextureRegion> hitL;	
	private Animation<TextureRegion> hitR;	

	// Balas
	private Array<Bala> balas;
	private int totalBalas = 25;
	private int balaActual = 0;
	private float cooldownDisparo = 0.12f;
	private float tiempoDisparo = cooldownDisparo * 2;

	private float cooldownDash = 0.5f;
	private float tiempoDash = cooldownDash * 3;
	
	private float cooldownHit = 2f;
	private float tiempoHit = cooldownHit * 3;

	public Element pies;
	public boolean tocoSuelo;
	public boolean direccion = true;
	private boolean controles;
	private boolean controlesHit;


	private float walkingSpeed = 500;
	private float jumpSpeed = 500000;
	public boolean door;

	public Player(float x, float y, Stage s) {
		super(x, y, s);
		tocoSuelo = false;
		controles = true;
		
		this.maxSpeed = 1000;

		cargarAnimaciones();
		cargarSonido();

		pies = new Element(0, 0, s, this.getWidth() / 2, this.getHeight() / 20);
		pies.setRectangle();

		balas = new Array<Bala>();
		for (int i = 0; i < totalBalas; i++) {
			balas.add(new Bala(0, 0, s));
		}

	}

	private void cargarSonido() {
				
	}

	public Array<Bala> getBalas() {
		return balas;
	}

	@Override
	public void act(float delta) {
		super.act(delta);

		if (tiempoDash >= cooldownDash) {
			controles = true;
		}
				
		if (controles && tiempoHit >= cooldownHit/2) {
			controles();
		}

		colocarPies();

		this.setPolygon(10, this.getWidth(), this.getHeight(), 0, 0);

		// aplico graviedad
		this.acceleration.add(0, Parametros.gravedad);
		this.applyPhysics(delta);

		this.tiempoDisparo += delta;
		this.tiempoDash += delta;
		this.tiempoHit += delta;

	}

	private void controles() {

//		MOVIMIENTO IZQUIERDA DERECHA

		if (Gdx.input.isKeyPressed(Keys.A) && !tocoSuelo) {
			direccion = false;
			this.setAnimation(jumpL);
			this.velocity.x = -walkingSpeed/2;
		}

		if (Gdx.input.isKeyPressed(Keys.A) && tocoSuelo) {
			direccion = false;
			this.setAnimation(runR);
			this.velocity.x = -walkingSpeed;
		}

		if (Gdx.input.isKeyPressed(Keys.D) && !tocoSuelo) {
			direccion = true;
			this.setAnimation(jumpR);
			this.velocity.x = walkingSpeed/2;
		}

		if (Gdx.input.isKeyPressed(Keys.D) && tocoSuelo) {
			direccion = true;
			this.setAnimation(runL);
			this.velocity.x = walkingSpeed;
		}

//		DASH

		if (Gdx.input.isKeyJustPressed(Keys.SHIFT_LEFT) && !Gdx.input.isKeyPressed(Keys.S) && this.velocity.x != 0
				&& tocoSuelo) {
			if (direccion) {
				dashR();
			} else {
				dashL();
			}
			
			SoundManager.playSound("Cuphead/Sound/sfx_player_dash_01.wav");
		}

		if (Gdx.input.isKeyJustPressed(Keys.SHIFT_LEFT) && !Gdx.input.isKeyPressed(Keys.S) && this.velocity.x != 0
				&& !tocoSuelo) {
			if (direccion) {
				dashR();
			} else {
				dashL();
			}
			
			
			SoundManager.playSound("Cuphead/Sound/sfx_player_dash_01.wav");
		}

//		ESTATICO

		if (!Gdx.input.isKeyPressed(Keys.D) && !Gdx.input.isKeyPressed(Keys.A) && tocoSuelo) {
			if (direccion) {
				this.setAnimation(stayR);
				this.velocity.x = 0;
			} else {
				this.setAnimation(stayL);
				this.velocity.x = 0;
			}

		}

//	AGACHARSE

		if (Gdx.input.isKeyPressed(Keys.S) && tocoSuelo) {
			if (direccion) {
				this.setAnimation(duckIdleR);
				this.velocity.x = 0;
			} else {
				this.setAnimation(duckIdleL);
				this.velocity.x = 0;
			}

		}

		if (Gdx.input.isKeyPressed(Keys.S) && Gdx.input.isKeyPressed(Keys.D) && tocoSuelo) {

			this.setAnimation(duckIdleR);
			this.velocity.x = 0;

		}

		if (Gdx.input.isKeyPressed(Keys.S) && Gdx.input.isKeyPressed(Keys.A) && tocoSuelo) {
			this.setAnimation(duckIdleL);
			this.velocity.x = 0;

		}

//	DISPAROS MOVIMIENTO

		if (Gdx.input.isKeyPressed(Keys.ENTER) && Gdx.input.isKeyPressed(Keys.A) && tocoSuelo) {
			direccion = false;
			this.setAnimation(shootRunL);
			this.acceleration.add(-walkingSpeed, 0);
		}

		if (Gdx.input.isKeyPressed(Keys.ENTER) && Gdx.input.isKeyPressed(Keys.D) && tocoSuelo) {
			direccion = true;
			this.setAnimation(shootRunR);
			this.acceleration.add(walkingSpeed, 0);
		}

		if (Gdx.input.isKeyPressed(Keys.ENTER) && Gdx.input.isKeyPressed(Keys.A) && Gdx.input.isKeyPressed(Keys.W)
				&& tocoSuelo) {
			direccion = false;
			this.setAnimation(shootDiagonalRunL);
			this.acceleration.add(-walkingSpeed, 0);
		}

		if (Gdx.input.isKeyPressed(Keys.ENTER) && Gdx.input.isKeyPressed(Keys.D) && Gdx.input.isKeyPressed(Keys.W)
				&& tocoSuelo) {
			direccion = true;
			this.setAnimation(shootDiagonalRunR);
			this.acceleration.add(walkingSpeed, 0);
		}

		// DISPAROS ESTATICO

		if (Gdx.input.isKeyPressed(Keys.ENTER) && !Gdx.input.isKeyPressed(Keys.D) && !Gdx.input.isKeyPressed(Keys.A)
				&& tocoSuelo) {
			if (direccion) {
				this.setAnimation(shootIdleR);
				this.velocity.x = 0;
			} else {
				this.setAnimation(shootIdleL);
				this.velocity.x = 0;
			}
		}

		if (Gdx.input.isKeyPressed(Keys.ENTER) && Gdx.input.isKeyPressed(Keys.W) && !Gdx.input.isKeyPressed(Keys.D)
				&& !Gdx.input.isKeyPressed(Keys.A) && tocoSuelo) {
			if (direccion) {
				this.setAnimation(shootUpIdleR);
				this.velocity.x = 0;
			} else {
				this.setAnimation(shootUpIdleL);
				this.velocity.x = 0;
			}
		}

		// DISPARO AGACHADO

		if (Gdx.input.isKeyPressed(Keys.ENTER) && Gdx.input.isKeyPressed(Keys.S) && tocoSuelo) {
			if (direccion) {
				this.setAnimation(shootDuckR);
				this.velocity.x = 0;
			} else {
				this.setAnimation(shootDuckL);
				this.velocity.x = 0;
			}
		}

		if (Gdx.input.isKeyPressed(Keys.ENTER) && Gdx.input.isKeyPressed(Keys.S) && Gdx.input.isKeyPressed(Keys.D)
				&& tocoSuelo) {
			this.setAnimation(shootDuckR);
			this.velocity.x = 0;
		}

		if (Gdx.input.isKeyPressed(Keys.ENTER) && Gdx.input.isKeyPressed(Keys.S) && Gdx.input.isKeyPressed(Keys.A)
				&& tocoSuelo) {
			this.setAnimation(shootDuckL);
			this.velocity.x = 0;
		}

		// DISPARO

		if (Gdx.input.isKeyPressed(Keys.ENTER) && tiempoDisparo >= cooldownDisparo) {
			dispara();
		}

//		SALTO

		
		
		if (Gdx.input.isKeyPressed(Keys.SPACE) && Gdx.input.isKeyPressed(Keys.A) && tocoSuelo) {
			saltaL();
			
		}
		
		if (Gdx.input.isKeyPressed(Keys.SPACE) && Gdx.input.isKeyPressed(Keys.D) && tocoSuelo) {
			saltaR();
		}
		
		if (Gdx.input.isKeyPressed(Keys.SPACE) && tocoSuelo) {
			salta();
		}

//		ENTRAR

		if (Gdx.input.isKeyJustPressed(Keys.E)) {
			if (door) {
				
			}
		}
	}

	private void colocarPies() {
		this.pies.setPosition(this.getX() + 20, this.getY());
	}

	private void salta() {
		if (direccion) {
			this.setAnimation(jumpR);
			this.acceleration.add(0, jumpSpeed);
		} else {
			this.setAnimation(jumpL);
			this.acceleration.add(0, jumpSpeed);
		}

		SoundManager.playSound("Cuphead/Sound/sfx_player_jump_01.wav");
		this.tocoSuelo = false;

	}

	private void saltaL() {
		this.setAnimation(jumpL);
		this.acceleration.add(0, jumpSpeed);
		this.velocity.x=-2000;
		SoundManager.playSound("Cuphead/Sound/sfx_player_jump_01.wav");
		this.tocoSuelo = false;
	}
	
	private void saltaR() {
		this.setAnimation(jumpL);
		this.acceleration.add(0, jumpSpeed);
		this.velocity.x=2000;
		SoundManager.playSound("Cuphead/Sound/sfx_player_jump_01.wav");
		this.tocoSuelo = false;
	}

	private void dashR() {

		this.setAnimation(dashR);
		this.setPosition(this.getX(), this.getY());
		this.addAction(Actions.moveTo(Parametros.playerX + 400, Parametros.playerY, 0.8f));
		controles = false;
		tiempoDash = 0;

	}

	private void dashL() {

		this.setAnimation(dashL);
		this.addAction(Actions.moveTo(Parametros.playerX - 400, Parametros.playerY, 0.8f));
		controles = false;
		tiempoDash = 0;

	}

	private void dispara() {

		if (direccion) {
			if (Gdx.input.isKeyPressed(Keys.W)) {
				if (Gdx.input.isKeyPressed(Keys.D)) {
					balas.get(this.balaActual).dispararRUP();
				} else {
					balas.get(this.balaActual).dispararUPR();
				}
			} else if (Gdx.input.isKeyPressed(Keys.S)) {
				balas.get(this.balaActual).dispararRDOWN();
			} else {
				balas.get(this.balaActual).dispararR();
			}
		} else {
			if (Gdx.input.isKeyPressed(Keys.W)) {
				if (Gdx.input.isKeyPressed(Keys.A)) {
					balas.get(this.balaActual).dispararLUP();
				} else {
					balas.get(this.balaActual).dispararUPL();
				}
			} else if (Gdx.input.isKeyPressed(Keys.S)) {
				balas.get(this.balaActual).dispararLDOWN();
			} else {
				balas.get(this.balaActual).dispararL();
			}

		}
		SoundManager.playSoundLoop("Cuphead/Sound/sfx_player_weapon_charge_fire_small_001.wav");
		balaActual = (balaActual + 1) % this.totalBalas;
		tiempoDisparo = 0;
	}
	
	public void getHit() {
		
		controles = false;
		if (tiempoHit >= cooldownHit) {
			if (direccion) {
				this.setAnimation(hitR);
			}else {
				this.setAnimation(hitL);
			}
			this.addAction(Actions.moveTo(Parametros.playerX, Parametros.playerY+100, 0.5f));
			SoundManager.playSound("Cuphead/Sound/sfx_player_hit_02.wav");
			Parametros.vida-=1;	
			tiempoHit = 0;			
		}
		
	}

	private void cargarAnimaciones() {

		stayR = this.loadFullAnimation("Cuphead/Idle/spriteIdleR.png", 8, 1, 0.1f, true);
		stayL = this.loadFullAnimation("Cuphead/Idle/spriteIdleL.png", 8, 1, 0.1f, true);
		runL = this.loadFullAnimation("Cuphead/Run/Normal/spriteRunR.png", 16, 1, 0.1f, true);
		runR = this.loadFullAnimation("Cuphead/Run/Normal/spriteRunL.png", 16, 1, 0.1f, true);
		jumpR = this.loadFullAnimation("Cuphead/Jump/Cuphead/spriteJumpR.png", 8, 1, 0.1f, true);
		jumpL = this.loadFullAnimation("Cuphead/Jump/Cuphead/spriteJumpL.png", 8, 1, 0.1f, true);
		duckIdleL = this.loadFullAnimation("Cuphead/Duck/Idle/duckIdleL.png", 5, 1, 0.1f, true);
		duckIdleR = this.loadFullAnimation("Cuphead/Duck/Idle/duckIdleR.png", 5, 1, 0.1f, true);
		shootUpIdleR = this.loadFullAnimation("Cuphead/Shoot/Up/shootUpR.png", 6, 1, 0.1f, true);
		shootUpIdleL = this.loadFullAnimation("Cuphead/Shoot/Up/shootUpL.png", 6, 1, 0.1f, true);
		shootIdleR = this.loadFullAnimation("Cuphead/Shoot/Straight/shootStraightR.png", 6, 1, 0.1f, true);
		shootIdleL = this.loadFullAnimation("Cuphead/Shoot/Straight/shootStraightL.png", 6, 1, 0.1f, true);
		shootDuckR = this.loadFullAnimation("Cuphead/Duck/Shoot/shootDuckR.png", 6, 1, 0.1f, true);
		shootDuckL = this.loadFullAnimation("Cuphead/Duck/Shoot/shootDuckL.png", 6, 1, 0.1f, true);
		shootRunR = this.loadFullAnimation("Cuphead/Run/Shooting/Straight/shootRunR.png", 16, 1, 0.1f, true);
		shootRunL = this.loadFullAnimation("Cuphead/Run/Shooting/Straight/shootRunL.png", 16, 1, 0.1f, true);
		shootDiagonalRunR = this.loadFullAnimation("Cuphead/Run/Shooting/Diagonal Up/shootDiagonalRunR.png", 16, 1,
				0.1f, true);
		shootDiagonalRunL = this.loadFullAnimation("Cuphead/Run/Shooting/Diagonal Up/shootDiagonalRunL.png", 16, 1,
				0.1f, true);

		dashL = this.loadFullAnimation("Cuphead/Dash/Ground/dashGL.png", 8, 1, 0.1f, true);
		dashR = this.loadFullAnimation("Cuphead/Dash/Ground/dashGR.png", 8, 1, 0.1f, true);

		hitL = this.loadFullAnimation("Cuphead/Hit/Ground/hitL.png", 6, 1, 0.1f, true);
		hitR = this.loadFullAnimation("Cuphead/Hit/Ground/hitR.png", 6, 1, 0.1f, true);

		
		
	}

	

}

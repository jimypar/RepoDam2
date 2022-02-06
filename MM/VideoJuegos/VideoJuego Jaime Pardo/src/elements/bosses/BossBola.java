package elements.bosses;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;

import elements.objects.Solid;
import elements.objects.Wall;
import elements.other.Hitbox;
import elements.player.Player;
import game.Parametros;
import screens.Boss1;
import screens.Level1;

public class BossBola extends Boss {

	private int direccion;
	private Animation<TextureRegion> idleL;
	private Animation<TextureRegion> idleR;
	private Animation<TextureRegion> moveL;
	private Animation<TextureRegion> moveR;
	private Animation<TextureRegion> smash;
	private Hitbox hitbox;
	
	private Boss1 nivel;
	private Stage s;
	private Player p;

	private boolean tocoSuelo;

	private float cooldownJump = 3.5f;
	private float tiempoJump = cooldownJump * 3;

	private float cooldownToSmash = 5f;
	private float tiempoToSmash = cooldownToSmash * 3;

	private float cooldownSmash = 2.2f;
	private float tiempoSmash = cooldownSmash * 3;

	private float cooldownInicio = 5f;
	private float tiempoInicio = 0f;

	public BossBola(float x, float y, Stage s, Boss1 nivel) {
		super(x, y, s, nivel);
		this.nivel = nivel;

		this.s=s;

		this.setEnabled(true);

		idleL = loadFullAnimation("Bosses/BossBola/Phase2/idleL.png", 5, 1, 0.2f, true);
		idleR = loadFullAnimation("Bosses/BossBola/Phase2/idleR.png", 5, 1, 0.2f, true);
		moveL = loadFullAnimation("Bosses/BossBola/Phase3/stoneL.png", 1, 4, 0.2f, true);
		moveR = loadFullAnimation("Bosses/BossBola/Phase3/stoneR.png", 1, 4, 0.2f, true);
		smash = loadFullAnimation("Bosses/BossBola/Phase3/smash.png", 1, 22, 0.1f, true);
		die = this.loadFullAnimation("Bosses/BossBola/Phase3/death.png", 11, 1, 0.2f, true);

		direccion = -1;

		this.setPolygon(10, this.getWidth(), this.getHeight(), 0, 0);

		velocidad = 250;

		this.setAnimation(idleL);

	}
	
	public void setP(Player p) {
		this.p = p;
	}

	@Override
	public void act(float delta) {
		super.act(delta);

		if (tiempoInicio < cooldownInicio) {
			this.setEnabled(false);
		} else {
			this.setEnabled(true);
		}

		tocoSuelo = false;

		if (!dying) {

			if (boss2) {

				collide2();

				movement();
				
				if (tiempoToSmash >= cooldownToSmash && tiempoInicio > cooldownInicio + 1) {

					hitbox=new Hitbox(getX(), 0, s, 0, 0);
					
					this.setRectangle(1, 1, 1, 1);
					this.setY(100);
					this.setScale(2);
					this.animationTime = 0;
					this.setAnimation(smash);
					this.tiempoToSmash = 0;
					this.tiempoSmash = 0;
					
				} else if (tiempoSmash >= cooldownSmash) {
					this.setY(250);
					this.setScale(1);
					this.changeAnimation2();
					moveBy(direccion * velocidad * delta, 0);
				}
				
				if (hitbox.getEnabled() && hitbox.overlaps(p) && !Parametros.nohit) {
					p.getHit();
				}

			} else {

				collide1();

				if (this.getX() > Parametros.playerX) {
					direccion = -1;
				} else {
					direccion = 1;

				}

				if (tiempoJump >= cooldownJump && tiempoInicio > cooldownInicio + 1) {
					this.acceleration.add(10000 * direccion, 30000);
					this.tiempoJump = 0;
				} else if (tocoSuelo) {
					this.velocity.set(0, 0);
				}

				if (tiempoInicio > cooldownInicio) {
					this.acceleration.add(0, -500);
					this.applyPhysics(delta);
				}
				

				this.changeAnimation();
			}

		}else {
			this.setAnimation(die);
		}

		this.tiempoJump += delta;
		this.tiempoInicio += delta;
		this.tiempoSmash += delta;
		this.tiempoToSmash += delta;

	}

	private void movement() {
		// TODO Auto-generated method stub
		
	}

	private void collide1() {

		for (Wall muro : nivel.muros) {
			if (this.overlaps(muro)) {
				this.preventOverlap(muro);
			}
		}

		for (Solid suelo : nivel.suelo) {
			if (this.overlaps(suelo)) {
				tocoSuelo = true;
			}
			this.preventOverlap(suelo);
		}

	}

	private void collide2() {

		for (Wall muro : nivel.muros) {
			if (this.overlaps(muro)) {
				direccion *= -1;
				this.preventOverlap(muro);
			}
		}

	}

	private void changeAnimation() {

		if (Parametros.playerX > this.getX()) {
			this.setAnimation(idleR);
		} else {
			this.setAnimation(idleL);
		}
	}

	private void changeAnimation2() {

		if (direccion > 0) {
			this.setPolygon(10, this.getWidth() / 1.5f, this.getHeight() / 2, 100, 200);
			this.setAnimation(moveR);
		} else {
			this.setPolygon(10, this.getWidth() / 1.5f, this.getHeight() / 2, 50, 200);
			this.setAnimation(moveL);
		}
	}

}

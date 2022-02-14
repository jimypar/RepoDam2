package elements.npc;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;

import elements.Element;
import elements.objects.Solid;
import game.Parametros;
import screens.Level2;

public class Bee extends Enemigo {

	Level2 n;

	private Animation<TextureRegion> moveL;
	private Animation<TextureRegion> moveR;
	private Animation<TextureRegion> explosion;

	private int velocidadBala;
	private boolean explode;

	private float cooldownBala = 0.8f;
	private float tiempoBala = cooldownBala * 2;
	
	private float cooldownBoom = 0.7f;
	private float tiempoBoom = cooldownBoom * 2;

	public Bee(float x, float y, Stage s, Level2 nivel) {
		super(x, y, s, nivel);
		
		this.inmortal=true;

		n = nivel;

		velocidadBala = -300;

		moveL = this.loadFullAnimation("Enemies/Bees/moveL.png", 1, 24, 0.04f, true);
		explosion = this.loadFullAnimation("Enemies/Dragon/explosion.png", 1, 7, 0.1f, true);
		
		this.setAnimation(moveL);

		this.setEnabled(true);

		tiempoBala = 0;
		tiempoBoom = 0;
		
		explode=false;

	}

	@Override
	public void act(float delta) {
		super.act(delta);

		this.setPolygon(10, this.getWidth()-15, this.getHeight(), 0, 0);

		if (getEnabled()) {
			if (!explode) {
				this.moveBy(velocidadBala * delta,0);
				collide();
			}else {
				tiempoBoom+=delta;				
			}		

		}

		if (tiempoBala >= cooldownBala) {
			this.setEnabled(true);
		}
		
		if (tiempoBoom>= cooldownBoom) {
			this.setEnabled(false);
		}

		tiempoBala += delta;

	}

	private void collide() {

		for (Solid s : n.suelo) {
			if (this.overlaps(s)) {
				explode=true;
				this.animationTime=0;
				this.setPosition(this.getX()-30, this.getY());
				this.setAnimation(explosion);
				tiempoBoom=0;
			}
		}
		
		if (this.overlaps(n.player) && !Parametros.nohit && this.getX()<n.door.getX()) {
			n.player.getHit();
		}

	}

}

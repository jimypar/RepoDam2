package elements.npc;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;

import elements.Element;
import screens.Level1;
import screens.Level2;

public class Enemigo extends Element {

	public int vida;
	public int velocidad;
	private Animation<TextureRegion> die;
	public boolean dying;
	public boolean inmortal;
	
	private float cooldownDie = 1f;
	private float timeDie = cooldownDie * 2;
	

	public Enemigo(float x, float y, Stage s, Level1 nivel) {
		super(x, y, s);

		this.setEnabled(true);
		die = loadFullAnimation("Enemies/Die/dieDust.png", 9, 1, 0.1f, true);

	}

	public Enemigo(float x, float y, Stage s, Level2 nivel) {
		super(x, y, s);

		this.setEnabled(true);
		die = loadFullAnimation("Enemies/Die/dieDust.png", 9, 1, 0.1f, true);
		
	}

	@Override
	public void act(float delta) {
		super.act(delta);

		if (dying && timeDie <= cooldownDie) {
			this.setAnimation(die);
		}
		if (dying && timeDie >= cooldownDie) {
			this.setEnabled(false);
		}
		
		timeDie+=delta;

	}

	public void damage(int damage) {
		vida -= damage;
		if (vida <= 0) {
			this.getHit();
		}

	}

	public void getHit() {

		this.dying=true;
		timeDie=0;

	}

}

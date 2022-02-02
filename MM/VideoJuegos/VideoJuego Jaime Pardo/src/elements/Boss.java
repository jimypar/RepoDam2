package elements;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;

import screens.Boss1;
import screens.Level1;

public class Boss extends Element {

	public int vida;
	public int velocidad;
	private Animation<TextureRegion> die;
	public boolean dying;
	
	private float cooldownDie = 1f;
	private float timeDie = cooldownDie * 2;

	public Boss(float x, float y, Stage s, Boss1 nivel) {
		super(x, y, s);

		this.setEnabled(true);		

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
			this.setEnabled(false);
		}

	}

	
}

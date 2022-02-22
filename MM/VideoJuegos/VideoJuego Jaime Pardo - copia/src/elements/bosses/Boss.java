package elements.bosses;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;

import elements.Element;
import elements.player.Player;
import screens.Boss1;
import screens.Boss2;
import screens.Level1;

public class Boss extends Element {

	public int vida;
	public int velocidad;
	protected Animation<TextureRegion> die;
	public boolean dying = false;
	public boolean dead = false;

	private float cooldownDie = 5f;
	private float timeDie = cooldownDie * 2;
	public boolean boss2;

	public Boss(float x, float y, Stage s, Boss1 nivel) {
		super(x, y, s);

		this.setEnabled(true);

		vida = 200;

	}

	public Boss(float x, float y, Stage s, Boss2 nivel) {
		
		super(x, y, s);
		this.setEnabled(true);		
		vida = 500;
		
	}

	@Override
	public void act(float delta) {
		super.act(delta);

		if (dying && timeDie >= cooldownDie) {
			this.setEnabled(false);
			this.dead=true;
		}

		if (dying) {
			timeDie += delta;
		}

	}

	public void damage(int damage) {
		vida -= damage;
		if (vida <= 100) {
			this.setEnabled(false);
			this.boss2 = true;
		}
		if (vida <= 0) {
			this.setEnabled(false);
			timeDie = 0;
			this.dying = true;
		}
	}

	public void setP(Player player) {
		this.setP(player);
	}

}

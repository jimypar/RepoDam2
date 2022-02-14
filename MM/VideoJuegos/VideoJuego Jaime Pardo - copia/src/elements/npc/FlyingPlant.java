package elements.npc;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;

import screens.Level1;

public class FlyingPlant extends Enemigo {

	private Animation<TextureRegion> movement;
	private Level1 nivel;

	public FlyingPlant(float x, float y, Stage s, Level1 nivel) {
		super(x, y, s, nivel);
		this.nivel = nivel;
		
		this.inmortal=true;
		
		movement = loadFullAnimation("Enemies/Planta/flyingPlant.png", 8, 1, 0.1f, true);

		this.setScale(0.75f);

		this.setPolygon(10, this.getWidth(), this.getHeight()*2, 0, 0);

		this.setAnimation(movement);

	}

	@Override
	public void act(float delta) {

		super.act(delta);

		if (!dying) {
			this.acceleration.add(0, -500);
			this.applyPhysics(delta);

			if (this.getY() < -500) {
				this.acceleration.add(0, 50000);
			}
		}

	}

}

package elements.npc;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import elements.objects.Solid;
import game.Parametros;
import screens.Level2;

public class Barrel extends Enemigo {

	private Animation<TextureRegion> down;
	private Animation<TextureRegion> up;
	
	private float cooldownUp = 1.2f;
	private float cooldownDown = 2.5f;
	private float timeUp = 0;

	private Level2 nivel;

	public Barrel(float x, float y, Stage s, Level2 nivel) {
		super(x, y, s, nivel);
		this.nivel = nivel;
		
		this.vida=10;
		
		down = loadFullAnimation("Enemies/Barril/down.png", 1, 9, 0.15f, true);
		up = loadFullAnimation("Enemies/Barril/up.png", 1, 6, 0.4f, true);

		this.setRectangle(200,150,0,0);

		this.setAnimation(down);

	}

	@Override
	public void act(float delta) {

		super.act(delta);

		if (!dying) {
			this.acceleration.add(0, -500);
			this.applyPhysics(delta);

			for (Solid s : nivel.suelo) {
				if (this.overlaps(s)) {
					this.preventOverlap(s);
				}
			}
			if (timeUp>cooldownUp) {
				if (timeUp<cooldownDown) {
					this.setAnimation(up);
					this.addAction(Actions.moveTo(this.getX(), this.getY()+100, 0.4f));
				}else {
					this.animationTime=0;
					this.setAnimation(down);
					timeUp=0;
				}
				
			}
			
			
		}
		
		timeUp+=delta;

	}

}

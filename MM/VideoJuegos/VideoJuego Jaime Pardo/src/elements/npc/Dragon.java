package elements.npc;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import elements.objects.Solid;
import game.Parametros;
import screens.Level2;

public class Dragon extends Enemigo {

	private Animation<TextureRegion> left;
	private Animation<TextureRegion> right;
	private Animation<TextureRegion> attack;
	
	private float direccion;
	
	private float cooldownShoot = 1.3f;
	private float timeShoot = cooldownShoot * 3;

	private Level2 nivel;

	public Dragon(float x, float y, Stage s, Level2 nivel) {
		super(x, y, s, nivel);
		this.nivel = nivel;
		left = loadFullAnimation("Enemies/Dragon/moveL.png", 1, 10, 0.1f, true);
		right = loadFullAnimation("Enemies/Dragon/moveR.png", 1, 10, 0.1f, true);
		attack = loadFullAnimation("Enemies/Dragon/atack.png", 2, 13, 0.05f, true);

		this.setRectangle();
		
		this.velocidad=300;


	}

	@Override
	public void act(float delta) {

		super.act(delta);
		
		

		if (!dying) {
			
			if (this.getX()>=nivel.player.getX()-5 && this.getX()<=nivel.player.getX()+5 && timeShoot>=cooldownShoot) {
				timeShoot=0;
				this.animationTime = 0;
				this.setAnimation(attack);
				new Fireball(this.getX()+(this.getWidth()/4), this.getY(), this.getStage(), nivel);
			} else if (timeShoot>=cooldownShoot) {
				followPlayer();
				this.moveBy(direccion*velocidad*delta,0);
			}
			
			
			
			
		}
		
		timeShoot+=delta;

	}

	private void followPlayer() {
		
		if (this.getX()>nivel.player.getX()) {
			direccion=-1;
			this.setAnimation(left);
		}else {
			direccion=1;
			this.setAnimation(right);
		}	
		
		
	}

}

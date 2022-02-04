package elements;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;

import game.Parametros;
import screens.Boss1;
import screens.Level1;

public class BossBola extends Boss{

	private int direccion;
	private Animation<TextureRegion> idleL;
	private Animation<TextureRegion> idleR;
	private Boss1 nivel;
	
	private float cooldownJump = 4f;
	private float tiempoJump = cooldownJump * 3;
	
	
	public BossBola(float x, float y, Stage s, Boss1 nivel) {
		super(x, y, s, nivel);
		this.nivel=nivel;
		
		idleL= loadFullAnimation("Bosses/BossBola/Phase2/idleL.png", 5, 1, 0.2f, true);
		idleR= loadFullAnimation("Bosses/BossBola/Phase2/idleR.png", 5, 1, 0.2f, true);
		direccion=-1;
		
		this.setPolygon(10, this.getWidth(), this.getHeight(), 0, 0);
		
		velocidad=250;
		
		this.setAnimation(idleL);
		
	}
	
	
	

	@Override
	public void act(float delta) {
		super.act(delta);
		
		for(Wall muro:nivel.muros) {
			if(this.overlaps(muro)) {
				this.preventOverlap(muro);
				changeAnimation();
				direccion*=-1;
			}
		}
		
		
		for(Solid suelo:nivel.suelo) {
			this.preventOverlap(suelo);
		}
			
		System.out.println(this.direccion);
		
		if (!dying) {
			
			
			if (this.getX()>850) {
				direccion=1;
			}else {
				direccion*=-1;
			}
			
			if (tiempoJump>=cooldownJump) {
				this.acceleration.add(5000000*direccion, 5000000);
				this.tiempoJump=0;
			}
			
		}		
		
		this.acceleration.add(0, Parametros.gravedad);
		this.applyPhysics(delta);
		this.tiempoJump+= delta;
		
	}


	private void changeAnimation() {
		
		if (this.direccion>0) {
			this.setAnimation(idleL);
		}else {
			this.setAnimation(idleR);
		}		
	}
	
	



	
	
	
}

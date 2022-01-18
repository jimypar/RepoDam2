package elements;

import com.badlogic.gdx.scenes.scene2d.Stage;

import game.Parametros;

public class Bala extends Element{

	public Bala(float x, float y, Stage s) {
		super(x, y, s);
		
		
		this.loadFullAnimation("Bullets/bulletsR.png", 8, 1, 200, false);
		
		this.setEnabled(false);
		
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		
		if(getEnabled()) {
			this.moveBy(this.velocity.x*delta, this.velocity.y*delta);
			
			
		}
		
	}

	public void disparar() {
		this.setEnabled(true);
		this.setPosition(Parametros.playerX, Parametros.playerY);
		this.getVelocity().set(150,0);
	}
	
	

}

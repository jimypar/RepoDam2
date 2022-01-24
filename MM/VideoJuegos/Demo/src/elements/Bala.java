package elements;

import com.badlogic.gdx.scenes.scene2d.Stage;

import game.Parametros;

public class Bala extends Element{

	public Bala(float x, float y, Stage s) {
		super(x, y, s);
		// TODO Auto-generated constructor stub
		
		
		this.loadFullAnimation("player/Bola.png", 1, 1, 209, true);
		this.setEnabled(false);
		
		
	}

	@Override
	public void act(float delta) {
		// TODO Auto-generated method stub
		super.act(delta);
		if(getEnabled()) {
			this.moveBy(this.velocity.x*delta, this.velocity.y*delta);
			
			
		}
	

	
	}
	
	
	
	public void disparar() {
		this.setEnabled(true);
		this.setPosition(Parametros.jugadorx, Parametros.jugadory);
		this.getVelocity().set(150,0);
		
	}
	
	
	
	

}

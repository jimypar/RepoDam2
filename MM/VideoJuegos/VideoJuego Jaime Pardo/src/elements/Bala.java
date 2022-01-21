package elements;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;

import game.Parametros;

public class Bala extends Element{
	
	private Animation<TextureRegion> izquierda;
	private Animation<TextureRegion> derecha;
	private Animation<TextureRegion> arriba;
	private Animation<TextureRegion> arribaIzquierda;
	private Animation<TextureRegion> arribaDerecha;

	private int velocidadBala;

	public Bala(float x, float y, Stage s) {
		super(x, y, s);
		
		velocidadBala = 900;
		
		derecha = this.loadFullAnimation("Bullets/bulletsR.png", 8, 1, 0.1f, false);
		izquierda = this.loadFullAnimation("Bullets/bulletsL.png", 8, 1, 0.1f, false);
		arriba = this.loadFullAnimation("Bullets/bulletsUP.png", 1, 8, 0.1f, false);
		arribaIzquierda = this.loadFullAnimation("Bullets/buleltsLUP.png", 8, 1, 0.1f, false);
		arribaDerecha = this.loadFullAnimation("Bullets/buleltsRUP.png", 8, 1, 0.1f, false);
				
		this.setEnabled(false);
		
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		
		if(getEnabled()) {
			this.moveBy(this.velocity.x*delta, this.velocity.y*delta);			
		}
		
	}

	public void dispararR() {
		this.setEnabled(true);
		this.setAnimation(derecha);
		this.setPosition(Parametros.playerX+20, Parametros.playerY+60);
		this.getVelocity().set(velocidadBala,0);
	}
	
	public void dispararL() {
		this.setEnabled(true);
		this.setAnimation(izquierda);
		this.setPosition(Parametros.playerX-20, Parametros.playerY+60);
		this.getVelocity().set(-velocidadBala,0);
	}
	
	public void dispararUPR() {
		this.setEnabled(true);
		this.setAnimation(arriba);
		this.setPosition(Parametros.playerX+70, Parametros.playerY+80);
		this.getVelocity().set(0,velocidadBala);
	}
	
	public void dispararUPL() {
		this.setEnabled(true);
		this.setAnimation(arriba);
		this.setPosition(Parametros.playerX, Parametros.playerY+80);
		this.getVelocity().set(0,velocidadBala);
	}
	
	public void dispararRUP() {
		this.setEnabled(true);
		this.setAnimation(arribaDerecha);
		this.setPosition(Parametros.playerX+50, Parametros.playerY+60);
		this.getVelocity().set(velocidadBala+(velocidadBala/4),velocidadBala);
	}
	
	public void dispararLUP() {
		this.setEnabled(true);
		this.setAnimation(arribaIzquierda);
		this.setPosition(Parametros.playerX-50, Parametros.playerY+60);
		this.getVelocity().set(-velocidadBala-(velocidadBala/4),velocidadBala);
	}

	public void dispararRDOWN() {
		this.setEnabled(true);
		this.setAnimation(derecha);
		this.setPosition(Parametros.playerX+70, Parametros.playerY+30);
		this.getVelocity().set(velocidadBala,0);		
	}

	public void dispararLDOWN() {
		this.setEnabled(true);
		this.setAnimation(izquierda);
		this.setPosition(Parametros.playerX-50, Parametros.playerY+30);
		this.getVelocity().set(-velocidadBala,0);				
	}
	
	
	
	
	

}

package elements;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Stage;

import game.Parametros;
import screens.GameScreen;

public class Blob extends Enemigo{

	private boolean direccion;
	private boolean pisa;
	private boolean cabezazo;

	private Animation walk;
	private Animation jump;
	private Element pie;
	private Element cabeza;
	private GameScreen nivel;

	public Blob(float x, float y, Stage s, GameScreen nivel) {
		super(x, y, s, nivel);
		
		this.nivel = nivel;
		
		jump = loadFullAnimation("enemy/blob_jump.png", 1, 7, 0.15f, true);
		walk = loadFullAnimation("enemy/blob.png", 2, 1, 0.2f, true);

		direccion = true;
		pie = new Element(0, 0, s, this.getWidth()/4,this.getHeight()/4);
		pie.setRectangle();
		ajustarPie();
		
		
		this.setEnabled(true);

	}
	
	private void ajustarPie() {
		
		if (direccion){
			pie.setPosition(this.getX(), this.getY()-this.getHeight()/8);
		}else {
			pie.setPosition(this.getX()+this.getWidth()*3/4, this.getY()-this.getHeight()/8);
		}
		
	}
	
	

	public void dano(int dano) {
		vida-=dano;
		if (vida<=0) {
			this.setEnabled(false);
		}
	}

	@Override
	public void act(float delta) {
		// TODO Auto-generated method stub
		super.act(delta);
		
		pisa=false;
		cabezazo=false;
		for(Solid solido:nivel.suelo) {
			if (pie.overlaps(solido)) {
				pisa=true;
			}
			if (cabeza.overlaps(solido)) {
				cabezazo=true;
			}
		}
		
		if (!pisa || cabezazo) {
			direccion
		}
		
		moveBy(direccion*velocidad*delta, 0);
		ajustarPie();
		
	}

}

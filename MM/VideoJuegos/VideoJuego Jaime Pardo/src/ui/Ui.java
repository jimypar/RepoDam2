package ui;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;

import elements.Element;
import game.Parametros;

public class Ui extends Element {

	Stage stage;
	private Animation<TextureRegion> hp4;	
	private Animation<TextureRegion> hp3;	
	private Animation<TextureRegion> hp2;	
	private Animation<TextureRegion> hp1;	
	private Animation<TextureRegion> hp0;	
	private Animation<TextureRegion> death1;	
	private Animation<TextureRegion> powerUp1;
	private Animation<TextureRegion> powerUp2;
	private Animation<TextureRegion> powerUpEmpty;
	
	Element powerUp;



	public Ui(float x, float y, Stage s) {
		super(x, y, s);
		// TODO Auto-generated constructor stub
		this.setEnabled(true);
		
		stage=s;

		hp4=this.loadFullAnimation("Lives/hp4.png", 1, 1, 200, true);
		hp3=this.loadFullAnimation("Lives/hp3.png", 1, 1, 200, true);
		hp2=this.loadFullAnimation("Lives/hp2.png", 1, 1, 200, true);
		hp1=this.loadFullAnimation("Lives/hp1.png", 1, 1, 200, true);
		hp0=this.loadFullAnimation("Lives/hp0.png", 1, 1, 200, true);
		death1=this.loadFullAnimation("Lives/cuphead_death.png", 40, 1, 0.02f, true);
		
		powerUpEmpty=this.loadFullAnimation("Lives/powerUpEmpty.png", 1, 1, 200, false);
		powerUp1=this.loadFullAnimation("Lives/powerUp1.png", 1, 1, 200, false);
		powerUp2=this.loadFullAnimation("Lives/powerUp2.png", 1, 1, 200, false);

		this.setScale(1.5f);
		
		this.setPosition(x, y);

		this.toFront();

	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		
		calcularVida();
		calcularPowerUp();
		
		batch.draw(animation.getKeyFrame(animationTime), getX(), getY(), getOriginX(), getOriginY(),
				animation.getKeyFrame(animationTime).getRegionWidth(),
				animation.getKeyFrame(animationTime).getRegionHeight(), getScaleX(), getScaleY(), getRotation());
	}

	private void calcularPowerUp() {
		
		if (Parametros.powerUpDisparo) {
			powerUp = new Element(this.getX()+97, this.getY()-23, stage);
			powerUp.setAnimation(powerUp1);
		}else if (Parametros.powerUpVida) {
			powerUp = new Element(this.getX()+97, this.getY()-23, stage);
			powerUp.setAnimation(powerUp2);
		}else {
			powerUp = new Element(this.getX()+100, this.getY()-20, stage);
			powerUp.setAnimation(powerUpEmpty);
		}
		
	}

	private void calcularVida() {
		
		switch (Parametros.vida) {
		case 4:
			this.setAnimation(hp4);
			break;
		case 3:
			this.setAnimation(hp3);
			break;
		case 2:
			this.setAnimation(hp2);
			break;
		case 1:
			this.setAnimation(hp1);
			break;
		case 0:
			this.setAnimation(hp0);
			die();
			break;
		default:
			die();
			break;
		}
		
	}

	private void die() {
		
		Element death = new Element(Parametros.getAnchoPantalla()/4, Parametros.getAltoPantalla()/2, this.getStage());
		death.setAnimation(death1);
		
	}

}
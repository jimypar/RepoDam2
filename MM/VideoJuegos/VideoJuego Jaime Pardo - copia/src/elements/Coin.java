package elements;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;

import game.Parametros;

public class Coin extends Element{
	
	private Animation<TextureRegion> coinStatic;
	private Animation<TextureRegion> coinExplode;
	
	public boolean explode = false;
	private float cooldownExplode = 1.5f;
	private float timeExplode = cooldownExplode * 2;

	public Coin(float x, float y, Stage s) {
		super(x, y, s);
					
		coinStatic = this.loadFullAnimation("Coin/CoinStages.png", 16, 1, 0.1f, true);
		coinExplode = this.loadFullAnimation("Coin/CoinExplode.png", 15, 1, 0.1f, true);
		this.setAnimation(coinStatic);
		this.setPosition(x, y);
		this.setEnabled(true);
		
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		
		this.setPolygon(10,this.getWidth(),this.getHeight(),0,0);
		
		if (explode) {
			if (timeExplode >= cooldownExplode) {
				this.setEnabled(false);
			}
		}
		
		this.timeExplode += delta;
				
	}
	
	public void getCoin() {
		this.setAnimation(coinExplode);
		this.setPosition(this.getX()-100, this.getY()-50);
		timeExplode = 0;
		explode = true;
	}

}

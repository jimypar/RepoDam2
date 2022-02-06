package elements.other;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;

import elements.Element;
import game.Parametros;

public class Clock extends Element{
	
	public static AssetManager assets=new AssetManager();
	private Animation<TextureRegion> clockStatic;

	public Clock(float x, float y, Stage s) {
		super(x, y, s);
					
		clockStatic = this.loadFullAnimation("Reloj/loadingClock.png", 1, 48, 0.05f, true);
		this.setAnimation(clockStatic);
		this.setPosition(x, y);
		this.setEnabled(true);
		
		

	}

	@Override
	public void act(float delta) {
		super.act(delta);
						
	}
	

}

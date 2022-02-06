package elements.other;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.scenes.scene2d.Stage;

import elements.Element;
import elements.player.Player;

public class Hitbox extends Element{
	
	
	private float cooldownEnd = 1.2f;
	private float tiempoEnd = 0f;
	
	private float cooldownStart = 0.8f;
	private float tiempoStart = 0f;

	public Hitbox(float x, float y, Stage s, float w, float h) {
		super(x, y, s, w, h);
		
		this.setEnabled(false);
		this.setRectangle(450, 400, -25, 0);
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		
		if (tiempoStart>=cooldownStart) {
			this.setEnabled(true);
			tiempoStart=0;
		}
		
		if (tiempoEnd>=cooldownEnd) {
			this.setEnabled(false);
		}
			
		tiempoEnd+=delta;
		tiempoStart+=delta;
	}

}

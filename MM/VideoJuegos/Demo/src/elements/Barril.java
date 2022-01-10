package elements;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class Barril extends Element{

	public Barril(float x, float y, Stage s) {
		super(x, y, s);
		
		this.loadFullAnimation("maps/images/barrel.png", 1, 1, 1, false);
		this.setRectangle();
		this.setScale(5);
		
		
	}

}

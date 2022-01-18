package elements;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class Suelo extends Element{

	public Suelo(float x, float y, Stage s) {
		super(x, y, s);
		
		this.loadFullAnimation("maps/images/grass.png", 1, 1, 1, false);
		
		this.setRectangle();

	}

}

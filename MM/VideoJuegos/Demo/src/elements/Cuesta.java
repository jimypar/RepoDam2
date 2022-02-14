package elements;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Cuesta extends Element{

	public Cuesta(float x, float y, Stage s, Polygon poly) {
		super(x, y, s);
		// TODO Auto-generated constructor stub
		this.colision=poly;
	}

}

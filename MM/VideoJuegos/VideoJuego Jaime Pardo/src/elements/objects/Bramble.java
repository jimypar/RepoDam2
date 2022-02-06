package elements.objects;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.scenes.scene2d.Stage;

import elements.Element;

public class Bramble extends Element{

	public Bramble(float x, float y, Stage s, float w, float h) {
		super(x, y, s, w, h);
		// TODO Auto-generated constructor stub
		
		float[] vertices= {0,0,w,0,w,h,0,h};
		colision=new Polygon(vertices);
		this.setSize(w, h);
	}

}

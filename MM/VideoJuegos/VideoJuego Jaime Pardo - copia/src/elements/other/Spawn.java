package elements.other;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.scenes.scene2d.Stage;

import elements.Element;
import elements.npc.Bee;
import screens.Level2;

public class Spawn extends Element{

	Stage stage;
	Level2 level;

	
	public Spawn(float x, float y, Stage s, float w, float h, Level2 l) {
		super(x, y, s, w, h);
		
		stage=s;
		level=l;
		
		float[] vertices= {0,0,w,0,w,h,0,h};
		colision=new Polygon(vertices);
		this.setSize(w, h);
	}

	public void spawn() {
		
		Bee b = new Bee(this.getX(),this.getY(),stage,level);
		
	}

}

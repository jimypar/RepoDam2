package elements.npc;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;

import elements.Element;
import elements.objects.Solid;
import elements.objects.Wall;
import screens.Level1;

public class Flower extends Enemigo{

	private int direccion;
	private Animation<TextureRegion> walkL;
	private Animation<TextureRegion> walkR;
	private Element pie;
	private boolean choca;
	private boolean pisa;
	private Level1 nivel;
	
	
	public Flower(float x, float y, Stage s, Level1 nivel) {
		super(x, y, s, nivel);
		this.nivel=nivel;
		// TODO Auto-generated constructor stub
		walkR= loadFullAnimation("Enemies/Flower/flowerR.png", 15, 1, 0.2f, true);
		walkL= loadFullAnimation("Enemies/Flower/flowerL.png", 15, 1, 0.2f, true);
		direccion=-1;
		
		this.setRectangle(this.getWidth(),this.getHeight(),0,0);
		
		pie=new Element(0,0,s,this.getWidth()/4 ,this.getHeight()/4);
		pie.setRectangle();
		ajustarPie();
		
		velocidad=250;
		
		this.setAnimation(walkR);
		
	}
	
	
	

	@Override
	public void act(float delta) {
		// TODO Auto-generated method stub
		super.act(delta);
		choca=false;
		pisa=false;
		for(Wall muro:nivel.muros) {
			if(this.overlaps(muro)) {
				choca=true;
				this.preventOverlap(muro);
			}
		}
		
		
		for(Solid suelo:nivel.suelo) {
			if(pie.overlaps(suelo)) {
				pisa=true;
			}
		}
		
		if(choca || !pisa) {
			changeAnimation();
			direccion*=-1;
		}
		
		if (!dying) {
			moveBy(direccion*velocidad*delta,0);
		}		
		ajustarPie();
		
	}

	private void ajustarPie() {
		// TODO Auto-generated method stub
		if(direccion==-1) {
			pie.setPosition(this.getX(), this.getY()-this.getHeight()/8);
		}else {
			pie.setPosition(this.getX()+this.getWidth()*3/4, this.getY()-this.getHeight()/8);
		}
		
	}


	private void changeAnimation() {
		
		if (this.direccion>0) {
			this.setAnimation(walkR);
		}else {
			this.setAnimation(walkL);
		}
		
	}
	
	



	
	
	
}

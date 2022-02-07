package elements.npc;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;

import elements.Element;
import elements.objects.Solid;
import elements.objects.Wall;
import screens.Level1;

public class Blueball extends Enemigo {

	private int direccion;
	private Animation<TextureRegion> walkL;
	private Animation<TextureRegion> walkR;
	private Element pie;
	private boolean choca;
	private boolean pisa;
	private Level1 nivel;

	public Blueball(float x, float y, Stage s, Level1 nivel) {
		super(x, y, s, nivel);
		this.nivel = nivel;
		
		this.vida=1;
		
		// TODO Auto-generated constructor stub
		walkR = loadFullAnimation("Enemies/Blueball/bolaR.png", 10, 1, 0.2f, true);
		walkL = loadFullAnimation("Enemies/Blueball/bolaL.png", 10, 1, 0.2f, true);
		direccion = -1;

		this.setScale(0.75f);
		this.setPolygon(10, this.getWidth(), this.getHeight()/2, 0, 0);

		pie = new Element(0, 0, s, this.getWidth() / 4, this.getHeight() / 4);
		pie.setRectangle();
		ajustarPie();

		velocidad = 250;

		this.setAnimation(walkR);

	}

	@Override
	public void act(float delta) {
		// TODO Auto-generated method stub
		super.act(delta);
		choca = false;
		pisa = false;
		for (Wall muro : nivel.muros) {
			if (this.overlaps(muro)) {
				choca = true;
			}
		}

		for (Solid suelo : nivel.suelo) {
			if (pie.overlaps(suelo)) {
				pisa = true;
			}
		}

		if (choca || !pisa) {
			changeAnimation();
			direccion *= -1;
		}

		if (!dying) {
			moveBy(direccion * velocidad * delta, 0);
		}
		ajustarPie();

	}

	private void ajustarPie() {
		if (direccion == -1) {
			pie.setPosition(this.getX(), this.getY() - 2);
		} else {
			pie.setPosition(this.getX() + this.getWidth() * 3 / 4, this.getY() - 2);
		}

	}

	private void changeAnimation() {

		if (this.direccion > 0) {
			this.setAnimation(walkL);
		} else {
			this.setAnimation(walkR);
		}

	}

}

package elements.other;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;

import elements.Element;
import game.Parametros;

public class FondoMenu extends Element{
	
	public static AssetManager assets=new AssetManager();
	private Animation<TextureRegion> imagen;

	public FondoMenu(float x, float y, Stage s) {
		super(x, y, s);
					
		imagen = this.loadFullAnimation("Menu/menuInGameSmall.png", 1, 1, 1f, false);
		this.setAnimation(imagen);
		this.setPosition(x-this.getWidth()/2, y+this.getHeight()/2-30);
		this.setEnabled(true);
		
		

	}

	@Override
	public void act(float delta) {
		super.act(delta);
						
	}
	

}

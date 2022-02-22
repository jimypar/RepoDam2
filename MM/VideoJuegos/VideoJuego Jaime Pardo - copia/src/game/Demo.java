package game;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import managers.ResourceManager;
import screens.BScreen;
import screens.LoadScreen;
import screens.TutorialScreen;

public class Demo extends Game
{
	SpriteBatch batch;
	public ResourceManager resourceManager;
	
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		InputMultiplexer im=new InputMultiplexer();
		Gdx.input.setInputProcessor(im);
		setScreen(new LoadScreen(this));
		
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		this.getScreen().dispose();
	}
}
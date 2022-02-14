package screens;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import elements.other.Clock;
import game.Demo;
import managers.ResourceManager;

public class LoadScreen extends BScreen{
	private float loadDelay=5;
	private float loadCount=0;
	private Clock clock;
	private TextureRegion imagen;
	
	public LoadScreen(Demo game){
		
	super(game);
		
	imagen=new TextureRegion();
	
	//clock = new Clock(100,100,);
	//this.resourceManager=new ResourceManager();
	//game.resourceManager=this.resourceManager;
	ResourceManager.loadAllResources();
	//while(!ResourceManager.update()){}
	
	
	}

	@Override
	public void render(float delta) {
		super.render(delta);
				
		if(ResourceManager.update()) {
			
			ResourceManager.botones();
			game.setScreen(new TitleScreen(game));
			
		}
		
		
	}
	
	
	
}

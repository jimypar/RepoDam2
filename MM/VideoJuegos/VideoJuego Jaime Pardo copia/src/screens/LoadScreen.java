package screens;

import com.badlogic.gdx.scenes.scene2d.Stage;

import elements.Clock;
import game.Demo;
import managers.ResourceManager;

public class LoadScreen extends BScreen{
	private float loadDelay=5;
	private float loadCount=0;
	private Clock clock;
	
	public LoadScreen(Demo game){
		
	super(game);
		
	//	clock = new Clock(100,100, loadingStage);
	//this.resourceManager=new ResourceManager();
	//game.resourceManager=this.resourceManager;
	ResourceManager.loadAllResources();
	//while(!ResourceManager.update()){}
	
	
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		
		
		
		if(ResourceManager.update()) {
			
			game.setScreen(new Boss1(game));
			
		}
		
		
	}
	
	
	
}

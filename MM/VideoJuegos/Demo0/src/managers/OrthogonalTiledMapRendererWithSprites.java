package managers;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;

public class OrthogonalTiledMapRendererWithSprites extends OrthogonalTiledMapRenderer {

	public OrthogonalTiledMapRendererWithSprites(TiledMap map) {
		super(map);
	}

	@Override
	public void renderObject(MapObject object) {
	    if(object instanceof TextureMapObject) {
	        TextureMapObject textureObj = (TextureMapObject) object;
	        batch.draw(textureObj.getTextureRegion(), textureObj.getX(), textureObj.getY(),
	                textureObj.getOriginX(), textureObj.getOriginY(), textureObj.getTextureRegion().getRegionWidth(), textureObj.getTextureRegion().getRegionHeight(),
	                textureObj.getScaleX(), textureObj.getScaleY(), textureObj.getRotation());
	       if(textureObj.getProperties().containsKey("image")) System.out.println(textureObj.getRotation());
	    } 
	}
	
	
	
	

	
	
}

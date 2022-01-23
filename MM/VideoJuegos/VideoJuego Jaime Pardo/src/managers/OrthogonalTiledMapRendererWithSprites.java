package managers;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class OrthogonalTiledMapRendererWithSprites extends OrthogonalTiledMapRenderer {

	private int i;

	public OrthogonalTiledMapRendererWithSprites(TiledMap map) {
		super(map);
		this.i = 0;
	}

	public OrthogonalTiledMapRendererWithSprites(TiledMap map, int i) {
		super(map);
		this.i = i;
	}

	@Override
	public void renderObject(MapObject object) {
		if (i == 0) {
			if (object instanceof TextureMapObject) {
				TextureMapObject textureObj = (TextureMapObject) object;
				batch.draw(textureObj.getTextureRegion(), textureObj.getX(), textureObj.getY(), textureObj.getOriginX(),
						textureObj.getOriginY(), textureObj.getTextureRegion().getRegionWidth(),
						textureObj.getTextureRegion().getRegionHeight(), textureObj.getScaleX(), textureObj.getScaleY(),
						textureObj.getRotation());
			}
		}
		if (i == 1) {
			if (object instanceof TextureMapObject) {
				TextureMapObject textureObj = (TextureMapObject) object;
				if (textureObj.getName()== null || !textureObj.getName().equals("imagen1")) {
					batch.draw(textureObj.getTextureRegion(), textureObj.getX(), textureObj.getY(),
							textureObj.getOriginX(), textureObj.getOriginY(),
							textureObj.getTextureRegion().getRegionWidth(),
							textureObj.getTextureRegion().getRegionHeight(), textureObj.getScaleX(),
							textureObj.getScaleY(), textureObj.getRotation());
				}
			}
		}
		if (i == 2) {
			if (object instanceof TextureMapObject) {
				TextureMapObject textureObj = (TextureMapObject) object;
				if (textureObj.getName()== null || !textureObj.getName().equals("imagen2") && !textureObj.getName().equals("imagen1")) {
					batch.draw(textureObj.getTextureRegion(), textureObj.getX(), textureObj.getY(),
							textureObj.getOriginX(), textureObj.getOriginY(),
							textureObj.getTextureRegion().getRegionWidth(),
							textureObj.getTextureRegion().getRegionHeight(), textureObj.getScaleX(),
							textureObj.getScaleY(), textureObj.getRotation());
				}
			}
		}

	}

}



import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class TextureAtlasAnimationDemo extends ApplicationAdapter {
   SpriteBatch batch;
   private TextureAtlas textureAtlas;
   private Animation animation;
   private float elapsedTime = 0f;
   
   @Override
   public void create () {
      batch = new SpriteBatch();
      textureAtlas = new TextureAtlas(Gdx.files.internal("spritesheets/myspritesheet.atlas"));
      animation = new Animation(1f/15f, textureAtlas.getRegions());
   }

   @Override
   public void dispose() {
      batch.dispose();
      textureAtlas.dispose();
   }

   @Override
   public void render () {
      elapsedTime += Gdx.graphics.getDeltaTime();
      Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
      batch.begin();
      batch.draw((Texture) animation.getKeyFrame(elapsedTime,true),0,0);
      batch.end();
   }
}
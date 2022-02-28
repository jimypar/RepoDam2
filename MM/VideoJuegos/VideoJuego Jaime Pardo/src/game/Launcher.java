package game;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Launcher

{
    public static void main (String[] args)
    {
        Game myGame = new Demo(); 
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "Cuphead";
        cfg.addIcon("ico.png", FileType.Internal);
        cfg.width = Parametros.getAnchoPantalla();
        cfg.height = Parametros.getAltoPantalla();
        LwjglApplication launcher = new LwjglApplication( myGame,cfg);
    }
}
package game;

public class Parametros {

//Screen
 //private static int anchoPantalla=1200;
 //private static int altoPantalla=900;
 
 private static int anchoPantalla=800;
 private static int altoPantalla=600;
 
 public static boolean debug=true;
 
 //Audio;
 public static float musicVolume=1;
 public static float soundVolume=1;
 
 
// public static float zoom=0.24f;
 public static float zoom=1f;
 
 //variables de juego
 public static int gravedad=-130;
 
 
 public static float playerX = 0;
 public static float playerY = 0;
 
 
 
 



public static int getAnchoPantalla() {
	return anchoPantalla;
}

public static void setAnchoPantalla(int anchoPantalla) {
	Parametros.anchoPantalla = anchoPantalla;
}

public static int getAltoPantalla() {
	return altoPantalla;
}

public static void setAltoPantalla(int altoPantalla) {
	Parametros.altoPantalla = altoPantalla;
}




 
}

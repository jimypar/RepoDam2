package game;

public class Parametros {

//Screen
// private static int anchoPantalla=1200;
// private static int altoPantalla=900;
 
 private static int anchoPantalla=1920;
 private static int altoPantalla=1080;

//private static int anchoPantalla=3000;
//private static int altoPantalla=2000;
 
 public static boolean debug=false;
 public static boolean nohit=false;
 
 //Audio;
 public static float musicVolume=0.1f;
 public static float soundVolume=0.1f;
 
 
// public static float zoom=0.5f;
 public static float zoom=0.85f;
 
 //variables de juego
 public static int gravedad= -3000;

 public static float playerX = 0;
 public static float playerY = 0;
 
 public static int vida = 0;
 



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

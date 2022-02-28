package game;

public class Parametros {

//Screen
// private static int anchoPantalla=1200;
// private static int altoPantalla=900;
 
 private static int anchoPantalla=1920;
 private static int altoPantalla=1080;

//private static int anchoPantalla=3000;
//private static int altoPantalla=2000;
 
 public static boolean fullscreen=false;
 
 public static boolean debug=false;
 public static boolean nohit=false;
 
 //Audio;
 public static float musicVolume=0.1f;
 public static float soundVolume=0.1f;
 
 
// public static float zoom=0.5f;
 public static float zoom=0.80f;
 
 //variables de juego
 public static int gravedad= -3000;

 public static float playerX = 0;
 public static float playerY = 0;
 
 public static int vida = 0;
 
 public static float playTime = 0;
 public static boolean pacifico = false;
 
 public static boolean powerUpDisparo = false;
 
 public static boolean pausa = false;

 public static boolean level1Unlocked = false;
 public static boolean boss1Unlocked = false;
 public static boolean level2Unlocked = false;
 public static boolean boss2Unlocked = false;



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

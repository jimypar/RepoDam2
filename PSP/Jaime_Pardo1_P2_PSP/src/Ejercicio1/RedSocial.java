package Ejercicio1;


public class RedSocial{

        String mensaje;
        Cliente cliente;

        private static RedSocial redSocial;

        private RedSocial(){
            mensaje = "";
        }

        //Se manda un mensaje al cliente
        public void enviarMensaje(String m,Cliente c){
            mensaje = m;
            c.mensaje(mensaje);

        }

        //Singleton de red social
        public static RedSocial getSingletonInstance(String nombre){

            if (redSocial == null){
                redSocial = new RedSocial();
            }
            else {
                System.out.println("No se puede crear el objeto");
            }

        return redSocial;

    }

}

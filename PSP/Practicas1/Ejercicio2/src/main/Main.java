package main;

public class Main {

	public static void main(String[] args) {

	}

	public void crearCuenta() {

		Cliente cliente1 = new Cliente("Jaime", "Pardo");
		Corriente corriente = (Corriente) new Cuenta(1, 1000, cliente1);
		corriente.consultarSaldo();

		Cliente cliente2 = new Cliente("Jaime", "Pardo");
		Ahorro ahorro = (Ahorro) new Cuenta(1, 1000, cliente1);
		corriente.consultarSaldo();

	}

}

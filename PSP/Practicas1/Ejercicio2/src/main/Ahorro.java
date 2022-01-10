package main;

public class Ahorro extends Cuenta {

	public Ahorro(int numeroCuenta, int dineroCuenta, Cliente cliente) {
		super(numeroCuenta, dineroCuenta, cliente);
	}

	public void remuneracion() {

		super.setDineroCuenta(super.consultarSaldo() * 0.02);

	}

}

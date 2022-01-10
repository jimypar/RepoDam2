package main;

public class Cuenta {

	public int numeroCuenta;
	public double dineroCuenta;
	public Cliente cliente;

	public Cuenta(int numeroCuenta, int dineroCuenta, Cliente cliente) {
		super();
		this.numeroCuenta = numeroCuenta;
		this.dineroCuenta = dineroCuenta;
		this.cliente = cliente;
	}

	public double getDineroCuenta() {
		return dineroCuenta;
	}

	public void setDineroCuenta(double dineroCuenta) {
		this.dineroCuenta = dineroCuenta;
	}

	public void depositarDinero(int dinero) {

		this.dineroCuenta = this.dineroCuenta + dinero;

	}

	public void retirarDinero(int dinero) {

		this.dineroCuenta = this.dineroCuenta - dinero;

	}

	public double consultarSaldo() {

		return this.dineroCuenta;

	}

}

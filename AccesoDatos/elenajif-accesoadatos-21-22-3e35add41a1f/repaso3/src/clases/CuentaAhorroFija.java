package clases;

public class CuentaAhorroFija extends Cuenta {
	// constructores usando constructor Cuenta
	public CuentaAhorroFija() {
		super();
		this.interes = 2.6;
	}

	public CuentaAhorroFija(String nombre, double saldo, double interes) {
		super(nombre, saldo);
		this.interes = interes;
	}

	// toString
	@Override
	public String toString() {
		return "CuentaAhorroFija [numero=" + numero + ", saldo=" + saldo + ", interes=" + interes + ", titular="
				+ titular + "]";
	}

	// metodos
	public double ingresoMes() {
		saldo += 100;
		return saldo;
	}

}

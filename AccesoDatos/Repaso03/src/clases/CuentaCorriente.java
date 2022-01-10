package clases;

public class CuentaCorriente extends Cuenta {

	public CuentaCorriente() {
		super();
	}

	public CuentaCorriente(String numero, double saldo, double interes) {
		super(numero, saldo, interes);
	}

	public CuentaCorriente(String numero, double saldo) {
		super(numero, saldo);
	}

	@Override
	public String toString() {
		return "CuentaCorriente [numero=" + numero + ", saldo=" + saldo + ", interes=" + interes + ", titular="
				+ titular + ", getNumero()=" + getNumero() + ", getSaldo()=" + getSaldo() + ", getInteres()="
				+ getInteres() + ", getTitular()=" + getTitular() + ", toString()=" + super.toString() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + "]";
	}

	public double reintegro(int dinero) {

		this.saldo = this.saldo - dinero;
		return saldo;

	}

}

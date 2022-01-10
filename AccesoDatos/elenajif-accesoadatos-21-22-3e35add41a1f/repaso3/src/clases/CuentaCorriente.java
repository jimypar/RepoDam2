package clases;

public class CuentaCorriente extends Cuenta {

	// constructores usando clase Cuenta
	public CuentaCorriente() {
		super();
	}

	public CuentaCorriente(String numero, double saldo, double interes) {
		super(numero, saldo, interes);
	}

	// toString
	@Override
	public String toString() {
		return "CuentaCorriente [numero=" + numero + ", saldo=" + saldo + ", interes=" + interes + ", titular="
				+ titular + "]";
	}

	// metodos
	public double reintegro(int perras) {
		saldo -= perras;
		return saldo;
	}
}

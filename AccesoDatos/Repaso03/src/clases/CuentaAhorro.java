package clases;

public class CuentaAhorro extends Cuenta {

	public CuentaAhorro() {
		super();
		this.interes = 2.6;
	}

	public CuentaAhorro(String numero, double saldo, double interes) {
		super(numero, saldo);
		this.interes = interes;
	}

	public CuentaAhorro(String numero, double saldo) {
		super(numero, saldo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "CuentaAhorro [numero=" + numero + ", saldo=" + saldo + ", interes=" + interes + ", titular=" + titular
				+ ", getNumero()=" + getNumero() + ", getSaldo()=" + getSaldo() + ", getInteres()=" + getInteres()
				+ ", getTitular()=" + getTitular() + ", toString()=" + super.toString() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + "]";
	}

	public double ingresoMes() {

		return interes;

	}

}

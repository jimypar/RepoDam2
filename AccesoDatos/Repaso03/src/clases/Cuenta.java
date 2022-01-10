package clases;

public class Cuenta {

	String numero;
	double saldo;
	double interes;
	Cliente titular;

	public Cuenta() {
		super();
		this.numero = "";
		this.saldo = 0;
		this.interes = 0;
	}

	public Cuenta(String numero, double saldo) {
		super();
		this.numero = numero;
		this.saldo = saldo;
	}

	public Cuenta(String numero, double saldo, double interes) {
		super();
		this.numero = numero;
		this.saldo = saldo;
		this.interes = interes;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public double getInteres() {
		return interes;
	}

	public void setInteres(double interes) {
		this.interes = interes;
	}

	public Cliente getTitular() {
		return titular;
	}

	public void setTitular(Cliente titular) {
		this.titular = titular;
	}

	@Override
	public String toString() {
		return "Cuenta [numero=" + numero + ", saldo=" + saldo + ", interes=" + interes + ", titular=" + titular + "]";
	}

	public double ingreso(int dinero) {
		
		saldo+=dinero;
		return saldo;
		
	}
	
}

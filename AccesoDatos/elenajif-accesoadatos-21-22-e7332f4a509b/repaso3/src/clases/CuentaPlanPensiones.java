package clases;

public class CuentaPlanPensiones extends Cuenta {

	// atributos
	double cotizacion;

	// constructores usando cuenta
	public CuentaPlanPensiones() {
		super();
		this.interes = 3.22;
		this.cotizacion = 6.5;
	}

	public CuentaPlanPensiones(String numero, double saldo, double interes, double cotizacion) {
		super(numero, saldo);
		this.interes = interes;
		this.cotizacion = cotizacion;
	}

	// setter y getter
	public double getCotizacion() {
		return cotizacion;
	}

	public void setCotizacion(double cotizacion) {
		this.cotizacion = cotizacion;
	}

	// toString
	@Override
	public String toString() {
		return "CuentaPlanPensiones [cotizacion=" + cotizacion + ", numero=" + numero + ", saldo=" + saldo
				+ ", interes=" + interes + ", titular=" + titular + "]";
	}

}

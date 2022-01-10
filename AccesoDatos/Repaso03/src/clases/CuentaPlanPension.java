package clases;

public class CuentaPlanPension extends Cuenta {

	double cotizacion;

	public CuentaPlanPension() {
		super();
		this.interes = 3.22;
		this.cotizacion = 6.5;
	}

	public CuentaPlanPension(String numero, double saldo, double interes, double cotizacion) {
		super(numero, saldo, interes);
		this.cotizacion = cotizacion;
	}

	public CuentaPlanPension(String numero, double saldo) {
		super(numero, saldo);
	}

	public double getCotizacion() {
		return cotizacion;
	}

	public void setCotizacion(double cotizacion) {
		this.cotizacion = cotizacion;
	}

}

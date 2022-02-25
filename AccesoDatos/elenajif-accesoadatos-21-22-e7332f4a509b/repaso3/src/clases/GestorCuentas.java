package clases;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

public class GestorCuentas {

	// atributos ArrayList
	private ArrayList<Cliente> listaClientes;
	private ArrayList<Cuenta> listaCuentas;

	// constructores
	public GestorCuentas() {
		listaClientes = new ArrayList<Cliente>();
		listaCuentas = new ArrayList<Cuenta>();
	}

	// metodo altaCliente
	public void altaCliente(String dni, String nombre, String fechaNacimiento) {
		Cliente nuevoCliente = new Cliente(dni, nombre);
		nuevoCliente.setFechaNacimiento(LocalDate.parse(fechaNacimiento));
		listaClientes.add(nuevoCliente);
	}

	// metodo listasClientes
	public void listarClientes() {
		for (Cliente cliente : listaClientes) {
			if (cliente != null) {
				System.out.println(cliente);
			}
		}
	}

	// metodo buscarCliente
	public Cliente buscarCliente(String dni) {
		for (Cliente cliente : listaClientes) {
			if (cliente != null && cliente.getDni().equals(dni)) {
				return cliente;
			}
		}
		return null;
	}

	// metodo altaCuenta
	public void altaCuenta(String numero, double saldo, double interes) {
		Cuenta nuevaCuenta = new Cuenta(numero, saldo, interes);
		listaCuentas.add(nuevaCuenta);
	}

	// metodo altaCuentaPlanPensiones
	public void altaCuentaPlanPensiones(String numero, double saldo, double interes, double cotizacion) {
		CuentaPlanPensiones nuevaCuenta = new CuentaPlanPensiones(numero, saldo, interes, cotizacion);
		listaCuentas.add(nuevaCuenta);
	}

	// metodo altaCuentaCorriente
	public void altaCuentaCorriente(String numero, double saldo, double interes) {
		CuentaCorriente nuevaCuenta = new CuentaCorriente(numero, saldo, interes);
		listaCuentas.add(nuevaCuenta);
	}

	// metodo altaCuentaAhorroFija
	public void altaCuentaAhorroFija(String numero, double saldo, double interes) {
		CuentaAhorroFija nuevaCuenta = new CuentaAhorroFija(numero, saldo, interes);
		listaCuentas.add(nuevaCuenta);
	}

	// metodo listaCuentas
	public void listarCuentas() {
		for (Cuenta cuenta : listaCuentas) {
			if (cuenta != null) {
				System.out.println(cuenta);
			}
		}
	}

	// metodo buscarCuenta
	public Cuenta buscarCuenta(String numero) {
		for (Cuenta cuenta : listaCuentas) {
			if (cuenta != null && cuenta.getNumero().equals(numero)) {
				return cuenta;
			}
		}
		return null;
	}

	// metodo eliminarCuenta
	public void eliminarCuenta(String numero) {
		Iterator<Cuenta> iteradorCuentas = listaCuentas.iterator();
		while (iteradorCuentas.hasNext()) {
			Cuenta cuenta = iteradorCuentas.next();
			if (cuenta.getNumero().equals(numero)) {
				iteradorCuentas.remove();
			}
		}
	}

	// metodo asignarCuentaCliente
	public void asignarCuentaCliente(String dni, String numero) {
		Cliente cliente = buscarCliente(dni);
		Cuenta cuenta = buscarCuenta(numero);
		cuenta.setTitular(cliente);
	}

	// metodo listarCuentasTitular
	public void listarCuentasDeTitular(String dni) {
		for (Cuenta cuenta:listaCuentas) {
			if (cuenta.getTitular()!=null && 
					cuenta.getTitular().getDni().equals(dni)) {
				System.out.println(cuenta);
			}
		}
	}

	// metodo ingreso
	public void ingreso(Cuenta miCuenta, int dinero) {
		miCuenta.ingreso(dinero);
	}

	// metodo reintegro
	public void reintegro(CuentaCorriente miCuenta, int dinero) {
		miCuenta.reintegro(dinero);
	}

	// metodo ingresoMes
	public void ingresoMes(CuentaAhorroFija miCuenta) {
		miCuenta.ingresoMes();
	}

}

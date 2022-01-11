package programa;

import clases.CuentaAhorroFija;
import clases.CuentaCorriente;
import clases.GestorCuentas;

public class PrincipalCuentas {

	public static void main(String[] args) {
		System.out.println("Creamos un banco");
		GestorCuentas banco = new GestorCuentas();
		System.out.println("1.- Creamos dos clientes y listamos");
		banco.altaCliente("1111111", "Maria", "1981-04-02");
		banco.altaCliente("2222222", "Carlos", "1980-09-03");
		banco.listarClientes();
		
		System.out.println("");
		System.out.println("2.- Buscar cliente 1111111");
		System.out.println(banco.buscarCliente("1111111"));
		
		System.out.println("");
		System.out.println("3.- Creamos tres cuentas y listamos");
		banco.altaCuenta("11-1111-11", 124, 0.2);
		banco.altaCuenta("22-2222-22", 654, 0.3);
		banco.altaCuenta("33-3333-33", 541, 0.2);
		banco.listarCuentas();
		System.out.println("4.- Asignar titulares y listamos");
		banco.asignarCuentaCliente("1111111", "11-1111-11");
		banco.asignarCuentaCliente("1111111", "22-2222-22");
		banco.asignarCuentaCliente("2222222", "33-3333-33");
		banco.listarCuentas();
		
		System.out.println("");
		System.out.println("5.- Buscar cuenta 11-1111-11");
		System.out.println(banco.buscarCuenta("11-1111-11"));
		
		System.out.println("");
		System.out.println("6.- Listar cuentas de un titular 1111111");
		banco.listarCuentasDeTitular("1111111");
		
		System.out.println("");
		System.out.println("7.- Eliminamos cuenta 11-1111-11 y listamos");
		banco.eliminarCuenta("11-1111-11");
		banco.listarCuentas();
		
		System.out.println("");
		System.out.println("8.- Creamos una cuenta plan pensiones, asignamos titular y listamos");
		banco.altaCuentaPlanPensiones("44-4444-44", 321, 0.15, 0.35);
		banco.asignarCuentaCliente("1111111", "44-4444-44");
		banco.listarCuentas();
		
		System.out.println("");
		System.out.println("9.- Creamos cuenta ahorro fija, asignamos titular y listamos");
		banco.altaCuentaAhorroFija("55-5555-55", 354, 0.15);
		banco.asignarCuentaCliente("1111111", "55-5555-55");
		banco.listarCuentas();
		
		System.out.println("");
		System.out.println("10.- Creamos cuenta corriente, asignamos titular y listamos");
		banco.altaCuentaCorriente("66-6666-66", 475, 0.3);
		banco.asignarCuentaCliente("2222222", "66-6666-66");
		banco.listarCuentas();
		
		System.out.println("");
		System.out.println("11.- Ingreso en cuenta y listamos");
		banco.ingreso(banco.buscarCuenta("33-3333-33"), 225);
		banco.listarCuentas();
		
		System.out.println("");
		System.out.println("12.- Reintegro de una cuenta corriente y listamos");
		banco.reintegro((CuentaCorriente) banco.buscarCuenta("66-6666-66"), 120);
		banco.listarCuentas();
		
		System.out.println("");
		System.out.println("13.- Ingreso mes en una cuenta ahorro fija y listamos");
		banco.ingresoMes((CuentaAhorroFija) banco.buscarCuenta("55-5555-55"));
		banco.listarCuentas();
		
	}

}

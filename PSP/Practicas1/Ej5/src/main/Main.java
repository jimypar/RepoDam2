package main;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		System.out.print("Introduce a:");
		int a = scan.nextInt();
		System.out.print("Introduce b:");
		int b = scan.nextInt();
		System.out.print("Introduce c:");
		int c = scan.nextInt();

		double x1 = (-b + Math.sqrt((b * b) - (4 * a * c))) / (2 * a);

		double x2 = (-b - Math.sqrt((b * b) - (4 * a * c))) / (2 * a);

		System.out.println("La solucion de x1: " + x1);

		System.out.println("La solucion de x2: " + x2);

	}

}

package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		System.out.println("Introduce 6 numeros");

		boolean error = false;
		int num = 0;

		ArrayList<Integer> numeros = new ArrayList();

		for (int i = 0; i < 6; i++) {

			do {
				try {
					num = scan.nextInt();
					numeros.add(num);
					error = false;
				} catch (InputMismatchException e) {
					System.out.println("Formato incorrecto");
					error = true;
					scan.next();
				}
			} while (error);

		}

		Collections.sort(numeros);

		for (int i = 0; i < numeros.size(); i++) {

			System.out.println(numeros.get(i));

		}

	}

}

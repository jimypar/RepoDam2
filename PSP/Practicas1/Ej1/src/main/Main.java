package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		boolean error = false;

		System.out.println("Introduce la cantidad en euros");

		float euros = (float) 0.00;

		do {
			try {
				euros = scan.nextFloat();
				error = false;
			} catch (InputMismatchException e) {
				System.out.println("Formato incorrecto");
				scan.next();
				error = true;
			}
		} while (error);

		if (euros / 500 >= 1) {
			System.out.println((int) euros / 500 + " billetes de 500");
			euros = euros - ((int) euros / 500) * 500;
		}
		if (euros / 200 >= 1) {
			System.out.println((int) euros / 200 + " billetes de 200");
			euros = euros - ((int) euros / 200) * 200;
		}
		if (euros / 100 >= 1) {
			System.out.println((int) euros / 100 + " billetes de 100");
			euros = euros - ((int) euros / 100) * 100;
		}
		if (euros / 50 >= 1) {
			System.out.println((int) euros / 50 + " billetes de 50");
			euros = euros - ((int) euros / 50) * 50;
		}
		if (euros / 20 >= 1) {
			System.out.println((int) euros / 20 + " billetes de 20");
			euros = euros - ((int) euros / 20) * 20;
		}
		if (euros / 10 >= 1) {
			System.out.println((int) euros / 10 + " billetes de 10");
			euros = euros - ((int) euros / 10) * 10;
		}
		if (euros / 5 >= 1) {
			System.out.println((int) euros / 5 + " billetes de 5");
			euros = euros - ((int) euros / 5) * 5;
		}
		if (euros / 2 >= 1) {
			System.out.println((int) euros / 2 + " moneda de 2");
			euros = euros - ((int) euros / 2) * 2;
		}
		if (euros / 1 >= 1) {
			System.out.println((int) euros / 1 + " moneda de 1");
			euros = euros - ((int) euros / 1);
		}
		euros = euros * 100;

		if (euros / 50 >= 1) {
			System.out.println((int) euros / 50 + " moneda de 50 cent");
			euros = euros - ((int) euros / 50) * 50;
		}
		if (euros / 20 >= 1) {
			System.out.println((int) euros / 20 + " moneda de 20 cent");
			euros = (euros - ((int) euros / 20) * 20);
		}
		if (euros / 10 >= 1) {
			System.out.println((int) euros / 10 + " moneda de 10 cent");
			euros = (euros - ((int) euros / 10) * 10);
		}
		if (euros / 5 >= 1) {
			System.out.println((int) euros / 5 + " moneda de 5 cent");
			euros = (euros - ((int) euros / 5) * 5);
		}
		if (euros / 2 >= 1) {
			System.out.println((int) euros / 2 + " moneda de 2 cent");
			euros = (euros - ((int) euros / 2) * 2);
		}
		if (euros / 1 >= 1) {
			System.out.println((int) euros / 1 + " moneda de 1 cent");
			euros = (euros - ((int) euros / 1));
		}

	}

}

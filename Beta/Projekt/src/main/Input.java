package main;

import java.util.Scanner;


public class Input {
	public static Scanner input = new Scanner(System.in);

	public static String stringInput() {
		return input.nextLine();
	}

	public static int intInput() {

		return Integer.parseInt(input.nextLine());
	}

}

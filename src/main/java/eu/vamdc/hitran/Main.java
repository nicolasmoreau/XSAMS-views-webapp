package eu.vamdc.hitran;

import java.util.*;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		Locale.setDefault(Locale.ENGLISH); // messages in english
		MainWindow Window = new MainWindow();

		Window.setSize(400, 100);
		Window.setLocationRelativeTo(null);
		Window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Window.setVisible(true);
	}
}

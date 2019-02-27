package main;

import java.io.File;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

import org.xml.sax.SAXParseException;

import validator.MyValidator;

public class Main {
	public static void main(String[] args) {
		try {
			if (args.length != 3) {
				args = new String[3];
				
				System.out.println("Bitte drei Argumente angeben: Pfad der XML, Pfad der XSD, Pfad der Logdatei");

				Scanner scanner = new Scanner(System.in);

				System.out.println("Pfad der XML eingeben:");
				args[0] = scanner.nextLine();

				System.out.println("Pfad der XSD eingeben:");
				args[1] = scanner.nextLine();

				System.out.println("Pfad der zu erstellenden Outputdatei eingeben:");
				args[2] = scanner.nextLine();
				
				scanner.close();
			}
			File xml = new File(args[0]);
			File xsd = new File(args[1]);
			File log = new File(args[2]);

			LinkedList<SAXParseException> exceptions = MyValidator.validateFile(xml, xsd);
			FileWriter w = new FileWriter(log);
			if (exceptions.isEmpty()) {
				w.write(xml.getName() + " is valid.");
			} else {
				Iterator<SAXParseException> iter = exceptions.iterator();
				while (iter.hasNext()) {
					SAXParseException ex = iter.next();
					w.write(ex.getLineNumber() + "\t" + ex.getMessage() + System.lineSeparator());
				}
				
				w.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

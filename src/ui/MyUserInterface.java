package ui;

import javax.swing.filechooser.*;

import javax.swing.JFrame;

public class MyUserInterface {
	
	public static void main(String[] args){
		showFileDialog(new JFrame());
	}

	public static void showFileDialog(JFrame frame) {
		FileChooser fc = new FileDialog(frame, "Choose a file", FileDialog.LOAD);
		fd.setDirectory("C:\\");
		fd.setFile("*.jpg;*.jpeg");

//		fd.setFilenameFilter((dir, name) -> name.endsWith(".xml"));
		fd.setVisible(true);
		String filename = fd.getFile();
		if (filename == null)
			System.out.println("You cancelled the choice");
		else
			System.out.println("You chose " + filename);
	}
}

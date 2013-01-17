package pl.edu.pw.elka.aal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(args.length);
		if(args.length == 0) return;
		
		DataReader reader = new DataReader();
		try {
			reader.readFile(args[0]);
			
			JFrame frame = new JFrame(args[0]);
			frame.setBackground(Color.WHITE);
			frame.setLayout(new BorderLayout());
			
			JPanel panel = PlotComponent.createPlot(reader.getData(), reader.getValues());
			frame.add(panel, BorderLayout.CENTER);
			
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			frame.setVisible(true);
			
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

}

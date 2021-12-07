package electricalsinventorysystem;

import java.awt.EventQueue;

import javax.swing.*;


import java.awt.Color;
import java.awt.Container;


public class App extends JFrame{

	/**
	 * TODO: find out what this does
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnItem;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App mainWindowApp = new App();
					mainWindowApp.setVisible(true);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public App() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Electricals Inventory System");
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		Container appContentPane = this.getContentPane();
		appContentPane.setBackground(Color.LIGHT_GRAY);
		appContentPane.setBounds(100, 100, 450, 300);		
		appContentPane.setLayout(null);
		
		btnItem = new JButton("ITEMS");
		btnItem.setBounds(10, 11, 176, 86);
		appContentPane.add(btnItem);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 125, 414, 2);
		appContentPane.add(separator);
		
		
	}
}

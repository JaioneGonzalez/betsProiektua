package Adapter;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import domain.Registered;

public class WindowTable extends JFrame {
	private Registered register;
	private JTable tabla;
	public WindowTable(Registered register) {
		super("Apuestas realizadas por "+register.getUsername()+":");
		this.setBounds(100,100,700,200);
		this.register = register;
		RegisteredAdapter adapt = new RegisteredAdapter(register);
		tabla = new JTable (adapt);
		tabla.setPreferredScrollableViewportSize(new Dimension(500, 70));
		JScrollPane scrollPane = new JScrollPane(tabla);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
	}
}
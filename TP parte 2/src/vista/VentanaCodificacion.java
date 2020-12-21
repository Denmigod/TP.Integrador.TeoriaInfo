package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import compresor.nodoListaCodificacion;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.JTextArea;

public class VentanaCodificacion extends JFrame
{
	private JFrame frame;
	private JPanel contentPane;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JTextArea textArea;

	/**
	 * Create the frame.
	 */
	public VentanaCodificacion(String nombreVentana)
	{
		this.frame = new JFrame(nombreVentana);
		this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frame.setBounds(100, 100, 694, 663);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.contentPane.setLayout(new BorderLayout(0, 0));
		this.frame.setContentPane(this.contentPane);

		this.panel = new JPanel();
		this.contentPane.add(this.panel, BorderLayout.CENTER);
		this.panel.setLayout(new BorderLayout(0, 0));

		this.scrollPane = new JScrollPane();
		this.panel.add(this.scrollPane, BorderLayout.CENTER);

		this.textArea = new JTextArea();
		this.scrollPane.setViewportView(this.textArea);

		this.frame.setVisible(true);
	}

	public void actualizaLista(ArrayList<nodoListaCodificacion> lista)
	{
		Iterator<nodoListaCodificacion> it = (lista).iterator();
		this.textArea.append("Ascii \t\tSimbolo \t\tFrecuencia \t\tCodificacion \n");
		while (it.hasNext())
		{
			this.textArea.append(it.next().toString() + "\n");
		}
	}

}

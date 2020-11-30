package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JButton;

public class VentanaEntradas extends JFrame
{

	private JPanel contentPane;
	private JPanel panel_1;
	private JPanel panel_3;
	private JLabel[] labelEntradas;
	private JTextField[] textFieldEntradas;
	private JButton btnAceptar;
	private JFrame frame;
	private ActionListener actionListener;
	private JPanel panel;

	/**
	 * Create the frame.
	 */
	public VentanaEntradas(int cantidadEntradas)
	{
		this.frame = new JFrame("Entradas");
		this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frame.setBounds(100, 100, 741, 303);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.frame.setContentPane(this.contentPane);
		this.contentPane.setLayout(new GridLayout(3, 0, 0, 0));

		this.panel_1 = new JPanel();
		this.contentPane.add(this.panel_1);

		this.panel = new JPanel();
		this.contentPane.add(this.panel);

		this.panel_3 = new JPanel();
		this.contentPane.add(this.panel_3);

		this.btnAceptar = new JButton("Aceptar");
		this.btnAceptar.setActionCommand("ACEPTAR_ENTRADAS");
		this.panel_3.add(this.btnAceptar);

		this.labelEntradas = new JLabel[cantidadEntradas];
		this.textFieldEntradas = new JTextField[cantidadEntradas];
		for (int i = 0; i < cantidadEntradas; i++)
		{
			this.labelEntradas[i] = new JLabel("a" + Integer.toString(i + 1) + ":");
			this.textFieldEntradas[i] = new JTextField();
			this.panel_1.add(this.labelEntradas[i]);
			this.panel_1.add(this.textFieldEntradas[i]);
			this.textFieldEntradas[i].setColumns(5);

		}

		this.frame.setVisible(true);
	}

	public void cerrar()
	{
		this.frame.dispose();
	}

	public void setActionListener(ActionListener actionListener)
	{
		this.btnAceptar.addActionListener(actionListener);
		this.actionListener = actionListener;
	}

	public double[] getEntradas()
	{
		int cantidadEntradas = this.textFieldEntradas.length;
		double[] entradas = new double[cantidadEntradas];
		for (int i = 0; i < cantidadEntradas; i++)
		{
			entradas[i] = Double.parseDouble(this.textFieldEntradas[i].getText());
		}

		return entradas;
	}

	public boolean validaBotonAceptar()
	{
		boolean condicion = true;
		int i = 0, n = this.textFieldEntradas.length;
		String simbolo;
		while (condicion && i < n)
		{
			simbolo = this.textFieldEntradas[i].getText();
			i++;
			condicion = !simbolo.isEmpty();
		}

		return condicion;
	}

	/**
	 * imprimeMensaje<br>
	 * imprime un mensaje pasado por parametro en una ventana emergente<br>
	 * 
	 * @param mensaje : mensaje a imprimir
	 */
	public void imprimeMensaje(String mensaje)
	{
		JOptionPane.showMessageDialog(this, mensaje);
	}
}

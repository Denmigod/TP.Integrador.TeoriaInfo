package UI;

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

public class VentanaEmergenteMatrizTransicion extends JFrame
{

	private JPanel contentPane;
	private JPanel[] panelFilas;
	private JPanel panelAceptar;
	private JTextField[][] textFieldProbabilidad;
	private JButton btnAceptar;
	private JFrame frame;
	private ActionListener actionListener;

	/**
	 * Create the frame.
	 */
	public VentanaEmergenteMatrizTransicion(int cantidadSimbolos)
	{
		this.frame = new JFrame("Matriz de transicion");
		this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frame.setBounds(100, 100, 100 * cantidadSimbolos, 50 * cantidadSimbolos + 100);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.frame.setContentPane(this.contentPane);
		this.contentPane.setLayout(new GridLayout(cantidadSimbolos + 1, 0, 0, 0));

		this.panelFilas = new JPanel[cantidadSimbolos];
		this.textFieldProbabilidad = new JTextField[cantidadSimbolos][cantidadSimbolos];
		for (int i = 0; i < cantidadSimbolos; i++)
		{
			this.panelFilas[i] = new JPanel();
			this.contentPane.add(this.panelFilas[i]);
			for (int j = 0; j < cantidadSimbolos; j++)
			{
				this.textFieldProbabilidad[i][j] = new JTextField();
				this.panelFilas[i].add(this.textFieldProbabilidad[i][j]);
				this.textFieldProbabilidad[i][j].setColumns(5);
			}

		}

		this.panelAceptar = new JPanel();
		this.contentPane.add(this.panelAceptar);

		this.btnAceptar = new JButton("Aceptar");
		this.btnAceptar.setActionCommand("AceptarProbabilidad");
		this.panelAceptar.add(this.btnAceptar);

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

	public double[][] getMatrizTransicion()
	{
		int dimension = this.textFieldProbabilidad.length;
		double[][] probabilidades = new double[dimension][dimension];
		for (int i = 0; i < dimension; i++)
		{
			for (int j = 0; j < dimension; j++)
			{
				probabilidades[i][j] = Double.parseDouble(this.textFieldProbabilidad[i][j].getText().replace(',', '.'));
			}
		}

		return probabilidades;
	}

	public boolean validaBotonAceptar()
	{
		boolean condicion = true;
		int i = 0, n = this.textFieldProbabilidad.length;
		double probabilidad;
		String simbolo;
		while (condicion && i < n)
		{
			int j = 0;
			while (condicion && j < n)
			{
				probabilidad = -1;
				simbolo = this.textFieldProbabilidad[i][j].getText();
				try
				{
					probabilidad = Double.parseDouble(this.textFieldProbabilidad[i][j].getText().replace(',', '.'));
				} catch (NumberFormatException e)
				{
				}
				j++;
				condicion = probabilidad >= 0 && probabilidad <= 1 && !simbolo.isEmpty();
			}
			i++;
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

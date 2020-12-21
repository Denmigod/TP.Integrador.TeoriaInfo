package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class MarkovMenu extends JFrame implements KeyListener
{

	private JFrame frame;
	private JPanel contentPane;
	private JPanel panel;
	private JPanel panel_1;
	private JScrollPane scrollPane;
	private JTextPane textPane;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JPanel panel_5;
	private JButton btnNewButton;
	private JLabel lblVectorEst;
	private JLabel lblEntropia;
	private ActionListener actionListener;
	private JButton btnSimular;
	private JTextField textFieldCantidad;
	private JLabel lblCantidad;

	/**
	 * Create the frame.
	 */
	public MarkovMenu()
	{
		this.frame = new JFrame("Markov");
		this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frame.setBounds(100, 100, 585, 411);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.frame.setContentPane(this.contentPane);
		this.contentPane.setLayout(new GridLayout(2, 1, 0, 0));

		this.panel_1 = new JPanel();
		this.panel_1.setBorder(new TitledBorder(new LineBorder(new Color(255, 200, 0), 2, true), "",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.ORANGE));
		this.contentPane.add(this.panel_1);
		this.panel_1.setLayout(new BorderLayout(0, 0));

		this.scrollPane = new JScrollPane();
		this.panel_1.add(this.scrollPane, BorderLayout.CENTER);

		this.textPane = new JTextPane();
		this.scrollPane.setViewportView(this.textPane);

		this.panel = new JPanel();
		this.contentPane.add(this.panel);
		this.panel.setLayout(new GridLayout(4, 0, 0, 0));

		this.panel_3 = new JPanel();
		this.panel.add(this.panel_3);

		this.lblVectorEst = new JLabel("Vector estacionario: ");
		this.panel_3.add(this.lblVectorEst);

		this.panel_4 = new JPanel();
		this.panel.add(this.panel_4);

		this.lblEntropia = new JLabel("Entropia:");
		this.panel_4.add(this.lblEntropia);

		this.panel_5 = new JPanel();
		this.panel.add(this.panel_5);

		this.lblCantidad = new JLabel("Cantidad:");
		this.panel_5.add(this.lblCantidad);

		this.textFieldCantidad = new JTextField();
		this.textFieldCantidad.addKeyListener(this);
		this.panel_5.add(this.textFieldCantidad);
		this.textFieldCantidad.setColumns(10);

		this.btnSimular = new JButton("Simular secuencia");
		this.btnSimular.setEnabled(false);
		this.btnSimular.setActionCommand("Simular");
		this.panel_5.add(this.btnSimular);

		this.panel_2 = new JPanel();
		this.panel.add(this.panel_2);

		this.btnNewButton = new JButton("Salir");
		this.btnNewButton.setActionCommand("Salir");
		this.panel_2.add(this.btnNewButton);
		this.frame.setVisible(true);
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

	public void setTextPane(String text)
	{
		this.textPane.setText(text);
	}

	public void setEntropia(double entropia)
	{
		this.lblEntropia.setText("Entropia: " + String.format("%.5f", entropia));
	}

	public void setVectorEstacionario(String vectorEstacinario)
	{
		this.lblVectorEst.setText("Vector estacionario: " + vectorEstacinario);
	}

	public void cerrar()
	{
		this.frame.dispose();
	}

	public void setActionListener(ActionListener actionListener)
	{
		this.btnSimular.addActionListener(actionListener);
		this.btnNewButton.addActionListener(actionListener);
		this.actionListener = actionListener;
	}

	public void keyPressed(KeyEvent arg0)
	{
	}

	public void keyReleased(KeyEvent arg0)
	{
		int cantidad = 0;

		try
		{
			cantidad = Integer.parseInt(this.textFieldCantidad.getText());
		} catch (NumberFormatException e)
		{
		}
		boolean condicion = cantidad > 0;
		this.btnSimular.setEnabled(condicion);

	}

	public void keyTyped(KeyEvent arg0)
	{
	}

	public int getCantidad()
	{
		return Integer.parseInt(this.textFieldCantidad.getText());
	}
}

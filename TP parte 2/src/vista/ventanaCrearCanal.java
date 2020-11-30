package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JButton;

public class ventanaCrearCanal extends JFrame implements KeyListener
{
	private JFrame frame;
	private JPanel contentPane;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JLabel lblEntradas;
	private JTextField textFieldEntradas;
	private JLabel lblSalidas;
	private JTextField textFieldSalidas;
	private JButton btnCrearCanal;
	private ActionListener actionListener;

	/**
	 * Create the frame.
	 */
	public ventanaCrearCanal()
	{
		this.frame = new JFrame("Crear canal");
		this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frame.setBounds(100, 100, 450, 300);
		this.contentPane = new JPanel();

		this.panel = new JPanel();
		this.frame.getContentPane().add(this.panel, BorderLayout.CENTER);
		this.panel.setLayout(new GridLayout(3, 0, 0, 0));

		this.panel_3 = new JPanel();
		this.panel.add(this.panel_3);

		this.panel_2 = new JPanel();
		this.panel.add(this.panel_2);
		FlowLayout flowLayout = (FlowLayout) this.panel_2.getLayout();

		this.lblEntradas = new JLabel("Entradas:");
		this.panel_2.add(this.lblEntradas);

		this.textFieldEntradas = new JTextField();
		this.panel_2.add(this.textFieldEntradas);
		this.textFieldEntradas.setColumns(10);

		this.lblSalidas = new JLabel("Salidas:");
		this.panel_2.add(this.lblSalidas);

		this.textFieldSalidas = new JTextField();
		this.panel_2.add(this.textFieldSalidas);
		this.textFieldSalidas.setColumns(10);

		this.panel_1 = new JPanel();
		this.panel.add(this.panel_1);

		this.btnCrearCanal = new JButton("Crear");
		this.btnCrearCanal.setEnabled(false);
		this.btnCrearCanal.setActionCommand("CREAR");
		this.panel_1.add(this.btnCrearCanal);
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.contentPane.setLayout(new BorderLayout(0, 0));
		this.frame.setContentPane(this.contentPane);
		this.frame.setVisible(true);
	}

	public void setActionListener(ActionListener actionListener)
	{
		this.btnCrearCanal.addActionListener(actionListener);
		this.actionListener = actionListener;
	}

	@Override
	public void keyPressed(KeyEvent arg0)
	{

	}

	@Override
	public void keyReleased(KeyEvent arg0)
	{
		int entradas = 0;
		int salidas = 0;
		try
		{
			entradas = Integer.parseInt(this.textFieldEntradas.getText());
			salidas = Integer.parseInt(this.textFieldSalidas.getText());
		} catch (NumberFormatException e)
		{
		}

		boolean condicion = entradas > 0 && salidas > 0;
		this.btnCrearCanal.setEnabled(condicion);
	}

	@Override
	public void keyTyped(KeyEvent arg0)
	{

	}

	public int getEntradas()
	{
		return Integer.parseInt(this.textFieldEntradas.getText());
	}

	public int getSalidas()
	{
		return Integer.parseInt(this.textFieldSalidas.getText());
	}

}

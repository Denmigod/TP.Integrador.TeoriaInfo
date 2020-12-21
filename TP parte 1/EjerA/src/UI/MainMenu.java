package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenu extends JFrame 
{

	private JFrame frame;
	private JPanel contentPane;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JButton btnFuente;



	/**
	 * Create the frame.
	 */
	public MainMenu()
	{
		this.frame = new JFrame("Menu Principal");
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setBounds(100, 100, 503, 314);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.contentPane.setLayout(new BorderLayout(0, 0));
		this.frame.setContentPane(this.contentPane);

		this.panel = new JPanel();
		this.contentPane.add(this.panel, BorderLayout.CENTER);
		this.panel.setLayout(new GridLayout(4, 3, 0, 0));

		this.panel_2 = new JPanel();
		this.panel.add(this.panel_2);

		this.panel_3 = new JPanel();
		this.panel.add(this.panel_3);

		this.btnFuente = new JButton("Crear Fuente");
		this.btnFuente.setActionCommand("crearFuente");

		this.panel_3.add(this.btnFuente);

		this.panel_4 = new JPanel();
		this.panel.add(this.panel_4);

		this.panel_1 = new JPanel();
		this.panel.add(this.panel_1);
		this.frame.setVisible(true);
	}

	public void setActionListener(ActionListener actionListener)
	{
		this.btnFuente.addActionListener(actionListener);
	}

	
}

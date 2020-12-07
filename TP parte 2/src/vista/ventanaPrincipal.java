package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;

public class ventanaPrincipal extends JFrame
{

	private JFrame frame;
	private JPanel contentPane;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JPanel panel_5;
	private JPanel panel_6;
	private JPanel panel_7;
	private JButton btnCompresor;
	private JButton btnCanal;
	private ActionListener actionListener;

	/**
	 * Create the frame.
	 */
	public ventanaPrincipal()
	{
		this.frame = new JFrame("Ventana Principal");
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setBounds(100, 100, 450, 300);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.contentPane.setLayout(new BorderLayout(0, 0));
		this.frame.setContentPane(this.contentPane);

		this.panel = new JPanel();
		this.contentPane.add(this.panel, BorderLayout.CENTER);
		this.panel.setLayout(new GridLayout(5, 0, 0, 0));

		this.panel_2 = new JPanel();
		this.panel.add(this.panel_2);

		this.panel_3 = new JPanel();
		this.panel.add(this.panel_3);

		this.panel_4 = new JPanel();
		this.panel.add(this.panel_4);
		this.panel_4.setLayout(new GridLayout(0, 2, 0, 0));

		this.panel_6 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) this.panel_6.getLayout();
		flowLayout.setVgap(10);
		flowLayout.setHgap(15);
		flowLayout.setAlignment(FlowLayout.RIGHT);
		this.panel_4.add(this.panel_6);

		this.btnCompresor = new JButton("Compresor");
		this.btnCompresor.setActionCommand("COMPRESOR");
		this.panel_6.add(this.btnCompresor);

		this.panel_7 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) this.panel_7.getLayout();
		flowLayout_1.setHgap(15);
		flowLayout_1.setVgap(10);
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		this.panel_4.add(this.panel_7);

		this.btnCanal = new JButton("Canal");
		this.btnCanal.setActionCommand("CANAL");
		this.panel_7.add(this.btnCanal);

		this.panel_5 = new JPanel();
		this.panel.add(this.panel_5);

		this.panel_1 = new JPanel();
		this.panel.add(this.panel_1);
		this.frame.setVisible(true);
	}

	public void setActionListener(ActionListener actionListener)
	{
		this.btnCompresor.addActionListener(actionListener);
		this.btnCanal.addActionListener(actionListener);
		this.actionListener = actionListener;
	}

}

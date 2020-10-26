package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class FuenteMenu extends JFrame implements KeyListener
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
	private JPanel panel_8;
	private JPanel panel_9;
	private JButton btnCrear;
	private JPanel panel_10;
	private JPanel panel_11;
	private JLabel lblNewLabel;
	private JTextField textField_Base;
	private JPanel panel_12;
	private JPanel panel_13;
	private JLabel lblNewLabel_1;
	private JTextField textField_CantSimbolos;
	private JPanel panel_14;
	private JPanel panel_15;
	private JRadioButton rdbtnNula;
	private JRadioButton rdbtnMarkov;
	private JButton btnAtrasMenu;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private ActionListener actionListener;

	/**
	 * Create the frame.
	 */
	public FuenteMenu()
	{
		this.frame = new JFrame("Crear Fuente");
		this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frame.setBounds(100, 100, 900, 500);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.frame.setContentPane(this.contentPane);
		this.contentPane.setLayout(new GridLayout(0, 3, 0, 0));

		this.panel = new JPanel();
		this.contentPane.add(this.panel);

		this.panel_1 = new JPanel();
		this.contentPane.add(this.panel_1);
		this.panel_1.setLayout(new GridLayout(7, 0, 0, 0));

		this.panel_4 = new JPanel();
		this.panel_1.add(this.panel_4);

		this.panel_5 = new JPanel();
		this.panel_1.add(this.panel_5);
		this.panel_5.setLayout(new GridLayout(2, 0, 0, 0));

		this.panel_15 = new JPanel();
		this.panel_5.add(this.panel_15);

		this.rdbtnNula = new JRadioButton("Memoria Nula");
		this.rdbtnNula.setSelected(true);
		buttonGroup.add(this.rdbtnNula);
		this.panel_15.add(this.rdbtnNula);

		this.panel_14 = new JPanel();
		this.panel_5.add(this.panel_14);

		this.rdbtnMarkov = new JRadioButton("Markov");
		buttonGroup.add(this.rdbtnMarkov);
		this.panel_14.add(this.rdbtnMarkov);

		this.panel_6 = new JPanel();
		this.panel_1.add(this.panel_6);

		this.panel_7 = new JPanel();
		this.panel_1.add(this.panel_7);
		this.panel_7.setLayout(new GridLayout(2, 0, 0, 0));

		this.panel_13 = new JPanel();
		this.panel_7.add(this.panel_13);

		this.lblNewLabel_1 = new JLabel("Ingrese la cantidad de simbolos");
		this.panel_13.add(this.lblNewLabel_1);

		this.panel_12 = new JPanel();
		this.panel_7.add(this.panel_12);

		this.textField_CantSimbolos = new JTextField();
		this.textField_CantSimbolos.addKeyListener(this);
		this.panel_12.add(this.textField_CantSimbolos);
		this.textField_CantSimbolos.setColumns(10);

		this.panel_8 = new JPanel();
		this.panel_1.add(this.panel_8);
		this.panel_8.setLayout(new GridLayout(2, 0, 0, 0));

		this.panel_11 = new JPanel();
		this.panel_8.add(this.panel_11);

		this.lblNewLabel = new JLabel("Ingrese la base");
		this.panel_11.add(this.lblNewLabel);

		this.panel_10 = new JPanel();
		this.panel_8.add(this.panel_10);

		this.textField_Base = new JTextField();
		this.textField_Base.addKeyListener(this);
		this.panel_10.add(this.textField_Base);
		this.textField_Base.setColumns(10);

		this.panel_3 = new JPanel();
		this.panel_1.add(this.panel_3);

		this.panel_9 = new JPanel();
		this.panel_1.add(this.panel_9);

		this.btnAtrasMenu = new JButton("Atras");
		this.btnAtrasMenu.setActionCommand("atras");
		this.panel_9.add(this.btnAtrasMenu);

		this.btnCrear = new JButton("Crear");
		this.btnCrear.setActionCommand("crear");
		this.btnCrear.setEnabled(false);
		this.panel_9.add(this.btnCrear);

		this.panel_2 = new JPanel();
		this.contentPane.add(this.panel_2);
		this.frame.setVisible(true);

	}

	public String getTipoFuente()
	{
		String tipoFuente = "";
		if (this.rdbtnNula.isSelected())
		{
			tipoFuente = "NULA";
		} else if (this.rdbtnMarkov.isSelected())
		{
			tipoFuente = "MARKOV";
		}
		return tipoFuente;
	}

	public int getCantidadSimbolos()
	{

		return Integer.parseInt(this.textField_CantSimbolos.getText());
	}

	public int getBase()
	{
		return Integer.parseInt(this.textField_Base.getText());
	}

	@Override
	public void keyPressed(KeyEvent arg0)
	{

	}

	@Override
	public void keyReleased(KeyEvent arg0)
	{
		int base = 0;
		int cantSimbolos = 0;
		try
		{
			base = Integer.parseInt(this.textField_Base.getText());
			cantSimbolos = Integer.parseInt(this.textField_CantSimbolos.getText());
		} catch (NumberFormatException e)
		{
		}

		boolean condicion = base > 0 && cantSimbolos > 0;
		this.btnCrear.setEnabled(condicion);

	}

	@Override
	public void keyTyped(KeyEvent arg0)
	{

	}
	
	public void setActionListener(ActionListener actionListener) {
		this.btnAtrasMenu.addActionListener(actionListener);
		this.btnCrear.addActionListener(actionListener);
		this.actionListener = actionListener;
	}

}

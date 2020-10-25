package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

public class fuenteMenu extends JFrame {

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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					fuenteMenu frame = new fuenteMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public fuenteMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 500);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.contentPane);
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
		this.panel_15.add(this.rdbtnNula);
		
		this.panel_14 = new JPanel();
		this.panel_5.add(this.panel_14);
		
		this.rdbtnMarkov = new JRadioButton("Markov");
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
		this.panel_10.add(this.textField_Base);
		this.textField_Base.setColumns(10);
		
		this.panel_3 = new JPanel();
		this.panel_1.add(this.panel_3);
		
		this.panel_9 = new JPanel();
		this.panel_1.add(this.panel_9);
		
		this.btnAtrasMenu = new JButton("Atras");
		this.panel_9.add(this.btnAtrasMenu);
		
		this.btnCrear = new JButton("Crear");
		this.panel_9.add(this.btnCrear);
		
		this.panel_2 = new JPanel();
		this.contentPane.add(this.panel_2);
	}

}

package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class markovMenu extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_3;
	private JPanel panel_4;
	private JPanel panel_5;
	private JPanel panel_6;
	private JPanel panel_7;
	private JPanel panel_8;
	private JPanel panel_9;
	private JPanel panel_10;
	private JPanel panel_11;
	private JPanel panel_12;
	private JPanel panel_13;
	private JPanel panel_14;
	private JPanel panel_15;
	private JLabel lblNewLabel;
	private JTextField textField_Simbolo;
	private JLabel lblNewLabel_1;
	private JTextField textField_Prob;
	private JButton btnAgrega;
	private JButton btnSalir;
	private JPanel panel_2;
	private JScrollPane scrollPane_1;
	private JList listMuestra;
	private JPanel panel_16;
	private JPanel panel_17;
	private JPanel panel_18;
	private JPanel panel_19;
	private JPanel panel_20;
	private JLabel lblEntropia;
	private JLabel lblNewLabel_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					markovMenu frame = new markovMenu();
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
	public markovMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 500);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.contentPane);
		this.contentPane.setLayout(new GridLayout(0, 2, 0, 0));
		
		this.panel = new JPanel();
		this.contentPane.add(this.panel);
		this.panel.setLayout(new GridLayout(12, 0, 0, 0));
		
		this.panel_5 = new JPanel();
		this.panel.add(this.panel_5);
		
		this.panel_6 = new JPanel();
		this.panel.add(this.panel_6);
		
		this.lblNewLabel = new JLabel("Ingrese Simbolo*");
		this.panel_6.add(this.lblNewLabel);
		
		this.panel_7 = new JPanel();
		this.panel.add(this.panel_7);
		
		this.textField_Simbolo = new JTextField();
		this.panel_7.add(this.textField_Simbolo);
		this.textField_Simbolo.setColumns(10);
		
		this.panel_8 = new JPanel();
		this.panel.add(this.panel_8);
		
		this.panel_9 = new JPanel();
		this.panel.add(this.panel_9);
		
		this.lblNewLabel_1 = new JLabel("Ingrese Probabilidad Pos i,j*");
		this.panel_9.add(this.lblNewLabel_1);
		
		this.panel_10 = new JPanel();
		this.panel.add(this.panel_10);
		
		this.textField_Prob = new JTextField();
		this.panel_10.add(this.textField_Prob);
		this.textField_Prob.setColumns(10);
		
		this.panel_11 = new JPanel();
		this.panel.add(this.panel_11);
		
		this.panel_12 = new JPanel();
		this.panel.add(this.panel_12);
		
		this.btnAgrega = new JButton("Agregar");
		this.panel_12.add(this.btnAgrega);
		
		this.panel_13 = new JPanel();
		this.panel.add(this.panel_13);
		
		this.panel_14 = new JPanel();
		this.panel.add(this.panel_14);
		
		this.panel_15 = new JPanel();
		this.panel.add(this.panel_15);
		
		this.panel_4 = new JPanel();
		this.panel.add(this.panel_4);
		
		this.btnSalir = new JButton("Salir");
		this.panel_4.add(this.btnSalir);
		
		this.panel_1 = new JPanel();
		this.contentPane.add(this.panel_1);
		this.panel_1.setLayout(new GridLayout(2, 0, 0, 0));
		
		this.panel_2 = new JPanel();
		this.panel_2.setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128), 2), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.panel_1.add(this.panel_2);
		this.panel_2.setLayout(new BorderLayout(0, 0));
		
		this.scrollPane_1 = new JScrollPane();
		this.panel_2.add(this.scrollPane_1, BorderLayout.CENTER);
		
		this.listMuestra = new JList();
		this.scrollPane_1.setViewportView(this.listMuestra);
		
		this.panel_3 = new JPanel();
		this.panel_1.add(this.panel_3);
		this.panel_3.setLayout(new GridLayout(2, 0, 0, 0));
		
		this.panel_17 = new JPanel();
		this.panel_3.add(this.panel_17);
		this.panel_17.setLayout(new GridLayout(3, 0, 0, 0));
		
		this.panel_19 = new JPanel();
		this.panel_17.add(this.panel_19);
		
		this.panel_20 = new JPanel();
		this.panel_17.add(this.panel_20);
		
		this.lblEntropia = new JLabel("Entropia:");
		this.panel_20.add(this.lblEntropia);
		
		this.panel_18 = new JPanel();
		this.panel_17.add(this.panel_18);
		
		this.panel_16 = new JPanel();
		this.panel_3.add(this.panel_16);
		
		this.lblNewLabel_2 = new JLabel("Vector Estacionario: ");
		this.panel_16.add(this.lblNewLabel_2);
	}

}

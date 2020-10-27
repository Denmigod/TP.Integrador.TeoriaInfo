package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JButton;

public class VentanaEmergenteSimbolo extends JFrame implements KeyListener {

	private JPanel contentPane;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JPanel panel_5;
	private JLabel lblNewLabel;
	private JTextField textFieldSimbolo;
	private JButton btnAceptar;
	private JFrame frame;
	private ActionListener actionListener;

	/**
	 * Create the frame.
	 */
	public VentanaEmergenteSimbolo() {
		this.frame = new JFrame("Ingrese probabilidad");
		this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frame.setBounds(100, 100, 450, 300);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.frame.setContentPane(this.contentPane);
		this.contentPane.setLayout(new GridLayout(6, 0, 0, 0));

		this.panel_1 = new JPanel();
		this.contentPane.add(this.panel_1);

		this.panel_2 = new JPanel();
		this.contentPane.add(this.panel_2);

		this.lblNewLabel = new JLabel("Ingrese el simbolo nro: 1");
		this.panel_2.add(this.lblNewLabel);

		this.panel_3 = new JPanel();
		this.contentPane.add(this.panel_3);

		this.textFieldSimbolo = new JTextField();
		this.textFieldSimbolo.addKeyListener(this);
		this.panel_3.add(this.textFieldSimbolo);
		this.textFieldSimbolo.setColumns(10);

		this.panel_4 = new JPanel();
		this.contentPane.add(this.panel_4);

		this.panel_5 = new JPanel();
		this.contentPane.add(this.panel_5);

		this.btnAceptar = new JButton("Aceptar");
		this.btnAceptar.setActionCommand("AceptarSimbolo");
		this.btnAceptar.setEnabled(false);
		this.panel_5.add(this.btnAceptar);

		this.panel = new JPanel();
		this.contentPane.add(this.panel);
		this.frame.setVisible(true);
	}

	public void keyPressed(KeyEvent arg0) {
	}

	@Override
	public void keyReleased(KeyEvent arg0) {

		this.btnAceptar.setEnabled(!this.textFieldSimbolo.getText().isEmpty());

	}

	public void keyTyped(KeyEvent arg0) {
	}

	public void cerrar() {
		this.frame.dispose();
	}

	public void setLabelSimbolo(int nroSimbolo) {
		this.textFieldSimbolo.setText("");
		this.lblNewLabel.setText("Ingrese el simbolo nro: " + nroSimbolo);
	}

	public String getSimbolo() {
		return this.textFieldSimbolo.getText();
	}

	public void setActionListener(ActionListener actionListener) {

		this.btnAceptar.addActionListener(actionListener);
		this.actionListener = actionListener;
	}
}

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
import javax.swing.JTextArea;
import java.awt.event.ActionEvent;

public class NulaMenu extends JFrame implements KeyListener
{

	private JFrame frame;
	private JPanel contentPane;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JPanel panel_5;
	private JTextArea textArea_Informacion;
	private JTextArea textArea_fuente;
	private JPanel panel_6;
	private JButton btnCodigoInstantaneo;
	private JPanel panel_7;
	private JButton btnSimularCodigos;
	private JButton btnSimularSimbolos;
	private JPanel panel_8;
	private JPanel panel_9;
	private JLabel lb_Entropia;
	private JPanel panel_10;
	private JPanel panel_4;
	private JScrollPane scrollPane_2;
	private JTextArea textArea_Secuencias;
	private ActionListener actionListener;
	private JLabel lblNewLabel;
	private JTextField textFieldCantidad;
	private JPanel panel_11;
	private JButton btnLongitudMedia;
	private JButton btnCompacto;
	private JButton btnKraft;

	/**
	 * Create the frame.
	 */
	public NulaMenu()
	{
		this.frame = new JFrame("Memoria Nula");
		this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frame.setBounds(100, 100, 900, 500);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.frame.setContentPane(this.contentPane);
		this.contentPane.setLayout(new GridLayout(0, 2, 0, 0));

		this.panel = new JPanel();
		this.contentPane.add(this.panel);
		this.panel.setLayout(new GridLayout(2, 0, 0, 0));

		this.panel_5 = new JPanel();
		this.panel.add(this.panel_5);
		this.panel_5.setLayout(new GridLayout(6, 0, 0, 0));

		this.panel_6 = new JPanel();
		this.panel_5.add(this.panel_6);

		this.btnCodigoInstantaneo = new JButton("Crear codificacion instantanea");
		this.btnCodigoInstantaneo.setActionCommand("CodificarFuente");
		this.panel_6.add(this.btnCodigoInstantaneo);

		this.panel_7 = new JPanel();
		this.panel_5.add(this.panel_7);

		this.panel_8 = new JPanel();
		this.panel_5.add(this.panel_8);

		this.lblNewLabel = new JLabel("Cantidad:");
		this.panel_8.add(this.lblNewLabel);

		this.textFieldCantidad = new JTextField();
		this.panel_8.add(this.textFieldCantidad);
		this.textFieldCantidad.addKeyListener(this);
		this.textFieldCantidad.setColumns(10);

		this.panel_9 = new JPanel();
		this.panel_5.add(this.panel_9);

		this.btnSimularSimbolos = new JButton("Simular simbolos");
		this.panel_9.add(this.btnSimularSimbolos);
		this.btnSimularSimbolos.setEnabled(false);
		this.btnSimularSimbolos.setActionCommand("SimularSimbolos");

		this.btnSimularCodigos = new JButton("Simular codigos");
		this.panel_9.add(this.btnSimularCodigos);
		this.btnSimularCodigos.setEnabled(false);
		this.btnSimularCodigos.setActionCommand("SimularCodigos");

		this.panel_11 = new JPanel();
		this.panel_5.add(this.panel_11);

		this.lb_Entropia = new JLabel("Entropia:");
		this.panel_11.add(this.lb_Entropia);

		this.panel_10 = new JPanel();
		this.panel_5.add(this.panel_10);

		this.btnLongitudMedia = new JButton("Longitud media");
		this.btnLongitudMedia.setActionCommand("LongitudMedia");
		this.panel_10.add(this.btnLongitudMedia);

		this.btnCompacto = new JButton("Compacto");
		this.btnCompacto.setActionCommand("Compacto");
		this.panel_10.add(this.btnCompacto);

		this.btnKraft = new JButton("Inecuacion Kraft");
		this.btnKraft.setActionCommand("Kraft");
		this.panel_10.add(this.btnKraft);

		this.panel_4 = new JPanel();
		this.panel_4.setBorder(new TitledBorder(new LineBorder(new Color(0, 255, 0), 2, true), "Secuencias",
				TitledBorder.LEFT, TitledBorder.ABOVE_TOP, null, new Color(0, 255, 0)));
		this.panel.add(this.panel_4);
		this.panel_4.setLayout(new BorderLayout(0, 0));

		this.scrollPane_2 = new JScrollPane();
		this.panel_4.add(this.scrollPane_2, BorderLayout.CENTER);

		this.textArea_Secuencias = new JTextArea();
		this.scrollPane_2.setViewportView(this.textArea_Secuencias);

		this.panel_1 = new JPanel();
		this.contentPane.add(this.panel_1);
		this.panel_1.setLayout(new GridLayout(2, 0, 0, 0));

		this.panel_3 = new JPanel();
		this.panel_3.setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128), 2), "", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		this.panel_1.add(this.panel_3);
		this.panel_3.setLayout(new BorderLayout(0, 0));

		this.scrollPane = new JScrollPane();
		this.panel_3.add(this.scrollPane, BorderLayout.CENTER);

		this.textArea_fuente = new JTextArea();
		this.scrollPane.setViewportView(this.textArea_fuente);

		this.panel_2 = new JPanel();
		this.panel_2.setBorder(new TitledBorder(new LineBorder(new Color(0, 255, 0), 2, true), "Informaci\u00F3n",
				TitledBorder.LEFT, TitledBorder.ABOVE_TOP, null, Color.GREEN));
		this.panel_1.add(this.panel_2);
		this.panel_2.setLayout(new BorderLayout(0, 0));

		this.scrollPane_1 = new JScrollPane();
		this.panel_2.add(this.scrollPane_1, BorderLayout.CENTER);

		this.textArea_Informacion = new JTextArea();
		this.scrollPane_1.setViewportView(this.textArea_Informacion);
		this.frame.setVisible(true);
	}

	public void setActionListener(ActionListener actionListener)
	{
		this.btnCodigoInstantaneo.addActionListener(actionListener);
		this.btnSimularCodigos.addActionListener(actionListener);
		this.btnSimularSimbolos.addActionListener(actionListener);
		this.btnCompacto.addActionListener(actionListener);
		this.btnKraft.addActionListener(actionListener);
		this.btnLongitudMedia.addActionListener(actionListener);
		this.actionListener = actionListener;
	}

	public void setEntropia(double entropia)
	{
		this.lb_Entropia.setText("Entropia: " + String.format("%.5f", entropia));
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

	public void setInformacion(String informacion)
	{
		this.textArea_Informacion.setText(informacion);
	}

	public void setFuente(String fuente)
	{
		this.textArea_fuente.setText(fuente);
	}

	public void setSecuencia(String secuencia)
	{
		this.textArea_Secuencias.setText(secuencia);
	}

	public void cerrar()
	{
		this.frame.dispose();
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
		this.btnSimularCodigos.setEnabled(condicion);
		this.btnSimularSimbolos.setEnabled(condicion);
	}

	public int getCantidad()
	{
		return Integer.parseInt(this.textFieldCantidad.getText());
	}

	public void keyTyped(KeyEvent arg0)
	{
	}
}

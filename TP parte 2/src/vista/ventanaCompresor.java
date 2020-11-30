package vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class ventanaCompresor extends JFrame implements KeyListener
{
	private JFrame frame;
	private JPanel contentPane;
	private JPanel panel;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_5;
	private JLabel lblDireccionEntrada;
	private JTextField textFieldOrigen;
	private JPanel panel_4;
	private JLabel lblDireccionSalida;
	private JTextField textFieldDestino;
	private JPanel panel_6;
	private JPanel panel_7;
	private JRadioButton rdbtnRLC;
	private JRadioButton rdbtnShannonFano;
	private JRadioButton rdbtnHuffman;
	private JPanel panel_8;
	private JPanel panel_9;
	private JPanel panel_10;
	private JPanel panel_11;
	private JPanel panel_12;
	private JButton btnComprimir;
	private JPanel panel_1;
	private JPanel panel_13;
	private JPanel panel_14;
	private JPanel panel_15;
	private JLabel lblRendimiento;
	private JLabel lblRedundancia;
	private JLabel lblTasaCompresion;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private ActionListener actionListener;
	private JPanel panel_16;
	private JLabel lblNombreSalida;
	private JTextField textFieldNombreSalida;

	/**
	 * Create the frame.
	 */
	public ventanaCompresor()
	{
		this.frame = new JFrame("Compresor");
		this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frame.setBounds(100, 100, 893, 506);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.contentPane.setLayout(new BorderLayout(0, 0));
		this.frame.setContentPane(this.contentPane);

		this.panel = new JPanel();
		this.contentPane.add(this.panel, BorderLayout.CENTER);
		this.panel.setLayout(new GridLayout(3, 1, 0, 0));

		this.panel_2 = new JPanel();
		this.panel.add(this.panel_2);
		this.panel_2.setLayout(new GridLayout(3, 0, 0, 0));

		this.panel_5 = new JPanel();
		this.panel_2.add(this.panel_5);
		this.panel_5.setLayout(new MigLayout("", "[][110.00][][][][][grow]", "[][]"));

		this.lblDireccionEntrada = new JLabel("Direccion de origen:");
		this.panel_5.add(this.lblDireccionEntrada, "cell 1 1");

		this.textFieldOrigen = new JTextField();
		this.panel_5.add(this.textFieldOrigen, "cell 4 1 3 1,growx");
		this.textFieldOrigen.setColumns(10);

		this.panel_4 = new JPanel();
		this.panel_2.add(this.panel_4);
		this.panel_4.setLayout(new MigLayout("", "[][110.00][][][][grow]", "[][][]"));

		this.lblDireccionSalida = new JLabel("Direccion de destino:");
		this.panel_4.add(this.lblDireccionSalida, "cell 1 2");

		this.textFieldDestino = new JTextField();
		this.textFieldDestino.setColumns(10);
		this.panel_4.add(this.textFieldDestino, "cell 4 2 2 1,growx");
		
		this.panel_16 = new JPanel();
		this.panel_2.add(this.panel_16);
		this.panel_16.setLayout(new MigLayout("", "[][110.00][][][grow]", "[][]"));
		
		this.lblNombreSalida = new JLabel("Nombre archivo salida:");
		this.panel_16.add(this.lblNombreSalida, "cell 1 1");
		
		this.textFieldNombreSalida = new JTextField();
		this.textFieldNombreSalida.setColumns(10);
		this.panel_16.add(this.textFieldNombreSalida, "cell 4 1,growx");

		this.panel_3 = new JPanel();
		this.panel.add(this.panel_3);
		this.panel_3.setLayout(new GridLayout(0, 4, 0, 0));

		this.panel_8 = new JPanel();
		this.panel_3.add(this.panel_8);

		this.panel_6 = new JPanel();
		this.panel_3.add(this.panel_6);
		this.panel_6.setLayout(new GridLayout(3, 0, 0, 0));

		this.rdbtnHuffman = new JRadioButton("Huffman");
		buttonGroup.add(this.rdbtnHuffman);
		this.rdbtnHuffman.setSelected(true);
		this.panel_6.add(this.rdbtnHuffman);

		this.rdbtnShannonFano = new JRadioButton("Shannon-Fano");
		buttonGroup.add(this.rdbtnShannonFano);
		this.panel_6.add(this.rdbtnShannonFano);

		this.rdbtnRLC = new JRadioButton("RLC");
		buttonGroup.add(this.rdbtnRLC);
		this.panel_6.add(this.rdbtnRLC);

		this.panel_7 = new JPanel();
		this.panel_3.add(this.panel_7);
		this.panel_7.setLayout(new GridLayout(3, 0, 0, 0));

		this.panel_11 = new JPanel();
		this.panel_7.add(this.panel_11);

		this.panel_12 = new JPanel();
		this.panel_7.add(this.panel_12);
		this.panel_12.setLayout(new BoxLayout(this.panel_12, BoxLayout.X_AXIS));

		this.btnComprimir = new JButton("Comprimir");
		this.btnComprimir.setEnabled(false);
		this.btnComprimir.setActionCommand("COMPRIMIR");
		this.panel_12.add(this.btnComprimir);

		this.panel_10 = new JPanel();
		this.panel_7.add(this.panel_10);

		this.panel_9 = new JPanel();
		this.panel_3.add(this.panel_9);

		this.panel_1 = new JPanel();
		this.panel.add(this.panel_1);
		this.panel_1.setLayout(new GridLayout(3, 0, 0, 0));

		this.panel_14 = new JPanel();
		this.panel_1.add(this.panel_14);

		this.lblRendimiento = new JLabel("");
		this.panel_14.add(this.lblRendimiento);

		this.panel_15 = new JPanel();
		this.panel_1.add(this.panel_15);

		this.lblRedundancia = new JLabel("");
		this.panel_15.add(this.lblRedundancia);

		this.panel_13 = new JPanel();
		this.panel_1.add(this.panel_13);

		this.lblTasaCompresion = new JLabel("");
		this.panel_13.add(this.lblTasaCompresion);
		this.frame.setVisible(true);
	}

	@Override
	public void keyPressed(KeyEvent arg0)
	{

	}

	@Override
	public void keyReleased(KeyEvent arg0)
	{
		this.btnComprimir
				.setEnabled(!this.textFieldOrigen.getText().isEmpty() && !this.textFieldNombreSalida.getText().isEmpty());
	}

	@Override
	public void keyTyped(KeyEvent arg0)
	{

	}

	public void setActionListener(ActionListener actionListener)
	{
		this.btnComprimir.addActionListener(actionListener);
		this.actionListener = actionListener;
	}

	public String getOrigen()
	{
		return this.textFieldOrigen.getText();
	}

	public String getDestino()
	{
		return this.textFieldDestino.getText();
	}
	
	public String getNombreSalida()
	{
		return this.textFieldNombreSalida.getText();
	}

	public String getTipoCompresion()
	{
		String tipo = "";
		if (this.rdbtnHuffman.isSelected())
		{
			tipo = "HUFFMAN";
		} else if (this.rdbtnShannonFano.isSelected())
		{
			tipo = "SHANNON-FANO";
		} else if (this.rdbtnRLC.isSelected())
		{
			tipo = "RLC";
		}
		return tipo;
	}

	public void setRendimiento(String rendimiento)
	{
		this.lblRendimiento.setText(rendimiento);
	}

	public void setRedundancia(String redundancia)
	{
		this.lblRendimiento.setText(redundancia);
	}

	public void setTasaCompresion(String tasaCompresion)
	{
		this.lblRendimiento.setText(tasaCompresion);
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

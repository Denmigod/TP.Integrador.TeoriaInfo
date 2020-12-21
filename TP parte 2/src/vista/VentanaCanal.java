package vista;

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

public class VentanaCanal extends JFrame
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
	private JButton btnSalir;
	private JLabel lblEquivocacion;
	private JLabel lblInformacionMutua;
	private ActionListener actionListener;
	private JLabel lblPropiedadesInformacionMutua;

	/**
	 * Create the frame.
	 */
	public VentanaCanal()
	{
		this.frame = new JFrame("Canal");
		this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frame.setBounds(100, 100, 588, 774);
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

		this.lblEquivocacion = new JLabel("Equivocacion: ");
		this.panel_3.add(this.lblEquivocacion);

		this.panel_4 = new JPanel();
		this.panel.add(this.panel_4);

		this.lblInformacionMutua = new JLabel("Informacion mutua:");
		this.panel_4.add(this.lblInformacionMutua);

		this.panel_5 = new JPanel();
		this.panel.add(this.panel_5);

		this.lblPropiedadesInformacionMutua = new JLabel("Propiedades de la informacion mutua:");
		this.panel_5.add(this.lblPropiedadesInformacionMutua);

		this.panel_2 = new JPanel();
		this.panel.add(this.panel_2);

		this.btnSalir = new JButton("Salir");
		this.btnSalir.setActionCommand("SALIR");
		this.panel_2.add(this.btnSalir);
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

	public void setInformacionMutua(double informacionMutua)
	{
		this.lblInformacionMutua.setText("Informacion mutua: " + String.format("%.5f", informacionMutua));
	}

	public void setEquivoacion(double equivocacion)
	{
		this.lblEquivocacion.setText("Equivocacion: " + String.format("%.5f", equivocacion));
	}
	
	public void setPropiedadesInformacionMutua(String propiedades)
	{
		this.lblPropiedadesInformacionMutua.setText("Propiedades de la informacion mutua: " + propiedades);
	}

	public void cerrar()
	{
		this.frame.dispose();
	}

	public void setActionListener(ActionListener actionListener)
	{
		this.btnSalir.addActionListener(actionListener);
		this.actionListener = actionListener;
	}

}

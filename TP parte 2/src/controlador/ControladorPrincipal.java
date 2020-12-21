package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vista.VentanaPrincipal;

public class ControladorPrincipal implements ActionListener
{
	private VentanaPrincipal ventana;

	public ControladorPrincipal()
	{
		this.ventana = new VentanaPrincipal();
		this.ventana.setActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		String comando = arg0.getActionCommand();

		if (comando.equals("COMPRESOR"))
		{
			new ControladorCompresor();
		} else if (comando.equals("CANAL"))
		{
			new ControladorCanal();
		}

	}

}

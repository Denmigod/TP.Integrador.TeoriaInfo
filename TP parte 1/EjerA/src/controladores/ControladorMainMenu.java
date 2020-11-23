package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import UI.MainMenu;


public class ControladorMainMenu implements ActionListener
{
	MainMenu ventana;
	
	public ControladorMainMenu()
	{
		this.ventana = new MainMenu();
		this.ventana.setActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		String comando = arg0.getActionCommand();
		if(comando.equals("crearFuente")) {
			new ControladorCrearFuente();
		}

	}

}

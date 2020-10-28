package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import UI.FuenteMenu;

public class ControladorCrearFuente implements ActionListener
{
	FuenteMenu ventana;

	public ControladorCrearFuente()
	{
		this.ventana = new FuenteMenu();
		this.ventana.setActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		String comando = arg0.getActionCommand();
		if (comando.equals("crear"))
		{
			this.crear();
		} else if (comando.equals("atras"))
		{
			this.atras();
		}
	}

	private void atras()
	{
		ventana.dispose();
	}

	private void crear()
	{
		String tipoFuente = this.ventana.getTipoFuente();
		if (tipoFuente.equals("NULA"))
		{	
			new ControladorNula(this.ventana.getBase(),this.ventana.getCantidadSimbolos());
		} else if (tipoFuente.equals("MARKOV"))
		{
			new ControladorMarkov(this.ventana.getBase(),this.ventana.getCantidadSimbolos());
		}
	}

}

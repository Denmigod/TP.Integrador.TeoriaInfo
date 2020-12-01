package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import canal.Canal;
import utilidades.Matrices;
import vista.VentanaCanal;
import vista.VentanaCrearCanal;
import vista.VentanaEntradas;
import vista.VentanaMatriz;

public class ControladorCanal implements ActionListener
{
	private VentanaCanal ventana;
	private VentanaEntradas ventanaEntradas;
	private VentanaMatriz ventanaMatriz;
	private VentanaCrearCanal ventanaCrearCanal;
	private Canal canal;

	public ControladorCanal()
	{
		this.crearCanal();
	}

	private void crearCanal()
	{
		this.ventanaCrearCanal = new VentanaCrearCanal();
		this.ventanaCrearCanal.setActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String comando = e.getActionCommand();
		if (comando.equals("CREAR"))
		{
			this.ventanaCrearCanal.cerrar();
			this.cargarEntradas();
		} else if (comando.equals("ACEPTAR_ENTRADAS"))
		{
			if (this.ventanaEntradas.validaBotonAceptar())
				this.cargarMatriz();
			else
				this.ventanaEntradas.imprimeMensaje("Debe llenar todos los campos con probabilidades validas");
		} else if (comando.equals("ACEPTAR_MATRIZ"))
		{
			if (this.ventanaMatriz.validaBotonAceptar())
				this.iniciaCanal();
			else
				this.ventanaEntradas.imprimeMensaje("Debe llenar todos los campos con probabilidades validas");

		} else if (comando.equals("SALIR"))
		{
			this.ventana.cerrar();
		}

	}

	private void iniciaCanal()
	{
		this.canal = new Canal(this.ventanaEntradas.getEntradas(), this.ventanaMatriz.getMatrizCanal());
		this.ventanaEntradas.cerrar();
		this.ventanaMatriz.cerrar();
		this.ventana = new VentanaCanal();
		this.ventana.setActionListener(this);
		this.ventana.setEquivoacion(this.canal.getEquivocacion());
		this.ventana.setInformacionMutua(this.canal.getInformacionMutua());
		String propiedades = "";
		if (this.canal.informacionMutuaIsSimetrica())
		{
			propiedades += "Es simetrica";
		} else
		{
			propiedades += "No es simetrica";
		}
		if (this.canal.informacionMutuaIsMayorIgualCero())
		{
			propiedades += " y es mayor/igual a cero";
		} else
		{
			propiedades += " y no es mayor/igual a cero";
		}
		this.ventana.setPropiedadesInformacionMutua(propiedades);
		this.ventana
				.setTextPane("Vector de entradas:\n" + Matrices.matrizToString(this.canal.getProbabilidadesAPriori())
						+ "Matriz del canal:\n" + Matrices.matrizToString(this.canal.getCanal()));
	}

	private void cargarMatriz()
	{
		this.ventanaEntradas.ocultar();
		this.ventanaMatriz = new VentanaMatriz(this.ventanaCrearCanal.getEntradas(),
				this.ventanaCrearCanal.getSalidas());
		this.ventanaMatriz.setActionListener(this);

	}

	private void cargarEntradas()
	{
		this.ventanaEntradas = new VentanaEntradas(this.ventanaCrearCanal.getEntradas());
		this.ventanaEntradas.setActionListener(this);
	}
}

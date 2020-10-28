package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import UI.MarkovMenu;
import UI.VentanaEmergenteMatrizTransicion;
import UI.VentanaEmergenteProbabilidad;
import UI.VentanaEmergenteSimbolo;
import UI.VentanaEmergenteSimbolos;
import excepciones.DimensionInconsistenteException;
import excepciones.ProbabilidadTotalException;
import modelo.Markov;
import utilidades.Matrices;

public class ControladorMarkov implements ActionListener
{
	private Markov fuente;
	private MarkovMenu ventana;
	private VentanaEmergenteSimbolos ventanaEmergenteSimbolos;
	private VentanaEmergenteMatrizTransicion ventanaEmergenteMatrizTransicion;
	private ArrayList<String> simbolos = new ArrayList<String>();
	private double matrizTransicion[][];
	private int base;
	private int cantidadSimbolos;

	public ControladorMarkov(int base, int cantSimbolos)
	{
		this.cantidadSimbolos = cantSimbolos;
		this.base = base;
		this.creaArrayListSimbolos();
	}

	private void creaArrayListSimbolos()
	{
		this.ventanaEmergenteSimbolos = new VentanaEmergenteSimbolos(this.cantidadSimbolos);
		ventanaEmergenteSimbolos.setActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		String comando = arg0.getActionCommand();
		if (comando.equals("AceptarSimbolo"))
			this.botonAceptarSimbolo();
		else if (comando.equals("AceptarProbabilidad"))
			this.botonAceptarProbabilidad();
		else if (comando.equals("Salir"))
		{
			this.ventana.cerrar();
		}

	}

	private void botonAceptarProbabilidad()
	{
		if (this.ventanaEmergenteMatrizTransicion.validaBotonAceptar())
		{
			this.matrizTransicion = this.ventanaEmergenteMatrizTransicion.getMatrizTransicion();
			try
			{
				this.fuente = new Markov(this.matrizTransicion, this.simbolos, this.base);
				this.ventanaEmergenteMatrizTransicion.cerrar();
				this.inicializaVentanaMarkov();
			} catch (DimensionInconsistenteException e)// nunca ocurre controlado en la generacion de la ventana
			{

			}

		} else
		{
			this.ventanaEmergenteMatrizTransicion.imprimeMensaje("Debe llenar todos los campos");
		}
	}

	private void inicializaVentanaMarkov()
	{
		this.ventana = new MarkovMenu();
		this.ventana.setActionListener(this);
		this.ventana.setEntropia(this.fuente.getEntropia());
		this.muestraVectorEstacionario();
		this.ventana.setTextPane(Matrices.matrizToString(this.fuente.getMatrizTransicion()));

	}

	private void muestraVectorEstacionario()
	{
		StringBuilder sb = new StringBuilder();
		double[] vectorEstacionario = this.fuente.getVectorEstacionario();
		sb.append(String.format("%.5f", vectorEstacionario[0]));
		for (int i = 1; i < vectorEstacionario.length; i++)
		{
			sb.append(" | " + String.format("%.5f", vectorEstacionario[i]));
		}
		this.ventana.setVectorEstacionario(sb.toString());

	}

	public void botonAceptarSimbolo()
	{
		if (this.ventanaEmergenteSimbolos.validaBotonAceptar())
		{
			String[] vectorSimbolos = this.ventanaEmergenteSimbolos.getSimbolos();
			for (int i = 0; i < this.cantidadSimbolos; i++)
			{
				this.simbolos.add(vectorSimbolos[i]);
			}
			this.ventanaEmergenteSimbolos.cerrar();
			this.ventanaEmergenteMatrizTransicion = new VentanaEmergenteMatrizTransicion(this.cantidadSimbolos);
			this.ventanaEmergenteMatrizTransicion.setActionListener(this);
		} else
		{
			this.ventanaEmergenteSimbolos.imprimeMensaje("Debe llenar todos los campos");
		}
	}

}

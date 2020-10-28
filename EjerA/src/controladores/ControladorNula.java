package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import UI.NulaMenu;
import UI.VentanaEmergenteMemoriaNula;
import excepciones.CodigoInexistenteException;
import excepciones.ProbabilidadTotalException;
import modelo.MemoriaNula;
import modelo.Simbolo;

public class ControladorNula implements ActionListener
{
	MemoriaNula fuente;
	NulaMenu ventana;
	VentanaEmergenteMemoriaNula ventanaEmergente;
	int base;

	public ControladorNula(int base, int cantSimbolos)
	{
		this.base = base;
		this.ventanaEmergente = new VentanaEmergenteMemoriaNula(cantSimbolos);
		this.ventanaEmergente.setActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		String comando = arg0.getActionCommand();
		if (comando.equals("Aceptar"))
		{
			this.botonAceptar();
		} else if (comando.equals("CodificarFuente"))
		{
			this.botonCodificarFuente();
		} else if (comando.equals("SimularSimbolos"))
		{
			this.botonSimularSimbolos();
		} else if (comando.equals("SimularCodigos"))
		{
			this.botonSimularCodigos();
		} else if (comando.equals("LongitudMedia"))
		{
			this.botonLongitudMedia();
		} else if (comando.equals("Compacto"))
		{
			this.botonCompacto();
		} else if (comando.equals("Kraft"))
		{
			this.botonKraft();
		}
	}

	private void botonKraft()
	{
		try
		{
			double kraft = this.fuente.sumatoriaInecuacionKraft();
			if (this.fuente.verificaInecuacionKraft())
			{
				this.ventana.imprimeMensaje("La ecuacion de Kraft se verifica \n" + kraft + " <= 1");
			} else
			{
				this.ventana.imprimeMensaje("La ecuacion de Kraft no se verifica \n" + kraft + " > 1");
			}

		} catch (ProbabilidadTotalException e)
		{
			this.ventana.imprimeMensaje("La probabilidad total no es 1 cree la fuente nuevamente");
		} catch (CodigoInexistenteException e)
		{
			this.ventana.imprimeMensaje("Se debe crear una codificacion para poder realizar esta accion");
		}

	}

	private void botonCompacto()
	{
		try
		{
			if (this.fuente.isCompacto())
			{
				this.ventana.imprimeMensaje("El codigo es compacto");

			} else
			{
				this.ventana.imprimeMensaje("El codigo no es compacto");
			}
		} catch (ProbabilidadTotalException e)
		{
			this.ventana.imprimeMensaje("La probabilidad total no es 1 cree la fuente nuevamente");
		} catch (CodigoInexistenteException e)
		{
			this.ventana.imprimeMensaje("Se debe crear una codificacion para poder realizar esta accion");
		}

	}

	private void botonLongitudMedia()
	{
		try
		{
			double longitudMedia = this.fuente.longitudMediaCodigo();
			this.ventana.imprimeMensaje("La longitud media del codigo es: " + String.format("%.5f", longitudMedia));
		} catch (ProbabilidadTotalException e)
		{
			this.ventana.imprimeMensaje("La probabilidad total no es 1 cree la fuente nuevamente");
		} catch (CodigoInexistenteException e)
		{
			this.ventana.imprimeMensaje("Se debe crear una codificacion para poder realizar esta accion");
		}

	}

	private void botonSimularCodigos()
	{
		try
		{
			String secuencia = this.fuente.generarSecuenciaCodigos(this.ventana.getCantidad());
			this.ventana.setSecuencia(secuencia);
		} catch (ProbabilidadTotalException e)
		{
			this.ventana.imprimeMensaje("La probabilidad total no es 1 cree la fuente nuevamente");
		} catch (CodigoInexistenteException e)
		{
			this.ventana.imprimeMensaje("Se debe crear una codificacion para poder realizar esta accion");
		}

	}

	private void botonSimularSimbolos()
	{
		try
		{
			String secuencia = this.fuente.generarSecuenciaSimbolos(this.ventana.getCantidad());
			this.ventana.setSecuencia(secuencia);
		} catch (ProbabilidadTotalException e)
		{
			this.ventana.imprimeMensaje("La probabilidad total no es 1 cree la fuente nuevamente");
		}

	}

	private void botonCodificarFuente()
	{
		try
		{
			this.fuente.generaCodigoInstantaneo();
			this.ventana.setFuente(this.fuente.enlistaFuente());
		} catch (ProbabilidadTotalException e)
		{
			this.ventana.imprimeMensaje("La probabilidad total no es 1 cree la fuente nuevamente");
		}

	}

	private void botonAceptar()
	{
		if (this.ventanaEmergente.validaBotonAceptar())
		{
			this.creaVenanaMemoriaNula(this.ventanaEmergente.getSimbolos(), this.ventanaEmergente.getProbabilidades());
		} else
		{
			this.ventanaEmergente.imprimeMensaje("Debe compleatar todos los campos de probabilidad y simbolos");
		}

	}

	private void creaVenanaMemoriaNula(String[] simbolos, double[] probabilidades)
	{
		int n = simbolos.length;
		this.fuente = new MemoriaNula(this.base);
		try
		{
			for (int i = 0; i < n; i++)
			{
				fuente.addSimbolo(new Simbolo(simbolos[i], probabilidades[i]));
			}

			this.ventana = new NulaMenu();
			this.ventana.setActionListener(this);
			this.inicializaVentanaMemoriaNula();
			this.ventanaEmergente.cerrar();
		} catch (ProbabilidadTotalException e)
		{
			this.ventanaEmergente.imprimeMensaje(e.getMessage());
			this.ventana.cerrar();
			this.fuente = new MemoriaNula(this.base);
		}
	}

	private void inicializaVentanaMemoriaNula() throws ProbabilidadTotalException
	{
		this.ventana.setEntropia(this.fuente.getEntropia());
		this.ventana.setFuente(this.fuente.enlistaFuente());
		this.ventana.setInformacion(this.fuente.enlistaCantidadInformacion());
	}

}

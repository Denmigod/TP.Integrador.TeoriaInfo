package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import compresor.Huffman;
import compresor.ICompresor;
import compresor.RLC;
import compresor.ShannonFano;
import vista.VentanaCodificacion;
import vista.VentanaCompresor;

public class ControladorCompresor implements ActionListener
{
	private VentanaCompresor ventana;
	private ICompresor compresor;

	public ControladorCompresor()
	{
		this.ventana = new VentanaCompresor();
		this.ventana.setActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String comando = e.getActionCommand();
		if (comando.equals("COMPRIMIR"))
		{
			this.botonComprimir();
			this.ventana.limpiaTextFields();
			this.ventana.desactivaBotones();
		} else if (comando.equals("DESCOMPRIMIR"))
		{
			this.descomprimir();
			this.ventana.limpiaTextFields();
			this.ventana.desactivaBotones();
		}

	}

	private void descomprimir()
	{
		String origen = this.ventana.getOrigen();
		String destino = this.ventana.getDestino();
		String nombre = this.ventana.getNombreSalida();
		String tipo = this.ventana.getTipoCompresion();

		if (tipo.equals("HUFFMAN"))
		{
			this.compresor = new Huffman();
		} else if (tipo.equals("SHANNON-FANO"))
		{
			this.compresor = new ShannonFano();

		} else if (tipo.equals("RLC"))
		{
			this.compresor = new RLC();

		}

		try
		{
			this.compresor.descomprimir(origen, destino, nombre);
			this.ventana.imprimeMensaje("El archivo se descomprimio correctamente");
		} catch (FileNotFoundException e)
		{
			this.ventana.imprimeMensaje(e.getMessage());
		} catch (IOException e)
		{
			// se supone que nunca entra en esta excepcion
		}

	}

	private void botonComprimir()
	{

		String tipo = this.ventana.getTipoCompresion();

		if (tipo.equals("HUFFMAN"))
		{
			this.compresor = new Huffman();
			this.comprimir();
			new VentanaCodificacion("HUFFMAN").actualizaLista(compresor.getListaCodificacion());
		} else if (tipo.equals("SHANNON-FANO"))
		{
			this.compresor = new ShannonFano();
			this.comprimir();
			new VentanaCodificacion("SHANNON-FANO").actualizaLista(compresor.getListaCodificacion());
		} else if (tipo.equals("RLC"))
		{
			this.compresor = new RLC();
			this.comprimir();
		}

	}

	private void comprimir()
	{
		String origen = this.ventana.getOrigen();
		String destino = this.ventana.getDestino();
		String nombre = this.ventana.getNombreSalida();
		try
		{
			this.compresor.comprimir(origen, destino, nombre);
			this.ventana.setRedundancia("Redundancia: " + this.compresor.getRedundancia());
			this.ventana.setRendimiento("Rendimiento: " + this.compresor.getRendimiento());
			this.ventana.setTasaCompresion("Tasa de compresion: " + this.compresor.getTasaCompresion());
			this.ventana.imprimeMensaje("El archivo se comprimio correctamente");

		} catch (FileNotFoundException e)
		{
			this.ventana.imprimeMensaje(e.getMessage());
		} catch (IOException e)
		{
			// se supone que nunca entra en esta excepcion
		}
	}

}

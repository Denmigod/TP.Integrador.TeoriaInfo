package modelo;

import excepciones.ProbabilidadTotalException;
import excepciones.SimboloNoEncontradoException;

public class PruebaNula
{
	public static void main(String[] args)
	{
		MemoriaNula fuenteMemNula = new MemoriaNula(2);
		try
		{
			fuenteMemNula.addSimbolo(new Simbolo("1", 0.1));
			fuenteMemNula.addSimbolo(new Simbolo("01", 0.4));
			fuenteMemNula.addSimbolo(new Simbolo("011", 0.5));
			System.out.println(fuenteMemNula.getCantInformacion("01"));
			System.out.println(fuenteMemNula.getEntropia());
		} catch (ProbabilidadTotalException e)
		{
			e.printStackTrace();
		} catch (SimboloNoEncontradoException e)
		{
			e.printStackTrace();
		}

	}
}

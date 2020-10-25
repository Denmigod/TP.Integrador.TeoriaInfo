package pruebas;

import excepciones.ProbabilidadTotalException;
import excepciones.SimboloNoEncontradoException;
import modelo.MemoriaNula;
import modelo.Simbolo;

public class PruebaNula
{
	public static void main(String[] args)
	{
		MemoriaNula fuenteMemNula = new MemoriaNula(2);
		try
		{
			fuenteMemNula.addSimbolo(new Simbolo("a", 0.1));
			fuenteMemNula.addSimbolo(new Simbolo("b", 0.4));
			fuenteMemNula.addSimbolo(new Simbolo("c", 0.5));
			System.out.println(fuenteMemNula.getCantInformacion("b"));
			System.out.println(fuenteMemNula.getEntropia());

			System.out.println(fuenteMemNula.generarSimbolos(10));

			System.out.println("\n" + fuenteMemNula.listaCantidadInformacion());
		} catch (ProbabilidadTotalException e)
		{
			e.printStackTrace();
		} catch (SimboloNoEncontradoException e)
		{
			e.printStackTrace();
		}

	}
}

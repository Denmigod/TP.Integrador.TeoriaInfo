package pruebas;

import excepciones.CodigoInexistenteException;
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
			fuenteMemNula.addSimbolo(new Simbolo("b", 0.5));
			fuenteMemNula.addSimbolo(new Simbolo("c", 0.2));
			fuenteMemNula.addSimbolo(new Simbolo("d", 0.05));
			fuenteMemNula.addSimbolo(new Simbolo("e", 0.15));

			System.out.println(fuenteMemNula.getCantInformacion("b"));
			System.out.println(fuenteMemNula.getEntropia());

			System.out.println(fuenteMemNula.generarSecuenciaSimbolos(10));

			fuenteMemNula.generaCodigoInstantaneo();

			System.out.println("\n" + fuenteMemNula.enlistaFuente());
			System.out
					.println(fuenteMemNula.verificaInecuacionKraft() + " " + fuenteMemNula.sumatoriaInecuacionKraft());
			System.out.println(fuenteMemNula.generarSecuenciaCodigos(10));
			System.out.println(fuenteMemNula.longitudMediaCodigo());
		} catch (ProbabilidadTotalException e)
		{
			e.printStackTrace();
		} catch (SimboloNoEncontradoException e)
		{
			e.printStackTrace();
		} catch (CodigoInexistenteException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

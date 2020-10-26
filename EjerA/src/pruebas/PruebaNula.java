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
			fuenteMemNula.addSimbolo(new Simbolo("a", 0.125));
			fuenteMemNula.addSimbolo(new Simbolo("b", 0.5));
			fuenteMemNula.addSimbolo(new Simbolo("c", 0.375));

			System.out.println(fuenteMemNula.getCantInformacion("b"));
			System.out.println(fuenteMemNula.getEntropia());

			System.out.println(fuenteMemNula.generarSecuenciaSimbolos(10));

			fuenteMemNula.generaCodigoInstantaneo();

			System.out.println("\n" + fuenteMemNula.enlistaFuente());
			System.out
					.println(fuenteMemNula.verificaInecuacionKraft() + " " + fuenteMemNula.sumatoriaInecuacionKraft());
			System.out.println(fuenteMemNula.generarSecuenciaCodigos(10));
			System.out.println(fuenteMemNula.longitudMediaCodigo());
			System.out.println(fuenteMemNula.isCompacto());
		} catch (ProbabilidadTotalException e)
		{
			e.printStackTrace();
		} catch (SimboloNoEncontradoException e)
		{
			e.printStackTrace();
		} catch (CodigoInexistenteException e)
		{
			e.printStackTrace();
		}

	}
}

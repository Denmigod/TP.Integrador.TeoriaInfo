package modelo;

import java.util.ArrayList;

import excepciones.DimensionInconsistenteException;
import excepciones.ProbabilidadTotalException;

public class prueba
{

	public static void main(String[] args)
	{
		double[][] mat = new double[3][3];
		ArrayList<String> simbolos = new ArrayList<String>();
		simbolos.add("a");
		simbolos.add("b");
		simbolos.add("c");

		mat[0][0] = 1. / 2;
		mat[0][1] = 1. / 3;
		mat[0][2] = 0;

		mat[1][0] = 1. / 2;
		mat[1][1] = 1. / 3;
		mat[1][2] = 1;

		mat[2][0] = 0;
		mat[2][1] = 1. / 3;
		mat[2][2] = 0;

		try
		{
			Markov fuenteMarkov = new Markov(mat, simbolos);
			System.out.println(fuenteMarkov.getEntropia());

			System.out.println(fuenteMarkov.getVectorEstacionario()[0] + " " + fuenteMarkov.getVectorEstacionario()[1]
					+ " " + fuenteMarkov.getVectorEstacionario()[2]);
		} catch (DimensionInconsistenteException e)
		{
			e.printStackTrace();
		} catch (ProbabilidadTotalException e)
		{
			e.printStackTrace();
		}

	}

}

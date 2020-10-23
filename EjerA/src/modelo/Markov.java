package modelo;

import java.util.ArrayList;

import excepciones.DimensionInconsistenteException;
import excepciones.ProbabilidadTotalException;


public class Markov extends Fuente
{
	private ArrayList<String> simbolos = new ArrayList<String>();
	private double[][] matrizTransicion;
	private int base;
	private double[] vectorEstacionario;

	public Markov(double[][] matrizTransicion, ArrayList<String> simbolos, int base)
			throws DimensionInconsistenteException
	{
		if (matrizTransicion.length != simbolos.size())
		{
			throw new DimensionInconsistenteException(
					"La matriz de transicion no tiene la misma dimension que el vector de simbolos");
		} else
		{
			this.base = base;
			this.matrizTransicion = matrizTransicion;
			this.simbolos = simbolos;
			this.vectorEstacionario = new double[simbolos.size()];
			this.calcularVectorEstacionario();
		}
	}

	private void calcularVectorEstacionario()
	{
		int iteraciones = 20; // se puede aumentar
		int n = simbolos.size();
		double[][] matrizAux = new double[n][n];
		Matrices.copiaMatriz(matrizAux, this.matrizTransicion);
		for (int i = 0; i < iteraciones; i++)
		{
			Matrices.multiplicarMatrices(matrizAux, matrizAux);
		}
		for (int i = 0; i < n; i++)
		{
			this.vectorEstacionario[i] = matrizAux[i][0];
		}

	}

	@Override
	public double getEntropia() throws ProbabilidadTotalException
	{
		double resultado = 0;
		int n = simbolos.size();
		
		for (int i = 0; i < n; i++)
		{
			double acumulador = 0;
			for (int j = 0; j < n; j++)
			{
				double probabilidad = this.matrizTransicion[j][i]; // recorre la matriz por columnas
				double cantidadInformacion = 0;
				if (probabilidad > 0)
				{
					cantidadInformacion = -1 * Math.log(probabilidad) / Math.log(this.base);
				}
				acumulador += probabilidad * cantidadInformacion;
			}
			resultado += this.vectorEstacionario[i] * acumulador;
		}

		return resultado;
	}

	/**
	 * @return the simbolos
	 */
	public ArrayList<String> getSimbolos()
	{
		return simbolos;
	}

	/**
	 * @return the matrizTransicion
	 */
	public double[][] getMatrizTransicion()
	{
		return matrizTransicion;
	}

	/**
	 * @return the vectorEstacionario
	 */
	public double[] getVectorEstacionario()
	{
		return vectorEstacionario;
	}

	/**
	 * @return the base
	 */
	public int getBase()
	{
		return base;
	}

}

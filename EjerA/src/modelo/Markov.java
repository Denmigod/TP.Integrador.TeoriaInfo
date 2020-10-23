package modelo;

import java.util.ArrayList;

import excepciones.DimensionInconsistenteException;
import excepciones.ProbabilidadTotalException;
import excepciones.SimboloNoEncontradoException;

public class Markov extends Fuente
{
	private ArrayList<String> simbolos = new ArrayList<String>();
	private double[][] matrizTransicion;
	private int base;
	private double[] vectorEstacionario;

	public Markov(double[][] matrizTransicion, ArrayList<String> simbolos) throws DimensionInconsistenteException
	{
		if (matrizTransicion.length != simbolos.size())
		{
			throw new DimensionInconsistenteException(
					"La matriz de transicion no tiene la misma dimension que el vector de simbolos");
		} else
		{
			this.base = simbolos.size();
			this.matrizTransicion = matrizTransicion;
			this.simbolos = simbolos;
			this.vectorEstacionario = new double[base];
			this.calcularVectorEstacionario();
		}
	}

	private void calcularVectorEstacionario()
	{
		int iteraciones = 20; // se puede aumentar
		double[][] matrizAux = new double[base][base];
		Matrices.copiaMatriz(matrizAux, this.matrizTransicion);
		for (int i = 0; i < iteraciones; i++)
		{
			Matrices.multiplicarMatrices(matrizAux, matrizAux);
		}
		for (int i = 0; i < base; i++)
		{
			this.vectorEstacionario[i] = matrizAux[i][0];
		}

	}

	@Override
	public double getEntropia() throws ProbabilidadTotalException
	{
		double resultado = 0;

		for (int i = 0; i < this.base; i++)
		{
			double acumulador = 0;
			for (int j = 0; j < base; j++)
			{
				double probabilidad = this.matrizTransicion[i][j];
				double cantidadInformacion = 0;
				if (probabilidad > 0)
				{
					cantidadInformacion = -1 * Math.log(probabilidad) / Math.log(base);
				}
				acumulador += probabilidad * cantidadInformacion;
			}
			resultado += this.vectorEstacionario[i] * acumulador;
		}

		return resultado;
	}

	@Override
	public double getCantInformacion(String simbolo) throws SimboloNoEncontradoException
	{

		return 0;
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

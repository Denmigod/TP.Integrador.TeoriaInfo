package modelo;

import java.util.ArrayList;
import java.util.Random;

import excepciones.DimensionInconsistenteException;
import excepciones.ProbabilidadTotalException;
import utilidades.Matrices;

/**
 * Clase que simula una fuente de markov, puede generar una secuencia de datos a
 * partir de la misma y realiza los calculos correspondientes a este tipo de
 * fuente
 * 
 * @author Grupo 6
 *
 */
public class Markov extends Fuente
{
	private ArrayList<String> simbolos = new ArrayList<String>();
	private double[][] matrizTransicion;
	private int base;
	private double[] vectorEstacionario;
	private String simboloAnterior = null;

	/**
	 * Costructor del objeto markov
	 * 
	 * @param matrizTransicion : matriz de transicion de la fuente de markov
	 * @param simbolos         : arraylist con los simbolos de la fuente
	 * @param base             : base de la fuente
	 * @throws DimensionInconsistenteException : cuando no coincide el tamaño del
	 *                                         arraylist con el tamaño de la matriz
	 */
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

	/**
	 * 
	 */
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
	public double getEntropia() 
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
	 * @param n : cantidad
	 * @return devuelve el simbolo que es string
	 */
	public String generarSimbolos(int n)
	{
		StringBuilder sb = new StringBuilder();
		int columnaSimboloAnterior;
		Random randnum = new Random();
		randnum.setSeed(System.nanoTime());

		if (simboloAnterior == null)
		{
			columnaSimboloAnterior = randnum.nextInt(this.simbolos.size());
			this.simboloAnterior = this.simbolos.get(columnaSimboloAnterior);
			sb.append(this.simboloAnterior);
			n--;
		}
		for (int i = 0; i < n; i++)
		{
			columnaSimboloAnterior = this.simbolos.indexOf(simboloAnterior);
			double numeroRandom = randnum.nextDouble();
			double sumaProbabilidades = 0;
			int j = -1;
			while (sumaProbabilidades <= numeroRandom)
			{
				sumaProbabilidades += this.matrizTransicion[j][columnaSimboloAnterior];
				j++;
			}
			sb.append(this.simbolos.get(j));
		}

		return sb.toString();
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

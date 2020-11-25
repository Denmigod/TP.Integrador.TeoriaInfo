package compresor;

public class NodoShannonFano
{
	private int simbolo;
	private int frecuencia;

	public NodoShannonFano(int simbolo, int frecuencia)
	{
		this.simbolo = simbolo;
		this.frecuencia = frecuencia;
	}

	/**
	 * @return the frecuencia
	 */
	public int getFrecuencia()
	{
		return frecuencia;
	}

	/**
	 * @return the simbolo
	 */
	public int getSimbolo()
	{
		return simbolo;
	}

}

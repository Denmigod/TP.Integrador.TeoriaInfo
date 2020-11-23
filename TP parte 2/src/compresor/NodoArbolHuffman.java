package compresor;

public class NodoArbolHuffman
{
	private NodoArbolHuffman hijoDerecha = null;
	private NodoArbolHuffman hijoIzquierda = null;
	private int Simbolo;
	private int frecuencia;

	public NodoArbolHuffman(int simbolo, int frecuencia)
	{
		Simbolo = simbolo;
		this.frecuencia = frecuencia;
	}

	/**
	 * @return the hijoDerecha
	 */
	public NodoArbolHuffman getHijoDerecha()
	{
		return hijoDerecha;
	}

	/**
	 * @param hijoDerecha the hijoDerecha to set
	 */
	public void setHijoDerecha(NodoArbolHuffman hijoDerecha)
	{
		this.hijoDerecha = hijoDerecha;
	}

	/**
	 * @return the hijoIzquierda
	 */
	public NodoArbolHuffman getHijoIzquierda()
	{
		return hijoIzquierda;
	}

	/**
	 * @param hijoIzquierda the hijoIzquierda to set
	 */
	public void setHijoIzquierda(NodoArbolHuffman hijoIzquierda)
	{
		this.hijoIzquierda = hijoIzquierda;
	}

	/**
	 * @return the simbolo
	 */
	public int getSimbolo()
	{
		return Simbolo;
	}

	/**
	 * @return the frecuencia
	 */
	public int getFrecuencia()
	{
		return frecuencia;
	}

}

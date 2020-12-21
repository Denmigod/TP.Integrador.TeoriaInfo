package compresor;

public class nodoListaCodificacion
{
	private int simbolo;
	private double frecuencia;
	private String codificacion;

	public nodoListaCodificacion(int simbolo, double frecuencia, String codificacion)
	{
		super();
		this.simbolo = simbolo;
		this.frecuencia = frecuencia;
		this.codificacion = codificacion;
	}

	/**
	 * @return the simbolo
	 */
	public int getSimbolo()
	{
		return simbolo;
	}

	/**
	 * @param simbolo the simbolo to set
	 */
	public void setSimbolo(char simbolo)
	{
		this.simbolo = simbolo;
	}

	/**
	 * @return the frecuencia
	 */
	public double getFrecuencia()
	{
		return frecuencia;
	}

	/**
	 * @param frecuencia the frecuencia to set
	 */
	public void setFrecuencia(double frecuencia)
	{
		this.frecuencia = frecuencia;
	}

	/**
	 * @return the codificacion
	 */
	public String getCodificacion()
	{
		return codificacion;
	}

	/**
	 * @param codificacion the codificacion to set
	 */
	public void setCodificacion(String codificacion)
	{
		this.codificacion = codificacion;
	}

	@Override
	public String toString()
	{
		String simboloAux = "";
		if (this.simbolo == 10)
		{
			simboloAux = "ENTER";
		} else if (this.simbolo == 9)
		{
			simboloAux = "TAB";
		} else if (this.simbolo == 13)
		{
			simboloAux = "RET";
		} else if (this.simbolo == 0)
		{
			simboloAux = "EOF";
		} else if (this.simbolo == 32)
		{
			simboloAux = "ESPACIO";
		} else
		{
			simboloAux = (char) this.simbolo + "";
		}

		return this.simbolo + "\t\t" + simboloAux + "\t\t" + String.format("%.5f", this.frecuencia) + "\t\t"
				+ this.codificacion;
	}

}

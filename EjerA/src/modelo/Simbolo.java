package modelo;

public class Simbolo
{

	private String dato;
	private double probabilidad;
	private String codigo = null;

	public Simbolo(String dato, double probabilidad)
	{
		super();
		this.dato = dato;
		this.probabilidad = probabilidad;
	}

	public Simbolo(String dato, double probabilidad, String codigo)
	{
		super();
		this.dato = dato;
		this.probabilidad = probabilidad;
		this.codigo = codigo;
	}

	public String getDato()
	{
		return dato;
	}

	public double getCantInformacion(int base)
	{
		double resultado = 0;
		if (probabilidad > 0)
		{
			resultado = (-1 * Math.log(probabilidad) / Math.log(base));
		}
		return resultado;

	}

	public double getProbabilidad()
	{
		return probabilidad;
	}

	public void setProbabilidad(double probabilidad)
	{
		this.probabilidad = probabilidad;
	}

	/**
	 * @return the codigo
	 */
	public String getCodigo()
	{
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo)
	{
		this.codigo = codigo;
	}

}

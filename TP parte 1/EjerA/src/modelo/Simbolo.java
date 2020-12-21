package modelo;

/**
 * @author Grupo 6
 *Representa un simbolo para una fuente
 */
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

	/**
	 * Se encarga de calcular y devolver la cantidad de informacion
	 * @param base : la base de la fuente
	 * @return devuelve la cantidad de informacion
	 */
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

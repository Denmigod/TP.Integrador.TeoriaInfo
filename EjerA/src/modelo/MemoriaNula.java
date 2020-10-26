package modelo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.Map.Entry;

import excepciones.CodigoInexistenteException;
import excepciones.ProbabilidadTotalException;
import excepciones.SimboloNoEncontradoException;

/**
 * Clase que contiene una fuente de memoria nula, genera secuencias de simbolos
 * y devuelve los calculos correspondientes a la misma
 * 
 * @author Grupo 6
 *
 */
public class MemoriaNula extends Fuente
{
	private HashMap<String, Simbolo> listaSimbolos = new HashMap<String, Simbolo>();
	private int base;
	private double probabilidadAcumulada = 0;

	public MemoriaNula(int base)
	{
		this.base = base;
	}

	/**
	 * Esta funcion agrega un simbolo a la fuente de memoria nula
	 * 
	 * @param simbolo : simbolo o codigo(lo que se prefiera usar)
	 * @throws ProbabilidadTotalException : se lanza cuando la probabilidad total va
	 *                                    a superar a 1
	 */
	public void addSimbolo(Simbolo simbolo) throws ProbabilidadTotalException
	{
		if (probabilidadAcumulada + simbolo.getProbabilidad() > 1)
		{
			throw new ProbabilidadTotalException("La probabilidad total supero el maximo de 1");
		} else
		{
			this.listaSimbolos.put(simbolo.getDato(), simbolo);
			this.probabilidadAcumulada += simbolo.getProbabilidad();
		}
	}

	/**
	 * Metodo que devuelve la entropia de la fuente
	 * 
	 * @throws ProbabilidadTotalException : se lanza cuando la probabilidad total no
	 *                                    es 1
	 */
	@Override
	public double getEntropia() throws ProbabilidadTotalException
	{
		if (probabilidadAcumulada != 1)
		{
			throw new ProbabilidadTotalException("La probabilidad total de la fuente no es 1");
		}
		Entry<String, Simbolo> entry;
		double resultado = 0;
		Set<Entry<String, Simbolo>> entrySet = this.listaSimbolos.entrySet();
		Iterator<Entry<String, Simbolo>> it = entrySet.iterator();
		while (it.hasNext())
		{
			entry = it.next();
			try
			{
				resultado += entry.getValue().getProbabilidad() * this.getCantInformacion(entry.getKey());
			} catch (SimboloNoEncontradoException e) // nunca entra al catch dado que el simbolo que busca siempre
														// existe
			{

				e.printStackTrace();
			}
		}
		return resultado;
	}

	/**
	 * Metodo que devuelve la cantidad de informacion de un simbolo para la fuente
	 * pasado por parametro
	 * 
	 * @param simbolo : simbolo a calcular la cantidad de informacion
	 * @return : cantidad de infromacion del simbolo solicitado
	 * @throws SimboloNoEncontradoException : cuando no se encuentra el simbolo
	 *                                      solicitado
	 */
	public double getCantInformacion(String simbolo) throws SimboloNoEncontradoException
	{

		double resultado = 0;
		if (this.listaSimbolos.containsKey(simbolo))
		{
			resultado = this.listaSimbolos.get(simbolo).getCantInformacion(base);
		} else
		{
			throw new SimboloNoEncontradoException("El simbolo " + simbolo + " no se encontro");
		}
		return (resultado);
	}

	/**
	 * metodo que genera una secuencia de simbolos a partir de la fuente y su
	 * distribuciuon de probabilidades
	 * 
	 * @param cantidad : cantidad de simbolos a generar en la secuencia
	 * @return : String con la secuencia de simbolos generada
	 * @throws ProbabilidadTotalException : cuando la probabilidad acumulada no es 1
	 *                                    para la fuente
	 */
	public String generarSecuenciaSimbolos(int cantidad) throws ProbabilidadTotalException
	{
		StringBuilder sb = new StringBuilder();
		Random randnum = new Random();
		randnum.setSeed(System.nanoTime());

		if (this.probabilidadAcumulada != 1)
		{
			throw new ProbabilidadTotalException("La probabilidad total de la fuente no es 1");
		} else
		{
			for (int i = 0; i < cantidad; i++)
			{
				double numeroRandom = randnum.nextDouble();
				double sumaProbabilidades;

				Entry<String, Simbolo> entry;
				Set<Entry<String, Simbolo>> entrySet = this.listaSimbolos.entrySet();
				Iterator<Entry<String, Simbolo>> it = entrySet.iterator();
				entry = it.next();
				sumaProbabilidades = entry.getValue().getProbabilidad();

				while (sumaProbabilidades <= numeroRandom)
				{
					entry = it.next();
					sumaProbabilidades += entry.getValue().getProbabilidad();
				}
				sb.append(entry.getValue().getDato());
			}
		}

		return sb.toString();
	}

	/**
	 * 
	 *  metodo que genera una secuencia de codigos a partir de la fuente y su
	 * distribuciuon de probabilidades
	 * 
	 * @param cantidad : cantidad de codigos a generar
	 * @return : String con la secuencia de simbolos generada
	 * @throws ProbabilidadTotalException : cuando la probabilidad acumulada no es 1  para la fuente
	 * @throws CodigoInexistenteException : cuando la fuente no tiene codificacion asociada a los simbolos
	 */
	public String generarSecuenciaCodigos(int cantidad) throws ProbabilidadTotalException, CodigoInexistenteException
	{
		StringBuilder sb = new StringBuilder();
		Random randnum = new Random();
		randnum.setSeed(System.nanoTime());

		if (this.probabilidadAcumulada != 1)
		{
			throw new ProbabilidadTotalException("La probabilidad total de la fuente no es 1");
		} else if (!this.isCodificada()) // pregunto si no existe codigo en los simbolos
		{
			throw new CodigoInexistenteException("la fuente no tiene codificacion asociada a los simbolos");
		}
		{
			for (int i = 0; i < cantidad; i++)
			{
				double numeroRandom = randnum.nextDouble();
				double sumaProbabilidades;

				Entry<String, Simbolo> entry;
				Set<Entry<String, Simbolo>> entrySet = this.listaSimbolos.entrySet();
				Iterator<Entry<String, Simbolo>> it = entrySet.iterator();
				entry = it.next();
				sumaProbabilidades = entry.getValue().getProbabilidad();

				while (sumaProbabilidades <= numeroRandom)
				{
					entry = it.next();
					sumaProbabilidades += entry.getValue().getProbabilidad();
				}
				sb.append(entry.getValue().getCodigo());
			}
		}

		return sb.toString();
	}

	/**
	 * Metodo que enlista la cantidad de informacion de todos los simbolos de la
	 * fuente
	 * 
	 * @return : la cantidad de informacion
	 */
	public String enlistaFuente()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("Simbolo" + "\t" + "cant.info" + "\t" + "codigo" + "\n");
		Entry<String, Simbolo> entry;
		Set<Entry<String, Simbolo>> entrySet = this.listaSimbolos.entrySet();
		Iterator<Entry<String, Simbolo>> it = entrySet.iterator();
		while (it.hasNext())
		{
			entry = it.next();
			sb.append(
					entry.getValue().getDato() + "\t" + String.format("%.5f", entry.getValue().getCantInformacion(base))
							+ "\t" + "\t" + entry.getValue().getCodigo() + "\n");
		}
		return sb.toString();
	}

	/**
	 * Metodo que genera un codigo instantaneo binario para esta fuente y lo guarda en listaSimbolos
	 * @throws ProbabilidadTotalException : cuando la probabilidad acumulada no es 1  para la fuente
	 */
	public void generaCodigoInstantaneo() throws ProbabilidadTotalException
	{
		if (this.probabilidadAcumulada != 1)
		{
			throw new ProbabilidadTotalException("La probabilidad total de la fuente no es 1");
		}
		Entry<String, Simbolo> entry;
		Set<Entry<String, Simbolo>> entrySet = this.listaSimbolos.entrySet();
		Iterator<Entry<String, Simbolo>> it = entrySet.iterator();
		Simbolo[] simbolos = new Simbolo[this.listaSimbolos.size()];
		int i = 0;
		while (it.hasNext())
		{
			entry = it.next();
			simbolos[i] = entry.getValue();
			i++;
		}
		CodigoInstantaneo.generaCodigo(simbolos);
		for (int j = 0; j < simbolos.length; j++)
		{
			this.listaSimbolos.get(simbolos[j].getDato()).setCodigo(simbolos[j].getCodigo());
			;
		}

	}

	public boolean isCodificada()
	{
		return this.listaSimbolos.entrySet().iterator().hasNext()
				&& this.listaSimbolos.entrySet().iterator().next().getValue().getCodigo() != null;// verifico si los
																									// simbolos tienen
																									// codificacion
	}

	public boolean verificaInecuacionKraft() throws ProbabilidadTotalException, CodigoInexistenteException
	{
		return this.sumatoriaInecuacionKraft() <= 1;
	}

	/**
	 * Se encarga de calcular la sumatoria de la inecuacion de Kraft
	 * @return : double sumatoria (inecuacion de Kraft)
	 * @throws ProbabilidadTotalException : cuando la probabilidad acumulada no es 1  para la fuente
	 * @throws CodigoInexistenteException : cuando la fuente no tiene codificacion asociada a los simbolos
	 */
	public double sumatoriaInecuacionKraft() throws ProbabilidadTotalException, CodigoInexistenteException
	{
		if (probabilidadAcumulada != 1)
		{
			throw new ProbabilidadTotalException("La probabilidad total de la fuente no es 1");
		} else if (!this.isCodificada())
		{
			throw new CodigoInexistenteException("la fuente no tiene codificacion asociada a los simbolos");
		}
		Entry<String, Simbolo> entry;
		Set<Entry<String, Simbolo>> entrySet = this.listaSimbolos.entrySet();
		Iterator<Entry<String, Simbolo>> it = entrySet.iterator();
		double sumatoria = 0;
		while (it.hasNext())
		{
			entry = it.next();
			sumatoria += Math.pow(base, -1 * entry.getValue().getCodigo().length());
		}
		return sumatoria;
	}

	/**
	 * Se encargar de calcular la longitud media de los codigos de la fuente
	 * @return devuelve la longitud media de los codigos de la fuente
	 * @throws ProbabilidadTotalException : cuando la probabilidad acumulada no es 1  para la fuente
	 * @throws CodigoInexistenteException : cuando la fuente no tiene codificacion asociada a los simbolos
	 */
	public double longitudMediaCodigo() throws ProbabilidadTotalException, CodigoInexistenteException
	{
		if (probabilidadAcumulada != 1)
		{
			throw new ProbabilidadTotalException("La probabilidad total de la fuente no es 1");
		} else if (!this.isCodificada())
		{
			throw new CodigoInexistenteException("la fuente no tiene codificacion asociada a los simbolos");
		}
		Entry<String, Simbolo> entry;
		Set<Entry<String, Simbolo>> entrySet = this.listaSimbolos.entrySet();
		Iterator<Entry<String, Simbolo>> it = entrySet.iterator();
		double sumatoria = 0;
		while (it.hasNext())
		{
			entry = it.next();
			sumatoria += entry.getValue().getProbabilidad() * entry.getValue().getCodigo().length();
		}

		return sumatoria;
	}

	/**
	 * Se encarga de comprobar todos los simbolos para ver si cumple que sea compacto o no
	 * @return devuelve un verdadero o falso dependiendo si cumple
	 * @throws ProbabilidadTotalException : cuando la probabilidad acumulada no es 1  para la fuente
	 * @throws CodigoInexistenteException : cuando la fuente no tiene codificacion asociada a los simbolos
	 */
	public boolean isCompacto() throws ProbabilidadTotalException, CodigoInexistenteException
	{

		if (probabilidadAcumulada != 1)
		{
			throw new ProbabilidadTotalException("La probabilidad total de la fuente no es 1");
		} else if (!this.isCodificada())
		{
			throw new CodigoInexistenteException("la fuente no tiene codificacion asociada a los simbolos");
		}
		Entry<String, Simbolo> entry;
		Set<Entry<String, Simbolo>> entrySet = this.listaSimbolos.entrySet();
		Iterator<Entry<String, Simbolo>> it = entrySet.iterator();
		boolean compacto = true;
		while (it.hasNext() && compacto)
		{
			entry = it.next();
			compacto = Math
					.ceil(entry.getValue().getCantInformacion(base)) == (double) entry.getValue().getCodigo().length();
		}
		return compacto;
	}

}

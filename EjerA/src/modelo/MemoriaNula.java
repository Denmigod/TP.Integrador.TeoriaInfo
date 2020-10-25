package modelo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.Map.Entry;

import excepciones.ProbabilidadTotalException;
import excepciones.SimboloNoEncontradoException;

public class MemoriaNula extends Fuente
{
	private HashMap<String, Simbolo> listaSimbolos = new HashMap<String, Simbolo>();
	private int cantidadSimbolos = 0;
	private int base;
	private double probabilidadAcumulada = 0;

	public MemoriaNula(int base)
	{
		this.base = base;
	}

	public void addSimbolo(Simbolo simbolo) throws ProbabilidadTotalException
	{
		if (probabilidadAcumulada + simbolo.getProbabilidad() > 1)
		{
			throw new ProbabilidadTotalException("La probabilidad total supero el maximo de 1");
		} else
		{
			this.listaSimbolos.put(simbolo.getDato(), simbolo);
			this.probabilidadAcumulada += simbolo.getProbabilidad();
			this.cantidadSimbolos++;
		}
	}

	/**
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

	public String generarSimbolos(int n) throws ProbabilidadTotalException
	{
		StringBuilder sb = new StringBuilder();
		Random randnum = new Random();
		randnum.setSeed(System.nanoTime());

		if (this.probabilidadAcumulada != 1)
		{
			throw new ProbabilidadTotalException("La probabilidad total de la fuente no es 1");
		} else
		{
			for (int i = 0; i < n; i++)
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

}

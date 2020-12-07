package compresor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;

import utilidades.LeeArchivo;

public class ShannonFano implements ICompresor
{
	private HashMap<Integer, String> diccionario = new HashMap<Integer, String>();
	private int[] probabilidadAcumulada = new int[1];
	private double entropia;
	private double longitudMedia;
	private double tasaCompresion;

	public ShannonFano()
	{
		this.entropia = 0;
		this.longitudMedia = 0;
		this.tasaCompresion = 0;
	}

	/**
	 * @return the diccionario
	 */
	public HashMap<Integer, String> getDiccionario()
	{
		return diccionario;
	}

	/**
	 * @return the probabilidadAcumulada
	 */
	public int getProbabilidadAcumulada()
	{
		return probabilidadAcumulada[0];
	}

	/**
	 * @return the entropia
	 */
	public double getEntropia()
	{
		return entropia;
	}

	/**
	 * @return the longitudMedia
	 */
	public double getLongitudMedia()
	{
		return longitudMedia;
	}

	/**
	 * @return the tasaCompresion
	 */
	public double getTasaCompresion()
	{
		return tasaCompresion;
	}

	public double getRendimiento()
	{
		return this.entropia / this.longitudMedia;
	}

	public double getRedundancia()
	{
		return 1 - this.getRendimiento();
	}

	private void cearDiccionario(String direccion) throws FileNotFoundException, IOException
	{
		NodoShannonFano[] vectorNodos = this
				.creaVectorNodos(LeeArchivo.obtenerFrecuencia(direccion, this.probabilidadAcumulada));
		this.BubbleSort(vectorNodos, vectorNodos.length);
		this.generaDiccionario(vectorNodos);
	}

	private NodoShannonFano[] creaVectorNodos(HashMap<Integer, Integer> listaSimbolos)
	{
		NodoShannonFano[] vectorNodos = new NodoShannonFano[listaSimbolos.size()];
		int i = 0;
		Entry<Integer, Integer> entry;
		Set<Entry<Integer, Integer>> entrySet = listaSimbolos.entrySet();
		Iterator<Entry<Integer, Integer>> it = entrySet.iterator();
		while (it.hasNext())
		{
			entry = it.next();
			vectorNodos[i] = new NodoShannonFano(entry.getKey(), entry.getValue());
			i++;
		}

		return vectorNodos;
	}

	private void BubbleSort(NodoShannonFano[] vectorNodos, int cantElementos) // ordena de mayor a menor
	{
		int n = cantElementos;
		NodoShannonFano tempSimb;
		for (int i = 0; i < n; i++)
		{
			for (int j = 1; j < (n - i); j++)
			{
				if (vectorNodos[j - 1].getFrecuencia() < vectorNodos[j].getFrecuencia())
				{

					// swap elements

					tempSimb = vectorNodos[j - 1];

					vectorNodos[j - 1] = vectorNodos[j];
					vectorNodos[j] = tempSimb;
				}
			}
		}
	}

	private void generaDiccionario(NodoShannonFano[] vectorNodos)
	{
		this.generaDiccionarioRecursividad(vectorNodos, this.probabilidadAcumulada, 0, vectorNodos.length - 1, "");
	}

	private void generaDiccionarioRecursividad(NodoShannonFano[] vectorNodos, int[] probabilidadAcumulada,
			int limiteIzq, int limiteDer, String binario)
	{
		if (limiteIzq == limiteDer) // caso base
		{
			this.diccionario.put(vectorNodos[limiteIzq].getSimbolo(), binario);
			double probabilidad = (double) vectorNodos[limiteIzq].getFrecuencia()
					/ (double) this.probabilidadAcumulada[0];
			this.entropia += probabilidad * (-1) * Math.log(probabilidad) / Math.log(2);
			this.longitudMedia += probabilidad * binario.length();
		} else
		{
			int[] frecuenciaIzq = new int[1];
			int[] frecuenciaDer = new int[1];
			int mitad = this.obtenerMitadVectorNodos(vectorNodos, limiteIzq, limiteDer, probabilidadAcumulada[0],
					frecuenciaIzq, frecuenciaDer);

			this.generaDiccionarioRecursividad(vectorNodos, frecuenciaIzq, limiteIzq, mitad, binario + "1");
			this.generaDiccionarioRecursividad(vectorNodos, frecuenciaDer, mitad + 1, limiteDer, binario + "0");
		}

		return;
	}

	/**
	 * funcion que devuelve la mitad de la zona del vector dividiendo por la
	 * frecuencia de los simbolos
	 * 
	 * @param vectorNodos:         vector de los nodos de Shannon Fano
	 * @param limiteIzq:           limite del vector por izq inclusive primer
	 *                             posicion
	 * @param limiteDer:           limite del vector por der inclusive ultima
	 *                             posicion
	 * @param frecuenciaAcumulada: frecuencia acumulada de la zona del vector a
	 *                             evaluar
	 * @param frecuenciaIzq:       frecuencia de salida de la zona izq del vector
	 * @param frecuenciaDer:       frecuencia de salida de la zona der del vector
	 * @return retorna la posicion de la mitad logica del vector teniendo en cuenta
	 *         las frecuencias
	 */
	private int obtenerMitadVectorNodos(NodoShannonFano[] vectorNodos, int limiteIzq, int limiteDer,
			int frecuenciaAcumulada, int[] frecuenciaIzq, int[] frecuenciaDer)
	{
		frecuenciaIzq[0] = vectorNodos[limiteIzq].getFrecuencia();
		frecuenciaDer[0] = 0;
		int i = limiteIzq;
		int mitadFrecuencia = frecuenciaAcumulada / 2;
		while (i < limiteDer && frecuenciaIzq[0] < mitadFrecuencia)
		{
			i++;
			frecuenciaIzq[0] += vectorNodos[i].getFrecuencia();

		}
		frecuenciaDer[0] = frecuenciaAcumulada - frecuenciaIzq[0];

		return i;
	}

	public void comprimir(String direccionOrigen, String direccionDestino, String nombre)
			throws FileNotFoundException, IOException
	{

		BufferedReader reader = null;
		BitSet bs = new BitSet();

		try
		{
			this.cearDiccionario(direccionOrigen);
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(direccionOrigen), "UTF-8"));
			int caracter = reader.read();
			int bitActual = 0;
			String codigo;
			while (caracter != -1)
			{
				codigo = this.diccionario.get(caracter);
				for (int i = 0; i < codigo.length(); i++)
				{
					bs.set(bitActual, codigo.charAt(i) == '1');
					bitActual++;
				}
				caracter = reader.read();
			}
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(direccionDestino + nombre + ".Shn"));
			oos.writeObject(this.diccionario);
			oos.writeObject(bs);
			oos.close();
			this.tasaCompresion = (double) Files.size(Paths.get(direccionOrigen))
					/ (double) Files.size(Paths.get(nombre + ".Shn"));

		} finally
		{
			if (reader != null)
			{

				reader.close();

			}



		}
	}

	@SuppressWarnings("unchecked")
	public void descomprimir(String direccionOrigen, String direccionDestino, String nombre)
			throws IOException, FileNotFoundException
	{
		BufferedWriter writer = null;
		ObjectInputStream ois = null;
		BitSet bs;
		try
		{
			writer = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(direccionDestino + nombre + ".txt"), "UTF-8"));
			ois = new ObjectInputStream(new FileInputStream(direccionOrigen));
			Object aux = ois.readObject();
			if (aux instanceof HashMap<?, ?>)
			{
				HashMap<Integer, String> diccionario = (HashMap<Integer, String>) aux;

				aux = ois.readObject();
				if (aux instanceof BitSet)
				{
					bs = (BitSet) aux;
					String codigo = "";
					for (int i = 0; i < bs.length(); i++)
					{
						if (bs.get(i))
							codigo = codigo + "1";
						else
							codigo = codigo + "0";
						int simbolo = this.buscaCodigo(diccionario, codigo);
						if (simbolo != -1)
						{ // lo encontre y tengo que escribirlo
							writer.write((char) simbolo);
							codigo = "";
						}
					}

				}

			}

		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		} finally
		{
			if (ois != null)
			{

				ois.close();

			}
			if (writer != null)
			{

				writer.close();

			}
		}
	}

	private int buscaCodigo(HashMap<Integer, String> diccionario, String codigo)
	{

		int simbolo = -1; // retorna -1 si no lo encuentra
		Entry<Integer, String> entry;
		Set<Entry<Integer, String>> entrySet = diccionario.entrySet();
		Iterator<Entry<Integer, String>> it = entrySet.iterator();
		boolean encontre = false;
		while (it.hasNext() && !encontre)
		{
			entry = it.next();
			if (entry.getValue().equals(codigo))
			{
				encontre = true;
				simbolo = entry.getKey();
			}
		}
		return simbolo;
	}

}

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
import java.util.BitSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;

import archivo.LeeArchivo;

public class ShannonFano
{
	private static HashMap<Integer, String> cearDiccionario(String direccion)
	{
		HashMap<Integer, String> diccionario = new HashMap<Integer, String>();
		int[] frecuenciaAcumulada = new int[1];

		NodoShannonFano[] vectorNodos = ShannonFano.creaVectorNodos(LeeArchivo.obtenerFrecuencia(direccion),
				frecuenciaAcumulada);
		ShannonFano.BubbleSort(vectorNodos, vectorNodos.length);
		ShannonFano.generaDiccionario(vectorNodos, frecuenciaAcumulada, diccionario);
		return diccionario;
	}

	private static NodoShannonFano[] creaVectorNodos(HashMap<Integer, Integer> listaSimbolos, int[] frecuenciaAcumulada)
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
			frecuenciaAcumulada[0] += entry.getValue();
			i++;
		}

		return vectorNodos;
	}

	private static void BubbleSort(NodoShannonFano[] vectorNodos, int cantElementos) // ordena de mayor a menor
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

	private static void generaDiccionario(NodoShannonFano[] vectorNodos, int[] frecuenciaAcumulada,
			HashMap<Integer, String> diccionario)
	{
		ShannonFano.generaDiccionarioRecursividad(vectorNodos, frecuenciaAcumulada, diccionario, 0,
				vectorNodos.length - 1, "");
	}

	private static void generaDiccionarioRecursividad(NodoShannonFano[] vectorNodos, int[] frecuenciaAcumulada,
			HashMap<Integer, String> diccionario, int limiteIzq, int limiteDer, String binario)
	{
		if (limiteIzq == limiteDer) // caso base
		{
			diccionario.put(vectorNodos[limiteIzq].getSimbolo(), binario);
			System.out.println(binario);
		} else
		{
			int[] frecuenciaIzq = new int[1];
			int[] frecuenciaDer = new int[1];
			int mitad = ShannonFano.obtenerMitadVectorNodos(vectorNodos, limiteIzq, limiteDer, frecuenciaAcumulada[0], frecuenciaIzq,
					frecuenciaDer);
			
			ShannonFano.generaDiccionarioRecursividad(vectorNodos, frecuenciaIzq, diccionario, limiteIzq, mitad, binario+"1");
			ShannonFano.generaDiccionarioRecursividad(vectorNodos, frecuenciaDer, diccionario, mitad+1, limiteDer, binario+"0");
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
	private static int obtenerMitadVectorNodos(NodoShannonFano[] vectorNodos, int limiteIzq, int limiteDer,
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

	public static void comprimir(String direccion, String nombre)
	{

		BufferedReader reader = null;
		BitSet bs = new BitSet();
		HashMap<Integer, String> diccionario = ShannonFano.cearDiccionario(direccion);
		try
		{
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(direccion), "UTF-8"));
			int caracter = reader.read();
			int bitActual = 0;
			String codigo;
			while (caracter != -1)
			{
				codigo = diccionario.get(caracter);
				for (int i = 0; i < codigo.length(); i++)
				{
					bs.set(bitActual, codigo.charAt(i) == '1');
					bitActual++;
				}
				caracter = reader.read();
			}
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombre + ".Shn"));
			oos.writeObject(diccionario);
			oos.writeObject(bs);
			oos.close();

		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			if (reader != null)
			{
				try
				{
					reader.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static void descomprimr(String direccion, String nombre)
	{
		BufferedWriter writer = null;
		ObjectInputStream ois = null;
		BitSet bs;
		try
		{
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(nombre + ".txt"), "UTF-8"));
			ois = new ObjectInputStream(new FileInputStream(direccion));
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
						int simbolo = ShannonFano.buscaCodigo(diccionario, codigo);
						if (simbolo != -1)
						{ // lo encontre y tengo que escribirlo
							writer.write((char) simbolo);
							codigo = "";
						}
					}

				}

			}

		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		} finally
		{
			if (ois != null)
			{
				try
				{
					ois.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			if (writer != null)
			{
				try
				{
					writer.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	private static int buscaCodigo(HashMap<Integer, String> diccionario, String codigo)
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

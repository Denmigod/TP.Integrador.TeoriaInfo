package compresor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
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

public class Huffman
{

	private static HashMap<Integer, String> cearDiccionario(String direccion)
	{
		HashMap<Integer, String> diccionario = new HashMap<Integer, String>();

		NodoArbolHuffman[] vectorNodosArbol = Huffman.creaVectorNodosArbol(LeeArchivo.obtenerFrecuencia(direccion));
		Huffman.BubbleSort(vectorNodosArbol, vectorNodosArbol.length);
		NodoArbolHuffman arbolHuffman = Huffman.crearArbolsHuffman(vectorNodosArbol);
		Huffman.generaDiccionario(arbolHuffman, diccionario, "");
		return diccionario;
	}

	private static NodoArbolHuffman[] creaVectorNodosArbol(HashMap<Integer, Integer> listaSimbolos)
	{
		NodoArbolHuffman[] vectorNodosArbol = new NodoArbolHuffman[listaSimbolos.size()];
		int i = 0;
		Entry<Integer, Integer> entry;
		Set<Entry<Integer, Integer>> entrySet = listaSimbolos.entrySet();
		Iterator<Entry<Integer, Integer>> it = entrySet.iterator();
		while (it.hasNext())
		{
			entry = it.next();
			vectorNodosArbol[i] = new NodoArbolHuffman(entry.getKey(), entry.getValue());
			i++;
		}

		return vectorNodosArbol;
	}

	private static void BubbleSort(NodoArbolHuffman[] vectorNodosArbol, int cantElementos)
	{
		int n = cantElementos;
		NodoArbolHuffman tempSimb;
		for (int i = 0; i < n; i++)
		{
			for (int j = 1; j < (n - i); j++)
			{
				if (vectorNodosArbol[j - 1].getFrecuencia() < vectorNodosArbol[j].getFrecuencia())
				{

					// swap elements

					tempSimb = vectorNodosArbol[j - 1];

					vectorNodosArbol[j - 1] = vectorNodosArbol[j];
					vectorNodosArbol[j] = tempSimb;
				}
			}
		}
	}

	private static NodoArbolHuffman crearArbolsHuffman(NodoArbolHuffman[] vectorNodosArbol)
	{
		NodoArbolHuffman raiz = null;
		int n = vectorNodosArbol.length;

		while (n > 1)
		{
			NodoArbolHuffman nuevoNodo = new NodoArbolHuffman(-1,
					vectorNodosArbol[n - 1].getFrecuencia() + vectorNodosArbol[n - 2].getFrecuencia());
			nuevoNodo.setHijoIzquierda(vectorNodosArbol[n - 1]);
			nuevoNodo.setHijoDerecha(vectorNodosArbol[n - 2]);
			vectorNodosArbol[n - 2] = nuevoNodo;
			n--;
			Huffman.BubbleSort(vectorNodosArbol, n);
		}

		raiz = vectorNodosArbol[0];

		return raiz;
	}

	private static void generaDiccionario(NodoArbolHuffman arbolHuffman, HashMap<Integer, String> diccionario,
			String binario)
	{
		if (arbolHuffman.getSimbolo() != -1) // nodo hoja
		{
			diccionario.put(arbolHuffman.getSimbolo(), binario);
		} else // voy izq y derecha
		{
			Huffman.generaDiccionario(arbolHuffman.getHijoIzquierda(), diccionario, binario + "0");
			Huffman.generaDiccionario(arbolHuffman.getHijoDerecha(), diccionario, binario + "1");
		}
		return;
	}

	public static void comprimir(String direccion, String nombre)
	{

		BufferedReader reader = null;
		BitSet bs = new BitSet();
		HashMap<Integer, String> diccionario = Huffman.cearDiccionario(direccion);
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
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombre + ".huf"));
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
						int simbolo = Huffman.buscaCodigo(diccionario, codigo);
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

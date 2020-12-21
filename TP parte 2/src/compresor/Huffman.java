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
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;

import utilidades.LeeArchivo;

public class Huffman implements ICompresor
{
	private HashMap<Integer, String> diccionario = new HashMap<Integer, String>();
	private ArrayList<nodoListaCodificacion> listaCodificacion = new ArrayList<nodoListaCodificacion>();
	private int[] probabilidadAcumulada = new int[1];
	private double entropia;
	private double longitudMedia;
	private double tasaCompresion;

	public Huffman()
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
	 * @return the listaCodificacion
	 */
	public ArrayList<nodoListaCodificacion> getListaCodificacion()
	{
		return listaCodificacion;
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
		NodoArbolHuffman[] vectorNodosArbol = this
				.creaVectorNodosArbol(LeeArchivo.obtenerFrecuencia(direccion, this.probabilidadAcumulada));
		this.BubbleSort(vectorNodosArbol, vectorNodosArbol.length);
		NodoArbolHuffman arbolHuffman = this.crearArbolsHuffman(vectorNodosArbol);
		this.generaDiccionario(arbolHuffman, "");

	}

	private NodoArbolHuffman[] creaVectorNodosArbol(HashMap<Integer, Integer> listaSimbolos)
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

	private void BubbleSort(NodoArbolHuffman[] vectorNodosArbol, int cantElementos)
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

	private NodoArbolHuffman crearArbolsHuffman(NodoArbolHuffman[] vectorNodosArbol)
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
			this.BubbleSort(vectorNodosArbol, n);
		}

		raiz = vectorNodosArbol[0];

		return raiz;
	}

	private void generaDiccionario(NodoArbolHuffman arbolHuffman, String binario)
	{
		if (arbolHuffman.getSimbolo() != -1) // nodo hoja
		{
			this.diccionario.put(arbolHuffman.getSimbolo(), binario);
			double probabilidad = (double) arbolHuffman.getFrecuencia() / (double) this.probabilidadAcumulada[0];
			this.entropia += probabilidad * (-1) * Math.log(probabilidad) / Math.log(2);
			this.longitudMedia += probabilidad * binario.length();
			this.listaCodificacion
					.add(new nodoListaCodificacion(arbolHuffman.getSimbolo(), probabilidad, binario));
		} else // voy izq y derecha
		{
			this.generaDiccionario(arbolHuffman.getHijoIzquierda(), binario + "0");
			this.generaDiccionario(arbolHuffman.getHijoDerecha(), binario + "1");
		}
		return;
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
				codigo = diccionario.get(caracter);
				for (int i = 0; i < codigo.length(); i++)
				{
					bs.set(bitActual, codigo.charAt(i) == '1');
					bitActual++;
				}
				caracter = reader.read();
			}
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(direccionDestino + nombre + ".huf"));
			oos.writeObject(diccionario);
			oos.writeObject(bs);
			oos.close();
			this.tasaCompresion = (double) Files.size(Paths.get(direccionOrigen))
					/ (double) Files.size(Paths.get(nombre + ".huf"));
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

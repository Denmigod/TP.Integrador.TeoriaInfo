package compresor;

import java.util.BitSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;

import archivo.LeeArchivo;

public class Huffman
{

	public HashMap<Integer, Integer> cearDiccionario(String direccion)
	{
		HashMap<Integer, Integer> diccionario = new HashMap<Integer, Integer>();

		NodoArbolHuffman[] vectorNodosArbol = this.creaVectorNodosArbol(LeeArchivo.obtenerFrecuencia(direccion));
		this.BubbleSort(vectorNodosArbol, vectorNodosArbol.length);
		NodoArbolHuffman arbolHuffman = this.crearArbolsHuffman(vectorNodosArbol);
		this.generaDiccionario(arbolHuffman, diccionario, "");
		return diccionario;
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

	private void generaDiccionario(NodoArbolHuffman arbolHuffman, HashMap<Integer, Integer> diccionario, String binario)
	{
		if (arbolHuffman.getSimbolo() != -1) // nodo hoja
		{
			diccionario.put(arbolHuffman.getSimbolo(), Integer.parseInt(binario, 2));
		} else // voy izq y derecha
		{
			this.generaDiccionario(arbolHuffman.getHijoIzquierda(), diccionario, binario + "0");
			this.generaDiccionario(arbolHuffman.getHijoDerecha(), diccionario, binario + "1");
		}

		return;
	}

}

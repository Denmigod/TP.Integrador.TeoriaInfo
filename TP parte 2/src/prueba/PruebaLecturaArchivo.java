package prueba;

import archivo.LeeArchivo;
import compresor.Huffman;
import compresor.RLC;
import compresor.ShannonFano;

public class PruebaLecturaArchivo
{

	public static void main(String[] args)
	{
		// String texto = LeeArchivo.leerArchivo("asd.txt");
		// System.out.println(LeeArchivo.obtenerFrecuencia("asd.txt").get((int)'\n'));
		//RLC.comprimir("asd.txt", "asdcomprimido");
		//RLC.descomprimir("asdcomprimido.rlc", "asd2");
		//Huffman.comprimir("asd.txt", "asdcomprimido");
		//Huffman.descomprimr("asdcomprimido.huf", "asd2");
		ShannonFano.comprimir("asd.txt", "asdcomprimido");
		ShannonFano.descomprimr("asdcomprimido.shn", "asd2");
	}

}

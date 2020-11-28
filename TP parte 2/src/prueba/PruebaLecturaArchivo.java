package prueba;

import compresor.Huffman;
import compresor.RLC;
import compresor.ShannonFano;

public class PruebaLecturaArchivo
{

	public static void main(String[] args)
	{
		Huffman compresorHuff = new Huffman();
		ShannonFano compresorShn = new ShannonFano();
		//RLC.comprimir("asd.txt", "asdcomprimido");
		//RLC.descomprimir("asdcomprimido.rlc", "asd2");
		
		compresorHuff.comprimir("asd.txt", "asdcomprimido");
		compresorHuff.descomprimr("asdcomprimido.huf", "asd2");
		System.out.println(compresorHuff.getRendimiento()+" "+compresorHuff.getRedundancia()+" "+compresorHuff.getTasaCompresion());
		
		compresorShn.comprimir("asd.txt", "asdcomprimido");
		compresorShn.descomprimr("asdcomprimido.shn", "asd2");
		System.out.println(compresorShn.getRendimiento()+" "+compresorShn.getRedundancia()+" "+compresorShn.getTasaCompresion());
	}

}

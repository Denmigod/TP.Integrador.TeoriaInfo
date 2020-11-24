package prueba;

import archivo.LeeArchivo;
import compresor.RLC;

public class PruebaLecturaArchivo
{

	public static void main(String[] args)
	{
		//String texto = LeeArchivo.leerArchivo("asd.txt");
		//System.out.println(LeeArchivo.obtenerFrecuencia("asd.txt").get((int)'\n'));
		RLC.comprimir("asd.txt", "asdcomprimido");
		RLC.descomprimir("asdcomprimido.rlc", "asd2");
	}

}

package Pruebas;

import archivos.LeeArchivo;

public class PruebaLecturaArchivo
{

	public static void main(String[] args)
	{
		String texto = LeeArchivo.leerArchivo("asd.txt");
		System.out.println(texto);
	}

}

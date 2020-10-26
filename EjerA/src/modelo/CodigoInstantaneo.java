package modelo;

/**
 * @author Grupo 6
 * Se encarga de generar un codigo instantaneo
 *
 */
public abstract class CodigoInstantaneo
{

	/**
	 * genera un codigo instantaneo dado un vector de simbolos de una fuente de memoria nula binaria
	 * @param simbolos : vector de simbolos
	 */
	public static void generaCodigo(Simbolo[] simbolos)
	{
		int cantidadSimbolos = simbolos.length;
		CodigoInstantaneo.completaVectores(simbolos, cantidadSimbolos);
	}

	
	private static void completaVectores(Simbolo[] simbolos, int cantidadSimbolos)
	{
		CodigoInstantaneo.BubbleSort(simbolos);
		simbolos[0].setCodigo(Integer.toString(0));
		for (int i = 1; i < cantidadSimbolos; i++)
		{
			simbolos[i].setCodigo(Integer.toString(1) + simbolos[i - 1].getCodigo());
		}
	}

	private static void BubbleSort(Simbolo[] simbolos)
	{
		int n = simbolos.length;
		Simbolo tempSimb;
		for (int i = 0; i < n; i++)
		{
			for (int j = 1; j < (n - i); j++)
			{
				if (simbolos[j - 1].getProbabilidad() < simbolos[j].getProbabilidad())
				{
					// swap elements

					tempSimb = simbolos[j - 1];

					simbolos[j - 1] = simbolos[j];
					simbolos[j] = tempSimb;
				}
			}
		}
	}
}

package modelo;

public class CodigoInstantaneo
{
	private int cantidadSimbolos;
	private int probabilidades[];
	private String codigos[], simbolos[];

	public CodigoInstantaneo(int cantidadSimbolos, int[] probabilidades)
	{
		this.cantidadSimbolos = cantidadSimbolos;
		this.probabilidades = probabilidades;
		for (int i = 0; i < probabilidades.length; i++)
		{
			this.simbolos[i] = "S" + Integer.toString(i + 1);
		}
	}

	public int getCantidadSimbolos()
	{
		return cantidadSimbolos;
	}

	public int[] getProbabilidades()
	{
		return probabilidades;
	}

	public String[] getCodigos()
	{
		return codigos;
	}

	public String[] getSimbolos()
	{
		return simbolos;
	}

	public static void parallelBubbleSort(int[] probabilidades, int[] simbolos)
	{
		int n = probabilidades.length;
		int tempProb = 0, tempSimb = 0;
		for (int i = 0; i < n; i++)
		{
			for (int j = 1; j < (n - i); j++)
			{
				if (probabilidades[j - 1] > probabilidades[j])
				{
					// swap elements
					tempProb = probabilidades[j - 1];
					tempSimb = simbolos[j - 1];
					probabilidades[j - 1] = probabilidades[j];
					probabilidades[j] = tempProb;
					simbolos[j - 1] = simbolos[j];
					simbolos[j] = tempSimb;
				}
			}
		}
	}
}

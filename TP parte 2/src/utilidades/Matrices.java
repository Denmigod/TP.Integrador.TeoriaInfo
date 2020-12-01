package utilidades;

/**
 * @author Grupo 6 Se encarga de hacer operaciones con matrices
 */
public abstract class Matrices
{

	/**
	 * Se encarga de multiplicar dos matrices multiplica la primera por la segunda
	 * guardandola en la primera
	 * 
	 * @param mat1
	 * @param mat2
	 */
	public static void multiplicarMatrices(double mat1[][], double mat2[][])
	{
		double matAux[][] = new double[mat1.length][mat1.length];
		for (int i = 0; i < mat1.length; i++)
		{
			for (int j = 0; j < mat2.length; j++)
			{
				matAux[i][j] = 0;
				for (int k = 0; k < mat1[0].length; k++)
				{
					matAux[i][j] += mat1[i][k] * mat2[k][j];
				}
			}
		}
		Matrices.copiaMatriz(mat1, matAux);
	}

	/**
	 * Se encarga de copiar matriz copia la matriz 2 en la matriz 1
	 * 
	 * @param mat1
	 * @param mat2
	 */
	public static void copiaMatriz(double mat1[][], double mat2[][])
	{
		for (int i = 0; i < mat1.length; i++)
		{
			for (int j = 0; j < mat1[0].length; j++)
			{
				mat1[i][j] = mat2[i][j];
			}
		}
	}

	/**
	 * Se encarga de mostrar la matriz
	 * 
	 * @param mat
	 */
	public static void muestraMatriz(double[][] mat)
	{
		for (int x = 0; x < mat.length; x++)
		{
			for (int y = 0; y < (mat[0].length); y++)
				System.out.print(" " + mat[x][y] + " |");
			System.out.println("");
		}

	}

	/**
	 * Se encarga de mostrar la matriz
	 * 
	 * @param mat
	 */
	public static String matrizToString(double[][] mat)
	{
		StringBuilder sb = new StringBuilder();

		for (int x = 0; x < mat.length; x++)
		{
			for (int y = 0; y < (mat[0].length); y++)
				sb.append(" " + String.format("%10f", mat[x][y]) + " |");
			sb.append("\n");
		}
		return sb.toString();

	}

	/**
	 * Se encarga de mostrar la matriz
	 * 
	 * @param mat
	 */
	public static String matrizToString(double[] mat)
	{
		StringBuilder sb = new StringBuilder();

		for (int x = 0; x < mat.length; x++)
		{

			sb.append(" " + String.format("%10f", mat[x]) + " |");

		}
		sb.append("\n");
		return sb.toString();

	}

}
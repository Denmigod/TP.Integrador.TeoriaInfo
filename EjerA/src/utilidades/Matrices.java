package utilidades;

public abstract class Matrices
{

	/*
	 * public static double[][] copiaMatriz(double[][] matriz) { double[][]
	 * matrizAux = new double[matriz.length][matriz[0].length];
	 * 
	 * for (int i = 0; i < matriz.length; i++) { for (int j = 0; j <
	 * matriz[0].length; j++) { matrizAux[i][j] = matriz[i][j]; } }
	 * 
	 * return matrizAux; }
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
	
	static void muestramatriz(double[][] mat)
	{
		for (int x = 0; x < mat.length; x++)
		{
			for (int y = 0; y < (mat[0].length); y++)
				System.out.print(" " + mat[x][y] + " |");
			System.out.println("");
		}

	}
}
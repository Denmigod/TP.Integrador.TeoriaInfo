package prueba;

import canal.Canal;

public class prubaCanal
{
	public static void main(String[] args)
	{
		double[][] matrizCanal = new double[2][2];
		double[] probabilidad = new double[2];
		Canal canal;

		probabilidad[0] = 0.5;
		probabilidad[1] = 0.5;
		//probabilidad[2] = 0.4;

		matrizCanal[0][0] = 1;
		matrizCanal[0][1] = 0;
		//matrizCanal[0][2] = 0.2;

		matrizCanal[1][0] = 0;
		matrizCanal[1][1] = 1;
		//matrizCanal[1][2] = 0.5;
		/*
		 * matrizCanal[2][0] = 0.3; matrizCanal[2][1] = 0.4; matrizCanal[2][2] = 0.3;
		 */
		canal = new Canal(probabilidad, matrizCanal);

		System.out.println("Equivocacion = " + canal.getEquivocacion());
		System.out.println("Informacion Mutua = " + canal.getInformacionMutua());
		System.out.println("Es simetrica? " + canal.informacionMutuaIsSimetrica());
		System.out.println("Es mayor a 0? "+canal.informacionMutuaIsMayorIgualCero());

	}
}

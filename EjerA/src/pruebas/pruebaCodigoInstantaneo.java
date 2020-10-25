package pruebas;

import modelo.CodigoInstantaneo;

public class pruebaCodigoInstantaneo {
	
	public static void main(String[] args) {
		int cantSimb = 5;
		double[] prob = new double[5];
		prob[0] = 0.1;
		prob[1] = 0.15;
		prob[2] = 0.15;
		prob[3] = 0.4;
		prob[4] = 0.2;
		
		CodigoInstantaneo codIns = new CodigoInstantaneo(cantSimb,prob);
		//CodigoInstantaneo.parallelBubbleSort(cod.getProbabilidades(), cod.getSimbolos());
		double[] probOrd = codIns.getProbabilidades();
		String[] simbOrd = codIns.getSimbolos();
		String[] codigos = codIns.getCodigos();
		for(int i=0; i<cantSimb; i++) {
			System.out.println(probOrd[i] + " " + simbOrd[i] + " " + codigos[i]);
		}
		
		//System.out.println(probOrd[1] + " " + simbOrd[1] + " " + codigos[1]);
		//System.out.println(probOrd[2] + " " + simbOrd[2] + " " + codigos[2]);
		//System.out.println();
	}
}

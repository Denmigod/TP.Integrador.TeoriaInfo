package modelo;

public class CodigoInstantaneo {
	private int cantidadSimbolos;
	private double probabilidades[];
	private String codigos[], simbolos[];
	
	public CodigoInstantaneo(int cantidadSimbolos, double[] probabilidades) {
		this.cantidadSimbolos = cantidadSimbolos;
		this.probabilidades = probabilidades;
		this.codigos = new String[cantidadSimbolos];
		this.simbolos = new String[cantidadSimbolos];
		this.completaVectores(cantidadSimbolos);
	}
	
	private void completaVectores(int cantidadSimbolos) {
		for(int i=0; i<probabilidades.length; i++) {
			this.simbolos[i] = "S"+Integer.toString(i+1);
		}
		CodigoInstantaneo.parallelBubbleSort(this.getProbabilidades(), this.getSimbolos());
		this.codigos[0] = Integer.toString(0);
		for(int i=1; i<probabilidades.length; i++) {
			this.codigos[i] = Integer.toString(1)+this.codigos[i-1];
		}
	}

	public int getCantidadSimbolos() {
		return cantidadSimbolos;
	}
	public double[] getProbabilidades() {
		return probabilidades;
	}
	public String[] getCodigos() {
		return codigos;
	}
	public String[] getSimbolos() {
		return simbolos;
	}
	public static void parallelBubbleSort(double[] probabilidades, String[] simbolos) {
		int n = probabilidades.length;
		double tempProb = 0;
		String tempSimb = "";
		for (int i=0; i<n; i++) {
			for (int j=1; j<(n-i); j++) {
				if (probabilidades[j-1] < probabilidades[j]) {
					// swap elements
					tempProb = probabilidades[j-1];
					tempSimb = simbolos[j-1];
					probabilidades[j-1] = probabilidades[j];
					probabilidades[j] = tempProb;
					simbolos[j-1] = simbolos[j];
					simbolos[j] = tempSimb;
				}
			}
		}
	}
}

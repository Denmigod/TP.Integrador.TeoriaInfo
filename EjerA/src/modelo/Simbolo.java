package modelo;

public class Simbolo {

	private String dato;
	private double probabilidad;

	public Simbolo(String dato, double probabilidad) {
		super();
		this.dato = dato;
		this.probabilidad = probabilidad;
	}

	public String getDato() {
		return dato;
	}

	public double getCantInformacion(int base) {
		double resultado = 0;
		if (probabilidad > 0) {
			resultado = (Math.log(1 / probabilidad) / Math.log(base));
		}
		return resultado;

	}

	public double getProbabilidad() {
		return probabilidad;
	}

	public void setProbabilidad(double probabilidad) {
		this.probabilidad = probabilidad;
	}

}

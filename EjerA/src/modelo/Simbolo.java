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
		
		
		return (Math.log(1/probabilidad) / Math.log(base));
		
	}

	public double getProbabilidad() {
		return probabilidad;
	}

	public void setProbabilidad(double probabilidad) {
		this.probabilidad = probabilidad;
	}
	

}

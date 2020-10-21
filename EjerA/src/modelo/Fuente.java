package modelo;

public abstract class Fuente {

	public  Fuente() {
		
	}
	public abstract double getEntropia();
	public abstract double getCantInformacion(String simbolo) throws SimboloNoEncontradoException;
	
	
	
}

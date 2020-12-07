package compresor;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface ICompresor
{
	public void comprimir(String direccionOrigen, String direccionDestino, String nombre)throws FileNotFoundException, IOException;
	
	public void descomprimir(String direccionOrigen, String direccionDestino, String nombre)throws FileNotFoundException, IOException;
	
	public double getTasaCompresion();


	public double getRendimiento();


	public double getRedundancia();

}

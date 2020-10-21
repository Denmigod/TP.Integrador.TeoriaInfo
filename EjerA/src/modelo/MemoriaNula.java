package modelo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;



public class MemoriaNula extends Fuente {
	private HashMap<String, Simbolo> listaSimbolos = new HashMap<String, Simbolo>();
	private int base;

	public MemoriaNula() {
		// TODO Auto-generated constructor stub
		this.base = 0;

	}

	public void addSimbolo(Simbolo simbolo) {
		this.listaSimbolos.put(simbolo.getDato(), simbolo);
		this.base++;
	}

	/**
	 *No lanza nunca la excepcion 
	 */
	@Override
	public double getEntropia() {
		// TODO Auto-generated method stub
		Entry<String, Simbolo> entry;
		double resultado = 0;
		Set<Entry<String, Simbolo>> entrySet = this.listaSimbolos.entrySet();
		Iterator<Entry<String, Simbolo>> it = entrySet.iterator();
		while (it.hasNext()) {
			entry = it.next();
			try {
				resultado+=entry.getValue().getProbabilidad()*this.getCantInformacion(entry.getKey());
			} catch (SimboloNoEncontradoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			return resultado;
		}
	

	@Override
	public double getCantInformacion(String simbolo) throws SimboloNoEncontradoException {
		// TODO Auto-generated method stub
		double resultado = 0;
		if (this.listaSimbolos.containsKey(simbolo)) {
			resultado = this.listaSimbolos.get(simbolo).getCantInformacion(this.base);
		} else {
			throw new SimboloNoEncontradoException("El simbolo " + simbolo + " no se encontro");
		}
		return (resultado);
	}

}

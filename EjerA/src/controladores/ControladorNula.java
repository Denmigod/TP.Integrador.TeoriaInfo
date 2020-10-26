package controladores;

import modelo.MemoriaNula;

public class ControladorNula
{
	MemoriaNula fuente;

	public ControladorNula(int base, int cantSimbolos)
	{
		fuente = new MemoriaNula(base);
	}

}

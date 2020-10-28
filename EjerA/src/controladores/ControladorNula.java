package controladores;

import UI.NulaMenu;
import modelo.MemoriaNula;

public class ControladorNula
{
	MemoriaNula fuente;
	NulaMenu ventana;

	public ControladorNula(int base, int cantSimbolos)
	{
		fuente = new MemoriaNula(base);
		ventana = new NulaMenu();
	}

}

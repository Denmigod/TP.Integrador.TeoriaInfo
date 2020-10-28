package controladores;

import UI.NulaMenu;
import UI.VentanaEmergenteMemoriaNula;
import modelo.MemoriaNula;

public class ControladorNula
{
	MemoriaNula fuente;
	NulaMenu ventana;
	VentanaEmergenteMemoriaNula ventanaEmergente;

	public ControladorNula(int base, int cantSimbolos)
	{
		fuente = new MemoriaNula(base);
		ventanaEmergente = new VentanaEmergenteMemoriaNula(cantSimbolos);
	}

}

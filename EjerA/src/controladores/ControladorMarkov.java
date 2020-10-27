package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import UI.MarkovMenu;
import UI.VentanaEmergenteSimbolo;
import modelo.Markov;

public class ControladorMarkov implements ActionListener {
	Markov fuente;
	MarkovMenu ventana;
	VentanaEmergenteSimbolo ventanaEmergenteSimbolo;
	ArrayList<String> simbolos = new ArrayList<String>();
	int cantSimbolos;

	public ControladorMarkov(int base, int cantSimbolos) {
		super();
		this.cantSimbolos = cantSimbolos;
		this.ventana = new MarkovMenu();
		this.creaArrayListSimbolos();
	}

	private void creaArrayListSimbolos() {
		this.ventanaEmergenteSimbolo = new VentanaEmergenteSimbolo();
		ventanaEmergenteSimbolo.setActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String comando = arg0.getActionCommand();
		if (comando.equals("AceptarSimbolo"))
			botonAceptarSimbolo();

	}

	public void botonAceptarSimbolo() {
		this.simbolos.add(this.ventanaEmergenteSimbolo.getSimbolo());
		this.ventanaEmergenteSimbolo.setLabelSimbolo(this.simbolos.size()+1);
		if (this.simbolos.size() >= this.cantSimbolos)
			this.ventanaEmergenteSimbolo.cerrar();
	}

}

package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import UI.MarkovMenu;
import UI.VentanaEmergenteProbabilidad;
import UI.VentanaEmergenteSimbolo;
import excepciones.DimensionInconsistenteException;
import modelo.Markov;

public class ControladorMarkov implements ActionListener {
	private Markov fuente;
	private MarkovMenu ventana;
	private VentanaEmergenteSimbolo ventanaEmergenteSimbolo;
	private VentanaEmergenteProbabilidad ventanaEmergenteProbabilidad;
	private ArrayList<String> simbolos = new ArrayList<String>();
	private double matrizTransicion[][];
	private int cantSimbolos;
	private int ultimaFila = 0;
	private int ultimaColumna = 0;
	private int base;

	public ControladorMarkov(int base, int cantSimbolos) {
		super();
		this.cantSimbolos = cantSimbolos;
		this.base = base;
		this.matrizTransicion=new double [cantSimbolos][cantSimbolos];
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
			this.botonAceptarSimbolo();
		else if (comando.equals("AceptarProbabilidad"))
			this.botonAceptarProbabilidad();
		else if (comando.equals("Salir"))
			this.ventana.cerrar();

	}

	private void botonAceptarProbabilidad() {
		// TODO Auto-generated method stub
		this.matrizTransicion[ultimaFila][ultimaColumna] = this.ventanaEmergenteProbabilidad.getProbabilidad();
		if (this.ultimaFila == this.cantSimbolos-1 && this.ultimaColumna == this.cantSimbolos-1) {
			this.ventanaEmergenteProbabilidad.cerrar();
			this.ventana = new MarkovMenu();
			try {
				this.fuente = new Markov(this.matrizTransicion, this.simbolos, this.base);
			} catch (DimensionInconsistenteException e) {
				// TODO Auto-generated catch block
				this.ventana.imprimeMensaje("La matriz de transicion no coincide con el vector de simbolos");
			}
		} else {
			if (this.ultimaColumna < this.cantSimbolos-1)
				this.ultimaColumna++;
			else {
				this.ultimaColumna = 0;
				this.ultimaFila++;
			}
			
			this.ventanaEmergenteProbabilidad.setLabelProbabilidad(ultimaFila+1, ultimaColumna+1);
		}
	}

	public void botonAceptarSimbolo() {
		this.simbolos.add(this.ventanaEmergenteSimbolo.getSimbolo());
		this.ventanaEmergenteSimbolo.setLabelSimbolo(this.simbolos.size() + 1);
		if (this.simbolos.size() >= this.cantSimbolos) {
			this.ventanaEmergenteSimbolo.cerrar();
			this.creaMatrizTransicion();

		}
	}

	private void creaMatrizTransicion() {
		// TODO Auto-generated method stub
		this.ventanaEmergenteProbabilidad = new VentanaEmergenteProbabilidad();
		ventanaEmergenteProbabilidad.setActionListener(this);
	}

}

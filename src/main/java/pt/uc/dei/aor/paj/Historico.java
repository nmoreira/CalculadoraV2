package pt.uc.dei.aor.paj;


import java.util.concurrent.CopyOnWriteArrayList;

import javax.enterprise.context.SessionScoped;

import java.io.Serializable;

@SessionScoped
public class Historico implements Serializable {	
	
	private static final long serialVersionUID = 1L;	
	
	private CopyOnWriteArrayList<Entrada> entradas = new CopyOnWriteArrayList<>();

	
	public void adicionaEntrada(Entrada ent) {
		entradas.add(ent);
	}
	
	public CopyOnWriteArrayList<Entrada> getEntradas() {
		return entradas;
	}

	
	
}

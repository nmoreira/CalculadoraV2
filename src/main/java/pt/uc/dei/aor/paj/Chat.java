package pt.uc.dei.aor.paj;

import java.io.Serializable;
import java.util.ArrayList;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;



@Named
@ApplicationScoped
public class Chat implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject Utilizador util;
	@Inject Mensagem msg;
	
	private ArrayList<String> listautil;
	private ArrayList<String> listamsg;
	
	public Chat(){}

	public ArrayList<String> getListautil() {
		return listautil;
	}

	public void setListautil(ArrayList<String> listautil) {
		this.listautil = listautil;
	}

	public ArrayList<String> getListamsg() {
		return listamsg;
	}

	public void setListamsg(ArrayList<String> listamsg) {
		this.listamsg = listamsg;
	}

	
}

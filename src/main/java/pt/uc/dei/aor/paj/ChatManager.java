package pt.uc.dei.aor.paj;

import java.io.Serializable;
import java.util.ArrayList;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
public class ChatManager implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	Utilizador util;
	@Inject
	Mensagem msg;

	private ArrayList<Utilizador> listautil;
	private ArrayList<String> listamsg;
	private Utilizador utilactivo;

	public ChatManager() {
		listautil = new ArrayList<Utilizador>();
		listamsg = new ArrayList<String>();
	}
	

	public boolean fazLogin(String nome, String pass) {
		boolean sucesso = false;
		for (Utilizador x : listautil) {
			if (x.getNome().equals(nome) & x.getPass().equals(pass)) {
				sucesso = true;
				utilactivo = x;
			}
		}
		return sucesso;
	}
	
	public boolean criaNovo(String nome, String pass) {
		boolean criado=false;
		for (Utilizador x : listautil) {
			if (x.getNome().equals(nome)){
				criado=false;
			}else{
				criado=true;
				util.setNome(nome);
				util.setPass(pass);
				listautil.add(util);
				utilactivo=util;
			}
		}
		return criado;
	}

	public ArrayList<Utilizador> getListautil() {
		return listautil;
	}

	public void setListautil(ArrayList<Utilizador> listautil) {
		this.listautil = listautil;
	}

	public Utilizador getUtilactivo() {
		return utilactivo;
	}

	public void setUtilactivo(Utilizador utilactivo) {
		this.utilactivo = utilactivo;
	}

	public ArrayList<String> getListamsg() {
		return listamsg;
	}

	public void setListamsg(ArrayList<String> listamsg) {
		this.listamsg = listamsg;
	}

}

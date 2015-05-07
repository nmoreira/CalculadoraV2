package pt.uc.dei.aor.paj;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

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

	
	private HashMap<String, Utilizador> utilizadores = new HashMap<>();

	public ChatManager() {
		listautil = new ArrayList<Utilizador>();
		listamsg = new ArrayList<String>();
		utilizadores.put("nuno", new Utilizador("nuno", "123"));
	}
	

public String validaLogin(){
		
		Utilizador tempUser = utilizadores.get(util.getNome());
		
		if(tempUser != null && tempUser.getPass().equals(util.getPass())){
			utilactivo = tempUser;
			return "basic";
		} else{
			FacesMessage msg = new FacesMessage("Login error!", "ERROR MSG");
	        msg.setSeverity(FacesMessage.SEVERITY_WARN);
	        FacesContext.getCurrentInstance().addMessage(null, msg);
			
	        return "testelogin";
		}
		 
	}
	
	
//	public boolean fazLogin(String nome, String pass) {
//		boolean sucesso = false;
//		for (Utilizador x : listautil) {
//			if (x.getNome().equals(nome) & x.getPass().equals(pass)) {
//				sucesso = true;
//				utilactivo = x;
//			}
//		}
//		return sucesso;
//	}
	
	public boolean criaNovo() {
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

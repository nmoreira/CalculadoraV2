package pt.uc.dei.aor.paj;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
	private Utilizador util;
	@Inject
	private Mensagem msg;

	private ArrayList<String> listamsg;
	private Utilizador utilactivo;
	private ConcurrentHashMap<String, Utilizador> utilizadores = new ConcurrentHashMap<>();

	public ChatManager() {
		listamsg = new ArrayList<String>();
		utilizadores.put("nuno", new Utilizador("nuno", "123"));
		utilizadores.put("pedro", new Utilizador("pedro", "123"));
		utilizadores.put("alberto", new Utilizador("alberto", "123"));
	}

	public String validaLogin() {

		Utilizador tempUser = utilizadores.get(util.getNome());

		if (tempUser != null && tempUser.getPass().equals(util.getPass())) {
			utilactivo = tempUser;
			util.setLogeduser(true);
			return "scientific?faces-redirect=true";

		} else {

			util.setNome("");
			util.setPass("");
			util.setLogeduser(false);
			FacesMessage msg = new FacesMessage("Erro de login!", "ERROR MSG");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			if(FacesContext.getCurrentInstance()!=null)
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "index";
		}

	}

	public String criaNovo() {

		Utilizador tempUser = utilizadores.get(util.getNome());

		if (tempUser != null) {

			util.setNome("");
			util.setPass("");
			util.setLogeduser(false);
			FacesMessage msg = new FacesMessage(
					"Nome de utilizador já existe!", "ERROR MSG");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			if(FacesContext.getCurrentInstance()!=null)
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "index";

		} else {

			utilizadores.put(util.getNome(), new Utilizador(util.getNome(),
					util.getPass()));
			util.setNome("");
			util.setLogeduser(false);
			FacesMessage msg = new FacesMessage(
					"Utilizador criado com sucesso!", "ERROR MSG");
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			if(FacesContext.getCurrentInstance()!=null)
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "index";
		}
	}

	public synchronized void addMsg() {
		listamsg.add(0,
				msg.getData() + " " + util.getNome() + ": " + msg.getMsg());
		msg.setMsg("");
	}
	
	

	public synchronized String logout() {
		util.setNome("");
		util.setPass("");
		utilactivo = null;
		util.setLogeduser(false);
		if(FacesContext.getCurrentInstance()!=null)
		FacesContext.getCurrentInstance().getExternalContext()
				.invalidateSession();
		return "index?faces-redirect=true";
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
	
	public ConcurrentHashMap<String, Utilizador> getUtilizadores() {
		return utilizadores;
	}

	public void setUtilizadores(ConcurrentHashMap<String, Utilizador> utilizadores) {
		this.utilizadores = utilizadores;
	}


}

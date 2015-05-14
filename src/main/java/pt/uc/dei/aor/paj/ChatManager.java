package pt.uc.dei.aor.paj;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

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
	Utilizador util;
	@Inject
	Mensagem msg;

	private ArrayList<String> listamsg;
	private Utilizador utilactivo;
	private HashMap<String, Utilizador> utilizadores = new HashMap<>();

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
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return "index";
		}
	}

	public void addMsg() {
		listamsg.add(0,
				msg.getData() + " " + util.getNome() + ": " + msg.getMsg());
		msg.setMsg("");
	}

	public String logout() {
		util.setNome("");
		util.setPass("");
		utilactivo = null;
		util.setLogeduser(false);
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

}

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
	private boolean logeduser=false; 

	
	public boolean isLogeduser() {
		return logeduser;
	}


	public void setLogeduser(boolean logeduser) {
		this.logeduser = logeduser;
	}

	private HashMap<String, Utilizador> utilizadores = new HashMap<>();

	public ChatManager() {
		listamsg = new ArrayList<String>();
		utilizadores.put("nuno", new Utilizador("nuno", "123"));
		utilizadores.put("pedro", new Utilizador("pedro", "123"));
		utilizadores.put("alberto", new Utilizador("alberto", "123"));

	}
	

public String validaLogin(){
		
		Utilizador tempUser = utilizadores.get(util.getNome());
		
		if(tempUser != null && tempUser.getPass().equals(util.getPass())){
			utilactivo = tempUser;
			logeduser=true;
			return "basic?faces-redirect=true";

		} else{
			
			util.setNome("");
			util.setPass("");
			logeduser=false;
			
			FacesMessage msg = new FacesMessage("Login error!", "ERROR MSG");
	        msg.setSeverity(FacesMessage.SEVERITY_WARN);
	        FacesContext.getCurrentInstance().addMessage(null, msg);
			
	        return "testelogin?faces-redirect=true";
		}
		 
	}
	
	public String criaNovo() {
		
		Utilizador tempUser = utilizadores.get(util.getNome());
		
		if (tempUser != null){
			
			util.setNome("");
			util.setPass("");
			logeduser=false;
			FacesMessage msg = new FacesMessage("Nome de utilizador já existe!", "ERROR MSG");
	        msg.setSeverity(FacesMessage.SEVERITY_WARN);
	        FacesContext.getCurrentInstance().addMessage(null, msg);
			
			return "testelogin?faces-redirect=true";
			
		} else {
			
			utilizadores.put(util.getNome(), new Utilizador(util.getNome(), util.getPass()));
			util.setNome("");
			logeduser=false;
			FacesMessage msg = new FacesMessage("Utilizador criado com sucesso!", "ERROR MSG");
	        msg.setSeverity(FacesMessage.SEVERITY_INFO);
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	        
			return "testelogin?faces-redirect=true";	
		}
	}
	
	
	public void addMsg (){
		listamsg.add(0,msg.getData() + " " + util.getNome() + ": "+msg.getMsg());	
	}

	public String logout(){
		
		util.setNome("");
		util.setPass("");
		utilactivo = null;
		logeduser=false;
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "testelogin?faces-redirect=true";
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

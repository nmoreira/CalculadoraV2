package pt.uc.dei.aor.paj;

import java.util.HashMap;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import java.io.Serializable;

@Named
@SessionScoped
public class LoginBean implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	
	
	public LoginBean() {
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	public String logout() {
	    //  session.invalidate();
	      return "testelogin";
	}
	
}

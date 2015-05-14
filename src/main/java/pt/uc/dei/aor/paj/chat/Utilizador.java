package pt.uc.dei.aor.paj.chat;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped

public class Utilizador implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nome;
	private String pass;
	private boolean logeduser=false; 

	
	public boolean isLogeduser() {
		return logeduser;
	}

	public void setLogeduser(boolean logeduser) {
		this.logeduser = logeduser;
	}

	public Utilizador (){

	}
	
	public Utilizador (String user, String pass){
		this.nome = user;
		this.pass = pass;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
}

package pt.uc.dei.aor.paj;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped

public class Mensagem implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msg;
	
	public Mensagem(){}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	

}

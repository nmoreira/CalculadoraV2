package pt.uc.dei.aor.paj;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped

public class Mensagem implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msg;
	private String user;
	private GregorianCalendar data;
	
	public Mensagem(){
		
	}
	
	public Mensagem(String user, String msg){
		this.user = user;
		this.msg = msg;
		this.data = (GregorianCalendar) Calendar.getInstance();
	}

	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public GregorianCalendar getData() {
		return data;
	}

	public void setData(GregorianCalendar data) {
		this.data = data;
	}

	
	

}

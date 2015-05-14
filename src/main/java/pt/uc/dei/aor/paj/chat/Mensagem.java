package pt.uc.dei.aor.paj.chat;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class Mensagem implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msg;
	private GregorianCalendar data;
	
	public Mensagem(){
		
	}
	


	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
		this.data = (GregorianCalendar) Calendar.getInstance();
	}

	public String getData() {
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");		
		String time = timeFormat.format(data.getTime());
		return 	time;
	}
	
}

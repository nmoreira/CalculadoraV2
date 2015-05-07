package pt.uc.dei.aor.paj;

import java.util.ArrayList;


public class Entrada {
	private String exp;	
	private String res;
	private ArrayList<Input> inputs;
	private String tempo;
		
	
	public Entrada(String exp, String res, ArrayList<Input> inputs, String tempo) {
		super();
		this.exp = exp;
		this.res = res;
		this.inputs = inputs;
		this.tempo = tempo;
		
	}

	public String getExp() {
		return exp;
	}

	public String getRes() {
		return res;
	}

	public ArrayList<Input> getInputs(){
		return inputs;
	}
	
	public String getTempo() {
		return tempo;
	}
	
}

package pt.uc.dei.aor.paj;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;


public class Entrada {
	private String exp;	
	private String res;
	private CopyOnWriteArrayList<Input> inputs;
	private String tempo;


	public Entrada(String exp, String res, ArrayList<Input> inputs, String tempo) {
		super();
		this.exp = exp;
		this.res = res;
		this.tempo = tempo;
		this.inputs = new CopyOnWriteArrayList<Input>();
		synchronized(this){
			for (Input input : inputs) {
				this.inputs.add(input);
			}
		}
	}

	public String getExp() {
		return exp;
	}

	public String getRes() {
		return res;
	}

	public synchronized ArrayList<Input> getInputs(){
		ArrayList<Input> out = new ArrayList<>();
		for (Input input : inputs) {
			out.add(input);
		}
		return out;
	}

	public String getTempo() {
		return tempo;
	}

}

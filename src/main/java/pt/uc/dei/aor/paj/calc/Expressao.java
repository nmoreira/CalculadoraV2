package pt.uc.dei.aor.paj.calc;

import java.util.ArrayList;
import java.util.Stack;



import javax.enterprise.context.SessionScoped;

import java.io.Serializable;

@SessionScoped
public class Expressao implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private Stack<Input> entrada = new Stack<>();
	
	public String add(Input in){
		if(this.entrada.size() > 0 && 
				(in.getTipo().contains("nm") || 
						in.getTipo().contains("vg")) &&
				(this.peekLastInput().getTipo().contains("nm") || 
					this.peekLastInput().getTipo().contains("vg"))){
			
			String tmp = this.getLastInput();			
			tmp = tmp.concat(in.getConteudo());			
			entrada.push(new Input("nm", tmp));
		} else {
			entrada.push(in);
		}		
		return getExpressao();
	}
	
	public String remove(){
		entrada.pop();
		return getExpressao();
	}
	
	private String getExpressao(){
		String out="";
		for (Input input : entrada) {
			out += input.getConteudo();
		}
		return out;
		
	}
	
	public String clear(){
		entrada.clear();
		return getExpressao();
	}
	
	public ArrayList<Input> getInputs(){
		ArrayList<Input> out = new ArrayList<>();
		for (Input input : entrada) {
			out.add(input);
		}
		return out;
	}
	
	public void loadInputs(ArrayList<Input> inputs){
		entrada.clear();
		for (Input input : inputs) {
			entrada.push(input);
		}
	}
	
	public Input peekLastInput(){
		return entrada.peek();
	}
	
	public String getLastInput(){
		return entrada.pop().getConteudo();
	}
	
	public String toString(){
		String out="";
		for (Input input : entrada) {
			out += input.getConteudo() + "\n";
		}
		return out;
	}
	
}

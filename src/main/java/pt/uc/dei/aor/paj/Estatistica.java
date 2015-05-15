package pt.uc.dei.aor.paj;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class Estatistica implements Serializable{
	
	
	private static final long serialVersionUID = -8382190444479383697L;
		
	private ConcurrentHashMap <String, Operador> operation = new ConcurrentHashMap<>();
	
	public Estatistica() {
		operation.put("+", new Operador("Soma"));
		operation.put("-", new Operador("Subtração"));
		operation.put("/", new Operador("Divisão"));
		operation.put("*", new Operador("Multiplicação"));
		operation.put("sqrt", new Operador("Raiz quadrada"));
		operation.put("+/-", new Operador("Inversão de sinal (+/-)"));
		operation.put("%", new Operador("Percentagem"));
		operation.put("sin", new Operador("Seno"));
		operation.put("cos", new Operador("Coseno"));
		operation.put("tan", new Operador("Tangente"));
		operation.put("1/x", new Operador("Inverso (1/x)"));
		operation.put("x^2", new Operador("Quadrado (x^2)"));
		operation.put("^", new Operador("Expoente (x^y)"));
		operation.put("pi", new Operador("PI"));
		operation.put("e", new Operador("e"));
		operation.put("log10", new Operador("Logaritmo de base 10 (log10)"));
		operation.put("log2", new Operador("Logaritmo de base 2 (log2)"));
		operation.put("log", new Operador("Logaritmo de base natural (ln)"));
		operation.put("!", new Operador("Fatorial (!)"));
		operation.put("cbrt", new Operador("Raiz Cúbica"));
		operation.put("e^x", new Operador("Função e^x"));
		operation.put("10^x", new Operador("Função 10^x"));		
	}
	
	public void recolheEstatistica(Expressao exp){
		
		ArrayList<Input> inputs = exp.getInputs();
		
		for (Input input : inputs) {
			if(input.getTipo().contains("op")){
				if(input.getConteudo().contains("+"))
					operation.get("+").add();
				else if(input.getConteudo().contains("-"))
					operation.get("-").add();
				else if(input.getConteudo().contains("*"))
					operation.get("*").add();
				else if(input.getConteudo().contains("/"))
					operation.get("/").add();
				else if(input.getConteudo().contains("sqrt"))
					operation.get("sqrt").add();
				else if(input.getConteudo().contains("sin"))
					operation.get("sin").add();
				else if(input.getConteudo().contains("cos"))
					operation.get("cos").add();
				else if(input.getConteudo().contains("tan"))
					operation.get("tan").add();
				else if(input.getConteudo().contains("log10"))
					operation.get("log10").add();
				else if(input.getConteudo().contains("log2"))
					operation.get("log2").add();
				else if(input.getConteudo().contains("log("))
					operation.get("log").add();
				else if(input.getConteudo().contains("!"))
					operation.get("!").add();
				else if(input.getConteudo().contains("cbrt"))
					operation.get("cbrt").add();
				else if(input.getConteudo().contains("e^"))
					operation.get("e^x").add();					
				else if(input.getConteudo().contains("10^"))
					operation.get("10^x").add();
				else if(input.getConteudo().contains("^"))
					operation.get("^").add();
			}
		}
		
	}
	
	public void recolheInput(Input in){
		if(in.getTipo().contains("+/-"))
			operation.get("+/-").add();
		else if(in.getTipo().contains("%"))
			operation.get("%").add();
		else if(in.getTipo().contains("1/x"))
			operation.get("1/x").add();
		else if(in.getTipo().contains("x^2"))
			operation.get("x^2").add();
		else if(in.getTipo().contains("pi"))
			operation.get("pi").add();
		else if(in.getTipo().contains("e"))
			operation.get("e").add();
	}

	public List<Operador> getEntrada() {
		List<Operador> entrada = new CopyOnWriteArrayList<Operador>(operation.values());
		Collections.sort(entrada);
		return entrada;
	}
}

package pt.uc.dei.aor.paj;

import java.io.Serializable;
import java.util.ArrayList;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.function.Function;
import net.objecthunter.exp4j.operator.Operator;



@Named
@SessionScoped
public class Calc implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String mostrador="";
	private boolean cientifica = false;
	
	private Expressao expressao;
	
	@Inject
	private Historico hist;
	
	@Inject 
	private Estatistica est;
	
	
	
	private boolean virgulaValida; // indica se é válido usar a vírgula na expressão
	private boolean operadorValido; // indica se é válido usar um operador na expressão
	private boolean existeVirgula; // indica se existe uma vírgula na última parte numérica da expressão
	private boolean parentsisAberto; // indica se existe um parentsis aberto
	private boolean radianos = true;	// indica se os ângulos introduzidos são em radianos (predefinido) ou em graus

	
	public Calc(){
		expressao = new Expressao();
		init();		
	}
	
	public void read(ActionEvent evento){
		this.read(evento.getComponent().getId());
	}
	
	public void read(String str){
		
		
		switch(str){
		case "num0": {			
			novoDigito("0");	
		}break;
		case "num1": {
			novoDigito("1");
		}break;
		case "num2": {
			novoDigito("2");
		}break;
		case "num3": {
			novoDigito("3");
		}break;
		case "num4": {
			novoDigito("4");
		}break;
		case "num5": {
			novoDigito("5");
		}break;
		case "num6": {
			novoDigito("6");
		}break;
		case "num7": {
			novoDigito("7");
		}break;
		case "num8": {
			novoDigito("8");
		}break;
		case "num9": {
			novoDigito("9");			
		}break;
		case "soma": {
			novoOperador(" + ");		
		}break;
		case "sub": {
			novoOperador(" - ");
		}break;
		case "mult": {
			novoOperador(" * ");
		}break;
		case "div": {
			novoOperador(" / ");
		}break;
		case "raizq": {
			novaRaiz(" sqrt(");
		}break;
		case "abrepar": {
			novoParentsis(" (");
		}break;
		case "fechapar": {
			novoParentsis(") ");
		}break;
		case "maismenos": {
			mudaSinal();
		}break;
		case "percent": {
			percentagem();
		}break;
		case "sen": {
			novoOpAngulo(" sin(");
		}break;
		case "cos": {
			novoOpAngulo(" cos(");
		}break;
		case "tan": {
			novoOpAngulo(" tan(");
		}break;
		case "inverso": {
			inverso();
		}break;
		case "xquad": {
			quadrado();
		}break;
		case "xexpy": {
			novoOperador("^");
		}break;
		case "pi": {
			inserePI("pi");
		}break;
		case "neper": {
			insereE("e");
		}break;
		case "log10": {
			novoLogaritmo(" log10(");
		}break;
		case "log2": {
			novoLogaritmo(" log2(");
		}break;
		case "log": {
			novoLogaritmo(" log(");
		}break;
		case "fact": {
			factorial();
		}break;
		case "cubicrt": {
			novaRaiz(" cbrt(");
		}break;
		case "eexpx": {
			operadorValido = true;
			novoOperador(" e^");
		}break;
		case "dezexpx": {
			operadorValido = true;
			novoOperador(" 10^");
		}break;
		case "virg": {
			insereVirgula(".");
		} break;
		case "igual": {
			calcula();			
		} break;
		
		}
		
	}
	
	private void calcula(){
		if(operadorValido && parentsisAberto == false){
			String [] res = opera(mostrador, expressao).split("::");
			if(res[0].contains("erros") == false){
				ArrayList<Input> inputs = expressao.getInputs();
				hist.adicionaEntrada(new Entrada(mostrador, res[0], inputs, res[1]));
				mostrador = expressao.clear();
				mostrador = expressao.add(new Input("nm", res[0]));
				init();
				operadorValido = true;
			}else {
				mostrador = res[0];
			}			
		}
	}
	
	private void factorial(){		
		if(mostrador.length() > 0 && expressao.peekLastInput().getTipo().contains("nm")){
			String tmp = expressao.peekLastInput().getConteudo();			
			double val = Double.parseDouble(tmp);
			if(val%1 == 0 && val > 0 && val <= 170){
				mostrador = expressao.add(new Input("op", "!"));
			}			
		}
	}
	
	private void novoLogaritmo(String d){
		operadorValido = true;
		parentsisAberto = true;
		if(existeVirgula == false){
			virgulaValida = true;
		}		
		mostrador = expressao.add(new Input("op", d));
	}
	
	private void insereE(String d){
		operadorValido = true;
		if(existeVirgula == false){
			virgulaValida = true;
		}
		est.recolheInput(new Input("e", d));
		mostrador = expressao.add(new Input("nm", d));
	}
	
	private void inserePI(String d){
		operadorValido = true;
		if(existeVirgula == false){
			virgulaValida = true;
		}
		est.recolheInput(new Input("pi", d));
		mostrador = expressao.add(new Input("nm", d));
	}
	
	private void quadrado(){		
		if(mostrador.length() > 0 && expressao.peekLastInput().getTipo().contains("nm")){
			String tmp = expressao.getLastInput();			
			float val = Float.parseFloat(tmp);
			mostrador = expressao.add(new Input("nm", Double.toString(Math.pow(val, 2))));
			est.recolheInput(new Input("x^2","x^2"));			
		}
	}
	
	private void inverso(){		
		if(mostrador.length() > 0 && expressao.peekLastInput().getTipo().contains("nm")){
			String tmp = expressao.getLastInput();			
			float val = Float.parseFloat(tmp);
			mostrador = expressao.add(new Input("nm", Float.toString(1/val)));
			est.recolheInput(new Input("1/x","1/x"));			
		}
	}
	
	private void novoOpAngulo(String d){
		
		parentsisAberto = true;
		if(existeVirgula == false){
			virgulaValida = true;
		}		
		if(!radianos)
			d =	d.replace("(", "d(");
		
		mostrador = expressao.add(new Input("op", d));
	}
	
	private void percentagem(){		
		if(mostrador.length() > 0 && expressao.peekLastInput().getTipo().contains("nm")){
			String tmp = expressao.getLastInput();			
			float val = Float.parseFloat(tmp);
			mostrador = expressao.add(new Input("nm", Float.toString(val/100f)));
			est.recolheInput(new Input("%","%"));			
		}
	}
	
	private void mudaSinal(){		
		if(mostrador.length() > 0 && expressao.peekLastInput().getTipo().contains("nm")){
			String tmp = expressao.getLastInput();			
			if(tmp.startsWith("-")){
				tmp = tmp.substring(1);				
			} else {
				tmp = "-" + tmp;				
			}
			est.recolheInput(new Input("+/-","+/-"));
			mostrador = expressao.add(new Input("nm", tmp));
		}
	}
	
	 private void insereVirgula(String v){
		if(virgulaValida && existeVirgula == false){
			virgulaValida = false;
			operadorValido = false;
			existeVirgula = true;
			mostrador = expressao.add(new Input("vg", v));
		} 
	}
	
	private void novoDigito(String d){
		operadorValido = true;
		if(existeVirgula == false){
			virgulaValida = true;
		}
		mostrador = expressao.add(new Input("nm", d));
	}
	
	private void novaRaiz(String d){
		operadorValido = true;
		parentsisAberto = true;
		if(existeVirgula == false){
			virgulaValida = true;
		}
		mostrador = expressao.add(new Input("op", d));
	}
	
	private void novoParentsis(String d){
		operadorValido = true;
		if(parentsisAberto)
			parentsisAberto = false;
		else parentsisAberto = true;
		
		if(existeVirgula == false){
			virgulaValida = true;
		}
		mostrador = expressao.add(new Input("par", d));
	}
	
	private void novoOperador(String op){
		if(operadorValido){
			operadorValido = false;
			virgulaValida = false;
			existeVirgula = false;
			mostrador = expressao.add(new Input("op", op));			
		}
	}
	
	private String opera(String exp, Expressao inputs){
		double res;
		String out;
		long tempoInicial, tempoFinal;
		double duracao;

		// definição de novas funções
		
		
		Function cosd = new Function("cosd", 1) {
		    @Override
		    public double apply(double... args) {
		    	return Math.cos(Math.toRadians(args[0]));
		    }
		};
		
		Function sind = new Function("sind", 1) {
		    @Override
		    public double apply(double... args) {
		    	return Math.sin(Math.toRadians(args[0]));
		    }
		};
		Function tand = new Function("tand", 1) {
		    @Override
		    public double apply(double... args) {
		    	return Math.tan(Math.toRadians(args[0]));
		    }
		};
		
		Operator factorial = new Operator("!", 1, true, Operator.PRECEDENCE_POWER + 1) {

		    @Override
		    public double apply(double... args) {
		        final int arg = (int) args[0];
		        double result = 1;
		        for (int i = 1; i <= arg; i++) {
		            result *= i;
		        }
		        return result;
		    }
		};
		
		// end
		try{
			Expression e = new ExpressionBuilder(exp)
			.function(cosd)
			.function(sind)
			.function(tand)
			.operator(factorial)
			.variables("pi", "e")
			.build()
			.setVariable("pi", Math.PI)
			.setVariable("e", Math.E);
			
			if(e.validate().isValid()){
				try {
					tempoInicial = System.nanoTime();
					res = e.evaluate();
					tempoFinal = System.nanoTime();
					duracao = (tempoFinal - tempoInicial)/1000d;
					if(res%1 != 0){		
						out = Double.toString(res);
						out += "::"+ Double.toString(duracao);
					} else if (Double.toString(res).endsWith(".0")){
						out = Double.toString(res);
						out = out.substring(0, out.length()-2);	
						out += "::"+ Double.toString(duracao);
					} else {
						out = Double.toString(res);
						out += "::"+ Double.toString(duracao);
					}
					est.recolheEstatistica(inputs);		
				} catch (Exception e1) {
					out = "erros na expressao";			
				}
			} else {
				out = "erros na expressao";				
			}	
		} catch (Exception excp){
			out = "erros na expressao";
		}	
		return out;
	}
	
	public String getMostrador() {
		return mostrador;
	}

	public void setMostrador(String mostrador) {
		this.mostrador = mostrador;
	}

	private void init(){
		virgulaValida = false;
		operadorValido = false;
		existeVirgula = false;
		parentsisAberto = false;
	}
	
	public void clearAll(){
		
		mostrador = expressao.clear();
		init();
		operadorValido = true;
	}
	
	public void clearLast(){
		if(mostrador.length() > 0 && expressao.peekLastInput() != null){
			String last = expressao.peekLastInput().getTipo();
			if(last.equals("op")){
				operadorValido = true;
				virgulaValida = true;
			}else if(last.equals("vg")){
				existeVirgula = false;
			}			
			mostrador=expressao.remove();
		}			
	}
	
	public void reUseExp(Entrada ent){
		clearAll();
		mostrador = ent.getExp();
		expressao.loadInputs(ent.getInputs());
		operadorValido = true;
	}
	
	public void reUseResult(Entrada ent){
		clearAll();
		mostrador = ent.getRes();
		expressao.add(new Input("nm", ent.getRes()));
		operadorValido = true;
	}

	public String getExp() {
		return mostrador;
	}
	
	public void setExp(String exp) {
		this.mostrador = exp;
	}	
	
	public Estatistica getEst() {
		return est;
	}
	
	public Historico getHist() {
		return hist;
	}	

	public boolean isRadianos() {
		return radianos;
	}

	public void setRadianos(boolean radianos) {
		this.radianos = radianos;
	}

	public boolean isCientifica() {
		return cientifica;
	}

	public void setCientifica(boolean cientifica) {
		this.cientifica = cientifica;
	}
	
	public boolean isVirgulaValida() {
		return virgulaValida;
	}

	public void setVirgulaValida(boolean virgulaValida) {
		this.virgulaValida = virgulaValida;
	}

	public boolean isOperadorValido() {
		return operadorValido;
	}

	public void setOperadorValido(boolean operadorValido) {
		this.operadorValido = operadorValido;
	}

	public boolean isExisteVirgula() {
		return existeVirgula;
	}

	public void setExisteVirgula(boolean existeVirgula) {
		this.existeVirgula = existeVirgula;
	}

	public boolean isParentsisAberto() {
		return parentsisAberto;
	}

	public void setParentsisAberto(boolean parentsisAberto) {
		this.parentsisAberto = parentsisAberto;
	}

}

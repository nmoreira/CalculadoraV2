package pt.uc.dei.aor.paj.calc;

public class Operador implements Comparable<Operador>{
	
	private String op;
	private int quant = 0;
	
	public Operador(String op) {
		this.op = op;
	}
	
	public void add(){
		quant++;
	}

	public String getOp() {
		return op;
	}

	public int getQuant() {
		return quant;
	}

	@Override
	public int compareTo(Operador o) {
		if(o.getQuant() - this.quant !=0)
			return o.getQuant() - this.quant;
		else return this.op.compareToIgnoreCase(o.getOp());
		
//		if(this.getQuant() > o.getQuant())
//			return 1;
//		else if(this.getQuant() < o.getQuant())
//			return 1;		
//		else return this.compareTo(o);
	}

		
}

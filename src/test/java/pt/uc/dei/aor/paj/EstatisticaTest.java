package pt.uc.dei.aor.paj;

import java.util.List;


import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
//import static org.junit.Assert.*;
//import static org.hamcrest.CoreMatchers.*;
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.*;
//import org.hamcrest.Matcher;
//import org.hamcrest.core.IsNot;

public class EstatisticaTest {

	Estatistica est;
	List<Operador> out;
	Expressao expressao;
	Input in1 = new Input("nm", "1");
	Input in2 = new Input("nm", "0");
	Input in3 = new Input("op", " + ");
	Input in4 = new Input("nm", "235.09");	
	Input in5 = new Input("op", " * ");
	Input in6 = new Input("%", "%");
	Input in7 = new Input("e", "e");
	

	public EstatisticaTest(){
		est = new Estatistica();
		expressao = new Expressao();
	}

	@Before
	@Test
	public void testaRecolheInputIsNotNull(){
		
		est.recolheInput(in6);
		est.recolheInput(in7);
		out = est.getEntrada();
		assertThat(out, notNullValue());
	}
	
	@Before
	@Test
	public void testaRecolheEstatisticaIsNotNull(){
		expressao.add(in1);
		expressao.add(in2);
		expressao.add(in3);
		expressao.add(in4);
		expressao.add(in5);
		est.recolheEstatistica(expressao);
		out = est.getEntrada();
		assertThat(out, notNullValue());
	}
	
	
	private int testaRecolheInputAdicionado(String input){
		
		for (Operador operador : out) {
			if(operador.getOp().equalsIgnoreCase(input))
				return operador.getQuant();
		}	
		return -1;
		
	}
	
	@Test
	public void testaRecolheInput3Adicionado(){
		expressao.clear();
		assertThat(testaRecolheInputAdicionado("soma"), is(1));		
	}
	
	@Test
	public void testaRecolheInput5Adicionado(){
		assertThat(testaRecolheInputAdicionado("multiplicação"), is(1));		
	}
	
	@Test
	public void testaRecolheInput6Adicionado(){
		assertThat(testaRecolheInputAdicionado("e"), is(1));		
	}
	
	@Test
	public void testaRecolheInput7Adicionado(){
		assertThat(testaRecolheInputAdicionado("percentagem"), is(1));		
	}	
}

package pt.uc.dei.aor.paj;

import java.util.ArrayList;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ExpressaoTest {

	Expressao exp;
	Input in1 = new Input("nm", "1");
	Input in2 = new Input("nm", "0");
	Input in3 = new Input("op", " + ");
	Input in4 = new Input("nm", "235.09");	
	Input in5 = new Input("op", " * ");
	Input in6 = new Input("%", "%");
	Input in7 = new Input("e", "e");
	
	public ExpressaoTest() {
		exp = new Expressao(); 
	}
	
	@Test
	public void TestAddInput1(){
		exp.add(in1);
		assertThat(exp.getLastInput(), is("1"));
	}
	
	@Test
	public void TestAdd7Inputs(){
		exp.add(in1);
		exp.add(in2);
		exp.add(in3);
		exp.add(in4);
		exp.add(in5);
		exp.add(in6);
		exp.add(in7);
		assertThat(exp.getInputs().size(), is(6));
	}
	
	@Test
	public void TestOutputAdd7Inputs(){
		exp.add(in1);
		exp.add(in2);
		exp.add(in3);
		exp.add(in4);
		exp.add(in5);
		exp.add(in6);
		String out = exp.add(in7);
		assertThat(out, is("10 + 235.09 * %e"));
	}
	
	@Test
	public void TestOutputAdd7InputsRemove3(){
		exp.add(in1);
		exp.add(in2);
		exp.add(in3);
		exp.add(in4);
		exp.add(in5);
		exp.add(in6);
		exp.add(in7);
		exp.remove();
		exp.remove();
		String out = exp.remove();
		assertThat(out, is("10 + 235.09"));
	}
	
	@Test
	public void TestOutputEntrada7InputsRemove3Clear(){
		exp.add(in1);
		exp.add(in2);
		exp.add(in3);
		exp.add(in4);
		exp.add(in5);
		exp.add(in6);
		exp.add(in7);
		exp.remove();
		exp.remove();
		exp.remove();
		String out = exp.clear();
		assertThat(out, isEmptyString());
	}
	
	@Test
	public void TestLoadInputs(){
		ArrayList<Input> inputs = new ArrayList<>();
		inputs.add(in1);
		inputs.add(in2);
		inputs.add(in3);
		inputs.add(in4);
		inputs.add(in5);
		inputs.add(in6);
		inputs.add(in7);		
		exp.loadInputs(inputs);		
		ArrayList<Input> out = exp.getInputs();		
		assertThat(out, isA(ArrayList.class));		
	}
	
	@Test
	public void TestLoadInputs2(){
		ArrayList<Input> inputs = new ArrayList<>();
		inputs.add(in1);
		inputs.add(in2);
		inputs.add(in3);
		inputs.add(in4);
		inputs.add(in5);
		inputs.add(in6);
		inputs.add(in7);		
		exp.loadInputs(inputs);		
		ArrayList<Input> out = exp.getInputs();		
		assertThat(out.size(), is(7));				
	}
	
	@Test
	public void TestLoadInputs3(){
		ArrayList<Input> inputs = new ArrayList<>();
		inputs.add(in1);
		inputs.add(in2);
		inputs.add(in3);
		inputs.add(in4);
		inputs.add(in5);
		inputs.add(in6);
		inputs.add(in7);		
		exp.loadInputs(inputs);		
		ArrayList<Input> out = exp.getInputs();		
		assertThat(out.get(0).getConteudo(), is("1"));			
	}
	
	@Test
	public void TestLoadInputs4(){
		ArrayList<Input> inputs = new ArrayList<>();
		inputs.add(in1);
		inputs.add(in2);
		inputs.add(in3);
		inputs.add(in4);
		inputs.add(in5);
		inputs.add(in6);
		inputs.add(in7);		
		exp.loadInputs(inputs);		
		ArrayList<Input> out = exp.getInputs();
		assertThat(out.get(6).getConteudo(), is("e"));		
	}
	
	@Test
	public void TestPeekLastInput(){
		ArrayList<Input> inputs = new ArrayList<>();
		inputs.add(in1);
		inputs.add(in2);
		inputs.add(in3);
		inputs.add(in4);
		inputs.add(in5);				
		exp.loadInputs(inputs);		
		Input last = exp.peekLastInput();
		assertThat(last.getConteudo(), is(" * "));		
	}
	
	@Test
	public void TestPeekLastInput2(){
		ArrayList<Input> inputs = new ArrayList<>();
		inputs.add(in1);
		inputs.add(in2);
		inputs.add(in3);
		inputs.add(in4);
		inputs.add(in5);				
		exp.loadInputs(inputs);		
		exp.peekLastInput();
		exp.peekLastInput();
		exp.peekLastInput();
		Input last = exp.peekLastInput();
		assertThat(last.getConteudo(), is(" * "));		
	}
	
	@Test
	public void TestGetLastInput(){
		ArrayList<Input> inputs = new ArrayList<>();
		inputs.add(in1);
		inputs.add(in2);
		inputs.add(in3);
		inputs.add(in4);
		inputs.add(in5);
		inputs.add(in6);
		inputs.add(in7);		
		exp.loadInputs(inputs);
		String out = exp.getLastInput();		
		assertThat(out, is("e"));		
	}
	
	@Test
	public void TestGetLastInput2(){
		ArrayList<Input> inputs = new ArrayList<>();
		inputs.add(in1);
		inputs.add(in2);
		inputs.add(in3);
		inputs.add(in4);
		inputs.add(in5);
		inputs.add(in6);
		inputs.add(in7);		
		exp.loadInputs(inputs);
		exp.getLastInput();
		exp.getLastInput();
		exp.getLastInput();
		String out = exp.getLastInput();		
		assertThat(out, is("235.09"));		
	}
	
}

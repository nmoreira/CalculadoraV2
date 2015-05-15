package pt.uc.dei.aor.paj;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HistoricoTest {
	
	Historico hist;
	Input i;
	ArrayList<Input> in;	
	Entrada ent;
	
	@Before
	public void init(){
		hist = new Historico();
	}
	
	@Test
	public void testGetEntradas(){
		assertThat(hist.getEntradas().size(), is(0));
	}
	
	@Test
	public void testAdicionaEntrada(){
		i = new Input("nm", "2");
		in = new ArrayList<>();
		in.add(i);
		ent = new Entrada("2 + 2", "4",  in, "0");		
		hist.adicionaEntrada(ent);
		assertThat(hist.getEntradas().size(), is(1));
	}
	
	@Test
	public void testAdiciona4Entradas(){
		i = new Input("nm", "2");
		in = new ArrayList<>();
		in.add(i);
		ent = new Entrada("2 + 2", "4",  in, "0");		
		hist.adicionaEntrada(ent);
		hist.adicionaEntrada(ent);
		hist.adicionaEntrada(ent);
		hist.adicionaEntrada(ent);
		assertThat(hist.getEntradas().size(), is(4));
	}
}

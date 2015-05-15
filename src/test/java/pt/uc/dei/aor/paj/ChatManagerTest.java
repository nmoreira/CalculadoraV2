package pt.uc.dei.aor.paj;

//import java.util.ArrayList;
//import javax.annotation.PostConstruct;
//import org.hamcrest.MatcherAssert;
//import org.hamcrest.Matchers;

import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
//import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)

public class ChatManagerTest {
	
	@Mock
	Mensagem m;
	@Mock
	Utilizador u;
	
	@InjectMocks
	ChatManager cm;
	
	@Test
	public void testMsgAdicionada() {
		cm.addMsg();
		Assert.assertThat(cm.getListamsg().size(), is(equalTo(1)));
	}
	
	@Test
	public void testLogin() {
		
		Mockito.when(u.getPass()).thenReturn("123");
		Mockito.when(u.getNome()).thenReturn("pedro");
		String st = cm.validaLogin();
		Assert.assertThat(st, is("scientific?faces-redirect=true"));
	}
	
	@Test
	public void testfalhapassLogin() {
		
		Mockito.when(u.getPass()).thenReturn("321");
		Mockito.when(u.getNome()).thenReturn("pedro");
		String st = cm.validaLogin();
		Assert.assertThat(st, is("index"));
	}
	
	@Test
	public void testfalhanomeLogin() {
		
		Mockito.when(u.getPass()).thenReturn("123");
		Mockito.when(u.getNome()).thenReturn("manel");
		String st = cm.validaLogin();
		Assert.assertThat(st, is("index"));
	}
	
	@Test
	public void testfalhanomepassLogin() {
		
		Mockito.when(u.getPass()).thenReturn("456");
		Mockito.when(u.getNome()).thenReturn("manel");
		String st = cm.validaLogin();
		Assert.assertThat(st, is("index"));
	}
	
	@Test
	public void testLoginsemnome() {
		
		Mockito.when(u.getPass()).thenReturn("123");
		Mockito.when(u.getNome()).thenReturn("");
		String st = cm.validaLogin();
		Assert.assertThat(st, is("index"));
	}
	
	@Test
	public void testLoginUserLogado() {
		
		Mockito.when(u.getPass()).thenReturn("123");
		Mockito.when(u.getNome()).thenReturn("pedro");
		cm.validaLogin();
		Mockito.verify(u).setLogeduser(true);
	}
	
	@Test
	public void testLoginsempass() {
		
		Mockito.when(u.getPass()).thenReturn("");
		Mockito.when(u.getNome()).thenReturn("pedro");
		String st = cm.validaLogin();
		Assert.assertThat(st, is("index"));
	}
	
	@Test
	public void testeCriaNovoComNomeIgual() {
		Mockito.when(u.getNome()).thenReturn("pedro");
		String st = cm.criaNovo();
		Assert.assertThat(st, is("index"));
	}
	
	@Test
	public void testeCriaNovoNomeDif() {
		Mockito.when(u.getNome()).thenReturn("manel");
		String st = cm.criaNovo();
		Assert.assertThat(st, is("index"));
	}
	
	@Test
	public void testeUserAdicionado() {
		int size = cm.getUtilizadores().size();
		Mockito.when(u.getNome()).thenReturn("manel");
		cm.criaNovo();
		Assert.assertThat(cm.getUtilizadores().size(), is(size+1));
	}
	
	@Test
	public void testeUserNaoAdicionado() {
		int size = cm.getUtilizadores().size();
		Mockito.when(u.getNome()).thenReturn("pedro");
		cm.criaNovo();
		Assert.assertThat(cm.getUtilizadores().size(), is(size));
	}
	
	@Test
	public void testeLogout() {
		String st = cm.logout();
		Assert.assertThat(st, is("index?faces-redirect=true"));
	}
	
	@Test
	public void testeLogoutLimpaNome() {
		cm.logout();
		Assert.assertThat(u.getNome(), is(equalTo(null)));
	}
	
	@Test
	public void testeLogoutLimpaPass() {
		cm.logout();
		Assert.assertThat(u.getPass(), is(equalTo(null)));
	}
	
	@Test
	public void testeLogoutLimpaUtilactivo() {
		cm.logout();
		Assert.assertThat(cm.getUtilactivo(), is(equalTo(null)));
	}
	
	@Test
	public void testeLogoutUserNaoLogado() {
		cm.logout();
		Assert.assertThat(u.isLogeduser(), is(false));
	}
	
	
	
	
	
	
	
	
	
	

	

}

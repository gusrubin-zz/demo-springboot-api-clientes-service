package com.gusrubin.proofs.clients.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.gusrubin.proofs.clients.infra.ClienteRepository;
import com.gusrubin.proofs.clients.infra.EnderecoRepository;
import com.gusrubin.proofs.clients.infra.TelefoneRepository;

public class ClienteServiceTest {
	
	private ClienteRepository clienteRepository;
	private EnderecoRepository enderecoRepository;
	private TelefoneRepository telefoneRepository;
	private ClienteService clienteService;
	
	@Before
	public void setUp() {
		clienteRepository = Mockito.mock(ClienteRepository.class);
		enderecoRepository = Mockito.mock(EnderecoRepository.class);
		telefoneRepository = Mockito.mock(TelefoneRepository.class);
		clienteService = new ClienteService(clienteRepository, enderecoRepository, telefoneRepository);
	}	
	
	@Test
	public void createClienteValido() {		
		Mockito.when(clienteRepository.countByCpf(Mockito.anyString())).thenReturn(0L);
		Mockito.doReturn(EnderecoBuilder.create().exemploValido().build()).when(enderecoRepository).save(Mockito.any(Endereco.class));
		Mockito.doReturn(TelefoneBuilder.create().fixo().build()).when(telefoneRepository).save(Mockito.any(Telefone.class));
		Mockito.doReturn(ClienteBuilder.create().exemploValido().build()).when(clienteRepository).save(Mockito.any(Cliente.class));
		
		Cliente novoCliente = clienteService.create(ClienteBuilder.create().exemploValido().build());
		
		Assert.assertEquals(novoCliente.getNome(), "Gustavo");
		Assert.assertEquals(novoCliente.getEndereco().getCidade(), "Campinas");		
	}
	
	@Test(expected = IllegalStateException.class)
	public void createClienteCpfInvalido() {		
		Mockito.when(clienteRepository.countByCpf(Mockito.anyString())).thenReturn(1L);
		Mockito.doReturn(ClienteBuilder.create().exemploInvalido().build()).when(clienteRepository).save(Mockito.any(Cliente.class));
		
		clienteService.create(ClienteBuilder.create().exemploInvalido().build());	
	}
	
	@Test
	public void updateClienteValido() {		
		Mockito.when(clienteRepository.countByCpf(Mockito.anyString())).thenReturn(0L);
		Mockito.when(clienteRepository.findOne(Mockito.anyString())).thenReturn(ClienteBuilder.create().exemploValido().build());
		Mockito.when(enderecoRepository.findOne(Mockito.anyLong())).thenReturn(EnderecoBuilder.create().exemploValido().build());
		Mockito.doReturn(EnderecoBuilder.create().exemploValido().build()).when(enderecoRepository).save(Mockito.any(Endereco.class));
		Mockito.doReturn(TelefoneBuilder.create().fixo().build()).when(telefoneRepository).save(Mockito.any(Telefone.class));
		Mockito.doReturn(ClienteBuilder.create().exemploValido().build()).when(clienteRepository).save(Mockito.any(Cliente.class));
		
		Cliente novoCliente = clienteService.update(ClienteBuilder.create().exemploValido().build(), "1");
		
		Assert.assertEquals(novoCliente.getNome(), "Gustavo");
		Assert.assertEquals(novoCliente.getEndereco().getCidade(), "Campinas");		
	}
	
	@Test(expected = IllegalStateException.class)
	public void updateClienteCpfInvalido() {		
		Mockito.when(clienteRepository.countByCpf(Mockito.anyString())).thenReturn(0L);
		Mockito.when(clienteRepository.findOne(Mockito.anyString())).thenReturn(ClienteBuilder.create().exemploValido().build());
		Mockito.doReturn(EnderecoBuilder.create().exemploValido().build()).when(enderecoRepository).save(Mockito.any(Endereco.class));
		Mockito.doReturn(TelefoneBuilder.create().fixo().build()).when(telefoneRepository).save(Mockito.any(Telefone.class));
		Mockito.doReturn(ClienteBuilder.create().exemploValido().build()).when(clienteRepository).save(Mockito.any(Cliente.class));
		
		clienteService.update(ClienteBuilder.create().exemploValido2().build(), "1");	
	}
	
	

}

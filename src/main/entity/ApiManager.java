package main.entity;

import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_port;

public class ApiManager extends Sim_entity {
	private Sim_port estabelecimentoIn;
	private Sim_port estabelecimentoOut;
	
	private Sim_port pedidoIn;
	private Sim_port pedidoOut;
	
	private Sim_port promocaoIn;
	private Sim_port promocaoOut;
	
	private Sim_port produtoIn;
	private Sim_port produtoOut;
	
	private Sim_port usuarioIn;
	private Sim_port usuarioOut;
	
	private Sim_port request;
	private Sim_port response;
	
	public ApiManager(String name) {
		super(name);
		
		this.estabelecimentoIn = new Sim_port("EstabelecimentoIn");
		this.estabelecimentoOut = new Sim_port("EstabelecimentoOut");
		
		this.pedidoIn = new Sim_port("PedidoIn");
		this.pedidoOut = new Sim_port("PedidoOut");
		
		this.promocaoIn = new Sim_port("PromocaoIn");
		this.promocaoOut = new Sim_port("PromocaoOut");
		
		this.produtoIn = new Sim_port("ProdutoIn");
		this.produtoOut = new Sim_port("ProdutoOut");
		
		this.usuarioIn = new Sim_port("UsuarioIn");
		this.usuarioOut = new Sim_port("UsuarioOut");
		
		this.request = new Sim_port("Request");
		this.response = new Sim_port("Response");
		
		add_port(this.estabelecimentoIn);
		add_port(this.estabelecimentoOut);
		add_port(this.pedidoIn);
		add_port(this.pedidoOut);
		add_port(this.promocaoIn);
		add_port(this.promocaoOut);
		add_port(this.produtoIn);
		add_port(this.produtoOut);
		add_port(this.usuarioIn);
		add_port(this.usuarioOut);
		add_port(this.request);
		add_port(this.response);
		
	}

}

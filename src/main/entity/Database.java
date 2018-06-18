package main.entity;

import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_event;
import eduni.simjava.Sim_port;
import eduni.simjava.Sim_system;
import eduni.simjava.distributions.Sim_normal_obj;

public class Database extends Sim_entity {
	
	private Sim_port estabelecimentoIn;
	private Sim_port pedidoIn;
	private Sim_port promocaoIn;
	private Sim_port produtoIn;
	private Sim_port usuarioIn;
	
	private Sim_port estabelecimentoOut;
	private Sim_port pedidoOut;
	private Sim_port promocaoOut;
	private Sim_port produtoOut;
	private Sim_port usuarioOut;
	
	private Sim_normal_obj delay;
	
	public Database(String name) {
		super(name);
		
		this.estabelecimentoIn = new Sim_port("EstabelecimentoIn");
		this.pedidoIn = new Sim_port("PedidoIn");
		this.promocaoIn = new Sim_port("PromocaoIn");
		this.produtoIn = new Sim_port("ProdutoIn");
		this.usuarioIn = new Sim_port("UsuarioIn");
		
		this.estabelecimentoOut = new Sim_port("EstabelecimentoOut");
		this.pedidoOut = new Sim_port("PedidoOut");
		this.promocaoOut = new Sim_port("PromocaoOut");
		this.produtoOut = new Sim_port("ProdutoOut");
		this.usuarioOut = new Sim_port("UsuarioOut");
		
		add_port(this.estabelecimentoIn);
		add_port(this.pedidoIn);
		add_port(this.promocaoIn);
		add_port(this.produtoIn);
		add_port(this.usuarioIn);

		add_port(this.estabelecimentoOut);
		add_port(this.pedidoOut);
		add_port(this.promocaoOut);
		add_port(this.produtoOut);
		add_port(this.usuarioOut);
		
		delay = new Sim_normal_obj("Delay", 10, 100);
        add_generator(delay);
	}
	
	@Override
    public void body() {
        while (Sim_system.running()) {
          Sim_event e = new Sim_event();
          sim_get_next(e);
          sim_process(delay.sample());
          sim_completed(e);
        }
    }
}

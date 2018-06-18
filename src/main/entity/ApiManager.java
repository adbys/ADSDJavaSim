package main.entity;

import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_event;
import eduni.simjava.Sim_port;
import eduni.simjava.Sim_system;
import eduni.simjava.distributions.Sim_normal_obj;
import eduni.simjava.distributions.Sim_random_obj;

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
	
	private Sim_normal_obj delay;
	private Sim_random_obj prob;
	
	private Sim_port apiManagerIn;
	private Sim_port apiManagerOut;
	
	public ApiManager(String name) {
		super(name);
		
		this.apiManagerOut = new Sim_port("ApiManagerOut");
		this.apiManagerIn = new Sim_port("ApiManagerIn");
		
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
		add_port(this.apiManagerIn);
		add_port(this.apiManagerOut);
		
		delay = new Sim_normal_obj("Delay", 10, 100);
        prob = new Sim_random_obj("Probability");
        add_generator(delay);
        add_generator(prob);
		
	}
	
	@Override
    public void body() {
		while (Sim_system.running()) {
            Sim_event e = new Sim_event();
            sim_get_next(e);
            sim_process(delay.sample());
            sim_completed(e);
            if (e.from_port(this.request)) {
	            	double p = prob.sample();
	            	if (p <= 0.35) {
	            		sim_schedule(this.estabelecimentoOut, 0.0, 1);
	            	} else if (p > 0.35 || p <= 0.55) {
	            		sim_schedule(this.pedidoOut, 0.0, 1);
	            	} else if (p > 0.55 || p <= 0.60) {
	            		sim_schedule(this.promocaoOut, 0.0, 1);            	
	            	} else if (p > 0.60 || p <= 0.90) {
	            		sim_schedule(this.produtoOut, 0.0, 1);            	            	
	            	} else {
	            		sim_schedule(this.usuarioOut, 0.0, 1);            	            	            	
	            	}
            } else {
            		//TODO: acaba
            }
		}
    }

}

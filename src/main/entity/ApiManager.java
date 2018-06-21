package main.entity;

import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_event;
import eduni.simjava.Sim_port;
import eduni.simjava.Sim_stat;
import eduni.simjava.Sim_system;
import eduni.simjava.distributions.Sim_normal_obj;
import eduni.simjava.distributions.Sim_random_obj;

public class ApiManager extends Sim_entity {
	
	private Sim_port request;
	
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
	
	private Sim_normal_obj delay;
	private Sim_random_obj prob;
	
	
	private Sim_stat stat;
	
	public ApiManager(String name) {
		super(name);
		
		this.stat = new Sim_stat();
		this.stat.add_measure(Sim_stat.ARRIVAL_RATE);
		this.stat.add_measure(Sim_stat.WAITING_TIME);
		this.stat.add_measure(Sim_stat.THROUGHPUT);
		this.stat.add_measure(Sim_stat.SERVICE_TIME);
		this.stat.add_measure(Sim_stat.QUEUE_LENGTH);
		
		set_stat(this.stat);
		

		
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
		
		add_port(this.request);
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
		
		delay = new Sim_normal_obj("Delay", 5, 1);
        prob = new Sim_random_obj("Probability");
        add_generator(delay);
        add_generator(prob);
		
	}
	
	@Override
    public void body() {
		while(Sim_system.running()) {
	
			Sim_event e = new Sim_event();
	        sim_trace(11, "Request received");
	        sim_get_next(e);
	        sim_process(delay.sample());
	        sim_completed(e);
			
			if(e.from_port(this.request)) {
				
				double p = prob.sample();
				// Send the processor a job
				if (p <= 0.35) {
					sim_trace(1, "Estabelecimento Manager selected");
					sim_schedule(this.estabelecimentoOut, 0.0, 1);
				} else if (p > 0.35 && p <= 0.55) {
					sim_trace(2, "Peido Manager selected");
					sim_schedule(this.pedidoOut, 0.0, 1);
				} else if (p > 0.55 && p <= 0.60) {
					sim_trace(3, "Promoção Manager selected");
					sim_schedule(this.promocaoOut, 0.0, 1);            	
				} else if (p > 0.60 && p <= 0.90) {
					sim_trace(4, "Produto Manager selected");
					sim_schedule(this.produtoOut, 0.0, 1);            	            	
				} else {
					sim_trace(5, "Usuário Manager selected");
					sim_schedule(this.usuarioOut, 0.0, 1);            	            	            	
				}
				
			}
		
    }
	}

}

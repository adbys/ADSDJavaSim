package main.entity;

import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_event;
import eduni.simjava.Sim_port;
import eduni.simjava.Sim_stat;
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
	
	private Sim_port bdIn;
	private Sim_port bdOut;

	private Sim_normal_obj delay;
	private Sim_stat stat;
	
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
		
		this.bdOut = new Sim_port("BdOut");
		this.bdIn = new Sim_port("BdIn");
		
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
		
		add_port(this.bdOut);
		add_port(this.bdIn);
		
		delay = new Sim_normal_obj("Delay", 5, 1);
        add_generator(delay);
        
        this.stat = new Sim_stat();
		this.stat.add_measure(Sim_stat.ARRIVAL_RATE);
		this.stat.add_measure(Sim_stat.WAITING_TIME);
		this.stat.add_measure(Sim_stat.THROUGHPUT);
		this.stat.add_measure(Sim_stat.SERVICE_TIME);
		this.stat.add_measure(Sim_stat.QUEUE_LENGTH);
		
		set_stat(this.stat);
	}
	
	@Override
    public void body() {
        while (Sim_system.running()) {
          Sim_event e = new Sim_event();
          sim_trace(6, "DataBase processing");
          sim_get_next(e);
          sim_process(delay.sample());
          sim_completed(e);
      
          if (e.from_port(this.estabelecimentoIn)) {
       	  	sim_trace(6, "DataBase responding to Estabelecimento Manger");
        	  	sim_schedule(this.estabelecimentoOut, 0.0, 1);
          } else if (e.from_port(this.pedidoIn)) {
	        	sim_trace(7, "DataBase responding to Pedido Manger");
      	  	sim_schedule(this.pedidoOut, 0.0, 1);
          } else if (e.from_port(this.promocaoIn)) {
        	  	sim_trace(8, "DataBase responding to Promoção Manger");
        	  	sim_schedule(this.promocaoOut, 0.0, 1);
          } else if (e.from_port(this.produtoIn)) {
        	  	sim_trace(9, "DataBase responding to Produto Manger");
        	  	sim_schedule(this.produtoOut, 0.0, 1);
          } else if (e.from_port(this.usuarioIn)) {
        	  	sim_trace(10, "DataBase responding to Usuario Manger");
        	  	sim_schedule(this.usuarioOut, 0.0, 1);
          }
        }
    }
}

package main.entity;

import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_event;
import eduni.simjava.Sim_port;
import eduni.simjava.Sim_stat;
import eduni.simjava.Sim_system;
import eduni.simjava.distributions.Sim_normal_obj;

public class RequestHandler extends Sim_entity {
	
	private Sim_port bdOut;
	private Sim_port bdIn;
	private Sim_port reentradaOut;
	private Sim_port apiManagerIn;
	private Sim_port apiManagerOut;
	private Sim_normal_obj delay;
	private Sim_stat stat;

	

	public RequestHandler(String name) {
		super(name);
		
		this.bdOut = new Sim_port("BdOut");
		this.bdIn = new Sim_port("BdIn");
		this.apiManagerOut = new Sim_port("ApiManagerOut");
		this.apiManagerIn = new Sim_port("ApiManagerIn");
		
		
		add_port(this.bdOut);
		add_port(this.bdIn);
		add_port(this.apiManagerIn);
		add_port(this.apiManagerOut);

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
       // 	System.out.println("handler");
          Sim_event e = new Sim_event();
          sim_get_next(e);
          sim_process(delay.sample());
          sim_completed(e);
          if(e.from_port(this.apiManagerIn)) {
        	  	sim_schedule(this.bdOut, 0.0, 1);
//        	  	sim_schedule(this.apiManagerOut, 0.0, 1);
          } //else {
//          }
        }
    }

}

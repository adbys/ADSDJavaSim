package main.entity;

import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_event;
import eduni.simjava.Sim_port;
import eduni.simjava.Sim_system;
import eduni.simjava.distributions.Sim_normal_obj;

public class RequestHandler extends Sim_entity {
	
	private Sim_port bdOut;
	private Sim_port reentradaOut;
	private Sim_port apiManagerIn;
	private Sim_port bdIn;
	private Sim_normal_obj delay;

	

	public RequestHandler(String name) {
		super(name);
		
		this.bdOut = new Sim_port("BdOut");
		this.reentradaOut = new Sim_port("ReentradaOut");
		this.apiManagerIn = new Sim_port("ApiManagerIn");
		this.bdIn = new Sim_port("BdIn");
		
		add_port(this.bdOut);
		add_port(this.reentradaOut);
		add_port(this.bdIn);
		add_port(this.apiManagerIn);
		
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

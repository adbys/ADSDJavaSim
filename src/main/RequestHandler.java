package main;

import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_port;

public class RequestHandler extends Sim_entity {
	
	private Sim_port bdOut;
	private Sim_port reentradaOut;
	private Sim_port apiManagerIn;
	private Sim_port bdIn;
	

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
		
	}
	
	@Override
	public void body() {
		
	}

}

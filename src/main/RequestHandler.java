package main;

import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_port;

public class RequestHandler extends Sim_entity {
	
	private Sim_port bdOut;
	private Sim_port reentranceOut;
	

	public RequestHandler(String name) {
		super(name);
		this.bdOut = new Sim_port("BdOut");
		this.reentranceOut = new Sim_port("ReentranceOut");
		
		add_port(this.bdOut);
		add_port(this.reentranceOut);
		
	}

}

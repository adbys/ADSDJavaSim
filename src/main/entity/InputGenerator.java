package main.entity;

import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_port;

public class InputGenerator extends Sim_entity {
	
	private Sim_port SystemIn;

	public InputGenerator(String name) {
		super(name);
		this.SystemIn = new Sim_port("SystemIn");
		
		add_port(this.SystemIn);
	}

	@Override
	public void body() {
		for (int i = 0; i < 100; i++) {
			sim_schedule(this.SystemIn, 0.0, 1); 
		}
	}
	
	
	
}

package main.entity;

import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_port;

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
	
	public Database(String arg0) {
		super(arg0);
	}

}

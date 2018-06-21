package main.entity;

import eduni.simjava.Sim_system;

public class ProcessorSubsystem {
	public static void main(String[] args) {
		
        Sim_system.initialise();
        
        ApiManager apiManager = new ApiManager("ApiManager");
        RequestHandler estabelecimentoManager = new RequestHandler("EstabelecimentoManager");
        RequestHandler pedido = new RequestHandler("PedidoManager");
        RequestHandler promocao = new RequestHandler("PromocaoManager");
        RequestHandler produto = new RequestHandler("ProdutoManager");
        RequestHandler usuario = new RequestHandler("UsuarioManager");
        Database bd = new Database("Bd");
        
        
        Sim_system.link_ports("ApiManager", "EstabelecimentoOut", "EstabelecimentoManager", "ApiManagerIn");
        Sim_system.link_ports("ApiManager", "PedidoOut", "PedidoManager", "ApiManagerIn");
        Sim_system.link_ports("ApiManager", "PromocaoOut", "PromocaoManager", "ApiManagerIn");
        Sim_system.link_ports("ApiManager", "ProdutoOut", "ProdutoManager", "ApiManagerIn");
        Sim_system.link_ports("ApiManager", "UsuarioOut", "UsuarioManager", "ApiManagerIn");
        
        Sim_system.link_ports("EstabelecimentoManager", "BdOut", "Bd", "EstabelecimentoIn");
        Sim_system.link_ports("EstabelecimentoManager", "BdIn", "Bd", "EstabelecimentoOut");
        
        Sim_system.link_ports("PedidoManager", "BdOut", "Bd", "PedidoIn");
        Sim_system.link_ports("PedidoManager", "BdIn", "Bd", "PedidoOut");


        Sim_system.link_ports("PromocaoManager", "BdOut", "Bd", "PromocaoIn");
        Sim_system.link_ports("PromocaoManager", "BdIn", "Bd", "PromocaoOut");

        Sim_system.link_ports("ProdutoManager", "BdOut", "Bd", "ProdutoIn");
        Sim_system.link_ports("ProdutoManager", "BdIn", "Bd", "ProdutoOut");

        Sim_system.link_ports("UsuarioManager", "BdOut", "Bd", "UsuarioIn");
        Sim_system.link_ports("UsuarioManager", "BdIn", "Bd", "UsuarioOut");

        
        Sim_system.set_trace_detail(false, true, false);
        Sim_system.run();
        
    
      }
}

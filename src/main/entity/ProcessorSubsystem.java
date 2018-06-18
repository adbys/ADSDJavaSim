package main.entity;

import eduni.simjava.Sim_system;

public class ProcessorSubsystem {
	public static void main(String[] args) {
		
        Sim_system.initialise();
        
        RequestHandler source = new RequestHandler("ApiManager");
        ApiManager estabelecimento = new ApiManager("Estabelecimento");
        ApiManager pedido = new ApiManager("Pedido");
        ApiManager promocao = new ApiManager("Promocao");
        ApiManager produto = new ApiManager("Produto");
        ApiManager usuario = new ApiManager("Usuario");
        Database bd = new Database("Bd");
        
        
        Sim_system.link_ports("ApiManager", "ApiManagerOut", "Estabelecimento", "EstabelecimentoIn");
        Sim_system.link_ports("ApiManager", "ApiManagerOut", "Pedido", "PedidoIn");
        Sim_system.link_ports("ApiManager", "ApiManagerOut", "Promocao", "PromocaoIn");
        Sim_system.link_ports("ApiManager", "ApiManagerOut", "Produto", "ProdutoIn");
        Sim_system.link_ports("ApiManager", "ApiManagerOut", "Usuario", "UsuarioIn");
        
        
        Sim_system.link_ports("Pedido", "PedidoOut", "Bd", "BdIn");
        Sim_system.link_ports("Pedido", "PedidoOut", "ApiManager", "ApiManagerIn");

        Sim_system.link_ports("Estabelecimento", "EstabelecimentoOut", "Bd", "BdIn");
        Sim_system.link_ports("Estabelecimento", "EstabelecimentoOut", "ApiManager", "ApiManagerIn");

        Sim_system.link_ports("Promocao", "PromocaoOut", "Bd", "BdIn");
        Sim_system.link_ports("Promocao", "PromocaoOut", "ApiManager", "ApiManagerIn");

        Sim_system.link_ports("Produto", "ProdutoOut", "Bd", "BdIn");
        Sim_system.link_ports("Produto", "ProdutoOut", "ApiManager", "ApiManagerIn");

        Sim_system.link_ports("Usuario", "UsuarioOut", "Bd", "BdIn");
        Sim_system.link_ports("Usuario", "UsuarioOut", "ApiManager", "ApiManagerIn");


        Sim_system.link_ports("Bd", "BdOut", "Pedido", "PedidoIn");
        Sim_system.link_ports("Bd", "BdOut", "Estabelecimento", "EstabelecimentoIn");
        Sim_system.link_ports("Bd", "BdOut", "Promocao", "PromocaoIn");
        Sim_system.link_ports("Bd", "BdOut", "Produto", "ProdutoIn");
        Sim_system.link_ports("Bd", "BdOut", "Usuario", "UsuarioIn");
        
        Sim_system.run();
        
    
      }
}

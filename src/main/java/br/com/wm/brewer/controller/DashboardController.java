package br.com.wm.brewer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.wm.brewer.repository.Cervejas;
import br.com.wm.brewer.repository.Clientes;
import br.com.wm.brewer.repository.Vendas;

@Controller
public class DashboardController {
	
	@Autowired
	private Vendas vendas;
	
	@Autowired
	private Cervejas cervejas;
	
	@Autowired
	private Clientes clientes;

	@GetMapping("/")
	public ModelAndView dashboard() {
		ModelAndView mv = new ModelAndView("Dashboard");
		
		mv.addObject("vendasNoAno", vendas.valorTotalNoAno());
		mv.addObject("vendasNoMes", vendas.valorTotalNoMes());
		mv.addObject("ticketMedio", vendas.valorTicketMedioNoAno());
		
		mv.addObject("valorItensEstoque", cervejas.valorItensEstoque());
		mv.addObject("totalClientes", clientes.count());
		
		return mv;
	}
	
}

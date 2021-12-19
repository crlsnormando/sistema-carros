package br.com.carro.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.carro.dao.CarroDAO;
import br.com.carro.exception.ErroSistema;
import br.com.carro.model.Carro;

@SessionScoped
@ManagedBean
public class CarroBean implements Serializable {

	private static final long serialVersionUID = 1L;

	Carro carro;

	
	private List<Carro> carros = new ArrayList<Carro>();
	private CarroDAO carroDao = new CarroDAO();
	
	public void adicionar() {
		
		try {
			carroDao.salvar(carro);
			
			adicionarMesnagem("Salvo!", "Carro Salvo com Sucesso", FacesMessage.SEVERITY_INFO);
		} catch (ErroSistema e) {
			// TODO Auto-generated catch block
			adicionarMesnagem(e.getMessage().toString(), e.getCause().toString(), FacesMessage.SEVERITY_ERROR);
		}
		carro = new Carro();
		listar();

	}
	
	@PostConstruct
	public void listar()  {
		
		try {
			carros = carroDao.buscar();
			if (carros == null || carros.size() == 0) {
				
				adicionarMesnagem("Salvo!", "Sua Busca Não retornou nenhum carro", FacesMessage.SEVERITY_WARN);
				
			}
		} catch (ErroSistema e) {
			// TODO Auto-generated catch block
			adicionarMesnagem(e.getMessage().toString(), e.getCause().toString(), FacesMessage.SEVERITY_ERROR);
		}
		carro = new Carro();
		
		
	}
	
	
	public void editar(Carro c) {
		
		carro = c;
			
	}
	
	
	public void excluir(Carro carro)  {
		
		try {
			carroDao.excluir(carro);
			adicionarMesnagem(null, "Carro Excluido", FacesMessage.SEVERITY_WARN);
		} catch (ErroSistema e) {
			// TODO Auto-generated catch block
			adicionarMesnagem(e.getMessage().toString(), e.getCause().toString(), FacesMessage.SEVERITY_ERROR);
		}
		listar();
		
	}
	
	
	public void adicionarMesnagem(String sumario, String detalhe, FacesMessage.Severity tipoErro) {
		
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(tipoErro, sumario, detalhe);
		context.addMessage(null, message);
		
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	public List<Carro> getCarros() {
		return carros;
	}

	public void setCarros(List<Carro> carros) {
		this.carros = carros;
	}
	
	

}

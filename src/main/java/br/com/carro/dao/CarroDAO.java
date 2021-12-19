package br.com.carro.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.carro.exception.ErroSistema;
import br.com.carro.model.Carro;
import br.com.carro.util.FabricaConexao;

public class CarroDAO {

	public void salvar(Carro carro) throws ErroSistema {

		Connection conexao = FabricaConexao.getConexao();
		PreparedStatement ps;
		try {
			
			if(carro.getId() == null) {
				
				
			
			
			ps = conexao
					.prepareStatement("INSERT INTO public.carro(modelo, fabricante, cor, ano) VALUES ( ?, ?, ?, ?)");
			}else {
				
				ps = conexao.prepareStatement("update public.carro set modelos=?, fabricante=?, cor=?, ano=? where id=?");
				ps.setInt(5, carro.getId());
				
			}
			
			ps.setString(1, carro.getModelo());
			ps.setString(2, carro.getFabricante());
			ps.setString(3, carro.getCor());
			ps.setDate(4, new Date(carro.getAno().getTime()));
			
			
			ps.execute();
			FabricaConexao.fecharConexao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ErroSistema("Erro ao tentar Salvar", e);
		}

	}

	public List<Carro> buscar() throws ErroSistema {

		Connection conexao = FabricaConexao.getConexao();
		PreparedStatement ps;
		try {
			ps = conexao.prepareStatement("select * from public.carro");
			ResultSet resultSet = ps.executeQuery();
			List<Carro> carros = new ArrayList<Carro>();

			while (resultSet.next()) {
				Carro carro = new Carro();
				carro.setId(resultSet.getInt("id"));
				carro.setModelo(resultSet.getString("modelo"));
				carro.setFabricante(resultSet.getString("fabricante"));
				carro.setCor(resultSet.getString("cor"));
				carro.setAno(resultSet.getDate("ano"));
				carros.add(carro);

			}
			return carros;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ErroSistema("Erro ao Buscar Carro", e);
			
		}

	}
	
	
	
	public void excluir(Carro carro) throws ErroSistema {
		
		Connection conexao = FabricaConexao.getConexao();
		PreparedStatement ps;
		
		try {
			ps = conexao.prepareStatement("DELETE FROM public.carro WHERE id=?");
			ps.setInt(1, carro.getId());
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ErroSistema("Erro ao Deletar Carro", e);
		}
		
		FabricaConexao.fecharConexao();
		
		
	}

}

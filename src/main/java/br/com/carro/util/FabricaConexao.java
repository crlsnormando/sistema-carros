package br.com.carro.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import br.com.carro.exception.ErroSistema;	

public class FabricaConexao {

	
	private static Connection conexao;
	private static final String URL_CONEXAO = "jdbc:postgresql://localhost:5432/sistema-carros";
	private static final String USUARIO = "postgres";
	private static final String SENHA = "local@123";
	
	public static Connection getConexao() throws ErroSistema{
		if (conexao == null) {
			
			try {
				Class.forName("org.postgresql.Driver");
				conexao = DriverManager.getConnection(URL_CONEXAO,USUARIO,SENHA);
			} catch (ClassNotFoundException e1) {
				
				throw new ErroSistema("O driver do Banco de dados não foi encontrado", e1);
				
			} catch (SQLException e) {
				throw new ErroSistema("Não Foi possivel conectar ao banco de dados", e);
			}
			
			
		}
		
		return conexao;
	}
	
	public static void fecharConexao() throws ErroSistema {
		
		if(conexao != null) {
			try {
				conexao.close();
				conexao = null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new ErroSistema("Erro ao Fechar conexão ao banco de Dados", e);
			}
			
			
			
		}
		
	}
}

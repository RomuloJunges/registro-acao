package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import beans.AcaoBean;
import connection.SingleConnection;

public class AcaoDAO {

	private Connection connection;

	public AcaoDAO() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(AcaoBean acao) {
		try {
			String sql = "INSERT INTO tb_acoes(codigo_acao, valor_acao, quantidade_acao, data_compra) VALUES (?,?,?,?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, acao.getCodigo());
			insert.setDouble(2, acao.getValor());
			insert.setDouble(3, acao.getQuantidade());
			insert.setString(4, acao.getData());
			insert.execute();
			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}

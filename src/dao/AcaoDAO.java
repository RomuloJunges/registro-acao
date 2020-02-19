package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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
			insert.setDate(4, new java.sql.Date(acao.getData().getTime()));
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

	public java.util.List<AcaoBean> listar() throws Exception {
		java.util.List<AcaoBean> listar = new ArrayList<AcaoBean>();

		String sql = "SELECT * FROM tb_acoes ORDER BY data_compra DESC";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			AcaoBean ac = new AcaoBean();
			ac.setId(resultSet.getLong("id_acoes"));
			ac.setCodigo(resultSet.getString("codigo_acao"));
			ac.setValor(resultSet.getDouble("valor_acao"));
			ac.setQuantidade(resultSet.getInt("quantidade_acao"));
			ac.setData(resultSet.getDate("data_compra"));

			listar.add(ac);
		}

		return listar;
	}

	public void delete(int idAcao) {
		try {
			String sql = "DELETE FROM tb_acoes WHERE id_acoes = '" + idAcao + "'";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.execute();
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}

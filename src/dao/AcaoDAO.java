package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

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

	public java.util.List<AcaoBean> listar() throws Exception {
		java.util.List<AcaoBean> listar = new ArrayList<AcaoBean>();

		String sql = "SELECT * FROM tb_acoes";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			AcaoBean ac = new AcaoBean();
			ac.setCodigo(resultSet.getString("codigo_acao"));
			ac.setValor(resultSet.getDouble("valor_acao"));
			ac.setQuantidade(resultSet.getDouble("quantidade_acao"));
			ac.setData(resultSet.getString("data_compra"));

			listar.add(ac);
		}

		return listar;
	}
}

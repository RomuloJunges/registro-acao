package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {

	private static String banco = "jdbc:mysql://localhost:3306/db_acoes?useLegacyDatetimeCode=false&serverTimezone=America/Sao_Paulo&useSSL=false";
	private static String user = "root";
	private static String password = "";
	private static Connection connection = null;

	static {
		conectar();
	}

	public SingleConnection() {
		conectar();
	}
	
	private static void conectar() {
		try {
			if(connection == null) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection(banco, user, password);
				connection.setAutoCommit(false);
			}
		}catch (Exception e){
			throw new RuntimeException("Erro ao conectar com o banco de dados");
		}
	}
	
	public static Connection getConnection() {
		return connection;
	}
}

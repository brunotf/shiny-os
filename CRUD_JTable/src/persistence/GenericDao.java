package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GenericDao implements IGenericDao {

	private Connection c;
	
	@Override
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		c = DriverManager.getConnection("jdbc:mysql://localhost:3306/crudMateriais", 
				"root", "");
		System.out.println("Conexão OK");
		return c;
	}

}


//
//c = DriverManager.getConnection("jdbc:jtds:sqlserver://localhost:1433;DatabaseName=ControleEstoque;namedPipes=true"
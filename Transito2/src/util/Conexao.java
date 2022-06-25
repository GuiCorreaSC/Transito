package util;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
	
	public static Connection conectaSqlite() {
		Connection conn = null;
		try {
			File arquivo = new File("database/transito.sqlite");
			if(arquivo.exists()) {
				Class.forName("org.sqlite.JDBC");
				conn = DriverManager.getConnection("jdbc:sqlite:database/transito.sqlite");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

}

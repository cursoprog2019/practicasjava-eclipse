package modografico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CensoLib {
	
	// conexion
	public static Connection conexion(String password) {
		Connection con = null;
		// String driver = "oracle.jdbc.driver.OracleDriver" // ORACLE
		String driver = "com.mysql.cj.jdbc.Driver"; // MySQL
		// String url = "jdbc:oracle:thin:@192.168.1.76:1521/XEPDB1"; // ORACLE
		String url = "jdbc:mysql://remotemysql.com:3306/8QznhvYaIi"; // MySQL
		// String url = "jdbc:mysql://db4free.net:3306/cursoprog2019"; // MySQL
		// estructura de la url: protocolo://servidor:puerto/basedatos
		String usuario = "8QznhvYaIi";
		try {
			// 1. Registrar el Driver JDBC
			// (lo carga en tiempo real en la memoria)
			Class.forName(driver);
			// 2. Obtener una conexiÃ³n
			// Necesitamos url (protocolo,servidor,puerto,base de datos),
			// usuario y contraseÃ±a
			con = DriverManager.getConnection(url, usuario, password);
			// System.out.println("Conectado");

		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			//e.printStackTrace();
		}
		return con;
	}

	public static boolean enviar(Connection con, int edad) {
		boolean resultado = false;
		String sql;
		try {
			// 3. Crear objeto Statement ("VAGON")
			Statement stmt = con.createStatement();
			if (edad >= 18 ) {
				sql = "INSERT INTO mayores (edad) VALUES ('" + edad +  "')";
			} else {
				sql = "INSERT INTO menores (edad) VALUES ('" + edad +  "')";
			}			
			// 4. Ejecutar la consulta SQL ("Lanzar el vagón por el tunel")
			stmt.executeUpdate(sql);
			System.out.println("Contacto insertado");
			resultado = true;
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			resultado = false;
		}
		return resultado;

	}

	public static double calculaTotal(Connection con, String tabla) {
		double resultado = 0;
		
		String sql;
		try {
			// El "TUNEL" ya lo tenemos, que es la variable "con"
			// 1. Crear objeto Statement ("VAGON")
			Statement stmt = con.createStatement();
			// 2. Ejecutar la consulta SQL ("Lanzar el vagón por el tunel")
			sql = "SELECT COUNT(edad) as contador FROM " + tabla ;
			ResultSet rs = stmt.executeQuery(sql);
			// 3. Recuperar los resultados ("Vagones de vuelta")
			rs.next(); // devuelve una fila con el contador
			resultado = rs.getDouble("contador");
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		
		return resultado;
	}

	
	public static double calculaMedia(Connection con, String tabla) {
		double resultado = 0;
		
		String sql;
		try {
			// El "TUNEL" ya lo tenemos, que es la variable "con"
			// 1. Crear objeto Statement ("VAGON")
			Statement stmt = con.createStatement();
			// 2. Ejecutar la consulta SQL ("Lanzar el vagón por el tunel")
			sql = "SELECT AVG(edad) as media FROM " + tabla ;
			ResultSet rs = stmt.executeQuery(sql);
			// 3. Recuperar los resultados ("Vagones de vuelta")
			rs.next(); // devuelve una fila con el contador
			resultado = rs.getDouble("media");
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		
		return resultado;
	}

	
	
}

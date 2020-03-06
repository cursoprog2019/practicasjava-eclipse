package modografico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * bdutil: Librer铆a de m茅todos para gestionar la base de datos
 */
public class bdutil {

	// conexion
	public static Connection conexion() {
		Connection con = null;
		// String driver = "oracle.jdbc.driver.OracleDriver" // ORACLE
		String driver = "com.mysql.cj.jdbc.Driver"; // MySQL
		// String url = "jdbc:oracle:thin:@192.168.1.76:1521/XEPDB1"; // ORACLE
		String url = "jdbc:mysql://remotemysql.com:3306/8QznhvYaIi"; // MySQL
		// String url = "jdbc:mysql://db4free.net:3306/cursoprog2019"; // MySQL
		// estructura de la url: protocolo://servidor:puerto/basedatos
		String usuario = "8QznhvYaIi";
		String password = "qUDBlTBjO4";
		try {
			// 1. Registrar el Driver JDBC
			// (lo carga en tiempo real en la memoria)
			Class.forName(driver);
			// 2. Obtener una conexi贸n
			// Necesitamos url (protocolo,servidor,puerto,base de datos),
			// usuario y contrase帽a
			con = DriverManager.getConnection(url, usuario, password);
			// System.out.println("Conectado");

		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			e.printStackTrace();
		}
		return con;
	}

	// cerrar conexion
	public static void cerrarConexion(Connection con) {
		try {
			if (con != null) {
				con.close();
			}
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}

	// menu
	public static void menu() {
		System.out.println("**************");
		System.out.println("*   AGENDA   *");
		System.out.println("**************");
		System.out.println("1.- ALTA");
		System.out.println("2.- BAJA");
		System.out.println("3.- MODIFICACION");
		System.out.println("4.- CONSULTA");
		System.out.println("5.- BUSQUEDA");
		System.out.println("6.- CONSULTA ORDENADA");
		System.out.println("9.- SALIR");
		System.out.println("------------");
	}

	// alta
	public static String alta(Connection con, Contacto contacto) {
		String resultado = "";
		int codigo = contacto.getCodigo();
		String nombre = contacto.getNombre();
		String telefono = contacto.getTelefono();
		String sql;
		try {
			// 3. Crear objeto Statement ("VAGON")
			Statement stmt = con.createStatement();
			// 4. Ejecutar la consulta SQL ("Lanzar el vag贸n por el tunel")
			sql = "INSERT INTO agenda (codigo,nombre,telefono) VALUES ('" + codigo + "','" + nombre + "','" + telefono
					+ "')";
			stmt.executeUpdate(sql);
			System.out.println("Contacto insertado");

		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			resultado = "ERROR: " + e.getMessage();
		}
		return resultado;

	}

	// baja
	public static boolean baja(Connection con, int codigo) {
		// Devuelve true si borra el contacto y false en otro caso
		boolean resultado = false;
		String sql;
		try {
			// 3. Crear objeto Statement ("VAGON")
			Statement stmt = con.createStatement();
			// 4. Ejecutar la consulta SQL ("Lanzar el vag贸n por el tunel")
			sql = "DELETE FROM agenda WHERE codigo = '" + codigo + "'";
			if (stmt.executeUpdate(sql) > 0) {
				resultado = true;
				System.out.println("Contacto borrado");
			} else {
				System.out.println("Contacto no encontrado");
			}
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return resultado;

	}

	// modificacion
	public static boolean modificacion(Connection con, Contacto contacto) {
		boolean resultado = true;
		int codigo = contacto.getCodigo();
		String nombre = contacto.getNombre();
		String telefono = contacto.getTelefono();
		String sql;
		try {
			// 3. Crear objeto Statement ("VAGON")
			Statement stmt = con.createStatement();
			// 4. Ejecutar la consulta SQL ("Lanzar el vag贸n por el tunel")
			if (!nombre.isEmpty()) {
				sql = "UPDATE agenda SET nombre = '" + nombre + "' WHERE codigo = '" + codigo + "'";
				if (stmt.executeUpdate(sql) > 0) {
					System.out.println("Nombre modificado");
				}
			}
			if (!telefono.isEmpty()) {
				sql = "UPDATE agenda SET telefono = '" + telefono + "' WHERE codigo = '" + codigo + "'";
				if (stmt.executeUpdate(sql) > 0) {
					System.out.println("Tel閒ono modificado");
				}
			}
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			resultado = false; // devuelve false si hay cualquier error
		}
		return resultado;

	}

	// consulta
	public static ArrayList<Contacto> consulta(Connection con) {
		// parecido a select.java pero guardando los resultados en un ArrayList
		System.out.println("** CONSULTA **");
		ArrayList<Contacto> resultado = new ArrayList<Contacto>();
		int codigo;
		String nombre;
		String telefono;
		Contacto contacto;
		try {
			// El "TUNEL" ya lo tenemos, que es la variable "con"
			// 1. Crear objeto Statement ("VAGON")
			Statement stmt = con.createStatement();
			// 2. Ejecutar la consulta SQL ("Lanzar el vag贸n por el tunel")
			ResultSet rs = stmt.executeQuery("SELECT * FROM agenda");
			// 3. Recuperar los resultados ("Vagones de vuelta")
			while (rs.next()) {
				// usando el nombre de la columna
				// System.out.println(rs.getString("codigo") + " - " + rs.getString("nombre") +
				// " - " + rs.getString("telefono"));
				// crear un objeto de tipo Contacto
				codigo = rs.getInt("codigo");
				nombre = rs.getString("nombre");
				telefono = rs.getString("telefono");
				contacto = new Contacto(codigo, nombre, telefono);
				// a帽adir el objeto al ArrayList resultado
				resultado.add(contacto);
			}
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return resultado;
	}

	// consulta ordenada
	public static ArrayList<Contacto> consultaOrdenada(Connection con) {
		// parecido a select.java pero guardando los resultados en un ArrayList
		System.out.println("** CONSULTA **");
		ArrayList<Contacto> resultado = new ArrayList<Contacto>();
		int codigo;
		String nombre;
		String telefono;
		Contacto contacto;
		try {
			// El "TUNEL" ya lo tenemos, que es la variable "con"
			// 1. Crear objeto Statement ("VAGON")
			Statement stmt = con.createStatement();
			// 2. Ejecutar la consulta SQL ("Lanzar el vag贸n por el tunel")
			ResultSet rs = stmt.executeQuery("SELECT * FROM agenda ORDER BY nombre");
			// 3. Recuperar los resultados ("Vagones de vuelta")
			while (rs.next()) {
				// usando el nombre de la columna
				// System.out.println(rs.getString("codigo") + " - " + rs.getString("nombre") +
				// " - " + rs.getString("telefono"));
				// crear un objeto de tipo Contacto
				codigo = rs.getInt("codigo");
				nombre = rs.getString("nombre");
				telefono = rs.getString("telefono");
				contacto = new Contacto(codigo, nombre, telefono);
				// a帽adir el objeto al ArrayList resultado
				resultado.add(contacto);
			}
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return resultado;
	}

	public static void escribirResultados(ArrayList<Contacto> listaContactos) {
		// recorrer el ArrayList y escribirlo por pantalla
		// System.out.println("escribirResultados");
		int codigo;
		String nombre;
		String telefono;
		Contacto contacto;
		for (int i = 0; i < listaContactos.size(); i++) {
			contacto = listaContactos.get(i);
			codigo = contacto.getCodigo();
			nombre = contacto.getNombre();
			telefono = contacto.getTelefono();
			System.out.println(codigo + ": " + nombre + " --> " + telefono);
		}
	}

	public static Contacto pedirContacto(Scanner entrada, boolean automatico, Connection con) {
		Contacto resultado = null;
		int codigo;
		String nombre;
		String telefono;
		// pedir todos los datos
		entrada.nextLine(); // leemos el intro que va despu茅s de opcion
		System.out.print("C贸digo: ");
		if (automatico) {
			codigo = bdutil.siguienteCodigo(con);
			System.out.println(codigo);
		} else {
			codigo = entrada.nextInt();
			entrada.nextLine(); // leemos el intro que va despu茅s de c贸digo
		}
		System.out.print("Nombre: ");
		nombre = entrada.nextLine();
		System.out.print("Tel茅fono: ");
		telefono = entrada.nextLine();
		System.out.println("---------------");
		// Crear el objeto de tipo Contacto
		resultado = new Contacto(codigo, nombre, telefono);
		return resultado;
	}

	public static int pedirCodigo(Scanner entrada) {
		int resultado = 0;
		// pedir el c贸digo
		entrada.nextLine(); // leemos el intro que va despu茅s de opcion
		System.out.println("* BAJA CONTACTO *");
		System.out.print("C贸digo: ");
		resultado = entrada.nextInt();
		return resultado;
	}

	public static boolean existeCodigo(Connection con, int codigo) {
		boolean resultado = false;
		String sql;
		try {
			// El "TUNEL" ya lo tenemos, que es la variable "con"
			// 1. Crear objeto Statement ("VAGON")
			Statement stmt = con.createStatement();
			// 2. Ejecutar la consulta SQL ("Lanzar el vag贸n por el tunel")
			sql = "SELECT * FROM agenda WHERE codigo = '" + codigo + "'";
			ResultSet rs = stmt.executeQuery(sql);
			// 3. Recuperar los resultados ("Vagones de vuelta")
			if (rs.next()) { // devuelve al menos una fila
				resultado = true;
			}
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return resultado;
	}

	public static String pedirFiltro(Scanner entrada) {
		String resultado;
		// pedir el c贸digo
		entrada.nextLine(); // leemos el intro que va despu茅s de opcion
		System.out.println("* BUSQUEDA *");
		System.out.print("Texto a buscar: ");
		resultado = entrada.nextLine();
		return resultado;
	}

	public static ArrayList<Contacto> consulta(Connection con, String filtro, boolean ordenada) {
		// parecido a select.java pero guardando los resultados en un ArrayList
		System.out.println("** CONSULTA **");
		ArrayList<Contacto> resultado = new ArrayList<Contacto>();
		int codigo;
		String nombre;
		String telefono;
		Contacto contacto;
		String sql;
		try {
			// El "TUNEL" ya lo tenemos, que es la variable "con"
			// 1. Crear objeto Statement ("VAGON")
			Statement stmt = con.createStatement();
			// 2. Ejecutar la consulta SQL ("Lanzar el vag贸n por el tunel")
			sql = "SELECT * FROM agenda WHERE nombre LIKE '%" + filtro + "%' OR telefono LIKE '%" + filtro + "%'";
			if (ordenada) {
				sql += " ORDER BY nombre";
			}
			ResultSet rs = stmt.executeQuery(sql);
			// 3. Recuperar los resultados ("Vagones de vuelta")
			while (rs.next()) {
				// usando el nombre de la columna
				// System.out.println(rs.getString("codigo") + " - " + rs.getString("nombre") +
				// " - " + rs.getString("telefono"));
				// crear un objeto de tipo Contacto
				codigo = rs.getInt("codigo");
				nombre = rs.getString("nombre");
				telefono = rs.getString("telefono");
				contacto = new Contacto(codigo, nombre, telefono);
				// a帽adir el objeto al ArrayList resultado
				resultado.add(contacto);
			}
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return resultado;
	}

	public static int siguienteCodigo(Connection con) {
		int resultado = 1;
		String sql;
		try {
			// El "TUNEL" ya lo tenemos, que es la variable "con"
			// 1. Crear objeto Statement ("VAGON")
			Statement stmt = con.createStatement();
			// 2. Ejecutar la consulta SQL ("Lanzar el vag髇 por el tunel")
			sql = "SELECT MAX(codigo) as maximo FROM agenda";
			ResultSet rs = stmt.executeQuery(sql);
			// 3. Recuperar los resultados ("Vagones de vuelta")
			rs.next(); // devuelve una fila con el m醲imo
			resultado = rs.getInt("maximo") + 1;
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return resultado;
	}

	public static Contacto consulta(Connection con, int codigo) {
		Contacto resultado = null;
		String nombre;
		String telefono;
		String sql;
		try {
			// El "TUNEL" ya lo tenemos, que es la variable "con"
			// 1. Crear objeto Statement ("VAGON")
			Statement stmt = con.createStatement();
			// 2. Ejecutar la consulta SQL ("Lanzar el vag贸n por el tunel")
			sql = "SELECT * FROM agenda WHERE codigo = '" + codigo + "'";
			ResultSet rs = stmt.executeQuery(sql);
			// 3. Recuperar los resultados ("Vagones de vuelta")
			if (rs.next()) {
				// usando el nombre de la columna
				// System.out.println(rs.getString("codigo") + " - " + rs.getString("nombre") +
				// " - " + rs.getString("telefono"));
				// crear un objeto de tipo Contacto
				nombre = rs.getString("nombre");
				telefono = rs.getString("telefono");
				resultado = new Contacto(codigo, nombre, telefono);
			}
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}

		return resultado;
	}

}